package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.Alias;

import kr.or.ddit.file.PartWrapper;
import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.validator.rules.constraints.Length;
import kr.or.ddit.validator.rules.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원관리 Domain Layer (VO, DTO, Bean, Model)
 * 
 * mybatis를 이용해서 여러 테이블을 조회하는 단계 (ORM)
 * ex) 한 명의 회원정보에 구매기록을 포함(MEMBER:prod = 1:N, MemberVO has many ProdVO)
 * ex) 한 건의 상품 정보에 구매자 목록을 포함(PROD:member = 1:N, ProdVO has many MemberVO)
 * ex) 한 건의 상품 정보에 거래처 정보를 포함.(PROD:buyer = 1:1, ProdVO has a BuyerVO)
 * 
 * 1. 문제 해결에 필요한 테이블을 선택
 * 2. 주데이터를 갖고있는 메인테이블 결정
 * 3. 테이블간의 관계성 파악 - 1:N , 1:1
 * 4. 테이블간의 관계를 객체에 반영
 * 		1:1 - has a 
 * 		1:N - has many
 * 5. resultType(자동설정이안됨) -> resultMap 사용
 * 		has a - association으로 바인드
 * 		has many - collection으로 바인드
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"mem_id", "mem_regno1", "mem_regno2"})
@ToString
@AllArgsConstructor
@Builder
@Alias("memberVO")
public class MemberVO implements Serializable{
	
	public MemberVO() {
		super();
	}
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}

	private int rn;
	@NotBlank(message="회원 아이디 필수") //신규로 등록할때만 검증
	private String mem_id;
	@NotBlank(message="패스워드 필수") 
	@Length(min=4, max=8, message="비번 4~8자리")
	private String mem_pass;
	@NotBlank(message="이름 필수", groups = InsertHint.class) 
	private String mem_name;
	private transient String mem_regno1;
	private transient String mem_regno2;
	private String mem_bir;
	@NotBlank(message="우편번호 필수") 
	private String mem_zip;
	@NotBlank(message="주소 필수") 
	private String mem_add1;
	@NotBlank(message="상세주소 필수") 
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	@NotBlank(message="이메일 필수") 
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
	//has many관계(1:N)
	private List<ProdVO> prodList;//구매내역 조회할 목적의 변수

	private byte[] mem_img; //db와의 연동을 위해
	private PartWrapper mem_image;//client와의 연동을 위해
	
	public void setMem_image(PartWrapper mem_image) throws IOException {
		this.mem_image = mem_image;
		if(mem_image!=null) 
		mem_img = mem_image.getBytes();
	}
	
	public String getImgBase64() {//바이트배열 인코딩해서 문자열로 만들기
		//라이브러리 가져오기
		if(mem_img==null) return null;
		return Base64.encodeBase64String(mem_img);
	}
	
	public String getTest() {
		return "테스트";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
