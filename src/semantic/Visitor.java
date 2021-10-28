package semantic;

import ast.FunctionInvocation;
import ast.Program;
import ast.RecordField;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.Cast;
import ast.expressions.FieldAcess;
import ast.expressions.Variable;
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

public interface Visitor {

    Object visit(Program campo, Object param);
    Object visit(FuncDefinition campo, Object param);
    Object visit(VarDefinition campo, Object param);
    Object visit(CharLiteral campo, Object param);
    Object visit(DoubleLiteral campo, Object param);
    Object visit(IntLiteral campo, Object param);
    Object visit(Arithmetic campo,Object param);
    Object visit(Comparison campo, Object param);
    Object visit(Logic campo, Object param);
    Object visit(Negation campo, Object param);
    Object visit(UnaryMinus campo, Object param);
    Object visit(ArrayAccess campo, Object param);
    Object visit(Cast campo, Object param);
    Object visit(FieldAcess campo, Object param);
    Object visit(Variable campo, Object param);
    Object visit(FunctionInvocation campo, Object param);
    Object visit(Assignment campo, Object param);
    Object visit(IfElse campo, Object param);
    Object visit(Input campo, Object param);
    Object visit(Print campo, Object param);
    Object visit(Return campo, Object param);
    Object visit(While campo,Object param);
    Object visit(ArrayType campo, Object param);
    Object visit(CharType campo, Object param);
    Object visit(DoubleType campo, Object param);
    Object visit(FunctionType campo, Object param);
    Object visit(IntType campo, Object param);
    Object visit(RecordType campo,Object param);
    Object visit(VoidType campo,Object param);
    Object visit(RecordField campo, Object param);





}
