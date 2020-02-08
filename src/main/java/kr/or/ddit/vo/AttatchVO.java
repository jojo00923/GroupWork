package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import kr.or.ddit.file.PartWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="att_no")
public class AttatchVO implements Serializable{
	public AttatchVO(PartWrapper part){
		this.part = part; //attatchVo와 1:1구조
		att_filename = part.getFilename();
		att_savename = part.getSavename();
		att_size = part.getFilesize();
		att_fancy = part.getFancySize();
		att_mime = part.getMime();
	}
	
	private PartWrapper part;
	private Integer att_no;
	private Integer bo_no;
	private String att_savename;
	private String att_filename;
	private Long att_size;
	private String att_fancy;
	private String att_mime;
	
	public void saveFile(File saveFolder) throws IOException {
		part.saveFile(saveFolder);
	}
}
