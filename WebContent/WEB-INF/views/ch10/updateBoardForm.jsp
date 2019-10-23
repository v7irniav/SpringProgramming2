<%@ page contentType="text/html; charset=UTF-8"%>  
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>	
			<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>							
			<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">									
			<script type="text/javascript" src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>																					<!--절대 경로  -->
			<script type="text/javascript">
				function checkForm(){
					var result = true;
					//모든 에러 내용 지우기
					$(".error").text("");
					//입력값 검사
					if($("#btitle").val() == ""){
						$("#btitleError").text("*제목을 입력하세요");					
						result = false;
					}
					if($("#bcontent").val() == ""){
						$("#bcontentError").text("*내용을 입력하세요");					
						result = false;
					}
					
					return result;/*하나라도 펄스가 되면 넘어가지 않는다.  */
				}
			</script>
		</head>	
		<body>
			<h5>게시물 수정</h5>
			<form method="post" action="updateBoard" onsubmit="return checkForm()"><!--checkForm()가 펄스가 되면 값이 넘어가지 않는다  -->
			<input type="hidden" name="bno" value="${board.bno}" /><!--hidden은 눈에 보이지 않는  -->
			  <div class="form-group">
			    <label for="btitle">Title</label>
			    <input id="btitle" name="btitle" value="${board.btitle}" type="text" class="form-control"  placeholder="제목을 입력하세요">
			    <span id="btitleError" class="error" style="color:red"></span><!--스팸은 글자만 들어간다  -->
			  </div>
			  <div class="form-group">
			    <label for="bcontent">Content</label>
			    <textarea id="bcontent" name="bcontent" class="form-control" rows="3">${board.bcontent}</textarea>
			    <span id="bcontentError" class="error" style="color:red"></span>
			  </div>
			  <div class="form-group">
			  <input type="submit" class="btn btn-danger" value="글수정"/>
			  </div>
			</form>
		</body>
</html>