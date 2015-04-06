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
                    <div id ="optionsform">
                        <td><form:select path="sessionHour" items="${sessionHours}" /></td>
                    </div>
                </tr>

                    <tr>
                    <td><form:label path="teacher"><b>Monitor </b></form:label></td>
                            <ul>
                                <c:if test="${not empty teachers}">
                                
                                    <c:forEach var="teacher" items="${teachers}">
                                    <tr>
                                        <div id ="optionsform">
                                            <td><a href="/teachers/${teacher.getId()}">${teacher.getId()}</a>: ${fn:escapeXml(teacher.getTeacherName())}</td>
                                            <td><form:radiobutton path="teacher" value="no"></form:radiobutton></td>
                                        </div>
                                    </tr>
                                    </c:forEach>                               

                                </c:if>
                            </ul>
                    </tr>

                    <tr>
                                <c:if test="${not empty swimmers}">

                                <c:forEach var="swimmer" items="${swimmers}">
                                    <c:choose>
                                        <c:when test="${not empty swimmer.getSwimmerGroup()}">
                                            <div id ="optionsform">
                                              <td><form:label path="swimmers"><b>Tots els nedadors assignats a un grup.</b></form:label></td>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <td><form:label path="swimmers"><b>Nedadors per assignar grup </b></form:label></td>
                                            <tr>
                                            <div id ="optionsform">
                                                <!--Això han de ser checkboxes o similar-->
                                                  <td><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${fn:escapeXml(swimmer.getSwimmerName())}</td>
                                                   <td><form:radiobutton path="swimmers" value="no"></form:radiobutton></td>
                                            </div>
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
