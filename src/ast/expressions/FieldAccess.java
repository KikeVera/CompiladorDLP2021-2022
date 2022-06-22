package ast.expressions;


import semantic.Visitor;

public class FieldAccess extends ExpressionNode{

    Expression left;
    String field;

    public FieldAccess(Expression left, String field, int line, int column){
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

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
