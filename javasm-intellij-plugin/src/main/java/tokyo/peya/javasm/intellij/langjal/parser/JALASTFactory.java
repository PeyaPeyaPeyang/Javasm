package tokyo.peya.javasm.intellij.langjal.parser;

import com.intellij.lang.DefaultASTFactoryImpl;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.StringNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassPropertyNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodClInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionParseContributor;
import tokyo.peya.langjal.compiler.JALLexer;

public class JALASTFactory extends DefaultASTFactoryImpl
{
    @Override
    public @NotNull CompositeElement createComposite(@NotNull IElementType type)
    {
        return super.createComposite(type);
    }

    @Override
    public @NotNull LeafElement createLeaf(@NotNull IElementType type, @NotNull CharSequence text)
    {
        if (type instanceof TokenIElementType token)
        {
            switch (token.getANTLRTokenType())
            {
                case JALLexer.ID:
                    return new IdentifierNode(type, text);
                case JALLexer.NUMBER:
                    return new NumberNode(type, text);
                case JALLexer.STRING:
                    return new StringNode(type, text);
                case JALLexer.FULL_QUALIFIED_CLASS_NAME:
                    return new FullQualifiedNameNode(type, text);
                case JALLexer.KWD_CLASS_PROP_MAJOR,
                     JALLexer.KWD_CLASS_PROP_MINOR,
                     JALLexer.KWD_CLASS_PROP_SUPER_CLASS,
                     JALLexer.KWD_CLASS_PROP_INTERFACES:
                    return new ClassPropertyNameNode(type, text);
                case JALLexer.KWD_MNAME_INIT:
                    return new IdentifierMethodInitNode(type);
                case JALLexer.KWD_MNAME_CLINIT:
                    return new IdentifierMethodClInitNode(type);
            }

            if (InstructionParseContributor.isInstructionNameToken(token.getANTLRTokenType()))
                return InstructionParseContributor.createInstructionNameLeaf(type, text);
        }
        return super.createLeaf(type, text);
    }
}
