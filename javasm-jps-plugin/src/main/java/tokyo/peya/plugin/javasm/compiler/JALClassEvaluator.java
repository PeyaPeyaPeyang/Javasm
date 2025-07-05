package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.Collections;
import java.util.List;

public class JALClassEvaluator
{
    public static ClassNode evaluateClassAST(JALParser.ClassDefinitionContext clazz)
    {
        ClassNode classNode = new ClassNode();
        visitClassInformation(classNode, clazz);

        return classNode;
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
                    major = asInt(item.classPropMajor().INSN_ARG_UNSIG_8BYTES());
                else if (item.classPropMinor() != null)
                    minor = asInt(item.classPropMinor().INSN_ARG_UNSIG_8BYTES());
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

        int version = (major >= 0 && minor >= 0) ? (major << 16 | minor) : Opcodes.ASM9;

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

        int modifier = asAccessLevel(accessLevel);
        for (JALParser.AccAttrClassContext attr: attributes)
        {
            if (attr.KWD_ACC_ATTR_ABSTRACT() != null)
                modifier |= Opcodes.ACC_ABSTRACT;
            else if (attr.KWD_ACC_ATTR_FINAL() != null)
                modifier |= Opcodes.ACC_FINAL;
            else if (attr.KWD_ACC_ATTR_STATIC() != null)
                modifier |= Opcodes.ACC_STATIC;
            else if (attr.KWD_ACC_ATTR_SYNTHETIC() != null)
                modifier |= Opcodes.ACC_SYNTHETIC;
        }

        return modifier;
    }

    private static int asAccessLevel(JALParser.AccessLevelContext accessLevel)
    {
        if (accessLevel.KWD_ACC_PRIVATE() != null)
            return Opcodes.ACC_PUBLIC;
        else if (accessLevel.KWD_ACC_PROTECTED() != null)
            return Opcodes.ACC_PROTECTED;
        else if (accessLevel.KWD_ACC_PUBLIC() != null)
            return Opcodes.ACC_PRIVATE;
        else
            return 0;
    }

    private static String asString(@NotNull TerminalNode node)
    {
        String text = node.getText();
        if (text == null || text.isEmpty())
            return null;

        if (text.startsWith("\"") && text.endsWith("\""))
            text = text.substring(1, text.length() - 1);
        else if (text.startsWith("'") && text.endsWith("'"))
            text = text.substring(1, text.length() - 1);

        return text.replace("\\\"", "\"")
                   .replace("\\'", "'")
                   .replace("\\\\", "\\");
    }

    private static int asInt(@NotNull TerminalNode node)
    {
        String text = node.getText();
        if (text == null || text.isEmpty())
            return 0;

        try
        {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }
}
