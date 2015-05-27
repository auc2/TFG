<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/teachers">Tornar</a></p>

		<c:if test="${not empty teacher}">
		    <h2>Monitor ${teacher.getTeacherName()} ${teacher.getSurname()}</h2>
		    <br>
		    <p>E-mail: ${teacher.getEmail()}</p>
		    <p>Telefon: ${teacher.getTelephone()}</p>
		    <p>Ciutat: ${teacher.getCity()}</p>

    <p>Foto:</p> <img alt="equip"  src="getImage/${teacher.getId()}"  width="10%" height="15%"/>

	<br>
 	<p>Grups assignats: </p>
 	

 	   <ul>
	    <c:if test="${not empty groups}">
			<c:forEach var="group" items="${groups}">
				<li><a href="/swimmerGroups/${group.getId()}">Grup: ${group.getId()}</a>  - Monitor:  ${group.getTeacher().getTeacherName()}</li>
			</c:forEach>
	    </c:if>
	    </ul>
		</c:if>

 		<form:form method="DELETE" action="/teachers/${teacher.getId()}">
        <p><input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest monitor?')"/></p>
        </form:form>


        <form action="/teachers/${teacher.getId()}/teacherForm">
           <input type="submit" value="Update"/> 
        </form>


	</div>



</body>
</html>
