package kr.or.ddit.vo;

import java.io.Serializable;

public class LeftContentVO implements Serializable{
	private String command;
	private String contentPath;
	public LeftContentVO(String command, String contentPath) {
		super();
		this.command = command;
		this.contentPath = contentPath;
	}
	public LeftContentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	
	@Override
	public String toString() {
		return "LeftContentVO [command=" + command + ", contentPath=" + contentPath + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
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
		LeftContentVO other = (LeftContentVO) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		return true;
	}
	
	
}
