package ast.statements;

import ast.expressions.Expression;
import semantic.Visitor;

import java.util.List;

public class While extends StatementNode {

    List<Statement> whileST;
    Expression condition;

    public While(List<Statement> whileST, Expression condition,int line,int column){
        super(line, column);
        this.whileST=whileST;
        this.condition=condition;

    }


    public List<Statement> getWhileST() {
        return whileST;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "While{" +
                "whileST=" + whileST +
                ", condition=" + condition +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
