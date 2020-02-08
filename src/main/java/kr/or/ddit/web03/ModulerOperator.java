package kr.or.ddit.web03;

public class ModulerOperator implements Operator{

	@Override
	public long operate(int leftOp, int rightOp) {
		return leftOp%rightOp;
	}

}
