package semantic;

import ast.FunctionInvocation;
import ast.Program;
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


public abstract class AbstractVisitor<TP,TR> implements Visitor<TP,TR> {




    @Override
    public TR visit(Program campo, TP param) {
        for(Definition definition: campo.getDefinitions()){
            definition.accept(this,param);
        }

        return null;
    }
    @Override
    public TR visit(FuncDefinition campo, TP param) {

        campo.getType().accept(this,null);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,param);
        }
        return null;
    }


    @Override
    public TR visit(VarDefinition campo, TP param) {
        campo.getType().accept(this,param);
        return null;
    }

    @Override
    public TR visit(CharLiteral campo, TP param) {
        return null;
    }

    @Override
    public TR visit(DoubleLiteral campo, TP param) {
        return null;
    }

    @Override
    public TR visit(IntLiteral campo, TP param) {
        return null;
    }

    @Override
    public TR visit(Arithmetic campo, TP param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Comparison campo, TP param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);

        return null;
    }

    @Override
    public TR visit(Logic campo, TP param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Negation campo, TP param) {
        campo.getExpression().accept(this,param);

        return null;
    }

    @Override
    public TR visit(UnaryMinus campo, TP param) {
        campo.getExpression().accept(this,param);
        return null;
    }

    @Override
    public TR visit(ArrayAccess campo, TP param) {
        campo.getArray().accept(this,param);
        campo.getAccess().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Cast campo, TP param) {
        campo.getExpression().accept(this,param);
        return null;
    }

    @Override
    public TR visit(FieldAccess campo, TP param) {
        campo.getLeft().accept(this,param);
        return null;
    }

    @Override
    public TR visit(Variable campo, TP param) { return null; }

    @Override
    public TR visit(FunctionInvocation campo, TP param) {
        campo.getFunction().accept(this,param);
        for(Expression expression:campo.getParams())
            expression.accept(this,param);
        return null;
    }

    @Override
    public TR visit(Assignment campo, TP param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);

        return null;
    }

    @Override
    public TR visit(IfElse campo, TP param) {
        campo.getCondition().accept(this,param);

        for(Statement statement: campo.getIfSt()){
            statement.accept(this,param);
        }
        for(Statement statement: campo.getElseSt()){
            statement.accept(this,param);
        }

        return null;
    }

    @Override
    public TR visit(Input campo, TP param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
        }

        return null;
    }

    @Override
    public TR visit(Print campo, TP param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
        }

        return null;
    }

    @Override
    public TR visit(Return campo, TP param) {
        campo.getExpression().accept(this,null);
        return null;
    }

    @Override
    public TR visit(While campo, TP param) {
        campo.getCondition().accept(this,param);
        for(Statement statement: campo.getWhileST()){
            statement.accept(this,param);
        }

        return null;
    }

    @Override
    public TR visit(ArrayType campo, TP param) {
        campo.getType().accept(this,null);
        return null;
    }

    @Override
    public TR visit(CharType campo, TP param) { return null; }

    @Override
    public TR visit(DoubleType campo, TP param) {
        return null;
    }

    @Override
    public TR visit(FunctionType campo, TP param) {
        campo.getReturnType().accept(this,null);
        for(Definition definition:campo.getParams())
            definition.accept(this,null);
        return null;
    }

    @Override
    public TR visit(IntType campo, TP param) {
        return null;
    }

    @Override
    public TR visit(RecordType campo, TP param) {
        for(RecordField field: campo.getFields())
            field.accept(this,null);
        return null;
    }

    @Override
    public TR visit(VoidType campo, TP param) {
        return null;
    }

    @Override
    public TR visit(ErrorType campo, TP param) {
        return null;
    }

    @Override
    public TR visit(RecordField campo, TP param) {
        campo.getType().accept(this,null);
        return null;
    }
}
