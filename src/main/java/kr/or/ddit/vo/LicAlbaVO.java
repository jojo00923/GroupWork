package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
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
//	private String lic_image;
	
	private byte[] lic_image;
	private PartWrapper lic_img;
	
	public void setLic_img(PartWrapper lic_img) throws IOException {
		this.lic_img = lic_img;
		if(lic_img!=null) 
			lic_image = lic_img.getBytes();
	}
	
	public String getImgBase64() {
		if(lic_image==null) return null;
		return Base64.encodeBase64String(lic_image);
	}
	
}
