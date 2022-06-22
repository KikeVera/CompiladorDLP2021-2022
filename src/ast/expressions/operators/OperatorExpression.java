package ast.expressions.operators;

import ast.expressions.Expression;
import ast.expressions.ExpressionNode;

public abstract class OperatorExpression extends ExpressionNode {
    Expression expressionIzq;
    Expression expressionDer;
    String operator;

    public OperatorExpression(Expression expressionIzq, Expression expressionDer, String operator,int line, int column){
        super(line,column);
        this.expressionDer =expressionDer;
        this.expressionIzq =expressionIzq;
        this.operator=operator;
    }

    public Expression getExpressionIzq() {
        return expressionIzq;
    }

    public Expression getExpressionDer() {
        return expressionDer;
    }

    public String getOperator() {
        return operator;
    }
}
