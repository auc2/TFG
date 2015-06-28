<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmers">Nedadors</a></p>

		<c:if test="${not empty swimmer}">
		    <h2>Nedador  ${swimmer.getSwimmerName()}  ${swimmer.getSurname()}</h2>

  		<c:choose>
		     <c:when test="${not empty swimmer.getGroup()}">
             	<p>Grup: <a href="/swimmerGroups/${swimmer.getGroup().getId()}">${swimmer.getGroup().getId()}</a> - ${swimmer.getGroup().getLevel()} - Professor: ${swimmer.getGroup().getTeacher().getTeacherName()}</p>


            <form action="/swimmers/${swimmer.getId()}/reportForm">
              <input type="submit" value="Add Report"/> 
            </form>

         </c:when>
         <c:otherwise>
         		<div id ="no_value_assigned"><p> No hi ha grup assignat</p></div>
         </c:otherwise>
      </c:choose>


            <p>Ciutat: ${swimmer.getCity()}</p>
    		    <p>Telefon: ${swimmer.getTelephone()}</p>
    		    <p>E-mail: ${swimmer.getEmail()}</p>
		</c:if>


         <form:form method="GET" action="/swimmers/${swimmer.getId()}/reports">
           <input type="submit" value="Informes"/>
         </form:form>


         <form:form method="DELETE" action="/swimmers/${swimmer.getId()}">
           <input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest nedador?')"/>
         </form:form>


         <form action="/swimmers/${swimmer.getId()}/swimmerForm">
           <input type="submit" value="Update"/> 
         </form>


 

	</div>
</body>
</html>
