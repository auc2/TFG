<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>



<a href="/swimmers/${swimmer.getId()}">Veure nedador</a>

	<h2><span>Llista de informes de ${swimmer.getSwimmerName()}</h2>
	  <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
    <div class="clr"></div>
    <div class="img"><img src="repo.png" width="200" height="210" alt="" class="fl" /></div>
    <div class="post_content">
    <br>

	    <ul>
		    <c:if test="${not empty reports}">
			<c:forEach var="report" items="${reports}">

				<li><a href="/swimmers/${swimmer.getId()}/reports/${report.getId()}">${report.getId()} : ${report.getLevel()}</a></li>
			</c:forEach>
		    </c:if>
	    </ul>
	<br>


<%@include file="bottom.jsp" %>
