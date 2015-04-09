<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


    <div id ="content">
        <c:choose>
            <c:when test="${swimmerGroup.getId()>0}">
                <h3>Modificar grup</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/swimmerGroups/${swimmerGroup.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Crear nou grup</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmerGroups"/>
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="swimmerGroup">
            <table>

                <tr>
                    <td><form:label path="sessionHour"><b>Hora Classe </b></form:label></td>
                        <td>
                                <form:select path="sessionHour" items="${sessionHours}" />
                        </td>
                </tr>

                    <tr>
                    <td><form:label path="teacher"><b>Monitor </b></form:label></td>
                            <ul>
                                <c:if test="${not empty teachers}">
                                
                                    <c:forEach var="teacher" items="${teachers}">
                                    <tr>
                                      <td>
                                       <div id ="optionsform">
                                            <a href="/teachers/${teacher.getId()}">${teacher.getId()}</a>: ${fn:escapeXml(teacher.getTeacherName())}</td>
                                        </div>
                                       <td><form:radiobutton path="teacher" value="no"></form:radiobutton></td>
                                    </tr>
                                    </c:forEach>                               

                                </c:if>
                            </ul>
                    </tr>

                    <tr>
                                <c:if test="${not empty swimmers}">
                                <td><form:label path="swimmers"><b>Nedadors per assignar grup </b></form:label></td>

                                <c:forEach var="swimmer" items="${swimmers}">
                                    <c:choose>
                                        <c:when test="${not empty swimmer.getSwimmerGroup()}">
                                            <div id ="optionsform">
                                              <td><form:label path="swimmers">Tots els nedadors assignats a un grup.</form:label></td>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                    <td>
                                                      <div id ="optionsform">
                                                        <a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${fn:escapeXml(swimmer.getSwimmerName())}
                                                      </div>
                                                    </td>
                                                   <td><form:radiobutton path="swimmers" value="no"></form:radiobutton></td>    
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                                </c:forEach>
                                </c:if>
                </tr>
                    <!-- (més endavant) Si la classe te més d'un nivell, mostrar llista per elegir nivell -->
                
                <tr>
                    <td><input type="submit"  id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
