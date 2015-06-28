<%@include file="holder.jsp" %>


<div id ="content">
	<h1>Llista de informes de ${swimmer.getSwimmerName()}</h1>

	    <ul>
		    <c:if test="${not empty reports}">
			<c:forEach var="report" items="${reports}">

				<li><a href="/swimmers/${swimmer.getId()}/reports/${report.getId()}">${report.getId()} : ${report.getLevel()}</a></li>
			</c:forEach>
		    </c:if>
	    </ul>
	<br>
</div>

</div>
   </body>
</html>

