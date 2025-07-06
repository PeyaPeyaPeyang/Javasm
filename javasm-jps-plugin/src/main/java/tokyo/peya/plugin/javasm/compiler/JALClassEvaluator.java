package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.Collections;
import java.util.List;

public class JALClassEvaluator
{
    public static ClassNode evaluateClassAST(@NotNull EvaluatingContext ctxt, @NotNull JALParser.ClassDefinitionContext clazz)
    {
        ClassNode classNode = new ClassNode();
        visitClassInformation(classNode, clazz);
        visitClassBody(ctxt, classNode, clazz.classBody());

        return classNode;
    }

    private static void visitClassBody(@NotNull EvaluatingContext ctxt, @NotNull ClassNode classNode,
                                       @Nullable JALParser.ClassBodyContext body)
    {
        if (body == null)
            return;

        List<JALParser.ClassBodyItemContext> items = body.classBodyItem();
        for (JALParser.ClassBodyItemContext item : items)
        {
            if (item.methodDefinition() != null)
            {
                JALMethodEvaluator evaluator = new JALMethodEvaluator(ctxt, classNode);
                evaluator.evaluateMethod(item.methodDefinition());
            }
        }
    }

    private static void visitClassInformation(ClassNode classNode,
                                       JALParser.ClassDefinitionContext definitionContext)
    {
        int major = -1;
        int minor = -1;
        int modifier = visitClassAccessModifier(definitionContext.accModClass());
        String className = definitionContext.className().getText();
        String superClassName = null;
        List<String> interfaceName = Collections.emptyList();

        JALParser.ClassMetaContext meta = definitionContext.classMeta();
        if (meta != null)
        {
            List<JALParser.ClassMetaItemContext> metaItems = meta.classMetaItem();
            for (JALParser.ClassMetaItemContext item : metaItems)
            {
                if (item.classPropMajor() != null)
                    major = EvaluatorCommons.asInt(item.classPropMajor().NUMBER());
                else if (item.classPropMinor() != null)
                    minor = EvaluatorCommons.asInt(item.classPropMinor().NUMBER());
                else if (item.classPropSuperClass() != null)
                    superClassName =  item.classPropSuperClass().className().getText();
                else if (item.classPropInterfaces() != null)
                    interfaceName = item.classPropInterfaces().className()
                                        .stream()
                                        .map(JALParser.ClassNameContext::getText)
                                        .filter(name -> name != null && !name.isEmpty())
                                        .toList();
            }
        }

        int version = (major >= 0 && minor >= 0) ? (minor << 16 | major) : EOpcodes.ASM9;

        if (superClassName == null || superClassName.isEmpty())
            superClassName = "java/lang/Object"; // デフォルトのスーパークラス

        classNode.visit(
                version,
                modifier,
                className,
                null, // signature
                superClassName,
                interfaceName.toArray(new String[0])
        );
    }

    private static int visitClassAccessModifier(@NotNull JALParser.AccModClassContext accessModifier)
    {
        JALParser.AccessLevelContext accessLevel = accessModifier.accessLevel();
        List<JALParser.AccAttrClassContext> attributes = accessModifier.accAttrClass();

        int modifier = EvaluatorCommons.asAccessLevel(accessLevel);
        for (JALParser.AccAttrClassContext attr: attributes)
        {
            if (attr.KWD_ACC_ATTR_FINAL() != null)
                modifier |= EOpcodes.ACC_FINAL;
            else if (attr.KWD_ACC_ATTR_SUPER() != null)
                modifier |= EOpcodes.ACC_SUPER;
            else if (attr.KWD_INTERFACE() != null)
                modifier |= EOpcodes.ACC_INTERFACE;
            else if (attr.KWD_ACC_ATTR_ABSTRACT() != null)
                modifier |= EOpcodes.ACC_ABSTRACT;
            else if (attr.KWD_ACC_ATTR_SYNTHETIC() != null)
                modifier |= EOpcodes.ACC_SYNTHETIC;
            else if (attr.KWD_ACC_ATTR_ANNOTATION() != null)
                modifier |= EOpcodes.ACC_ANNOTATION;
            else if (attr.KWD_ACC_ATTR_ENUM() != null)
                modifier |= EOpcodes.ACC_ENUM;
        }

        return modifier;
    }

}
