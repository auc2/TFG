<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmers">Tornar</a></p>

		<c:if test="${not empty swimmer}">
		    <h2>Swimmer ${swimmer.getSwimmerName()}</h2>
		    <p>E-mail: ${swimmer.getEmail()}</p>
		</c:if>
	</div>
</body>
</html>
