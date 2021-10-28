package ast.statements;

import ast.expressions.Expression;
import semantic.Visitor;

import java.util.List;

public class IfElse extends StatementNode {

    List<Statement> ifSt;
    List<Statement> elseSt;
    Expression condition;

    public IfElse(List<Statement> ifSt, List<Statement> elseSt, Expression condition, int line, int column){
        super(line, column);
        this.ifSt=ifSt;
        this.elseSt=elseSt;
        this.condition=condition;


    }


    public List<Statement> getIfSt() {
        return ifSt;
    }

    public List<Statement> getElseSt() {
        return elseSt;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "IfElse{" +
                "ifSt=" + ifSt +
                ", elseSt=" + elseSt +
                ", condition=" + condition +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
