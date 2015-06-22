<%@include file="holder.jsp" %>

	<div id ="content">

		<c:if test="${not empty report}">
		  

            <p>Informe del nedador: ${swimmer.getSwimmerName()}</p>

            <p>Nivell Informe: ${report.getLevel()}</p>



            <c:forEach var="question" items="${questions}" varStatus="status">
              <tr>
                <td>${question}</td>  
                <td><b>${values[status.index]}</b></td>
              </tr>
            </c:forEach>

          
    		
		</c:if>

        
         <form:form method="DELETE" action="/swimmers/${swimmer.getId()}">
           <input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest report?')"/>
         </form:form>


         <form action="/swimmers/${swimmer.getId()}/swimmerForm">
           <input type="submit" value="Update"/> 
         </form>

         

 

	</div>
</body>
</html>
