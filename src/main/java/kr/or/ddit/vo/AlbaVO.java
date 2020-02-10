package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.Data;

@Alias("albaVO")
@Data
public class AlbaVO implements Serializable{

	private int rnum;
	//@NotBlank(message = "회원 아이디 필수", groups = UpdateHint.class)
	private String al_id;
	//@NotBlank(message = "이름 필수", groups = InsertHint.class)
	private String al_name;
	private Integer al_age;
	//@NotBlank(message = "주소 필수")
	private String al_address;
	//@NotBlank(message = "핸드폰번호 필수")
	private String al_hp;
	private String al_spec;
	private String al_desc;
	//@NotBlank(message = "학력 필수")
	private String gr_code;
	private String gr_name;
	private String al_career;
	//@NotBlank(message = "성별 필수", groups = InsertHint.class)
	private String al_gen;
	//@NotBlank(message = "혈액형 필수", groups = InsertHint.class)
	private String al_btype;
	//@NotBlank(message = "이메일 필수")
	private String al_mail;
	
	private List<LicAlbaVO> licAlbaList;
	
	private LicAlbaVO licAlba;
	private String lic_code;
	
	private int rowcnt;
	
	private String[] licCodes;
	
	private String[] deleteLicCodes;
	
}












