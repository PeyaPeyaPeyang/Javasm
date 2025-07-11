package tokyo.peya.javasm.langjal.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.Collections;
import java.util.List;

public class JALClassEvaluator
{
    public static ClassNode evaluateClassAST(@NotNull FileEvaluatingReporter reporter,
                                             @NotNull JALParser.ClassDefinitionContext clazz)
    {
        ClassNode classNode = new ClassNode();
        visitClassInformation(classNode, clazz);
        visitClassBody(reporter, classNode, clazz.classBody());

        return classNode;
    }

    private static void visitClassBody(@NotNull FileEvaluatingReporter reporter, @NotNull ClassNode classNode,
                                       @Nullable JALParser.ClassBodyContext body)
    {
        if (body == null)
            return;

        List<JALParser.ClassBodyItemContext> items = body.classBodyItem();
        for (JALParser.ClassBodyItemContext item : items)
        {
            if (item.methodDefinition() != null)
            {
                JALMethodEvaluator evaluator = new JALMethodEvaluator(reporter, classNode);
                evaluator.evaluateMethod(item.methodDefinition());
            }
            if (item.fieldDefinition() != null)
                visitField(classNode, item.fieldDefinition());
        }
    }

    private static void visitField(@NotNull ClassNode classNode,
                                   @NotNull JALParser.FieldDefinitionContext fieldDefinition)
    {
        JALParser.AccModFieldContext accessModifier = fieldDefinition.accModField();
        String fieldName = fieldDefinition.fieldName().getText();
        String fieldType = fieldDefinition.typeDescriptor().getText();
        Object scalarValue = null;

        if (fieldDefinition.jvmInsArgScalarType() != null)
            scalarValue = evaluateScalar(fieldDefinition.jvmInsArgScalarType());

        int modifier = visitFieldAccessModifier(accessModifier);
        FieldNode fieldNode = new FieldNode(
                modifier,
                fieldName,
                fieldType,
                null, // signature
                scalarValue // value
        );
        classNode.fields.add(fieldNode);
    }

    private static Object evaluateScalar(JALParser.JvmInsArgScalarTypeContext scalar)
    {
        if (scalar.NUMBER() != null)
            return EvaluatorCommons.toNumber(scalar.NUMBER().getText());
        else if (scalar.STRING() != null)
        {
            String value = scalar.STRING().getText();
            return value.substring(1, value.length() - 1); // Remove quotes
        }
        else if (scalar.BOOLEAN() != null)
            return EvaluatorCommons.toBoolean(scalar.BOOLEAN().getText());
        else
            throw new IllegalArgumentException("Unsupported scalar type: " + scalar.getText());
    }

    private static void visitClassInformation(@NotNull ClassNode classNode,
                                              @NotNull JALParser.ClassDefinitionContext definitionContext)
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
                    major = EvaluatorCommons.asInteger(item.classPropMajor().NUMBER());
                else if (item.classPropMinor() != null)
                    minor = EvaluatorCommons.asInteger(item.classPropMinor().NUMBER());
                else if (item.classPropSuperClass() != null)
                    superClassName = item.classPropSuperClass().className().getText();
                else if (item.classPropInterfaces() != null)
                    interfaceName = item.classPropInterfaces().className()
                                        .stream()
                                        .map(JALParser.ClassNameContext::getText)
                                        .filter(name -> name != null && !name.isEmpty())
                                        .toList();
            }
        }

        // フォールバック
        if (major <= 0 || minor < 0)
        {
            // デフォルトのバージョンを使用
            major = RuntimeUtils.getClassFileMajorVersion();
            minor = 0;
        }

        if (superClassName == null || superClassName.isEmpty())
            superClassName = "java/lang/Object"; // デフォルトのスーパークラス

        int version = minor << 16 | major;

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
        for (JALParser.AccAttrClassContext attr : attributes)
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

    private static int visitFieldAccessModifier(@NotNull JALParser.AccModFieldContext accessModifier)
    {
        JALParser.AccessLevelContext accessLevel = accessModifier.accessLevel();
        List<JALParser.AccAttrFieldContext> attributes = accessModifier.accAttrField();

        int modifier = EvaluatorCommons.asAccessLevel(accessLevel);
        for (JALParser.AccAttrFieldContext attr : attributes)
        {
            if (attr.KWD_ACC_ATTR_FINAL() != null)
                modifier |= EOpcodes.ACC_FINAL;
            else if (attr.KWD_ACC_ATTR_STATIC() != null)
                modifier |= EOpcodes.ACC_STATIC;
            else if (attr.KWD_ACC_ATTR_VOLATILE() != null)
                modifier |= EOpcodes.ACC_VOLATILE;
            else if (attr.KWD_ACC_ATTR_TRANSIENT() != null)
                modifier |= EOpcodes.ACC_TRANSIENT;
            else if (attr.KWD_ACC_ATTR_SYNTHETIC() != null)
                modifier |= EOpcodes.ACC_SYNTHETIC;
            else if (attr.KWD_ACC_ATTR_ENUM() != null)
                modifier |= EOpcodes.ACC_ENUM;
        }

        return modifier;
    }
}
