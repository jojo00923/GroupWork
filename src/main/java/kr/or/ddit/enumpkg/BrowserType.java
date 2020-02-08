package kr.or.ddit.enumpkg;


public enum BrowserType {
	FIREFOX(new BrowserData("파이어폭스")), 
	CHROME(new BrowserData("크롬",3.2)), 
	TRIDENT(new BrowserData("IE")), 
	OTHERS(new BrowserData("기타등등"));
	
	
	private BrowserData data;//전역변수
	public BrowserData getData() {
		return data;
	}
	private BrowserType(BrowserData data) {//생성자
		this.data = data;
	}

	public static BrowserType findBrowser(String userAgent) {  
		//상수가져오는것을 enum에게 떠넘김
		BrowserType result = OTHERS;//우선 할당
		//enum은 자신의 이름과 같은 name값을 가진다.
		BrowserType[] types = values(); //values() - 모든상수를 배열로 가져옴(firefox,chrome,...)
		for(BrowserType type : types) {
			if(userAgent.toUpperCase().contains(type.name())) {
					//찾았을 때
				result = type;
				break;
			}
			
		}
		
		return result;
	}
}
