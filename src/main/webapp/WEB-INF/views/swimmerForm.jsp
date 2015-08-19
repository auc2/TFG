<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


        <c:choose>
            <c:when test="${swimmer.getId()>0}">
                <h2>Modificar nedador</h2>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmers/${swimmer.getId()}"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="ediu.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:when>
            <c:otherwise>
                <h2>Crear nou nedador</h2>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmers"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="addu.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:otherwise>
        </c:choose>
        <br>

        <form:form method="${method}" action="${action}" modelAttribute="swimmer" enctype="multipart/form-data">
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

                    <td><form:label path="photo">Foto: </form:label></td>
                    <td><form:input path="photo" type="file"/> <i><form:errors path="photo"></form:errors></i></td>
                </tr>

                  <br>
                    <tr>
                    <td><form:label path="group"><b>Grup:  </b></form:label></td>
                            <ul>
                                      <td>
                                       <div id ="optionsform">
                                       
                                            <select name="groupId" >

                                                 <c:if test="${not empty swimmer.getGroup()}">
                                                        <option value="${swimmer.getGroup().getId()}">Grup Actual: ${swimmer.getGroup().getSessionHour()} - ${swimmer.getGroup().getTeacher().getTeacherName()} -  ${swimmer.getGroup().getLevel()}  </option>
                                                 </c:if>
                                                <option value="9999"> sense classe </option><%--null value, no grup--%>
                                                
                                                <c:if test="${not empty groups}">
                                                    <c:forEach items="${groups}" var="group">
                                                     <c:choose>
                                                       <c:when  test="${not empty swimmer.getGroup()}">
                                                          <c:choose>
                                                                <c:when test="${swimmer.getGroup().getId() != group.getId()}">

                                                                     <option value="${group.getId()}">${group.getSessionHour()} - ${group.getTeacher().getTeacherName()} -  ${group.getLevel()}    </option>
                                                                </c:when>

                                                                <c:otherwise>
                                                                </c:otherwise>
                                                           </c:choose>
                                                     </c:when>       
                                                        <c:otherwise>
                                                               <option value="${group.getId()}">${group.getSessionHour()} - ${group.getTeacher().getTeacherName()} -  ${group.getLevel()}    </option>       
                                                        </c:otherwise>
                                                       </c:choose>                    

                                                    </c:forEach>
                                                 </c:if>
                                            </select>
                                                                          
                                         </div>
                                     </td>
                           </ul>
                    </tr>                               

                 <tr>
                    <td><input type="submit" id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
   

<%@include file="bottom.jsp" %>
