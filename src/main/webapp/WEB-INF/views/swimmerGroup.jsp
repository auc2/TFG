<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

	<div id ="content">
		<p><a href="/swimmerGroups">Tornar</a></p>

		<c:if test="${not empty swimmerGroup}">
		 
		    <p>ID: ${swimmerGroup.getId()}</p>
		
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>

		    <p>Nivell:  ${swimmerGroup.getLevel()}</p>

		    <p>Monitor: ${swimmerGroup.getTeacher().getTeacherName()}</p>

		    <p><b>Nedadors</b></p>

		      <ul>
			    <c:if test="${not empty swimmers}">
				<c:forEach var="swimmer" items="${swimmers}">
					<li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${swimmer.getSwimmerName()}</li>
				</c:forEach>
			    </c:if>
			  </ul>

		    
		</c:if>
	</div>
</body>
</html>
