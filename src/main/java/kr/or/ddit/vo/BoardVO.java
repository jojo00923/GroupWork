package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.validator.rules.constraints.NotBlank;
import kr.or.ddit.validator.rules.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="bo_no")
@Alias("boardVO")
public class BoardVO implements Serializable{
	
	
	private int rn;
	@NotNull(message = "글번호 필수", groups = UpdateHint.class)
	private Integer bo_no;
	@NotBlank(message = "제목 필수")
	private String bo_title;
	@NotBlank(message = "작성자 필수")
	private String bo_writer;
	@NotBlank(message = "비번 필수")
	private transient String bo_pass;
	private String bo_content;
	private String bo_date;
	private Integer bo_hit;
	private Integer bo_like;
	@NotBlank(message = "아이피 필수")
	private String bo_ip;
	private String bo_mail;
	private boolean recommended;
     
	private List<PartWrapper> bo_file; //attatchList와 한쌍
	
	
	public void setBo_file(List<PartWrapper> bo_file) { //메타데이터처리
		this.bo_file = bo_file;
		attatchList = new ArrayList<>();
		if(bo_file!=null && bo_file.size()>0) {                         
			for(PartWrapper tmp:bo_file) {
				//1) 2진
				//2) 메타
				AttatchVO attatchVO = new AttatchVO(tmp); //메타데이터 세팅
				attatchList.add(attatchVO);
			}
		}
	}
	
	// 1:N -> has many
	private List<AttatchVO> attatchList;
	private int startAttNo;
	private List<ReplyVO> replyList;
	
	private int[] delAttNos; //여러개의 첨부파일 삭제 대비 
	
	private int rowcnt;
	
}