package kr.or.ddit.alba.service;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicAlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface IAlbaService {
	
	public int readAlbaCount(PagingVO<AlbaVO> pagingVO);
	public List<AlbaVO> readAlbaList(PagingVO<AlbaVO> pagingVO);
	public AlbaVO readAlba(String al_id);
	public ServiceResult modifyAlba(AlbaVO alba);
	public ServiceResult createAlba(AlbaVO alba);
	public ServiceResult removeAlba(AlbaVO alba);
	public HashMap<String, Object> readImage(LicAlbaVO licAlba);
	public LicAlbaVO retrieveLicense(LicAlbaVO licAlba);

}
