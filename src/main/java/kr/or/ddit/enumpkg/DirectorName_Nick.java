package kr.or.ddit.enumpkg;

public class DirectorName_Nick {
	private String name;
	private String nick;
	private String path;
	
	public DirectorName_Nick(String name,String path) {
		super();
		this.name = name;
		this.path = path;
	}

	public DirectorName_Nick(String name,String nick, String path) {
		super();
		this.name = name;
		this.nick = nick;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getNick() {
		return nick;
	}
	public String getPath() {
		return path;
	}

	
	
	
}
