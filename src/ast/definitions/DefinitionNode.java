package ast.definitions;

import ast.types.Type;

public abstract class DefinitionNode implements Definition {

    int line;
    int column;
    Type type;
    String name;
    int scope;


    public DefinitionNode(Type type,String name,int line, int column){
        this.line=line;
        this.column=column;
        this.type=type;
        this.name=name;
    }


    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getScope(){
        return scope;
    }
    public void setScope(int scope) {
        this.scope=scope;
    }
}
