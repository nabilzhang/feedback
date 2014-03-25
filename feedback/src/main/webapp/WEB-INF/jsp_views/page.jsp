<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="firstUrl" value="?pageNo=1" />
<c:url var="lastUrl" value="?pageNo=${deploymentLog.totalPages}" />
<c:url var="prevUrl" value="?/${currentIndex - 1}" />
<c:url var="nextUrl" value="?${currentIndex + 1}" />

<div class="pagination">
	<ul>
		<c:choose>
			<c:when test="${currentIndex == 1}">
				<li class="disabled"><a href="#">&lt;&lt;</a></li>
				<li class="disabled"><a href="#">&lt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${firstUrl}">&lt;&lt;</a></li>
				<li><a href="${prevUrl}">&lt;</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
			<c:url var="pageUrl" value="/pages/${i}" />
			<c:choose>
				<c:when test="${i == currentIndex}">
					<li class="active"><a href="${pageUrl}"><c:out
								value="${i}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${currentIndex == deploymentLog.totalPages}">
				<li class="disabled"><a href="#">&gt;</a></li>
				<li class="disabled"><a href="#">&gt;&gt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${nextUrl}">&gt;</a></li>
				<li><a href="${lastUrl}">&gt;&gt;</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>