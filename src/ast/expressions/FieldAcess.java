package ast.expressions;

import semantic.Visitor;

public class FieldAcess extends ExpressionNode{

    Expression left;
    String field;

    public FieldAcess(Expression left,String field,int line,int column){
        super(line, column);
        this.left=left;
        this.field=field;
    }


    public Expression getLeft() {
        return left;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return "FieldAcess{" +
                "left=" + left +
                ", field='" + field + '\'' +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
