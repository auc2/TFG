<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


		<h2><span>Llista de nedadors</h2>
	        <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
          <div class="clr"></div>
          <div class="img"><img src="swim.png" width="200" height="210" alt="" class="fl" /></div>
          <div class="post_content">
            <br>

	    <ul>
		    <c:if test="${not empty swimmers}">
			<c:forEach var="swimmer" items="${swimmers}">
				<li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${swimmer.getSwimmerName()}</li><br>
			</c:forEach>
		    </c:if>
	    </ul>
   

<%@include file="bottom.jsp" %>



