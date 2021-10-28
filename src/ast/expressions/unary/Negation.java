package ast.expressions.unary;

import ast.expressions.Expression;
import semantic.Visitor;

public class Negation extends UnaryExpression {



    public Negation (Expression expression,int line,int column){
        super(expression, line, column);

    }

    @Override
    public String toString() {
        return "Negation{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        visitor.visit(this,param);
        return null;
    }
}
