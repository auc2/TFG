<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

	<div id ="content">
		<p><a href="/swimmerGroups">Tornar</a></p>

		<c:if test="${not empty swimmerGroup}">
		 
		    <p>ID: ${swimmerGroup.getId()}</p>
		
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>

		    <p>Monitor: ${swimmerGroup.getTeacher().getTeacherName()}</p>

		    
		</c:if>
	</div>
</body>
</html>
