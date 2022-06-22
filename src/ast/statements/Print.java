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

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
