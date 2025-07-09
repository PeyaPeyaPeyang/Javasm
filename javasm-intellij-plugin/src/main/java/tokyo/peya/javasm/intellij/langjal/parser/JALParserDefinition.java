package tokyo.peya.javasm.intellij.langjal.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessAttributeNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessLevelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessModifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.TypeDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyItemNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassMetaNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionLocalReferenceNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionParseContributor;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyInterfacesNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyMajorVersionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyMinorVersionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertySuperClassNode;
import tokyo.peya.javasm.langjal.compiler.JALLexer;
import tokyo.peya.javasm.langjal.compiler.JALParser;

import java.util.List;

public final class JALParserDefinition implements ParserDefinition
{
    public static final IFileElementType FILE = new IFileElementType(JALLanguage.INSTANCE);

    public static TokenIElementType ID;
    public static TokenIElementType NUMBER;
    public static TokenIElementType FULL_QUALIFIED_CLASS_NAME;

    public JALParserDefinition()
    {
        initStatic();
    }

    private static void initStatic()
    {
        if (ID != null)
            return;

        PSIElementTypeFactory.defineLanguageIElementTypes(
                JALLanguage.INSTANCE,
                JALParser.tokenNames,
                JALParser.ruleNames
        );
        List<TokenIElementType> tokenIElementTypes =
                PSIElementTypeFactory.getTokenIElementTypes(JALLanguage.INSTANCE);
        ID = tokenIElementTypes.get(JALLexer.ID);
        NUMBER = tokenIElementTypes.get(JALLexer.NUMBER);
        FULL_QUALIFIED_CLASS_NAME = tokenIElementTypes.get(JALLexer.FULL_QUALIFIED_CLASS_NAME);

    }

    @Override
    public @NotNull Lexer createLexer(Project project)
    {
        return new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    }

    @Override
    public @NotNull PsiParser createParser(Project project)
    {
        JALParser parser = new JALParser(null);
        return new ANTLRParserAdaptor(JALLanguage.INSTANCE, parser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root)
            {
                if (root instanceof IFileElementType)
                    return ((JALParser) parser).root();
                else
                    return ((JALParser) parser).jvmInsArgScalarType();
            }
        };
    }

    @Override
    public @NotNull IFileElementType getFileNodeType()
    {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens()
    {
        return JALTokens.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements()
    {
        return JALTokens.STRING;
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens()
    {
        return JALTokens.WHITESPACE;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node)
    {
        IElementType elementType = node.getElementType();
        if (elementType instanceof RuleIElementType ruleType)
            return this.createRuleElement(node, ruleType);
        return new ANTLRPsiNode(node);
    }

    @NotNull
    private PsiElement createRuleElement(ASTNode node, RuleIElementType type)
    {
        if (InstructionParseContributor.isInstructionRule(type.getRuleIndex()))
            return InstructionParseContributor.createInstructionElement(node, type);

        return switch (type.getRuleIndex()) {
            case JALParser.RULE_classDefinition -> new ClassDefinitionNode(node, FULL_QUALIFIED_CLASS_NAME);
            case JALParser.RULE_className -> new ClassNameNode(node);

            case JALParser.RULE_accModClass, JALParser.RULE_accModField, JALParser.RULE_accModMethod -> new AccessModifierNode(node);
            case JALParser.RULE_accessLevel -> new AccessLevelNode(node);
            case JALParser.RULE_accAttrClass, JALParser.RULE_accAttrField, JALParser.RULE_accAttrMethod -> new AccessAttributeNode(node);

            case JALParser.RULE_classMeta -> new ClassMetaNode(node);
            case JALParser.RULE_classPropMajor -> new ClassPropertyMajorVersionNode(node);
            case JALParser.RULE_classPropMinor -> new ClassPropertyMinorVersionNode(node);
            case JALParser.RULE_classPropSuperClass -> new ClassPropertySuperClassNode(node);
            case JALParser.RULE_classPropInterfaces -> new ClassPropertyInterfacesNode(node);

            case JALParser.RULE_classBody -> new ClassBodyNode(node);
            case JALParser.RULE_classBodyItem -> new ClassBodyItemNode(node);
            case JALParser.RULE_label -> new LabelNode(node, ID);
            case JALParser.RULE_labelName -> new LabelNameNode(node);

            case JALParser.RULE_methodDefinition -> new MethodDefinitionNode(node, ID);
            case JALParser.RULE_methodName -> new MethodNameNode(node);
            case JALParser.RULE_methodBody -> new MethodBodyNode(node);
            case JALParser.RULE_instructionSet -> new InstructionSetNode(node, ID);

            case JALParser.RULE_typeDescriptor -> new TypeDescriptorNode(node);
            case JALParser.RULE_methodDescriptor -> new MethodDescriptorNode(node);

            case JALParser.RULE_jvmInsArgLocalRef -> new InstructionLocalReferenceNode(node);
            default -> new ANTLRPsiNode(node);
        };
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right)
    {
        return SpaceRequirements.MAY;
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider)
    {
        return new JALFile(fileViewProvider);
    }
}
