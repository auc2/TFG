<%@include file="holder.jsp" %>


<div id ="content">
	<h1>Llista de monitors	</h1>
	<p>Professors donats d'alta.</p>

	    <ul>
		    <c:if test="${not empty teachers}">
			<c:forEach var="teacher" items="${teachers}">
				<li><a href="/teachers/${teacher.getId()}">${teacher.getId()}</a>: ${teacher.getTeacherName()}</li>
			</c:forEach>
		    </c:if>
	    </ul>
	<br>
</div>

</div>
   </body>
</html>

