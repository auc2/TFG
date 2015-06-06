<%@include file="holder.jsp" %>


    <div id ="content">
        <c:choose>
            <c:when test="${report.getId()>0}">
                <h3>Modificar informe</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/reports/${swimmer.getId()}/${report.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Crear nou informe</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/reports/${swimmer.getId()}/"/>
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="report">
            <table>
             
<%--
            <tr>
                 <c:if test="${not empty questions}">
                     <c:forEach items="${questions}" var="question">
                              <p>Pregunta : ${question} </p>                                                    
                    </c:forEach>
                </c:if>
            </tr>
--%>


                    <tr>
                  <%--      <td>${question1}</td>--%>
                        <td><form:radiobuttons path="value" items="${puntuation}" /></td>
                        <td><form:radiobuttons path="value" items="${puntuation}" /></td>

                    </tr>
                    <%--
                    <tr>
                     <td>${question2}</td>
                        <td><form:radiobuttons path="value2" items="${puntuation}" />
                    </tr>


                    <tr>
                        <td>Prova 1 :</td>
                        <td><form:radiobuttons path="value" items="${puntuation}" />
                    </tr>

--%>
                 <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
