<%@page import="kr.or.ddit.vo.DirectorVO"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
<%
	//request scope안에서 꺼내기
	DirectorVO searchedVO = (DirectorVO) request.getAttribute("searchedVO");
%>
<h4><%=Objects.toString(searchedVO) %></h4>
<div class="people_info section _people_info people_spc"
	id="people_info_z" data-dss-logarea="x29">
	<div class="section_head">
		<h2>인물 정보</h2>
	</div>
	<div class="cont_noline" style="z-index: 10">
		<div class="profile_wrap">
			<div class="big_thumb">
				<a nocr=""
					onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.image&amp;i='+'1800000D_000000017402');"
					href="https://search.naver.com/search.naver?where=image&amp;query=%EB%B4%89%EC%A4%80%ED%98%B8+%EA%B0%90%EB%8F%85&amp;merge=0&amp;res_fr=0&amp;res_to=0&amp;sort=0&amp;viewtype=0&amp;site=&amp;face=1&amp;color=0&amp;mcs=0&amp;a_q=&amp;n_q=&amp;o_q=&amp;sm=tab_etc"><img
					src="https://search.pstatic.net/common?type=a&amp;size=120x150&amp;quality=95&amp;direct=true&amp;src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2F189%2F201710111116464471.jpg"
					alt="영화감독 봉준호 이미지" width="120" height="150"
					onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"></a>
				<a nocr=""
					onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.edit&amp;i='+'1800000D_000000017402');"
					class="btn_profile_update" href="http://myprofile.naver.com"
					target="_blank"><span class="blind">영화감독 봉준호</span>내 인물정보 수정</a>
			</div>
			<dl class="detail_profile">
				<dd class="name">
					<a nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.namepeople&amp;i='+'1800000D_000000017402');"
						href="https://people.search.naver.com/search.naver?where=nexearch&amp;query=%EB%B4%89%EC%A4%80%ED%98%B8&amp;sm=tab_etc&amp;ie=utf8&amp;key=PeopleService&amp;os=95234"
						target="_blank"><strong>봉준호</strong></a> <span>영화감독</span>
				</dd>
				<dt>출생</dt>
				<dd>
					<span> 1969년 9월 14일, 대구광역시</span>
				</dd>
				<dt>가족</dt>
				<dd>
					아버지 <a nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.family&amp;i='+'1800000D_000000017402');"
						href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjky&amp;pkid=1&amp;os=563520&amp;query=%EB%B4%89%EC%83%81%EA%B7%A0">
						봉상균</a>, 외할아버지 <a nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.family&amp;i='+'1800000D_000000017402');"
						href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjky&amp;pkid=1&amp;os=104119&amp;query=%EC%86%8C%EC%84%A4%EA%B0%80%20%EB%B0%95%ED%83%9C%EC%9B%90">
						박태원</a>, 형 봉준수, 누나 <a nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.family&amp;i='+'1800000D_000000017402');"
						href="?where=nexearch&amp;sm=tab_etc&amp;mra=bjky&amp;pkid=1&amp;os=563521&amp;query=%EB%B4%89%EC%A7%80%ED%9D%AC">
						봉지희</a>
				</dd>
				<dt>학력</dt>
				<dd>
					<a nocr=""
						onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.school&amp;i='+'1800000D_000000017402');"
						href="?where=nexearch&amp;sm=tab_etc&amp;query=%EC%97%B0%EC%84%B8%EB%8C%80%ED%95%99%EA%B5%90">연세대학교</a>
					사회학 학사
				</dd>
				<dt>데뷔</dt>
				<dd>
					<span>1994년 영화 '백색인'</span>
				</dd>
				<dt>수상</dt>
				<dd>
					2020년 제77회 골든 글로브 시상식 외국어 영화상 (기생충) <br> 2020년 제54회 전미비평가협회
					시상식 작품상 (기생충)
				</dd>
				<dt>경력</dt>
				<dd>2013.12 해양경찰 명예홍보대사</dd>
			</dl>
		</div>
	</div>
	<div class="go_relate">
		<div class="offer_more2">
			<div class="confirm">
				<em>본인참여</em> 2017.10.11. <a nocr="" href="javascript:;"
					id="more_div_btn"><img
					src="https://ssl.pstatic.net/sstatic/search/img/ico_help.gif"
					width="12" height="12" alt="네이버 인물정보 도움말"></a>
			</div>
			<div style="left: -65px; width: 286px; visibility: hidden;"
				class="layer_tooltip_wrap" id="tooltip_layer">
				<div class="layer_tooltip2">
					<div class="content">
						<p>
							네이버 인물검색은 당사자 본인이 제공한 자료 또는 언론 보도 등 일반에 공개된 자료를 바탕으로 인물정보를 제공합니다.<br>인물정보의
							당사자 본인은 직접 또는 대리인을 통해 언제든지 네이버에 수정 또는 삭제를 신청하실 수 있으며 본인 또는 그 대리인의
							마지막 수정요청이 처리된 날짜가 본인참여 일자로 표시·제공됩니다.<br>네이버는 인물 본인이 제공하거나
							일반에 공개된 자료를 매개 서비스하는 제공자로서 해당 정보의 정확성, 신뢰성 또는 최신성을 보증하지 않습니다.<br>
							<br>
						</p>
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.imaginfmy&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							href="https://myprofile.naver.com/Main.nhn" class="request"
							target="_blank">인물정보 수정신청</a>, <a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.imaginfcs&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							href="https://help.naver.com/support/alias/search/people/people_18.naver"
							class="helpdesk" target="_blank">네이버 고객센터</a> <a nocr=""
							onclick="fnLayerClose();return false;" href="javascript:;"
							class="closelayer C"><img
							src="https://ssl.pstatic.net/sstatic/search/images11/btn_layer_close.gif"
							width="10" height="9" alt="네이버 인물정보 도움말 닫기"></a> <span
							class="arw detail C"></span>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$Fn(fnLayerOpen).attach($('more_div_btn'), "click");
			function fnLayerOpen(e) {
				$('tooltip_layer').style.visibility = "visible";
				goOtherTCR(this,
						'a=nco_x29*3.imaginf&r=1&i=1800000D_000000017402');
			}
			function fnLayerClose(e) {
				$('tooltip_layer').style.visibility = "hidden";
				$('more_div_btn').focus();
				goOtherTCR(this,
						'a=nco_x29*3.imaginfclose&r=1&i=1800000D_000000017402');
			}
		</script>
		<a nocr=""
			onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.profiledetail&amp;i='+'1800000D_000000017402');"
			href="https://people.search.naver.com/search.naver?where=nexearch&amp;query=%EB%B4%89%EC%A4%80%ED%98%B8&amp;sm=tab_etc&amp;ie=utf8&amp;key=PeopleService&amp;os=95234"
			class="btn_txt_more" target="_blank"><span class="blind">영화감독
				봉준호</span>인물정보 더보기</a>
	</div>
	<a nocr=""
		onclick="return goOtherCR(this, 'a=nco_x29*3.svcbanner&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
		href="https://search.naver.com/search.naver?where=news&amp;sm=tab_jum&amp;query=%EA%B3%A8%EB%93%A0%EA%B8%80%EB%A1%9C%EB%B8%8C+%EC%99%B8%EA%B5%AD%EC%96%B4%EC%98%81%ED%99%94%EC%83%81+%EC%88%98%EC%83%81"
		class="banner_link" style="background-color: #e4c73c"> <img
		src="https://ssl.pstatic.net/sstatic/keypage/outside/scui/people/img/banner_200103.png"
		width="590" alt="골든글로브 수상">
	</a>
	<script type="text/javascript">
		var __sDefaultPeopleTab = "네이버TV";
	</script>
	<div class="people_type" id="tx_ca_people_workall">
		<div class="texttab_type">
			<ul>
				<li id="tx_ca_people_tvcast_tab" class="select"><a nocr="1"
					href="#" class="pg_btn">네이버TV</a></li>
				<li id="tx_ca_people_movie_tab"><a nocr="1" href="#"
					class="pg_btn">영화</a></li>
				<li id="tx_ca_people_book_tab"><a nocr="1" href="#"
					class="pg_btn">도서</a></li>
			</ul>
		</div>
		<div class="lst_cont tvcast " style="display: block;">
			<h3 class="blind">네이버TV</h3>
			<ul>
				<li>
					<div class="thumb_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11807387"> <img
							src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20200108_218%2FlORON_1578488810751lWJmW_JPEG%2F696d0739-7e8f-4a73-89b0-3dcd643def28.jpg"
							alt="작품성을 넘어 대중성까지 장악한 영화 ‘기생충’" width="128" height="72"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""> <span class="ico_play"></span> <span
							class="runtime"><span class="bg">재생시간</span><em>2분
									27초</em></span> <span class="img_bd"></span>
						</a>
					</div>
					<div class="txt_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11807387"
							class="cast_txt">작품성을 넘어 대중성까지 장악한 영화 ‘기...</a> <span
							class="day_txt">19시간 전</span>
					</div>
				</li>
				<li>
					<div class="thumb_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11074316"> <img
							src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20191121_256%2FMITh0_1574346997135QMDy9_JPEG%2FVD65947562_w1280.jpg"
							alt="‘기생충’ 최우수작품상 수상하며 청룡영화상 피날레 장식" width="128" height="72"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""> <span class="ico_play"></span> <span
							class="runtime"><span class="bg">재생시간</span><em>4분
									09초</em></span> <span class="img_bd"></span>
						</a>
					</div>
					<div class="txt_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11074316"
							class="cast_txt">‘기생충’ 최우수작품상 수상하며 청룡영화상...</a> <span
							class="day_txt">2019.11.21.</span>
					</div>
				</li>
				<li>
					<div class="thumb_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11074067"> <img
							src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20191121_135%2FZdzP5_1574345499756me1l0_JPEG%2FVD50985242_w1280.jpg"
							alt="“영원히 기생하는 창작자 될 것!” 봉준호 감독의 센스 있는 수상 소감!" width="128"
							height="72"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""> <span class="ico_play"></span> <span
							class="runtime"><span class="bg">재생시간</span><em>3분
									20초</em></span> <span class="img_bd"></span>
						</a>
					</div>
					<div class="txt_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/11074067"
							class="cast_txt">“영원히 기생하는 창작자 될 것!” 봉준호...</a> <span
							class="day_txt">2019.11.21.</span>
					</div>
				</li>
				<li>
					<div class="thumb_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvcimg&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/9258711"> <img
							src="https://search.pstatic.net/common?type=ofullfill_black&amp;size=128x72&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fphinf.pstatic.net%2Ftvcast%2F20190723_193%2FOGdDe_1563884830484zRmgy_JPEG%2FVD38077879_w1280.jpg"
							alt="별들의 잔치 ‘춘사영화제’ 수상자는 누구?" width="128" height="72"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""> <span class="ico_play"></span> <span
							class="runtime"><span class="bg">재생시간</span><em>2분
									45초</em></span> <span class="img_bd"></span>
						</a>
					</div>
					<div class="txt_box">
						<a nocr=""
							onclick="return goOtherCR(this, 'a=nco_x29*3.tvctit&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
							target="_blank" href="https://tv.naver.com/v/9258711"
							class="cast_txt">별들의 잔치 ‘춘사영화제’ 수상자는 누구?</a> <span
							class="day_txt">2019.07.23.</span>
					</div>
				</li>
			</ul>
			<a nocr=""
				onclick="return goOtherCR(this, 'a=nco_x29*3.tvcmore&amp;r=1&amp;i=1800000D_000000017402&amp;u=' + urlencode(this.href));"
				target="_blank" class="more_view"
				href="http://tv.naver.com/search/clip?query=봉준호&amp;sort=date"><span
				class="blind">네이버TV </span>더보기</a>
		</div>
		<div class="lst_cont" id="tx_ca_people_movie_content"
			style="display: none;">
			<h3 class="blind">영화</h3>
			<ul>
				<li>
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movieimage&amp;i='+'1800000D_000000017402');"
							href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=161967"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=ofullfill&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fssl.pstatic.net%2Fimgmovie%2Fmdi%2Fmi%2F1619%2F161967_P80_151640.jpg"
							alt="기생충" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movietitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=161967"
								target="_blank">기생충</a>
						</dd>
						<dt>각본, 감독, 작사</dt>
						<dd>2019</dd>
					</dl>
				</li>
				<li>
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movieimage&amp;i='+'1800000D_000000017402');"
							href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=143435"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=ofullfill&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fssl.pstatic.net%2Fimgmovie%2Fmdi%2Fmi%2F1434%2F143435_P166_181445.jpg"
							alt="옥자" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movietitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=143435"
								target="_blank">옥자</a>
						</dd>
						<dt>STORY, 각본, 감독, 제작</dt>
						<dd>2017</dd>
					</dl>
				</li>
				<li class="last">
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movieimage&amp;i='+'1800000D_000000017402');"
							href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=109753"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=ofullfill&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fssl.pstatic.net%2Fimgmovie%2Fmdi%2Fmi%2F1097%2F109753_P10_095813.jpg"
							alt="해무" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.movietitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="https://movie.naver.com/movie/bi/mi/basic.nhn?code=109753"
								target="_blank">해무</a>
						</dd>
						<dt>각본, 기획, 제작</dt>
						<dd>2014</dd>
					</dl>
				</li>
			</ul>
			<a nocr=""
				onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.wtmoviemore&amp;i='+'1800000D_000000017402');"
				class="more_view"
				href="http://movie.naver.com/movie/bi/pi/filmo.nhn?code=9479&amp;v=i"
				target="_blank"><span class="blind">영화 </span>더보기</a>
		</div>
		<div class="lst_cont" id="tx_ca_people_book_content"
			style="display: none;">
			<h3 class="blind">도서</h3>
			<ul>
				<li>
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.bookimage&amp;i='+'1800000D_000000017402');"
							href="http://book.naver.com/bookdb/book_detail.nhn?bid=15875461"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=o&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fbookthumb-phinf.pstatic.net%2Fcover%2F158%2F754%2F15875461.jpg%3Ftype%3Dm200_290%26udate%3D20191130"
							alt="메모리즈 오브 마더" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.booktitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="http://book.naver.com/bookdb/book_detail.nhn?bid=15875461"
								target="_blank" title="메모리즈 오브 마더 (마더 10주년 사진집)"> 메모리즈 오브 마더
								<span class="subt"> (마더 10주...</span>
							</a>
						</dd>
						<dt>플레인</dt>
						<dd>2019.11.18</dd>
					</dl>
				</li>
				<li>
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.bookimage&amp;i='+'1800000D_000000017402');"
							href="http://book.naver.com/bookdb/book_detail.nhn?bid=15369152"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=o&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fbookthumb-phinf.pstatic.net%2Fcover%2F153%2F691%2F15369152.jpg%3Ftype%3Dm200_290%26udate%3D20191129"
							alt="기생충 각본집 &amp; 스토리보드북 초판 한정 박스 세트" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.booktitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="http://book.naver.com/bookdb/book_detail.nhn?bid=15369152"
								target="_blank" title="기생충 각본집 &amp; 스토리보드북 초판 한정 박스 세트 (전2권)">
								기생충 각본집 &amp; 스토리보드북 ... </a>
						</dd>
						<dt>플레인</dt>
						<dd>2019.10.21</dd>
					</dl>
				</li>
				<li class="last">
					<div class="big_thumb">
						<a nocr=""
							onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.bookimage&amp;i='+'1800000D_000000017402');"
							href="http://book.naver.com/bookdb/book_detail.nhn?bid=7523748"
							target="_blank"><img
							src="https://search.pstatic.net/common?type=o&amp;size=56x80&amp;quality=95&amp;direct=true&amp;src=https%3A%2F%2Fbookthumb-phinf.pstatic.net%2Fcover%2F075%2F237%2F07523748.jpg%3Ftype%3Dm200_290%26udate%3D20190202"
							alt="‘작가’가 선정한 오늘의 영화" width="56" height="80"
							onerror="tx_noimage_thumb(this, 'sp_noimg', 'https://ssl.pstatic.net/sstatic/search/images11/blank.gif');"
							class=""></a>
					</div>
					<dl>
						<dd>
							<a nocr=""
								onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.booktitle&amp;i='+'1800000D_000000017402');"
								class="sh_people_title sh_people_link"
								href="http://book.naver.com/bookdb/book_detail.nhn?bid=7523748"
								target="_blank" title="‘작가’가 선정한 오늘의 영화 (2014)"> ‘작가’가 선정한
								오늘의 영화 <span class="subt"> (...</span>
							</a>
						</dd>
						<dt>작가</dt>
						<dd>2014.04.11</dd>
					</dl>
				</li>
			</ul>
			<a nocr=""
				onclick="return goOtherCR(this, 'u='+urlencode(this.href)+'&amp;r=1&amp;a='+nco_area_95234+'.wtbookmore&amp;i='+'1800000D_000000017402');"
				class="more_view"
				href="http://book.naver.com/search/search.nhn?query=%EB%B4%89%EC%A4%80%ED%98%B8&amp;frameFilterType=1&amp;frameFilterValue=5000027222"
				target="_blank"><span class="blind">도서 </span>더보기</a>
		</div>
	</div>
	<script type="text/javascript">
		nhn.peopleTabView = function(a) {
			var d = a;
			for (var b = 0, c = d.length; b < c; b++) {
				new nhn.peopleTabView.Tab(d[b]);
			}
		};
		nhn.peopleTabView.Tab = $Class({
			$init : function(b) {
				var a = $$.getSingle(">div.texttab_type>ul", b);
				this.aTab = $$(">li>a", a);
				this.aLayer = $$(">div.lst_cont", b);
				this.htClickArea = {
					"영화" : "nco_x29*3.wtmovie",
					"앨범" : "nco_x29*3.wtmusic",
					"선거이력" : "nco_x29*3.telection",
					"경기성적" : "nco_x29*3.wtsports",
					"공연" : "nco_x29*3.wtplay",
					"주요근황" : "nco_x29*3.wtrecent",
					"방송" : "nco_x29*3.wtonair",
					"도서" : "nco_x29*3.wtbook",
					"웹툰" : "nco_x29*3.wtwebtoon",
					"미술작품" : "nco_x29*3.wtarts",
					"사진작품" : "nco_x29*3.wtphoto",
					"칼럼" : "nco_x29*3.wtcolumn",
					"네이버TV" : "nco_x29*3.tvc",
					"그라폴리오" : "nco_x29*3.gra",
					"아트" : "nco_x29*3.art",
					"명언" : "nco_x29*3.quotation",
					"지식iN" : "nco_x29*3.wtkin",
					"전시" : "nco_x29*3.exh"
				};
				$Fn(this.click, this).attach(a, "click");
				this.initTab();
			},
			click : function(a) {
				a.stop();
				var d = a.element;
				if (d.tagName == "A") {
					for (var c = 0, f = this.aTab.length; c < f; c++) {
						var b = $Element(this.aTab[c]).parent();
						if (this.aTab[c] == d) {
							if (!b.hasClass("select")) {
								b.addClass("select");
							}
							$Element(this.aLayer[c]).show();
							nhn.common.ellipsis(this.aLayer[c]);
						} else {
							b.removeClass("select");
							$Element(this.aLayer[c]).hide();
						}
					}
					try {
						for ( var c in this.htClickArea) {
							if (d.innerHTML.match(new RegExp(c))) {
								goOtherTCR(this, ("a=" + this.htClickArea[c]
										+ "&r=1&i=" + txinfo_people_gdid));
								break;
							}
						}
					} catch (g) {
					}
				}
			},
			initTab : function() {
				try {
					if (__sDefaultPeopleTab && __sDefaultPeopleTab != "") {
						for (var b = 0, c = this.aTab.length; b < c; b++) {
							var a = $Element(this.aTab[b]).parent();
							if ($Element(this.aTab[b]).html().match(
									__sDefaultPeopleTab)) {
								if (!a.hasClass("select")) {
									a.addClass("select");
								}
								$Element(this.aLayer[b]).show();
							} else {
								a.removeClass("select");
								$Element(this.aLayer[b]).hide();
							}
						}
					}
				} catch (d) {
				}
			}
		});
		if ($("tx_ca_people_workall")) {
			var aPeopleType = [ $("tx_ca_people_workall") ];
			nhn.peopleTabView(aPeopleType);
		} else {
			$Fn(function() {
				var a = [ $("tx_ca_people_workall") ];
				nhn.peopleTabView(a);
			}).attach(window, "load");
		}
		var elTxCaPeopleKinContent = $("tx_ca_people_kin_content");
		if (elTxCaPeopleKinContent)
			nhn.common.ellipsis(elTxCaPeopleKinContent);
	</script>
	<script type="text/javascript">
		function tx_noimage_thumb(img, className, noimage) {
			img.onerror = null;
			if (noimage) {
				img.src = noimage;
			} else {
				img.src = "https://ssl.pstatic.net/sstatic/search/images11/blank.gif";
			}
			if (img.alt == '') {
				img.alt = "이미지 준비중";
			} else {
				img.alt = img.alt + " 준비중";
			}
			if (className) {
				img.className = className;
			}
		}
		function tx_set_href(a) {
			if (a.href)
				a.href = a.href.replace(/&query=[^&]*/g, "&query="
						+ headerfooter_query_encoded_utf8);
		}
	</script>
</div>
</body>
</html>