package semantic;

import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.statements.Statement;
import ast.types.FunctionType;
import errorHandler.ErrorType;
import semantic.symboltable.SymbolTable;

public class ScopeVisitor extends AbstractVisitor {

    SymbolTable symbolTable= new SymbolTable();

    @Override
    public Object visit(FuncDefinition campo, Object param) {
        if(!symbolTable.insert(campo)){
            new ErrorType("Definicion de la función ("+campo.getName() +") duplicada ", campo.getLine(),
                    campo.getColumn());

        }
        symbolTable.set();
       campo.getType().accept(this,param);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,param);
        }
        symbolTable.reset();
        return defaultMethod();
    }



    @Override
    public Object visit(VarDefinition campo, Object param) {
        if(!symbolTable.insert(campo)){
            new ErrorType("Variabe ("+campo.getName()+") duplicada  ", campo.getLine(),
                    campo.getColumn());

        }


        return defaultMethod();
    }

    @Override
    public Object visit(Variable campo, Object param) {
        Definition definition= symbolTable.find(campo.getName());
        if(definition==null){
            new ErrorType("La variable ("+campo.getName() +") no está definida  ", campo.getLine(),
                    campo.getColumn());
        }

        else{
            campo.setDefinition(definition);
        }
        return defaultMethod();
    }
}
