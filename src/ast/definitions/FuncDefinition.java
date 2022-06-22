package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;
import semantic.Visitor;


import java.util.List;

public class FuncDefinition extends DefinitionNode{

    public int offSetLocal;
    List<Statement> statements;


    public FuncDefinition(List<Statement> statements,Type funcType,String name,int line,int column){
        super(funcType, name, line, column);
        this.statements=statements;

    }



    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        return "FuncDefinition{" +
                "statements=" + statements +
                '}';
    }



    public <TP, TR> TR accept(Visitor <TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }
}

