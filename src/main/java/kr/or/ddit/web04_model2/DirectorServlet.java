package kr.or.ddit.web04_model2;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import kr.or.ddit.vo.DirectorVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.DirectorInfo;

@WebServlet("/directors.do")
public class DirectorServlet extends HttpServlet{
	@Override //라이프사이클콜백
	public void init() throws ServletException {//한 번 실행
		super.init();
		Set<DirectorVO> dataSet = new LinkedHashSet<DirectorVO>();
		dataSet.add(new DirectorVO("DIRECTOR01","봉준호","봉보로봉봉","/WEB-INF/views/directors/bongjunho.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR02","박찬욱",null,"/WEB-INF/views/directors/parkchanuk.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR03","김지운",null,"/WEB-INF/views/directors/kimjiun.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR04","최동훈",null,"/WEB-INF/views/directors/choidonghun.jsp"));
		dataSet.add(new DirectorVO("DIRECTOR05","장준환",null,"/WEB-INF/views/directors/jangjunhwan.jsp"));
		//중복되지 않으면 true가 반환됨
		getServletContext().setAttribute("dataSet", dataSet);//어디서든쓸수있다.
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/directors/directorsForm.jsp").forward(req,resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String director = req.getParameter("director");
		
		if(StringUtils.isBlank(director)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;//메서드를 빠져나간다.
		}
		
		//if(director ==null || director.trim().length()==0) {
		//	resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		//	return;//메서드를 빠져나간다.
		//}
		
		Set<DirectorVO> dataSet = (Set<DirectorVO>) getServletContext().getAttribute("dataSet");
		DirectorVO paramVO = new DirectorVO(director,null,null,null);
		boolean searched = !dataSet.add(paramVO); //true false가 반환
		System.out.println(searched);
		
		if(!searched) {//못 찾았을 때
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}//상태코드 변수로 만들어서 따로 메서드로 빼는 방법도 있음
		
		DirectorVO searchedVO = null;
		for(DirectorVO tmp:dataSet) {//tmp와 paramVO비교
			if(tmp.equals(paramVO)) {//찾았을 때
				searchedVO = tmp;
				break;
			}
		}
		System.out.println(searchedVO.toString()); //상태확인
		String bPage = searchedVO.getContentPath();//web-inf아래
		
		//DirectorInfo dir = DirectorInfo.findInfo(director);
		//String path = dir.getData().getPath();
		
		//req.getRequestDispatcher(director).forward(req, resp);
		//req.getRequestDispatcher(path).forward(req, resp);
		
		//별명출력위해 session이용해서 jsp에 전달
		req.setAttribute("searchedVO", searchedVO);
		req.getRequestDispatcher(bPage).forward(req, resp);
		
	}
}
