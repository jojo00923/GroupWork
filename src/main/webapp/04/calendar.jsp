<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>calendar.jsp</title>
<style>
	td,th{
		border:1px solid black;
	}
	table{
		border-collapse : collapse;
		width:100%;
		height:500px;
	}
</style>
<script type="text/javascript">

	function clickHandler(year,month){ //form태그로 인해서만 요청이 발생하도록. input태그들의 값 변경하기.
		console.log(document.calendarForm);
		var form = document.calendarForm;
		console.log(form.year);
		console.log(form.month);
		form.year.value = year;
		form.month.value = month;
		form.submit();
	
	}
</script>
</head>
<%
	//static임포트구문사용(버전1.7이후에서 사용가능)

	//파라미터 먼저 꺼내기
	String yearParam = request.getParameter("year");
	String monthParam = request.getParameter("month");
	String language = request.getParameter("language"); //언어코드반환
	
	
	Calendar cal = getInstance(); //기본생성자가 없다.

	
	if(yearParam!=null && yearParam.matches("\\d{4}") &&
	monthParam!=null && monthParam.matches("\\d{1,2}")){//null이아니고 숫자일때
		cal.set(YEAR,Integer.parseInt(yearParam));//특정년도,월의 캘린더로 세팅하기위해 
		cal.set(MONTH,Integer.parseInt(monthParam));
	}
	
	//마지막날 꺼내기
	int maxDay = cal.getActualMaximum(DAY_OF_MONTH);//DAY_OF_MONTH - 현재 월의 날짜
	
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);//0부터 시작
	cal.set(DAY_OF_MONTH, 1);	//offset구하기 위해 현재 월의 1일로 세팅해놓기	
	int offset = cal.get(DAY_OF_WEEK)-1; //dayofweek - 요일코드.
	//1월의 경우 1일이 수요일(code:4) offset = 3 
	//요일코드 1~7인데 
	//offset을 위해서는 sunday는 0부터 시작해야하므로 -1
	
	//이전달
	cal.add(MONTH, -1);
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	
	//다음달
	cal.add(MONTH, 2); //위에서 이미 지난달로 가있는상태라서 +2
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	
	Locale locale = request.getLocale();//client의 기본 locale객체을 잡는다.
	if(language!=null && language.trim().length()>0){
		locale = Locale.forLanguageTag(language);//언어코드에 맞는 locale객체 뽑기
	}
	DateFormatSymbols dfs = new DateFormatSymbols(locale);
	String[] weekdays = dfs.getShortWeekdays(); //일요일이 1 , 언어에맞는 '월~일'뽑아오기 
	String[] months = dfs.getMonths();//언어에맞는 '월'출력
%>
<h4>
<a href="#" onclick="clickHandler(<%=beforeYear%>,<%=beforeMonth%>);">이전달</a> 
<!-- href 클라이언트사이드절대경로 주소생략시 현재폼의주소  #:해시링크(아무것도 호출x)-->
===========
<%=year %>년 <%=month+1 %>월
===========
<a href="#" onclick="clickHandler(<%=nextYear%>,<%=nextMonth%>);">다음달</a>
</h4>
<form name="calendarForm">
	<input type="hidden" name="command" value="CALENDAR"/> <!-- hidden :전송용 -->
	<input type="number" name="year" value="<%=year%>"/>년
	<select name="month">
	<%
		for(int i=0; i<months.length-1; i++){//열두달 뽑아옴
	%>
		<option value="<%=i %>" <%=i==month?"selected":"" %>><%=months[i] %></option>
	<%			
		}
	%>
	</select>
	<select name="language">
		<%
			Locale[] locales = Locale.getAvailableLocales();//모든지역의 정보가 다 들어있음
			for(Locale tmp : locales){
				String text = tmp.getDisplayLanguage(tmp);
				if(text==null || text.trim().length()==0) continue;
				String code = tmp.toLanguageTag();
				String selected = tmp.equals(locale)?"selected":"";//선택한언어유지
		%>
		<option value="<%=code %>" <%=selected %>><%=text %></option>
		<%
			}
		%>
	</select>
	<input type="submit" value="달력"/>
</form>
	<table>
		<thead>
			<tr>
				<%
					for(int col=1;col<=7;col++){
						%>
						<th><%=weekdays[col] %></th>	
						<%
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
				int count =1; //1씩 증가하는 변수
				String ptrn ="<td>%s</td>";
				for(int row=1;row<=6;row++){
					out.println("<tr>");
					for(int col=1;col<=7;col++){
						int dayCount = count++ - offset;
						String display = dayCount>0 && dayCount<=maxDay?dayCount+"":"&nbsp;"; //음수,0 없애기
						
						out.println(String.format(ptrn, display));
					//오프셋이란, 두 번째 주소를 만들기 위해 기준이 되는 주소에 더해진 값을 의미한다.
					//offset설정 필요 . 1일의 위치에 따라 offset 달라짐. date(가능하면쓰지않는다.), calendar객체 사용
					
					}
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
