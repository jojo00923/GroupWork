package kr.or.ddit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("gradeVO")
@Data
public class GradeVO {
	private String al_id;
	private String gr_code;
	private String gr_name;
}
