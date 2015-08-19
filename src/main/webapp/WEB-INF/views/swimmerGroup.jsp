<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

		<c:if test="${not empty swimmerGroup}">
		 
		    <p><h2>GRUP ID: ${swimmerGroup.getId()}</h2></p>
			  <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
	          <div class="clr"></div>
	          <div class="img"><img src="grou.png" width="200" height="210" alt="" class="fl" /></div>
	          <div class="post_content">
            <br>
		
		    <p>Hora: ${swimmerGroup.getSessionHour()}</p>
		    <p>Nivell:  ${swimmerGroup.getLevel()}</p>
		    <p>Monitor: ${swimmerGroup.getTeacher().getTeacherName()}</p>

		    <p><b>Nedadors</b></p>
			</ul>
				<c:forEach items="${swimmers}"  var="swimmer">
					<li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${swimmer.getSwimmerName()}</li>
				</c:forEach>
			  </ul>


			   <form:form method="DELETE" action="/swimmerGroups/${swimmerGroup.getId()}">
		       <p><input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest grup?')"/></p>
		       </form:form>


	           <form action="/swimmerGroups/${swimmerGroup.getId()}/swimmerGroupForm">
	           <input type="submit" value="Update"/> 
	           </form>

		</c:if>


<%@include file="bottom.jsp" %>
