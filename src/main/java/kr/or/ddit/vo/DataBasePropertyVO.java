package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * javabean규약(VO,DTO(데이터담을목적),Bean,Model)
 * 1. 값을 가질 수 있는 property 정의
 * 2. 적절한 캡슐화 
 * 3. 인터페이스 메서드 정의(getter/setter)
 * 4. 직렬화 가능하도록 선언
 * 5. 상태 비교 방법 제공(equals)
 * 6. 상태 확인 방법 제공(toString)
 */
public class DataBasePropertyVO implements Serializable{//하나의 레코드 대상
	private String property_name;
	private String property_value;
	private String description;
	
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public String getProperty_value() {
		return property_value;
	}
	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property_name == null) ? 0 : property_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBasePropertyVO other = (DataBasePropertyVO) obj;
		if (property_name == null) {
			if (other.property_name != null)
				return false;
		} else if (!property_name.equals(other.property_name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DataBasePropertyVO [property_name=" + property_name + ", property_value=" + property_value
				+ ", description=" + description + "]";
	}
	
	
	
}
