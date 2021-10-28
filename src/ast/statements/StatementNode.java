package ast.statements;

public abstract class StatementNode implements Statement {

    int line;
    int column;

    public StatementNode(int line, int column){
        this.line=line;
        this.column=column;
    }
    
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
