/**
 * 
 */
/* 초기화 작업
	1.세션 만료 시간 (formatting: 2:00)
	2.1초 단위 스케쥴링
	3.메시지 히든 처리,1분 남은 시점에 show
	반복형태 작업
	2. 1초 단위 스케쥴링
		timer ==0 
	이벤트 처리: 예/아니오 버튼	
	
	$.fn. --> 엘리먼트대상함수
		*/

	/*function timeFormat(timeValue){
		var min = Math.trunc(timeValue/60);
		var sec = Math.trunc(timeValue%60);
		return min+":"+sec;
	}*/
	$.timeFormat = function(timeValue){//제이쿼리안에 새로운 함수 생성
		var min = Math.trunc(timeValue/60);
		var sec = Math.trunc(timeValue%60);
		return min+":"+sec;
	}
	
	var timer;
	var timerArea;//한번만 트래버싱하기!
	var msgArea;
	var ctrlBtn;
	var msgJob =null;
	var timerJob = null; //interval이 걸려있는지 여부
	
	$.fn.uiInit=function(uiAll){//제이쿼리함수형태. 플러그인 형태 라이브러리
		//스크립트 위치 중요★
		timerArea = this; //주인객체 참조
		msgArea = uiAll.msgArea.hide();
		ctrlBtn = uiAll.ctrlBtn.on("click",function(){
			var btnId = $(this).prop("id");
			if(btnId==uiAll.yesBtnId){
				//2분으로 돌려놓기
				if(msgJob!=null){
					clearTimeout(msgJob);//연장 후 1분 남았을 때 뜨게끔 타임아웃 초기화
					msgJob =null;
				}
				init(timeout);//다시 호출
			}
			
			msgArea.hide();
		});
	}
	var timeout;
	
	function init(timeout){//윈도우 함수형태
		window.timeout = timeout;//전역적으로 쓸 수 있다.
		timer = timeout;
		if(timerJob==null){
			timerJob = setInterval(function(){
				timer--;
				if(timer<=0){//종료될 때
					clearInterval(timerJob);								
				}else{
					//timerArea.html(timeFormat(timer)); window가 함수 주인
					timerArea.html($.timeFormat(timer));//jquery가 함수 주인
				}
			},500);
		}
		if(msgJob==null){
			msgJob = setTimeout(function(){
				msgArea.show();
			},(timeout-60)*500); //밀리세컨드로 바꿈
		}
	}
