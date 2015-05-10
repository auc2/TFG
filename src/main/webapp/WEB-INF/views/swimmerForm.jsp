<%@include file="holder.jsp" %>


    <div id ="content">
        <c:choose>
            <c:when test="${swimmer.getId()>0}">
                <h3>Modificar nedador</h3>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}"/>
            </c:when>
            <c:otherwise>
                <h3>Crear nou nedador</h3>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmers"/>
            </c:otherwise>
        </c:choose>

        <form:form method="${method}" action="${action}" modelAttribute="swimmer">
            <table>
                <tr>
                    <td><form:label path="swimmerName">Nom: </form:label></td>
                    <td><form:input path="swimmerName"/> <i><form:errors path="swimmerName"></form:errors></i></td>
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
                    <td><form:label path="email">E-Mail:</form:label></td>
                    <td><form:input path="email"/> <i><form:errors path="email"></form:errors></i></td>
                </tr>


                    <tr>
                    <td><form:label path="group"><b>Grup:  </b></form:label></td>
                            <ul>
                              
                                
                                    <tr>
                                      <td>
                                       <div id ="optionsform">
                                       
                                            <select name="groupId" >

                                                <option value="9999"> sense classe </option><%--null value, no grup--%>
                                                
                                                <c:if test="${not empty groups}">
                                                    <c:forEach items="${groups}" var="group">
                                                       <option value="${group.getId()}">${group.getSessionHour()} - ${group.getTeacher().getTeacherName()} -  ${group.getLevel()}                                                    
                                                    </c:forEach>
                                                 </c:if>
                                            </select>
                                                                          
                                         </div>
                                     </td>
                                    </tr>
                                  <%--   </c:forEach>  --%>                          

                               
                            </ul>
                    </tr>

                <!--LLISTAR SWIMMERGROUPS DISPONIBLES PER AFEGIR-->
<br>
<br>
                 <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
