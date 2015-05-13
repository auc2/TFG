<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmers">Nedadors</a></p>

		<c:if test="${not empty swimmer}">
		    <h2>Nedador  ${swimmer.getSwimmerName()}  ${swimmer.getSurname()}</h2>

  		 <c:choose>
		 <c:when test="${not empty swimmer.getGroup()}">
             	<p>Grup: <a href="/swimmerGroups/${swimmer.getGroup().getId()}">${swimmer.getGroup().getId()}</a> - ${swimmer.getGroup().getLevel()} - Professor: ${swimmer.getGroup().getTeacher().getTeacherName()}</p>
             	<a href="www.google.es">   Canviar grup</a>

         </c:when>
         <c:otherwise>
         		<div id ="no_value_assigned"><p> No hi ha grup assignat</p></div>
         	<%--	<a href="www.google.es">   Assignar grup</a> --%>
         </c:otherwise>
         </c:choose>


            <p>Ciutat: ${swimmer.getCity()}</p>
		    <p>Telefon: ${swimmer.getTelephone()}</p>
		    <p>E-mail: ${swimmer.getEmail()}</p>
		</c:if>

         <form:form method="DELETE" action="/swimmers/${swimmer.getId()}">
         <p><input type="submit" value="Delete"/></p>
         </form:form>


	</div>
</body>
</html>
