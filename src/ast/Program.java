package ast;

import ast.definitions.Definition;
import semantic.Visitor;

import java.util.List;

public class Program implements ASTNode{

    List<Definition> definitions;
    int line;
    int column;

    public Program (List<Definition> definitions,int line,int column){
        this.definitions=definitions;
        this.line=line;
        this.column=column;


    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public String toString() {
        return "Program{" +
                "definitions=" + definitions +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
