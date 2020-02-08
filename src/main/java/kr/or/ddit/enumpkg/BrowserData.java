package kr.or.ddit.enumpkg;

public class BrowserData {
//브라우저의 이름과 버전을 저장할 수 있는 캡슐화된 전역변수?
	private String name;
	private double version;
	
	//setter대신 생성자 - 생성자는 한번 실행되고 끝! setter는 언제든 값을 바꿀수 있기때문에 사용하지 않음
	//name값만 알고있을때 browser객체 생성
	public BrowserData(String name) {
		super();
		this.name = name;
	}
	
	//둘다알때
	public BrowserData(String name, double version) {
		super();
		this.name = name;
		this.version = version;
	}
	
	//getter
	public String getName() {
		return name;
	}
	
	public double getVersion() {
		return version;
	}

	
	
}
