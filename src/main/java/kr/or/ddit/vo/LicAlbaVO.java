package kr.or.ddit.vo;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Alias("licAlbaVO")
@Data
public class LicAlbaVO implements Serializable{

	private String al_id;
	private String lic_code;
	private String lic_name;
	private String lic_image;
	
}
