grammar Pmm;

@header{
import ast.*;
import ast.expressions.*;
import ast.expressions.literals.*;
import ast.expressions.operators.*;
import ast.expressions.unary.*;
import ast.definitions.*;
import ast.statements.*;
import ast.types.*;


}

program returns [Program ast] :  definitions mainDef  EOF {$definitions.ast.add($mainDef.ast); $ast= new Program($definitions.ast,0,0);}
       ;

mainDef returns [FuncDefinition ast]:
    P='def' 'main' T='(' ')' ':' '{' funcStatements '}' {$ast=new FuncDefinition($funcStatements.ast,
   new FunctionType(new ArrayList<VarDefinition>(),VoidType.getInstance(),$T.getLine(), $T.getCharPositionInLine()+1),"main",
   $P.getLine(),$P.getCharPositionInLine()+1);}
;


definitions returns [List<Definition> ast = new ArrayList<Definition>()]:
    (funcDefinition {$ast.add($funcDefinition.ast);} | varDefinitions ';' {$ast.addAll($varDefinitions.ast);})*
;


funcDefinition returns [FuncDefinition ast]:
     P='def' ID functionType  '{' funcStatements '}' {$ast=new FuncDefinition($funcStatements.ast,$functionType.ast,$ID.text,$P.getLine(),$P.getCharPositionInLine()+1);}
;

funcStatements returns [List<Statement> ast = new ArrayList<Statement>()]:
    (varDefinitions ';' {$ast.addAll($varDefinitions.ast);})* (statement {$ast.add($statement.ast);})*

;

functionType returns [FunctionType ast] locals [Type localType=VoidType.getInstance(),List<VarDefinition> vars = new ArrayList<VarDefinition>() ]:
   P='(' (defParameters {$vars.addAll($defParameters.ast);})?  ')' ':' (simpleType {$localType=$simpleType.ast;} )?
   {$ast=new FunctionType($vars,$localType,$P.getLine(),$P.getCharPositionInLine()+1);}

;

defParameters returns [List<VarDefinition> ast = new ArrayList<VarDefinition>()] :
    ID1=ID ':' type {$ast.add(new VarDefinition($type.ast,$ID1.text,$ID1.getLine(),$ID1.getCharPositionInLine()+1));}
    (',' ID2=ID ':' type {$ast.add(new VarDefinition($type.ast,$ID2.text,$ID2.getLine(),$ID2.getCharPositionInLine()+1));} )*
;



varDefinitions returns [List<VarDefinition> ast = new ArrayList<VarDefinition>()] :
   ids ':' type {for(Variable var: $ids.ast){VarDefinition def= new VarDefinition ($type.ast,var.getName(),var.getLine(),var.getColumn());
   if($ast.contains(def)){new ErrorType("Error de definición de variable duplicada: "+var.getName(),var.getLine(),var.getColumn());} $ast.add(def);}}
;


functionInvocation returns [FunctionInvocation ast] locals [List<Expression> exps = new ArrayList<Expression>()]:
    ID '('  (expressionList {$exps.addAll($expressionList.ast);} )? ')' {$ast = new FunctionInvocation(new Variable($ID.text,$ID.getLine(),$ID.getCharPositionInLine()+1),
    $exps,$ID.getLine(),$ID.getCharPositionInLine()+1 ); }

;

expressionList returns [List<Expression> ast = new ArrayList<Expression>()]:
    exp1=expression{$ast.add($exp1.ast);} (',' exp2=expression{$ast.add($exp2.ast);})*

;

multiStatement returns [List<Statement> ast = new ArrayList<Statement>()]:
    '{' (statement {$ast.add($statement.ast);})*  '}'
    | statement {$ast.add($statement.ast);}


;


statement returns [Statement ast]:

    P='input'  expressionList ';'  {$ast= new Input($expressionList.ast,$P.getLine(),$P.getCharPositionInLine()+1); }|
    P='print'  expressionList ';' {$ast= new Print($expressionList.ast,$P.getLine(),$P.getCharPositionInLine()+1); }|

    P='if' expression ':' ms1=multiStatement {$ast= new IfElse($ms1.ast,new ArrayList<Statement>(),$expression.ast,$P.getLine(),$P.getCharPositionInLine()+1); }
    ('else' ms2=multiStatement {$ast= new IfElse($ms1.ast,$ms2.ast,$expression.ast,$P.getLine(),$P.getCharPositionInLine()+1); })?  |

    P='while' expression ':'multiStatement {$ast= new While($multiStatement.ast,$expression.ast,$P.getLine(),$P.getCharPositionInLine()+1); } |
    exp1=expression '=' exp2=expression ';' {$ast= new Assignment($exp1.ast,$exp2.ast,$exp1.ast.getLine(),$exp1.ast.getColumn()); } |
    functionInvocation ';' {$ast = $functionInvocation.ast;} |
    P='return' expression';' {$ast= new Return($expression.ast,$P.getLine(),$P.getCharPositionInLine()+1); }

;



expression returns [Expression ast]:

    '('expression')' {$ast=$expression.ast;}|
     array=expression '['access=expression']' {$ast = new ArrayAccess($array.ast, $access.ast,$array.ast.getLine(),$array.ast.getColumn());}|
     exp1=expression '.' ID {$ast = new FieldAccess($exp1.ast,$ID.text,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    '('simpleType')'exp1=expression  {$ast = new Cast($exp1.ast,$simpleType.ast,$exp1.ast.getLine(),$exp1.ast.getColumn());}|
    '-' exp1=expression {$ast = new UnaryMinus($exp1.ast,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    '!' exp1=expression {$ast = new Negation($exp1.ast,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    exp1=expression OP=('*'|'/'|'%') exp2=expression {$ast = new Arithmetic($exp1.ast,$exp2.ast,$OP.text,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    exp1=expression OP=('+'|'-') exp2=expression {$ast = new Arithmetic($exp1.ast,$exp2.ast,$OP.text,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    exp1=expression OP=('>'|'>='|'<'|'<='|'!='|'==') exp2=expression {$ast = new Comparison($exp1.ast,$exp2.ast,$OP.text,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    exp1=expression OP=('&&'|'||') exp2=expression {$ast = new Logic($exp1.ast,$exp2.ast,$OP.text,$exp1.ast.getLine(),$exp1.ast.getColumn());} |
    functionInvocation {$ast = $functionInvocation.ast;} |
    INT_CONSTANT {$ast = new IntLiteral(LexerHelper.lexemeToInt($INT_CONSTANT.text),$INT_CONSTANT.getLine(),$INT_CONSTANT.getCharPositionInLine()+1);} |
    REAL_CONSTANT  {$ast = new DoubleLiteral(LexerHelper.lexemeToReal($REAL_CONSTANT.text),$REAL_CONSTANT.getLine(),$REAL_CONSTANT.getCharPositionInLine()+1);} |
    CHAR_CONSTANT {$ast = new CharLiteral(LexerHelper.lexemeToChar($CHAR_CONSTANT.text),$CHAR_CONSTANT.getLine(),$CHAR_CONSTANT.getCharPositionInLine()+1);} |
    ID {$ast = new Variable($ID.text,$ID.getLine(),$ID.getCharPositionInLine()+1);}
;

type returns [Type ast]:

    simpleType {$ast = $simpleType.ast;}|complexType {$ast = $complexType.ast;}

;

simpleType returns [Type ast]:

    TP='double' {$ast = DoubleType.getInstance();} |
    TP='int' {$ast = IntType.getInstance();} |
    TP='char' {$ast = CharType.getInstance() ;}
;

complexType returns [Type ast]:

    TP='struct' '{'fields  '}' {$ast = new RecordType($fields.ast,$TP.getLine(),$TP.getCharPositionInLine()+1);} |
    '[' INT_CONSTANT ']' type   {$ast= new ArrayType($type.ast,LexerHelper.lexemeToInt($INT_CONSTANT.text),$INT_CONSTANT.getLine(),$INT_CONSTANT.getCharPositionInLine()+1) ;}

;


fields returns [List<RecordField> ast = new ArrayList<RecordField>()] :

    (ids ':' type ';' {for(Variable var: $ids.ast){RecordField campo= new RecordField ($type.ast,var.getName(),var.getLine(),var.getColumn());
    if($ast.contains(campo)){new ErrorType("Error de campo duplicado: "+var.getName(),var.getLine(),var.getColumn());} $ast.add(campo);}})*

 ;

ids returns [List<Variable> ast = new ArrayList<Variable>()]:
    ID1=ID{$ast.add(new Variable($ID1.text,$ID1.getLine(),$ID1.getCharPositionInLine()+1)); }
    (',' ID2=ID {$ast.add(new Variable($ID2.text,$ID2.getLine(),$ID2.getCharPositionInLine()+1));})*

;


fragment
EXPONENTE:('E' | 'e')('+' | '-')?INT_CONSTANT+
;

fragment
LETRA: [a-zA-Z]
;

fragment
DIGITO: [0-9]
;

TRASH: [ \r\n\t] -> skip
;

COMENTARIO: (('#'.*?('\n' | EOF))|('"""'.*?'"""')) -> skip
;

INT_CONSTANT: ([1-9]DIGITO*)|'0'
            ;

REAL_CONSTANT:(((INT_CONSTANT '.' INT_CONSTANT?) | (INT_CONSTANT? '.' INT_CONSTANT)) EXPONENTE?) | INT_CONSTANT EXPONENTE
;

CHAR_CONSTANT: (('\''.'\'') | ('\'''\\'DIGITO+'\'') | ('\'\\n\'')|('\'\\t\''))
;

ID: LETRA | (LETRA|'_')(LETRA|DIGITO|'_')+
;
