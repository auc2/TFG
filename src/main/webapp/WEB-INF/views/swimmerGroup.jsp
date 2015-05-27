<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

	<div id ="content">
		<p><a href="/swimmerGroups">Grups</a></p>

		<c:if test="${not empty swimmerGroup}">
		 
		    <p><h1><b>GRUP ID: ${swimmerGroup.getId()}</b></h1></p>
		
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>

		    <p>Nivell:  ${swimmerGroup.getLevel()}</p>

		    <p>Monitor: ${swimmerGroup.getTeacher().getTeacherName()}</p>

		    <p><b>Nedadors</b></p>

	

<%--
error--->failed to lazily initialize a collection of role: cat.udl.eps.softarch.hello.model.SwimmerGroup.swimmers, could not initialize proxy - no Session
	--%> 
			</ul>
				<c:forEach items="${swimmers}"  var="swimmer">
					<li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${swimmer.getSwimmerName()}</li>
				</c:forEach>
			  </ul>


			   <form:form method="DELETE" action="/swimmerGroups/${swimmerGroup.getId()}">
		       <p><input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest grup?')"/></p>
		       </form:form>


	           <form action="/swimmerGroups/${swimmerGroup.getId()}/swimmerGroupForm">
	           <input type="submit" value="Update"/> 
	           </form>

		</c:if>
	</div>
</body>
</html>
