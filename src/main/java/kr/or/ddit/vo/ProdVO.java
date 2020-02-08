package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.or.ddit.hint.InsertHint;
import kr.or.ddit.hint.UpdateHint;
import kr.or.ddit.validator.rules.constraints.NotBlank;
import kr.or.ddit.validator.rules.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data //아래 어노테이션을 한번에
@Getter //lombok 프레임웍 , 플러그인 설치 ***
@Setter
@EqualsAndHashCode(of = {"prod_id", "prod_name"}) //상태비교할 property명 나열
@ToString(exclude = {"prod_detail"} ) //제외할 property나열
@NoArgsConstructor //기본생성자 생성
@AllArgsConstructor //모든property를 포함한 생성자
@Builder
@Alias("prodVO")
public class ProdVO implements Serializable{//4.직렬화
	//1.property생성
	//2.getter , setter
	//3.equals, tostring
	
	@NotBlank(message = "상품아이디 필수", groups = UpdateHint.class)
	private String prod_id;
	@NotBlank(message="상품명 필수")
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	private String lprod_nm;
	@NotBlank
	private String prod_buyer;
	private String buyer_name;
	// has a 관계 (1:1)
	private BuyerVO buyer;
	@NotNull
	private Integer prod_cost;
	@NotNull
	private Integer prod_price;
	@NotNull
	private Integer prod_sale;
	@NotBlank
	private String prod_outline;
	private String prod_detail;
	@NotBlank(groups = InsertHint.class)
	private String prod_img;
	@NotNull
	private Integer prod_totalstock;
	private String prod_insdate;
	@NotNull
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	private List<MemberVO> memberList;
	//mybatis는 property명과 컬럼명이 동일한 상태이거나  아래의 상황인 경우 매핑해준다.
	//configuration에서 <settings><setting name="mapUnderscoreTo~~" value="true"/>
	//내부적으로 reflection시 _를 대문자로 바꿔준다. ex) buyerName 
	//현재는 여기서 언더바를 사용했기 때문에 적용되지 않음! 
	
	
}
