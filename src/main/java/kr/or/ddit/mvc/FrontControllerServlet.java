package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

/**
 * 현재 어플리케이션의 유일한 서블릿.
 * 모든 요청은 FrontControllerServlet을 통해 접수.
 *
 */
//템플릿클래스
public class FrontControllerServlet extends HttpServlet{
	//콘솔대신 로깅 사용(부하가 적다.) 
	//(slf4j - 리모콘 역할)
	private static Logger logger = LoggerFactory.getLogger(FrontControllerServlet.class);
	//계층구조에 따라 로거등급 따라감
	
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	//객체생성을 위해 라이프사이클콜백 생성
	@Override
	public void init(ServletConfig config) throws ServletException {
			
		super.init(config);
		String[] basePackages = null;
		String pkgs = config.getInitParameter("basePackages");
		if(pkgs!=null) {
			basePackages = pkgs.split("\\s+"); //web.xml - paramvalue의 공백이 한 칸 이상
		}
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		handlerMapper = new HandlerMapper(basePackages);
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcessor();
		if(prefix!=null)
			viewProcessor.setPrefix(prefix);
		if(suffix!=null)
			viewProcessor.setSuffix(suffix);
	}
	
	
	@Override //템플릿메서드
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doget, dopost등 모든 요청을 받기 위해서
		
		URIMappingInfo mappingInfo = handlerMapper.findCommandHandler(req);
		if(mappingInfo==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		String viewName = handlerInvoker.invokeHandler(mappingInfo, req, resp);
		if(viewName==null) {
			if(!resp.isCommitted()) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return;
		}
		viewProcessor.viewProcess(viewName, req, resp);
	}
}
