package ast.types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;

public interface Type extends ASTNode {
    boolean isLogical();
    boolean isBuiltInType();
    Type arithmetic(Type right,ASTNode campo);
    Type arithmetic(ASTNode campo);
    Type comparison(Type right ,ASTNode campo);
    Type logic(Type right,ASTNode campo);
    Type logic(ASTNode campo);
    Type promotesTo(Type left,ASTNode campo);
    Type canBeCastTo(Type right,ASTNode campo);
    Type squareBrackets(Type array,ASTNode campo);
    Type dot(String campo,ASTNode node);
    Type parenthesis(List<Expression> list,ASTNode campo);
    int numberOfBytes();
    char getSuffix();




}
