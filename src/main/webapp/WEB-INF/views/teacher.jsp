<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

		<p><a href="/teachers">Tornar</a></p>
		<c:if test="${not empty teacher}">

          <h2><span>Monitor ${teacher.getTeacherName()} ${teacher.getSurname()}</h2>
          <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
          <div class="clr"></div>
          <div class="img"><img src="getImage/${teacher.getId()}" width="200" height="210" alt="" class="fl" /></div>

          <div class="post_content">

		      <br>
		      <p>E-mail: ${teacher.getEmail()}</p>
		      <p>Telefon: ${teacher.getTelephone()}</p>
		      <p>Ciutat: ${teacher.getCity()}</p>

		  
		    </c:if>

		        <form:form method="DELETE" action="/teachers/${teacher.getId()}">
		            <input type="submit" value="Delete" onclick ="return confirm('Segur que vols eliminar aquest monitor?')"/>
		            </form:form>


		            <form action="/teachers/${teacher.getId()}/teacherForm">
		            <input type="submit" value="Update"/> 
		            </form>


<%@include file="bottom.jsp" %>



