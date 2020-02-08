package kr.or.ddit.enumpkg;

public enum LeftContent {
	STANDARD(new LeftContentData("스탠다드","/03/standard.jsp")),
	GUGUDAN(new LeftContentData("","/03/gugudan.jsp")),
	IMAGESTREAMING(new LeftContentData("","/03/gugudan.jsp")),
	VIDEOSTREAMING(new LeftContentData("","/03/gugudan.jsp")),
	CALENDAR(new LeftContentData("","/03/gugudan.jsp")),
	SESSIONTIMER(new LeftContentData("","/03/gugudan.jsp"));
	
	private LeftContentData data;
	public LeftContentData getData() {
		return data;
	}
	private LeftContent(LeftContentData data) {
		this.data = data;
	}
	public static LeftContent findInfo(String command) {  
		LeftContent result = SESSIONTIMER;//우선 할당
		LeftContent[] types = values(); //values() - 모든상수를 배열로 가져옴(firefox,chrome,...)
		for(LeftContent type : types) {
			if(command.contains(type.name())) {
				result = type;
				break;
			}
		}
		return result;
	}

}

