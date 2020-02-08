package kr.or.ddit.exception;

/**
 * Throwable : 모든 비정상 실행 상황의 추상,최상위
 * 	-Error : 일반적으로 개발자가 직접 처리하지 않는 비정상
 * 	-Exception : 개발자가 직접 처리하는 비정상
 *		-checked(Exception) : 반드시 처리를 해야하는 비정상
 *		-unchecked(RuntimeException) : 처리하지 않아도 jvm에게 자동 Throws가 되는 비정상
 *
 * throws(호출자에게 처리를 위임), 
 * try~catch~finally(현재의 코드블럭내에서 처리)
 * 
 * throw(생성된 객체를 예외의 형태로 만들 때)
 *
 */
public class UserNotFoundException extends RuntimeException { //unchecked
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
