package kr.or.ddit.vo;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;

import kr.or.ddit.file.PartWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("licAlbaVO")
@Data
@NoArgsConstructor
public class LicAlbaVO implements Serializable{

	private PartWrapper part;
	private String al_id;
	private String lic_code;
	private String lic_name;
	private String lic_image;
	
}
