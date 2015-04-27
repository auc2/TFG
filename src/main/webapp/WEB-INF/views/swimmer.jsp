<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmers">Tornar</a></p>

		<c:if test="${not empty swimmer}">
		    <h2>Nedador  ${swimmer.getSwimmerName()}  ${swimmer.getSurname()}</h2>


			<p>Grup: <a href="/swimmerGroups/${swimmer.getGroup().getId()}">${swimmer.getGroup().getId()}</a> - ${swimmer.getGroup().getLevel()} - Professor: ${swimmer.getGroup().getTeacher().getTeacherName()}</p>

		    <p>Ciutat: ${swimmer.getCity()}</p>
		    <p>Telefon: ${swimmer.getTelephone()}</p>
		    <p>E-mail: ${swimmer.getEmail()}</p>
		</c:if>
	</div>
</body>
</html>
