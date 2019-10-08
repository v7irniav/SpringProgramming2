<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.mycompany.web.dto.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <c:forEach>

</c:forEach> 접두사를 사용해서 html태그의외에 태그를 사용할수 잇게 해주는것--%>

<h5>게시물 리스트(전체 게시물 수 : ${totalNo})</h5>
<table class="table table-sm">
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">글쓴이</th>
			<th scope="col">날짜</th>
			<th scope="col">조회수</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${boardList}" var="board"><%--jstl  코드 --%>
		<tr>
			<th scope="row">${board.bno}</th><%-- --%>
			<td>${board.btitle}</td><%-- --%>
			<td>${board.writer}</td>
			<td><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/></td>
			<td>${board.hitcount}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>