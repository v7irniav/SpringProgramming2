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
	<form method="post" action="join"><!--join은 상대 경로이다  -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">아이디</span>
			</div>
			<input type="text" class="form-control" placeholder="id"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">이름</span>
			</div>
			<input type="text" class="form-control" placeholder="name"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">비밀번호</span>
			</div>
			<input type="password" class="form-control" placeholder="password"
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		
		<input type="submit" value="회원가입" class="btn btn-danger" />
	</form>

</body>
</html>