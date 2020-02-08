package kr.or.ddit.enumpkg;

import kr.or.ddit.web03.ModulerOperator;
import kr.or.ddit.web03.Operator;

public enum OperatorType {
	PLUS('+',(left,right)->{return left + right;}), 
	MINUS('-',(left,right)->{return left - right;}), 
	MULTIPLY('*',(left,right)->{return left * right;}), 
	DIVIDE('/',new Operator() {
		public long operate(int leftOp, int rightOp) {
			return leftOp / rightOp;
		}
	}),
	MODULAR('%', new ModulerOperator());
	
	OperatorType(char sign, Operator operator){
		this.sign = sign;
		this.operator = operator;
	}
	private Operator operator;
	public long operate(int leftOp, int rightOp){
		return operator.operate(leftOp, rightOp);
	}
	private char sign;
	public char getSign(){
		return sign;
	}
	private static String ptrn = "%d %s %d = %d";
	public String opExpr(int left, int right) {
		return String.format(ptrn, left, sign, right, operate(left, right));
	}
}
