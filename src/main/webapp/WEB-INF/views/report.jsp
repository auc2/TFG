<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


		<c:if test="${not empty report}">
		  
        <h2>Informe del nedador: <a href="/swimmers/${swimmer.getId()}">${swimmer.getSwimmerName()}</a></h2>
        <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
        <div class="clr"></div>
        <div class="img"><img src="addr.png" width="200" height="210" alt="" class="fl" /></div>
        <div class="post_content">

        <table>
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

         

<%@include file="bottom.jsp" %>
