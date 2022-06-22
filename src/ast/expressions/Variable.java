package ast.expressions;


import ast.definitions.Definition;
import semantic.Visitor;

public class Variable extends ExpressionNode {

    String name;
    Definition definition;


    public Variable(String name,int line,int column){
        super(line, column);
        this.name=name;
    }


    public String getName() {
        return name;
    }


    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }


    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
