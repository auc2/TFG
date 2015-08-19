<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


          <h2><span>Llista de Monitors</h2>
          <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
          <div class="clr"></div>
          <div class="img"><img src="teac.jpg" width="200" height="210" alt="" class="fl" /></div>
          <div class="post_content">
            <br>

		    <c:if test="${not empty teachers}">
			<c:forEach var="teacher" items="${teachers}">
				<li><a href="/teachers/${teacher.getId()}">${teacher.getId()}</a>: ${teacher.getTeacherName()}</li>
        <br>
			</c:forEach>
		    </c:if>


<%@include file="bottom.jsp" %>

			    