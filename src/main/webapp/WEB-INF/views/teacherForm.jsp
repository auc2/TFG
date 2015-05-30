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

                    <!-- FER UN METODE APART AL CONTROLLER, PER AGAFAR LA INFORMACIÓ EN BYTES DEL DEBLOB, I RETORNARLA AL HTML, QUE SERA 
                    L'ENCARREGAT DE TRANSFORMARLA.-->
                    
                    <td><form:label path="photo">Foto: </form:label></td>
                    <td><input type="file" name="file" id="file"></td>
                </tr>
<br>
<br>


                    <tr>
                     <c:if test="${not empty groupsTeacher}">
                        <td><form:label path="swimmerGroups"><b>Grups assignats al monitor:  </b></form:label></td>

                                <c:forEach var="group" items="${groupsTeacher}">
                                    
                                    <c:choose>
                                        <c:when test="${not empty groupsTeacher}">
                                           <tr>
                                                <td>
                                                    <div id ="optionsform">

                                                        <td><b>Grup: </b> ${group.getId()} - ${group.getLevel()}</td>
                                                        
                                                        <td>
                                                            <input type ="checkbox" name ="groupsListId" value="${group.getId()}" checked/>    
                                                        </td>    

                                                    </div>
                                                </td>
                                            </tr>                                           
                                        </c:when>
                                        
                                        <c:otherwise>
                                         <div id ="optionsform">
                                              <td><form:label path="swimmerGroups">Monitor sense grups assignats.</form:label></td>
                                            </div>                                         
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>      
                     </c:if>                      
                    </tr>


                <tr>
                    <c:if test="${not empty groups}">
                        <td><form:label path="swimmerGroups"><b>Grups per assignar al monitor:  </b></form:label></td>

                                <c:forEach var="group" items="${groups}">
                                    
                                    <c:choose>
                                        <c:when test="${not empty groups}">
                                           <tr>
                                                <td>
                                                    <div id ="optionsform">

                                                        <td><b>Grup: </b> ${group.getId()} - ${group.getLevel()}</td>
                                                        <td>
                                                            <input type ="checkbox" name ="groupsListId" value="${group.getId()}"/>    
                                                        </td>    

                                                    </div>
                                                </td>
                                            </tr>                                           
                                        </c:when>
                                        
                                        <c:otherwise>
                                         <div id ="optionsform">
                                              <td><form:label path="swimmerGroups">Tots els grups assignats a un monitor.</form:label></td>
                                            </div>                                         
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>                      
                       </c:if>                      
                    </tr>

                <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
    </div>

</body>
</html>
