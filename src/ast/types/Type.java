package ast.types;

import ast.ASTNode;
import ast.expressions.Expression;

import java.util.List;

public interface Type extends ASTNode {

    boolean isLogical();
    boolean isBuiltInType();
    Type arithmetic(Type right);
    Type arithmetic();
    Type comparison(Type right);
    Type logic(Type right);
    Type logic();
    Type promotesTo(Type left);
    Type canBeCastTo(Type right);
    Type squareBrackets(Type array);
    Type dot(String campo);
    Type parenthesis(List<Expression> list);
    int numberOfBytes() ;
    char getSuffix();
    boolean isLvalue();
}
