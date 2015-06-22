<%@include file="holder.jsp" %>


    <div id ="content">
        <c:choose>
            <c:when test="${report.getId()>0}">
                <h3>Modificar informe</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}/reports/${report.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Nou Informe</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}/reports"/>
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="report">
            <table>
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
    </div>

</body>
</html>
