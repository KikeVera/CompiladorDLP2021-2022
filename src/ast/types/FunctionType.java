package ast.types;


import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import semantic.Visitor;

import java.util.List;

public class FunctionType extends TypeNode {

    List<VarDefinition> params;
    Type returnType;

    public FunctionType(List<VarDefinition> params, Type returnType,int line,int column){
        super(line, column);
        this.params=params;
        this.returnType=returnType;
    }

    public List<VarDefinition> getParams() {
        return params;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public Type parenthesis(List<Expression> list){
        if(list.size()!=params.size()){
            return null;
        }

        for(int i=0;i<list.size();i++){
            if(list.get(0).getType().promotesTo(params.get(0).getType())==null){
                return null;
            }
        }

        return returnType;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "params=" + params +
                ", returnType=" + returnType +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
