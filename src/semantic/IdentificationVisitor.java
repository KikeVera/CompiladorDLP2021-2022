package semantic;



import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;

import ast.statements.Statement;
import ast.types.ErrorType;
import symboltable.SymbolTable;


public class IdentificationVisitor extends AbstractVisitor<Void,Void> {


    SymbolTable symbolTable= new SymbolTable();

    @Override
    public Void visit(FuncDefinition campo, Void param) {
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
        return null;
    }



    @Override
    public Void visit(VarDefinition campo, Void param) {
        if(!symbolTable.insert(campo)){
            new ErrorType("Variabe ("+campo.getName()+") duplicada  ", campo.getLine(),
                    campo.getColumn());

        }

        campo.getType().accept(this,param);
        return null;
    }

    @Override
    public Void visit(Variable campo, Void param) {
        Definition definition= symbolTable.find(campo.getName());
        if(definition==null){
            new ErrorType("La variable ("+campo.getName() +") no está definida  ", campo.getLine(),
                    campo.getColumn());
        }

        else{
            campo.setDefinition(definition);
        }
        return null;
    }




}
