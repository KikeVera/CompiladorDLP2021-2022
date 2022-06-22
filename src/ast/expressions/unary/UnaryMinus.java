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

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }

}
