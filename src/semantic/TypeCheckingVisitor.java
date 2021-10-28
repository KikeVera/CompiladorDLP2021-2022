package semantic;


import ast.FunctionInvocation;
import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.expressions.operators.Arithmetic;
import ast.expressions.operators.Comparison;
import ast.expressions.operators.Logic;
import ast.expressions.unary.Negation;
import ast.expressions.unary.UnaryMinus;
import ast.statements.*;

import ast.types.*;

import errorHandler.ErrorType;

public class TypeCheckingVisitor extends AbstractVisitor {

    @Override
    public Object visit(FuncDefinition campo, Object param) {

        campo.getType().accept(this,null);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,campo.getType());
        }
        return defaultMethod();
    }



    @Override
    public Object visit(CharLiteral campo, Object param) {

        campo.setLValue(false);
        campo.setType(CharType.getInstance());
        return null;
    }

    @Override
    public Object visit(DoubleLiteral campo, Object param) {
        campo.setLValue(false);
        campo.setType(DoubleType.getInstance());
        return null;
    }

    @Override
    public Object visit(IntLiteral campo, Object param) {
        campo.setLValue(false);
        campo.setType(IntType.getInstance());
        return null;
    }

    @Override
    public Object visit(Arithmetic campo, Object param) {
        campo.getExpressionIzq().accept(this,null);
        campo.getExpressionDer().accept(this,null);

        campo.setType(campo.getExpressionIzq().getType().arithmetic(campo.getExpressionDer().getType()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar la operación entre un: "+campo.getExpressionIzq().getType()+
                    " y un "+campo.getExpressionDer().getType(),campo.getLine(),campo.getColumn()));
        }

        campo.setLValue(false);


        return null;
    }

    @Override
    public Object visit(Comparison campo, Object param) {
        campo.getExpressionIzq().accept(this,null);
        campo.getExpressionDer().accept(this,null);
        campo.setType(campo.getExpressionIzq().getType().comparison(campo.getExpressionDer().getType()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar la comparación entre un: "+campo.getExpressionIzq().getType()+
                    " y un "+campo.getExpressionDer().getType(),campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Logic campo, Object param) {
        campo.getExpressionIzq().accept(this,null);
        campo.getExpressionDer().accept(this,null);
        campo.setType(campo.getExpressionIzq().getType().logic(campo.getExpressionDer().getType()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar la operacion logica entre un: "+campo.getExpressionIzq().getType()+
                    " y un "+campo.getExpressionDer().getType(),campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;
    }

    @Override
    public Object visit(Negation campo, Object param) {
        campo.getExpression().accept(this,null);
        campo.setType(campo.getExpression().getType().logic());
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar la operacion logica con un: "+campo.getExpression().getType()
                    ,campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;
    }

    @Override
    public Object visit(UnaryMinus campo, Object param) {
        campo.getExpression().accept(this,null);
        campo.setType(campo.getExpression().getType().arithmetic());
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar la operación con un: "
                    +campo.getExpression().getType(),campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;
    }



    @Override
    public Object visit(ArrayAccess campo, Object param) {
        campo.getArray().accept(this,param);
        campo.getAccess().accept(this,param);
        campo.setLValue(true);

        campo.setType(campo.getArray().getType().squareBrackets(campo.getAccess().getType()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar el acceso al array",campo.getLine(),campo.getColumn()));
        }


        if(!campo.getArray().getLValue()){
            new ErrorType("El array al que se intenta acceder no es válido", campo.getArray().getLine(),
                    campo.getArray().getColumn());

        }

        return null;
    }

    @Override
    public Object visit(Cast campo, Object param) {
        campo.getExpression().accept(this,null);

        campo.setType(campo.getExpression().getType().canBeCastTo(campo.getCastType()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible realizar un cast: "+campo.getCastType()+
                    " en un "+campo.getExpression().getType(),campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;
    }

    @Override
    public Object visit(FieldAcess campo, Object param) {
        campo.getLeft().accept(this,param);

        campo.setType(campo.getLeft().getType().dot(campo.getField()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Imposible acceder al campo del struct: "+campo.getField(),campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(true);
        if(!campo.getLeft().getLValue()){
            new ErrorType("La expresión mediante la cual se intenta acceder al campo no es válida", campo.getLeft().getLine(),
                    campo.getLeft().getColumn());

        }
        return null;
    }

    @Override
    public Object visit(Variable campo, Object param) {


        campo.setType(campo.getDefinition().getType());

        if(campo.getType().isLvalue())
            campo.setLValue(true);
        else
            campo.setLValue(false);
        return null;
    }

    @Override
    public Object visit(FunctionInvocation campo, Object param) {
        campo.getFunction().accept(this,null);
        for(Expression exp :campo.getParams()){
            exp.accept(this,null);
        }
        campo.setType(campo.getFunction().getType().parenthesis(campo.getParams()));
        if(campo.getType()==null){
            campo.setType(new ErrorType("Invocación a la función no válida: ",campo.getLine(),campo.getColumn()));
        }
        campo.setLValue(false);
        return null;

    }



    @Override
    public Object visit(Assignment campo, Object param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        Type typeDer=campo.getExpressionDer().getType();
        campo.getExpressionDer().setType(typeDer.promotesTo(campo.getExpressionIzq().getType()));
        if(campo.getExpressionDer().getType()==null){
            campo.getExpressionDer().setType(new ErrorType("Imposible realizar la asignación a un "+campo.getExpressionIzq().getType()+
                    " de un "+typeDer,campo.getLine(),campo.getColumn()));
        }

        if (!campo.getExpressionIzq().getLValue())
            new ErrorType("La expresión a la izquierda de la asignación no es válida", campo.getExpressionIzq().getLine(),
                    campo.getExpressionIzq().getColumn());
        return null;
    }




    @Override
    public Object visit(Input campo, Object param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
            if(!expression.getLValue()){
                new ErrorType("No se puede guardar ningún valor en la expresión", expression.getLine(),expression.getColumn());


            }
        }
        return null;
    }



    @Override
    public Object visit(While campo, Object param) {
        campo.getCondition().accept(this,param);
        if(!campo.getCondition().getType().isLogical()){
            campo.getCondition().setType(new ErrorType("La condición del while no es válida",
                    campo.getCondition().getLine(),campo.getCondition().getColumn()));
        }
        for(Statement statement: campo.getWhileST()){
            statement.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(IfElse campo, Object param) {
        campo.getCondition().accept(this,param);

        if(!campo.getCondition().getType().isLogical()){
            campo.getCondition().setType(new ErrorType("La condición del if no es válida",
                    campo.getCondition().getLine(),campo.getCondition().getColumn()));
        }

        for(Statement statement: campo.getIfSt()){
            statement.accept(this,param);
        }
        for(Statement statement: campo.getElseSt()){
            statement.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(Return campo, Object param) {
        campo.getExpression().accept(this,null);
        if(campo.getExpression().getType().promotesTo(((FunctionType)param).getReturnType())==null){
            campo.getExpression().setType(new ErrorType("No se puede retornar un tipo "+campo.getExpression().getType() +" como un "+
                    ((FunctionType)param).getReturnType(), campo.getExpression().getLine(),campo.getExpression().getColumn()));

        }
        return defaultMethod();
    }





    @Override
    public Object visit(FunctionType campo, Object param) {
        campo.getReturnType().accept(this,null);
        for(Definition definition:campo.getParams()) {
            definition.accept(this, null);
            if(!definition.getType().isBuiltInType()){
                new ErrorType("La definicion de la variable no es un tipo BuiltIN",
                        definition.getLine(),definition.getColumn());

            }
        }
        return defaultMethod();
    }













}
