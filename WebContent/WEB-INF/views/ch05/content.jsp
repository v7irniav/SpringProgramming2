<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<!--절대 경로  -->
<script type="text/javascript">
	function getBoardList(pageNo){
		/* $("#boardListDiv").html("Hello"); */
		$.ajax({
			url: "getBoardList",
			data: "pageNo=" + pageNo, 
			method: "get",
			success: function(data) {/* data에 테이블이 들어온다 */
				$("#boardListDiv").html(data);
			}
		});	
	}
	
	 $(function() {
		getBoardList(1);
	});  /* $( handler ) */
	
/* 	function init () {
		getBoardList(1);
	}; */
	
	 /* $(document).ready(function(){
		getBoardList(1);
	}); */ /* $( document ).ready( handler ) */
	<%-- window.onload = function(){onload는 jsp를 실행하자 마자 해당것을 실행하겠다.
		$.ajax({
			url: "getBoardList",
			data: "pageNo=1", 
			method: "get",
			success: function(data) {data에 테이블이 들어온다
				$("#boardListDiv").html(data);
			}
		});	
	} --%>
</script>
</head>
<!-- <body onload="init()"> --><!--완전하게 해석 및 그림이 불러오면 이것이 실행된다. -->
<body>
	<div id="boardListDiv"></div>
	<div class="btn-toolbar" role="toolbar"
		aria-label="Toolbar with button groups">
		<div class="btn-group mr-2" role="group" aria-label="First group">
			<%for(int i=1;i <= 10; i++){%>
			<a href="javascript:getBoardList(<%=i%>)" type="button" class="btn btn-danger"><%=i%></a>
			<%}%>
		</div>
<%-- 		<img src="<%=application.getContextPath()%>/resources/image/Desert.jpg"/>
		<img src="<%=application.getContextPath()%>/resources/image/Koala.jpg"/>
		<img src="<%=application.getContextPath()%>/resources/image/Tulips.jpg"/>
		<img src="<%=application.getContextPath()%>/resources/image/Penguins.jpg"/> --%>
	</div>
</body>
</html>