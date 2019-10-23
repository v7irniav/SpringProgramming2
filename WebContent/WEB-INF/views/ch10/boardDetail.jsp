<%@ page contentType="text/html; charset=UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>	
			<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>							
			<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/css/bootstrap.min.css">									
			<script type="text/javascript" src="<%=application.getContextPath()%>/resources/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>																					<!--절대 경로  -->
			<script type="text/javascript">
			</script>
		</head>	
		<body>
			<h5>게시물 내용</h5>
			<form><!--checkForm()가 펄스가 되면 값이 넘어가지 않는다  -->
			<!-- #은 현재페이지를 다시 보여준다 -->
			<%--  <div class="form-group">
			    <label for="bno">No</label>
			    <input id="bno"  type="text" class="form-control" value="${board.bno}" readonly>
			  </div> --%>
			  <div class="form-group">
			    <label for="btitle">Title</label>
			    <input id="btitle"  type="text" class="form-control" value="${board.btitle}" readonly>
			  </div>
			  <%-- <div class="form-group">
			    <label for="bwriter">Writer</label>
			    <input id="bwriter"  type="text" class="form-control" value="${board.bwriter}" readonly>
			  </div>--%>
			   <div class="form-group">
			    <label for="bhitcount">Hitcount</label>
			    <input id="bhitcount"  type="text" class="form-control" value="${board.bhitcount}" readonly>
			  </div> 
			  <div class="form-group">
			    <label for="bdate">Date</label>
			    <input id="bdate"  type="text" class="form-control" value='<fmt:formatDate value="${board.bdate}" pattern="yyyy년 MM월 dd일"/>' readonly>
			  </div>
			  <div class="form-group">
			    <label for="bcontent">Content</label>
			    <textarea id="bcontent" class="form-control" rows="3" readonly>${board.bcontent}</textarea>
			  </div>
			</form>
			
			<a href="boardList?pageNo=${pageNo}" class="btn btn-info">목록</a><!--누르면 있었던 페이지 수로 돌아가기  -->
			
			<c:if test="${board.bwriter == mid}"><!--해당 사용자만 수정 및 삭제 할수 있게  -->
				<a href="updateBoard?bno=${board.bno}" class="btn btn-warning">수정</a>
				<a href="deleteBoard?bno=${board.bno}" class="btn btn-danger">삭제</a>
			</c:if>
		</body>
</html>



