package kr.or.ddit.mvc.annotation;

/**
 * @URIMapping 어노테이션의 정보를 읽어서 캡슐화하기 위한 객체.
 *
 */
public class URIMappingCondition {
	private String uri;
	private HttpMethod method;
	
	public URIMappingCondition(String uri, HttpMethod method) {
		super();
		this.uri = uri;
		this.method = method;
	}

	
	public String getUri() {
		return uri;
	}
	public HttpMethod getMethod() {
		return method;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		URIMappingCondition other = (URIMappingCondition) obj;
		if (method != other.method)
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return String.format("[uri:%s, method:%s]", uri, method);
	}
	
	
	
}
