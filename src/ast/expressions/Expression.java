package ast.expressions;

import ast.ASTNode;
import ast.types.Type;

public interface Expression extends ASTNode {
    public int getLine();
    public int getColumn();
    public boolean getLValue();
    public void setLValue(boolean lValue);
    public Type getType();
    public void setType(Type type);
}
