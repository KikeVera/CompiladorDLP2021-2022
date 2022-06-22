package semantic;


import ast.FunctionInvocation;

import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
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


public class TypeCheckingVisitor extends AbstractVisitor<FunctionType,Void> {




     @Override
    public Void visit(FuncDefinition campo, FunctionType param) {

        campo.getType().accept(this,param);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,(FunctionType) campo.getType());
        }
        return null;
    }


    @Override
    public Void visit(CharLiteral campo, FunctionType param) {

        campo.setLValue(false);
        campo.setType(CharType.getInstance());
        return null;
    }

    @Override
    public Void visit(DoubleLiteral campo, FunctionType param) {
        campo.setLValue(false);
        campo.setType(DoubleType.getInstance());
        return null;
    }

    @Override
    public Void visit(IntLiteral campo, FunctionType param) {
        campo.setLValue(false);
        campo.setType(IntType.getInstance());
        return null;
    }

    @Override
    public Void visit(Arithmetic campo, FunctionType param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        campo.setType(campo.getExpressionIzq().getType().arithmetic(campo.getExpressionDer().getType(),campo));
        campo.setLValue(false);

        return null;
    }

    @Override
    public Void visit(Comparison campo, FunctionType param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        campo.setType(campo.getExpressionIzq().getType().comparison(campo.getExpressionDer().getType(),campo));
        campo.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic campo, FunctionType param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        campo.setType(campo.getExpressionIzq().getType().logic(campo.getExpressionDer().getType(),campo));
        campo.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Negation campo, FunctionType param) {
        campo.getExpression().accept(this,param);
        campo.setType(campo.getExpression().getType().logic(campo));
        campo.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus campo, FunctionType param) {
        campo.getExpression().accept(this,param);
        campo.setType(campo.getExpression().getType().arithmetic(campo));
        campo.setLValue(false);
        return null;
    }



    @Override
    public Void visit(ArrayAccess campo, FunctionType param) {
        campo.getArray().accept(this,param);
        campo.getAccess().accept(this,param);
        campo.setLValue(true);

        campo.setType(campo.getArray().getType().squareBrackets(campo.getAccess().getType(),campo));
        if(!campo.getArray().getLValue()){
            new ErrorType("El array al que se intenta acceder no es válido", campo.getArray().getLine(),
                    campo.getArray().getColumn());

        }

        return null;
    }

    @Override
    public Void visit(Cast campo, FunctionType param) {
        campo.getExpression().accept(this,param);

        campo.setType(campo.getExpression().getType().canBeCastTo(campo.getCastType(),campo));
        campo.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess campo, FunctionType param) {
        campo.getLeft().accept(this,param);

        campo.setType(campo.getLeft().getType().dot(campo.getField(),campo));
        campo.setLValue(true);
        if(!campo.getLeft().getLValue()){
            new ErrorType("La expresión mediante la cual se intenta acceder al campo no es válida", campo.getLeft().getLine(),
                    campo.getLeft().getColumn());

        }
        return null;
    }

    @Override
    public Void visit(Variable campo, FunctionType param) {
        campo.setType(campo.getDefinition().getType());
        campo.setLValue(true);
        return null;
    }

    @Override
    public Void visit(FunctionInvocation campo, FunctionType param) {
        campo.getFunction().accept(this,param);
        for(Expression exp :campo.getParams()){
            exp.accept(this,param);
        }
        campo.setType(campo.getFunction().getType().parenthesis(campo.getParams(),campo));
        campo.setLValue(false);
        return null;

    }



    @Override
    public Void visit(Assignment campo, FunctionType param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);

        Type typeDer=campo.getExpressionDer().getType();
        if(!(typeDer instanceof ErrorType))
             campo.getExpressionDer().setType(typeDer.promotesTo(campo.getExpressionIzq().getType(),campo));


        if (!campo.getExpressionIzq().getLValue())
            new ErrorType("La expresión a la izquierda de la asignación no es válida", campo.getExpressionIzq().getLine(),
                    campo.getExpressionIzq().getColumn());
        return null;
    }




    @Override
    public Void visit(Input campo, FunctionType param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
            if(!expression.getLValue()){
                new ErrorType("No se puede guardar ningún valor en la expresión", expression.getLine(),expression.getColumn());


            }
        }
        return null;
    }


    @Override
    public Void visit(While campo, FunctionType param) {
        campo.getCondition().accept(this,param);
        if(!campo.getCondition().getType().isLogical()){
            campo.getCondition().setType(new ErrorType("La condición del while no es válida",
                    campo.getCondition().getLine(),campo.getCondition().getColumn()));
        }
        for(Statement statement: campo.getWhileST()){
            statement.accept(this,param);
        }

        return null;
    }


    @Override
    public Void visit(IfElse campo, FunctionType param) {
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

        return null;
    }

    @Override
    public Void visit(Return campo, FunctionType param) {
        campo.getExpression().accept(this,param);
        campo.getExpression().setType(campo.getExpression().getType().promotesTo(param.getReturnType(),campo));

        return null;
    }

    @Override
    public Void visit(FunctionType campo, FunctionType param) {
        campo.getReturnType().accept(this,param);
        for(Definition definition:campo.getParams()) {
            definition.accept(this, param);
            if(!definition.getType().isBuiltInType()){
                new ErrorType("La definicion de la variable no es un tipo BuiltIN",
                        definition.getLine(),definition.getColumn());

            }
        }
        return null;
    }









}
