<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/teachers">Tornar</a></p>

		<c:if test="${not empty teacher}">
		    <h2>Monitor ${teacher.getTeacherName()}</h2>
		    <p>E-mail: ${teacher.getEmail()}</p>
		</c:if>
	</div>
</body>
</html>
