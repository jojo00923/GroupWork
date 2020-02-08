package kr.or.ddit.vo;

import java.io.Serializable;
//1.속성설정
//2.
//3.
public class DirectorVO implements Serializable{//4.객체직렬화
	//생성자 (기본생성자도 필수)
	public DirectorVO(String code, String name, String nickname, String contentPath) {
		super();
		this.code = code;
		this.name = name;
		this.nickname = nickname;
		this.contentPath = contentPath;
	}
	
	public DirectorVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String code;//pk역할
	private String name;
	private String nickname;
	private String contentPath;

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getContentPath() {
		return contentPath;
	}
	
	
	@Override
	public String toString() {//5.객체상태 확인
		return "DirectorVO [code=" + code + ", name=" + name + ", nickname=" + nickname + ", contentPath=" + contentPath
				+ "]";
	}

	@Override
	public int hashCode() {//같은객체인지 판단
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	// 참고로 eclipse(IDE)에서는 equals() 메서드를 generate 시켜주는 기능을 가지고 있는데 자연스럽게 hashCode()도 함께 generate 시켜준다. 즉, equals()와 hashCode()를 같이 재정의하게 한다는 것이다.

	//중복여부판단
	//유일성을 가진 것을 기준으로 
	@Override
	public boolean equals(Object obj) {//equals가 없으면 주소만 비교해서(객체생성 할 때마다 다름) 중복을 가릴 수 없다.
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectorVO other = (DirectorVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code)) //코드가 pk역할 이니까 코드가 같으면 같은 객체로 판단
			return false;
		return true;
	}

	
}
