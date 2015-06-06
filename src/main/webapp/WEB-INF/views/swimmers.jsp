<%@include file="holder.jsp" %>


<div id ="content">
	<h1>Llista de nedadors	</h1>
	<p>Neadors donats d'alta.</p>

	    <ul>
		    <c:if test="${not empty swimmers}">
			<c:forEach var="swimmer" items="${swimmers}">
				<li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${swimmer.getSwimmerName()}</li>
			</c:forEach>
		    </c:if>
	    </ul>
	<br>
</div>

</div>
   </body>
</html>

