<%@include file="holder.jsp" %>

	<div id ="content">
		<p><a href="/swimmerGroups">Tornar</a></p>

		<c:if test="${not empty swimmerGroup}">
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>
		</c:if>
	</div>
</body>
</html>
