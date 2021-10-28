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


public abstract class AbstractVisitor implements Visitor {



    protected Object defaultMethod(){
        return null;

    }

    @Override
    public Object visit(Program campo, Object param) {
        for(Definition definition: campo.getDefinitions()){
            definition.accept(this,param);
        }

        return defaultMethod();
    }
    @Override
    public Object visit(FuncDefinition campo, Object param) {

        campo.getType().accept(this,null);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,param);
        }
        return defaultMethod();
    }


    @Override
    public Object visit(VarDefinition campo, Object param) {
        campo.getType().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(CharLiteral campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(DoubleLiteral campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(IntLiteral campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(Arithmetic campo, Object param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(Comparison campo, Object param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);

        return defaultMethod();
    }

    @Override
    public Object visit(Logic campo, Object param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(Negation campo, Object param) {
        campo.getExpression().accept(this,param);

        return defaultMethod();
    }

    @Override
    public Object visit(UnaryMinus campo, Object param) {
        campo.getExpression().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(ArrayAccess campo, Object param) {
        campo.getArray().accept(this,param);
        campo.getAccess().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(Cast campo, Object param) {
        campo.getExpression().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(FieldAcess campo, Object param) {
        campo.getLeft().accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(Variable campo, Object param) { return defaultMethod(); }

    @Override
    public Object visit(FunctionInvocation campo, Object param) {
        campo.getFunction().accept(this,param);
        for(Expression expression:campo.getParams())
            expression.accept(this,param);
        return defaultMethod();
    }

    @Override
    public Object visit(Assignment campo, Object param) {
        campo.getExpressionIzq().accept(this,param);
        campo.getExpressionDer().accept(this,param);

        return defaultMethod();
    }

    @Override
    public Object visit(IfElse campo, Object param) {
        campo.getCondition().accept(this,param);

        for(Statement statement: campo.getIfSt()){
            statement.accept(this,param);
        }
        for(Statement statement: campo.getElseSt()){
            statement.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(Input campo, Object param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(Print campo, Object param) {
        for(Expression expression: campo.getExpressions()){
            expression.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(Return campo, Object param) {
        campo.getExpression().accept(this,null);
        return defaultMethod();
    }

    @Override
    public Object visit(While campo, Object param) {
        campo.getCondition().accept(this,param);
        for(Statement statement: campo.getWhileST()){
            statement.accept(this,param);
        }

        return defaultMethod();
    }

    @Override
    public Object visit(ArrayType campo, Object param) {
        campo.getType().accept(this,null);
        return defaultMethod();
    }

    @Override
    public Object visit(CharType campo, Object param) { return defaultMethod(); }

    @Override
    public Object visit(DoubleType campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(FunctionType campo, Object param) {
        campo.getReturnType().accept(this,null);
        for(Definition definition:campo.getParams())
            definition.accept(this,null);
        return defaultMethod();
    }

    @Override
    public Object visit(IntType campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(RecordType campo, Object param) {
        for(RecordField field: campo.getFields())
            field.accept(this,null);
        return defaultMethod();
    }

    @Override
    public Object visit(VoidType campo, Object param) {
        return defaultMethod();
    }

    @Override
    public Object visit(RecordField campo, Object param) {
        campo.getType().accept(this,null);
        return defaultMethod();
    }
}
