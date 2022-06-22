package ast.expressions.operators;

import ast.expressions.Expression;
import semantic.Visitor;

public class Comparison extends OperatorExpression {

    public Comparison(Expression expIzq, Expression expDer, String operator, int line, int column){
        super(expIzq,expDer,operator,line,column);
    }

    @Override
    public String toString() {
        return "Comparison{" +
                "expressionIzq=" + expressionIzq +
                ", expressionDer=" + expressionDer +
                ", operator='" + operator + '\'' +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}
