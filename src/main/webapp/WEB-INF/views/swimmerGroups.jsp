<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


	<h2>Llista de Grups	</h2>
	 <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
     <div class="clr"></div>
     <div class="img"><img src="swimmers.png" width="200" height="210" alt="" class="fl" /></div>
     <div class="post_content">
     <br>

	    <ul>
	    <c:if test="${not empty swimmerGroups}">
		<c:forEach var="swimmerGroup" items="${swimmerGroups}">
		<li><a href="/swimmerGroups/${swimmerGroup.getId()}">Grup ID ${swimmerGroup.getId()}</a> Monitor Assignat: ${swimmerGroup.getTeacher().getTeacherName()}</li>
		</c:forEach>
	    </c:if>
	    </ul>
	<br>


<%@include file="bottom.jsp" %>
