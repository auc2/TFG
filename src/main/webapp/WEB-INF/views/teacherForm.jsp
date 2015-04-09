<%@include file="holder.jsp" %>


    <div id ="content">
        <c:choose>
            <c:when test="${teacher.getId()>0}">
                <h3>Modificar monitor</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/teachers/${teacher.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Crear nou monitor</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/teachers"/>
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="teacher">
            <table>
                <tr>
                    <td><form:label path="teacherName">Nom: </form:label></td>
                    <td><form:input path="teacherName"/> <i><form:errors path="teacherName"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="surname">Cognom: </form:label></td>
                    <td><form:input path="surname"/> <i><form:errors path="surname"></form:errors></i></td>
                </tr>
                  <tr>
                    <td><form:label path="city">Ciutat: </form:label></td>
                    <td><form:input path="city"/> <i><form:errors path="city"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="telephone">Telefon: </form:label></td>
                    <td><form:input path="telephone"/> <i><form:errors path="telephone"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="email">E-Mail: </form:label></td>
                    <td><form:input path="email"/> <i><form:errors path="email"></form:errors></i></td>
                </tr>
                <tr>
                    <td><form:label path="photo">Foto: </form:label></td>
                    <td><input type="file" name="file" id="file"></td>
                </tr>

                <!--LLISTAR SWIMMERGROUPS DISPONIBLES PER AFEGIR-->
                <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
