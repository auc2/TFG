<%@include file="holder.jsp" %>


<div id ="content">
	<h1>Llista de Grups	</h1>
	<p>Grups de natació donats d'alta.</p>

	    <ul>
	    <c:if test="${not empty swimmerGroups}">
		<c:forEach var="swimmerGroup" items="${swimmerGroups}">
		<li><a href="/swimmerGroups/${swimmerGroup.getId()}">${swimmerGroup.getId()}</a>: ${fn:escapeXml(swimerGroup.getLevel())}</li>
		</c:forEach>
	    </c:if>
	    </ul>
	<br>
</div>

</div>
   </body>
</html>

