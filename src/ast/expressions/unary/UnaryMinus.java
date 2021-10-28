package ast.expressions.unary;

import ast.expressions.Expression;
import semantic.Visitor;

public class UnaryMinus extends UnaryExpression {



    public UnaryMinus(Expression expression,int line,int column){
        super(expression, line, column);

    }


    @Override
    public String toString() {
        return "UnaryMinus{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        visitor.visit(this,param);
        return null;
    }
}
