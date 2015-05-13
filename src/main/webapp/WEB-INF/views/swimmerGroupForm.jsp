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
                    <td><form:label path="sessionHour"><b>Hora Classe:  </b></form:label></td>
                        <td>
                                <form:select path="sessionHour" items="${sessionHours}" />
                        </td>
                </tr>
                <tr>
                    <td><form:label path="level"><b>Nivell: </b></form:label></td>
                    <td><form:input path="level"/> <i><form:errors path="level"></form:errors></i></td>
                </tr>

                    <tr>
                    <td><form:label path="teacher"><b>Monitor </b></form:label></td>
                            <ul>

                    <tr>
                       <td>
                         <div id ="optionsform">

                                <select name="teacherId" >
                                <option value="9999"> no monitor assignat </option><%--null value, no teacher--%>

                                <c:if test="${not empty teachers}">
                                                <c:forEach items="${teachers}" var="teacher">
                                                   <option value="${teacher.getId()}">${teacher.getTeacherName()}
                                                </c:forEach>
                                            </select>
                                    
                                         </div>
                                     </td>                    

                                </c:if>
                            </ul>
                    </tr>
<br>
                    <tr>
                        <c:if test="${not empty swimmers}">
                        <td><form:label path="swimmers"><b>Nedadors per assignar al grup:  </b></form:label></td>

                                <c:forEach var="swimmer" items="${swimmers}">
                                    
                                    <c:choose>
                                        <c:when test="${not empty swimmer.getGroup()}">
                                            <div id ="optionsform">
                                              <td><form:label path="swimmers">Tots els nedadors assignats a un grup.</form:label></td>
                                            </div>
                                        </c:when>
                                        
                                        <c:otherwise>
                                            <tr>
                                                <td>
                                                    <div id ="optionsform">

                                                        <td><b>Nedador:</b> ${swimmer.getSwimmerName()}</td>
                                                        <td>
                                                            <input type ="checkbox" name ="swimmersListId" value="${swimmer.getId()}"/>
    
                                                        </td>    

                                                    </div>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>                            
                        </c:if>
                    </tr>

<%--
    <td><form:radiobutton path="teacherId" value="${teacher.getId()}"></form:radiobutton></td>    
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

                                                        <td>${fn:escapeXml(swimmer.getSwimmerName())}</td>
                                                        <td><form:checkbox path="swimmers" value="${swimmer}"></form:checkbox></td>    

                                                      </div>
                                                    </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>

                                </div>

                                </c:forEach>
                                </c:if>

                                    TEacher no obligatori, i crear camp teacherID a SwimmerGroup amb el qual en el controller ho agafare després amb el findONe de repository Teacaher....teacherFormId
                </tr>-->
                    <!-- (més endavant) Si la classe te més d'un nivell, mostrar llista per elegir nivell -->
                 --%> 
                <tr>
                    <td><input type="submit"  id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
