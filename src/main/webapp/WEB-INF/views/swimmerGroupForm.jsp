<%@include file="holder.jsp" %>


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
                    <td><form:label path="sessionHour">Hora Classe </form:label></td>

                    hourSessions
                    <div id ="optionsform">

                          <ul>
                                <c:if test="${not empty hourSessions}">
                                <c:forEach var="hour" items="${hourSessions}">
                                    <li> ${hour}</li>
                                </c:forEach>
                                </c:if>
                            </ul>
                    </div>


                    <td><form:input path="sessionHour"/> <i><form:errors path="sessionHour"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="level">Nivell </form:label></td>
                    <!-- Mostrar llista per elegir nivell -->
                    <td><form:input path="level"/> <i><form:errors path="level"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="level">Monitor </form:label></td>
                    <!-- Mostrar llista en combobox per exemple, per elegir monitor -->
                            <ul>
                                <c:if test="${not empty teachers}">
                                <c:forEach var="teacher" items="${teachers}">
                                    <li><a href="/teachers/${teacher.getId()}">${teacher.getId()}</a>: ${fn:escapeXml(teacher.getTeacherName())}</li>
                                </c:forEach>
                                </c:if>
                            </ul>
                    <td><form:input path="level"/> <i><form:errors path="level"></form:errors></i></td>

                     <td>Nedadors </td>
                               <ul>
                                <c:if test="${not empty swimmers}">
                                <c:forEach var="swimmer" items="${swimmers}">

                                <c:choose>
                                    <c:when test="${not empty swimmer.getSwimmerGroup()}">
                                         <p>Tots els nedadors assignats a un grup.</p>
                                    </c:when>
                                    <c:otherwise>
                                    <p>Nedadors per assignar a un grup</p>
                                      <li><a href="/swimmers/${swimmer.getId()}">${swimmer.getId()}</a>: ${fn:escapeXml(swimmer.getSwimmerName())}</li>    
                                    </c:otherwise>
                                </c:choose>

                                </c:forEach>
                                </c:if>
                            </ul>

                </tr>
                <tr>
                    <td><input type="submit" id="addSwimmerGroupButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
