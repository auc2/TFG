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
                    <td><form:label path="sessionHour">Hora Classe: </form:label></td>
                    <!-- Mostrar opcions (10:15-11:15, 11:15-12:15, 12:15-1:15) -->
                    <td><form:input path="sessionHour"/> <i><form:errors path="sessionHour"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="level">Nivell: </form:label></td>
                    <!-- Mostrar llista per elegir nivell -->
                    <td><form:input path="level"/> <i><form:errors path="level"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="level">Monitor: </form:label></td>
                    <!-- Mostrar llista en combobox per exemple, per elegir monitor -->
                    <td><form:input path="level"/> <i><form:errors path="level"></form:errors></i></td>

                     <!--LLISTAR SWIMMERS NO ASSIGNATS DISPONIBLES PER AFEGIR-->
                     <!--LLISTAR TEACHERS NO ASSIGNATS PER ASSIGNAR GRUP-->

                </tr>
                <tr>
                    <td><input type="submit" id="addSwimmerGroupButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
