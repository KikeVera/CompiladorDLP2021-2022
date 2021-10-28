package ast.statements;

import ast.expressions.Expression;
import semantic.Visitor;

public class Return extends StatementNode {

    Expression expression;

    public Return(Expression expression,int line,int column){
        super(line, column);
        this.expression=expression;


    }


    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Return{" +
                "expression=" + expression +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
