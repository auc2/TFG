<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>

        <c:choose>
            <c:when test="${teacher.getId()>0}">
                <h2>Modificar monitor</h2>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/teachers/${teacher.getId()}"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="ediu.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:when>
            <c:otherwise>
                <h2>Crear nou monitor</h2>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/teachers"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="addu.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:otherwise>
        </c:choose>
        <br>



        <form:form method="${method}" action="${action}" modelAttribute="teacher" enctype="multipart/form-data">
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
                    <td><form:input path="photo" type="file"/> <i><form:errors path="photo"></form:errors></i></td>
                </tr>
  <%--    
                <tr>
                        <fieldset>
                            <legend>Afegir Foto: </legend>
                            <table>
                                <tr>
                                <td><form:label for="fileData" path="fileData">Fitxer</form:label><br />
                                </td>
                                <td><form:input path="fileData" id="image" type="file" /></td>
                            </tr>
                            <tr>
                                <td><br />
                                </td>
                            </tr></table>
                        </fieldset> 
                </tr>--%>
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


<%@include file="bottom.jsp" %>
