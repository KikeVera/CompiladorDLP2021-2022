package semantic;

import ast.FunctionInvocation;
import ast.Program;
import ast.RecordField;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.Cast;
import ast.expressions.FieldAccess;
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

public interface Visitor<TP,TR> {

    TR visit(Program campo, TP param);
    TR visit(FuncDefinition campo, TP param);
    TR visit(VarDefinition campo, TP param);
    TR visit(CharLiteral campo, TP param);
    TR visit(DoubleLiteral campo, TP param);
    TR visit(IntLiteral campo, TP param);
    TR visit(Arithmetic campo,TP param);
    TR visit(Comparison campo, TP param);
    TR visit(Logic campo, TP param);
    TR visit(Negation campo, TP param);
    TR visit(UnaryMinus campo, TP param);
    TR visit(ArrayAccess campo, TP param);
    TR visit(Cast campo, TP param);
    TR visit(FieldAccess campo, TP param);
    TR visit(Variable campo, TP param);
    TR visit(FunctionInvocation campo, TP param);
    TR visit(Assignment campo, TP param);
    TR visit(IfElse campo, TP param);
    TR visit(Input campo, TP param);
    TR visit(Print campo, TP param);
    TR visit(Return campo, TP param);
    TR visit(While campo,TP param);
    TR visit(ArrayType campo, TP param);
    TR visit(CharType campo, TP param);
    TR visit(DoubleType campo, TP param);
    TR visit(FunctionType campo, TP param);
    TR visit(IntType campo, TP param);
    TR visit(RecordType campo,TP param);
    TR visit(VoidType campo,TP param);
    TR visit(ErrorType campo,TP param);
    TR visit(RecordField campo, TP param);





}
