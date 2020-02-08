package kr.or.ddit.web06_model2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.web06_model2.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.web06_model2.service.DataBasePropertyServiceImpl;
import kr.or.ddit.web06_model2.service.IDataBasePropertyService;
@WebServlet("/09/jdbcDesc.do")
public class DataBasePropertiesController extends HttpServlet{
	//서비스를 의존할 객체생성
	IDataBasePropertyService service = new DataBasePropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		service.readDataBasePropertyList(model);
		req.setAttribute("model", model);
		
		String view = "/WEB-INF/views/jdbcDesc.jsp";
		req.getRequestDispatcher(view)
			.forward(req, resp);
	}
}
