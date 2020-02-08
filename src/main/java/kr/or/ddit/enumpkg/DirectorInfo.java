package kr.or.ddit.enumpkg;

public enum DirectorInfo {
	DIRECTOR01(new DirectorName_Nick("봉준호","봉보로봉","/WEB-INF/views/directors/bongjunho.jsp")),
	DIRECTOR02(new DirectorName_Nick("박찬욱","/WEB-INF/views/directors/parkchanuk.jsp")),
	DIRECTOR03(new DirectorName_Nick("김지운","/WEB-INF/views/directors/kimjiun.jsp")),
	DIRECTOR04(new DirectorName_Nick("최동훈","/WEB-INF/views/directors/choidonghun.jsp")),
	DIRECTOR05(new DirectorName_Nick("장준환","/WEB-INF/views/directors/jangjunhwan.jsp"));
	
	
	private DirectorInfo(DirectorName_Nick nn) {
		this.nn = nn;
	}
	private DirectorName_Nick nn;
	public DirectorName_Nick getData() {
		return nn;
	}

	public static DirectorInfo findInfo(String director) {  
		DirectorInfo result = DIRECTOR01;//우선 할당
		DirectorInfo[] types = values(); //values() - 모든상수를 배열로 가져옴(firefox,chrome,...)
		for(DirectorInfo type : types) {
			if(director.toUpperCase().contains(type.name())) {
					//찾았을 때
				result = type;
				break;
			}
			
		}
		
		return result;
	}

	
	
}
