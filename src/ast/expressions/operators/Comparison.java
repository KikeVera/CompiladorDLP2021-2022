package ast.expressions.operators;

import ast.expressions.Expression;
import semantic.Visitor;

public class Comparison extends OperatorExpression {



    public Comparison(Expression expressionIzq, Expression expressionDer,String operator,int line,int column){
        super(expressionIzq, expressionDer, operator, line, column);

    }

    @Override
    public String toString() {
        return "Comparison{" +
                "expressionIzq=" + expressionIzq +
                ", expressionDer=" + expressionDer +
                ", operator='" + operator + '\'' +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
