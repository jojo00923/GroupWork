<%@page import="kr.or.ddit.enumpkg.LeftContent"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		String param = request.getParameter("command");
		if(StringUtils.isBlank(param)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	LeftContent info = LeftContent.findInfo(param);
	String path = info.getData().getContentpath();
	%>
