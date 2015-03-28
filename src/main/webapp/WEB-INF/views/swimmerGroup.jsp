<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmerGroups">Tornar</a></p>

		<c:if test="${not empty swimmerGroup}">
		    <h2>Nivell ${swimmerGroup.getLevel()}</h2>
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>
		</c:if>
	</div>
</body>
</html>
