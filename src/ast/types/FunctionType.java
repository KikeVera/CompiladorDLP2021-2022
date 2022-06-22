package ast.types;


import ast.ASTNode;
import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import errorhandler.ErrorHandler;
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
    public Type parenthesis(List<Expression> list, ASTNode campo){
        if(list.size()!=params.size()){
            return super.parenthesis(list,campo);
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).getType().promotesTo(params.get(i).getType(),campo)instanceof ErrorType ){

                return super.parenthesis(list,campo);
            }
        }

        return returnType;
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
