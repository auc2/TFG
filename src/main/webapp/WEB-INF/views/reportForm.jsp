<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


    <div id ="content">
        <c:choose>
            <c:when test="${report.getId()>0}">
                <h3>Modificar informe</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}/reports/${report.getId()}"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="edir.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:when>
            <c:otherwise>
                <h3>Nou Informe</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}/reports"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="addr.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="report">
            <table>

                    <td><form:label path="level">Nivell: </form:label></td>
                    <td><form:input path="level" value="${level}" readonly="true" /></td>

                      <c:set var="count" value="1" scope="page" />
              
                       <c:if test="${not empty questions}">
                          <c:forEach items="${questions}" var="question">
                            <tr>
                               <td><p>${question}</p> </td><td><form:radiobuttons path="value${count}" items="${puntuation}" /></td>
                               <c:set var="count" value="${count + 1}" scope="page"/>
                            </tr>

                          </c:forEach>
                       </c:if>
                    


                <tr>
                    <td><form:label path="comment">Comentari: </form:label>
                    <form:textarea path="comment" rows="5" cols="100"/> <i><form:errors path="comment"></form:errors></i></td>
                </tr>


                 <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    

<%@include file="bottom.jsp" %>
