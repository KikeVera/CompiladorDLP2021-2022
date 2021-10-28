package ast;

import semantic.Visitor;

public interface ASTNode {

    int getLine();
    int getColumn();
    Object accept(Visitor visitor, Object param);

}
