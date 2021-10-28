package ast.statements;

import ast.expressions.Expression;
import semantic.Visitor;

import java.util.List;

public class Print extends StatementNode {

    List<Expression> expressions;

    public Print(List<Expression> expressions,int line,int column){
        super(line, column);
        this.expressions=expressions;


    }


    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public String toString() {
        return "Print{" +
                "expressions=" + expressions +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
