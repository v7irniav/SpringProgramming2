<%@ page contentType="text/html; charset=UTF-8"%>
<%--language는 생략해도 디폴트로 자바이다  pageEncoding도 생략이 가능하다 기본이 utf8이다--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--application 서블릿 컨텍스트 객체  -->
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>
<!--자동적으로 컨테스트 경로 바꾸기  -->
<!--절대 경로  -->
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<!--절대 경로  -->
</head>
<body>
	<p>
		<a href="join?mid=fall&mname=홍길동&mpassword=1234&mbirth=1995-12-31"
			class="btn btn-primary">get 방식 테스트</a>
		<a href="join2?mid=fall&mname=홍길동&mpassword=1234&mbirth=1995-12-31"
			class="btn btn-primary">get 방식 테스트(객체로 받기)</a>
	</p>

	<p>
	<form id="joinForm" name="joinForm" method="post" action="join2">
	
	<%-- <form method="post" action="join"> --%>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">아이디</span>
			</div>
			<input id="mid" name="mid" value="fall" type="text" class="form-control" placeholder="id"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">이름</span>
			</div>
			<input id="mname" name="mname" value="홍길동" type="text" class="form-control" placeholder="name"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">비밀번호</span>
			</div>
			<input id="mpassword" name="mpassword" value="12345" type="password" class="form-control" placeholder="password"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">나이</span>
			</div>
			<input id="mage" name="mage" value="25" type="number" class="form-control" placeholder="password"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">생년월일</span>
			</div>
			<input id="mbirth" name="mbirth" value="1995-12-25" type="date" class="form-control" placeholder="password"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<input type="submit" value="회원가입" class="btn btn-danger" /><!--submit은 폼테크 액션에 되어 잇는것을 작동 시킨다  -->
	</form>
	</p>
	
	<p>
		<script type="text/javascript">
			function join() {
				//how2
				/*if(mid == "") return;//유효성 검사 아무것도 입력이 되지 않으면 진행하면 안된다
				var mid = $("#mid").val();//제이쿼리
				var mname = $("#mname").val();
				var mpassword = $("#mpassword").val();
				var mage = $("#mage").val();
				var mbirth = $("#mbirth").val();
				var params = "";
				params += "mid=" + mid + "&";
				params += "mname=" + mname + "&";
				params += "mpassword=" + mpassword + "&";
				params += "mage=" + mage + "&"
				params += "mbirth=" + mbirth;
				location.href = "join2?" + params;/* 파라메타 값 */
				
				//how2
				//var joinForm = document.querySelector("#joinForm")
				//joinForm.submit();
				
				//how3
				document.joinForm.submit();//이방법을 쓸려면 네임이 있어야 한다
			}
		</script>
		<button onclick="join()" class="btn btn-danger">자바스크립트를 이용해서 데이터 전달</button><!--버튼은 폼테그 안에 넣으면 submit효과가 있다  -->
	</p>
	
	<p>
		<script type="text/javascript">
			function joinAjax(){
				$.ajax({/* <!--ajax는 부분 내용 갱신  --> */
					url:"join3", /* <!-- join3를 요청하면 디스페쳐 서블릿이 실행되고 컨트롤러가 실행된다--> */
					/*포스트 방식으로 보내기 위해 개별로 넣어 준다 url:"join3?mid=fall&mname=홍길동"은 겟방이식이다*/
					data:{mid:"fall", mname:"홍길동"},/* <!--데이타를 넘길때 사용하는 속성  -->/* "mid=fall&mname=홍길동" */
					method:"post",/* //요청방식 디폴트가 겟이므로 이렇게 포스트로 바꿔 줄수 있다. */
					success: function(data){/* <!--자동적으로 호출하는 함수  --> */
						var html = "<span>mid: " + data.mid + "</span> <br/>";
						html += "<span>mname: " + data.mname + "</span>";
						$("#resultDiv").html(html);
					}
				});
			}
		</script>
		<button onclick="joinAjax()" class="btn btn-danger">AJAX를 통한 데이터 전달</button>
		<div id="resultDiv">
		</div>
	</p>
	
</body>
</html>