package kr.or.ddit.exception;

public class CommonException extends RuntimeException{
//공통으로 사용할 exception
//checked는 exception 상속받음. 단점: 매번 처리해줘야한다.

	//super생성자
	public CommonException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommonException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommonException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
