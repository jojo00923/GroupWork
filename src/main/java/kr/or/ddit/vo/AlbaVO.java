package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;

@Alias("albaVO")
@Data
public class AlbaVO implements Serializable{

	private int rnum;
	//@NotBlank(message = "회원 아이디 필수", groups = InsertHint.class)
	private String al_id;
	@NotBlank(message = "이름 필수")
	private String al_name;
	private Integer al_age;
	private String al_address;
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String gr_code;
	private String gr_name;
	private String al_career;
	private String al_gen;
	private String al_btype;
	private String al_mail;
	private List<LicAlbaVO> licAlbaList;
	
	private LicAlbaVO licAlba;
	
}
