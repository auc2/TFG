<%@include file="holder.jsp" %>

	<div id ="content">

		<c:if test="${not empty report}">
		  
            <table>

           <tr> <p>Informe del nedador: <a href="/swimmers/${swimmer.getId()}">${swimmer.getSwimmerName()}</a></p></tr>

           <tr> <p>Nivell Informe: ${report.getLevel()}</p></tr>


            <c:forEach var="question" items="${questions}" varStatus="status">
              <tr>
                <td>${question}</td>
                <td><b>${values[status.index]}</b></td>
              </tr>
            </c:forEach>


            <tr><p>Comentari: ${report.getComment()}</p></tr>
         
    		</table>
		</c:if>


         <form:form method="DELETE" action="/swimmers/${swimmer.getId()}/reports/${report.getId()}">
           <input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest report?')"/>
         </form:form>


         <form action="/swimmers/${swimmer.getId()}/reports/${report.getId()}/reportForm">
           <input type="submit" value="Update"/> 
         </form>

         

 

	</div>
</body>
</html>
