<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/teachers">Tornar</a></p>

		<c:if test="${not empty teacher}">
		    <h2>Monitor ${teacher.getTeacherName()} ${teacher.getSurname()}</h2>
		    <br>
		    <p>E-mail: ${teacher.getEmail()}</p>
		    <p>Telefon: ${teacher.getTelephone()}</p>
		    <p>Ciutat: ${teacher.getCity()}</p>

		    <p>Foto:</p> <img alt="equip"  src="${teacher.getPhoto()}"  width="10%" height="15%"/> 

		    <!--Llistar swimmerGroups que te-->
		</c:if>
	</div>
</body>
</html>
