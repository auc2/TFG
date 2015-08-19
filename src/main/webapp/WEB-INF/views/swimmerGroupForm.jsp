<%@include file="holder.jsp" %>
<%@page contentType="Text/html" pageEncoding="UTF-8"%>


        <c:choose>
            <c:when test="${swimmerGroup.getId()>0}">
                <h2><span>Modificar grup</h2>
                <c:set var="method" value="PUT"/>
                <c:set var="action" value="/swimmerGroups/${swimmerGroup.getId()}"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="edig.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:when>
            <c:otherwise>
                <h2><span>Crear nou grup</h2>
                <c:set var="method" value="POST"/>
                <c:set var="action" value="/swimmerGroups"/>
                <p class="infopost">Posted <span class="date">on 11 sep 2015</span> by <a href="#">Admin</a> 
                <div class="clr"></div>
                <div class="img"><img src="addu.png" width="200" height="210" alt="" class="fl" /></div>
                <div class="post_content">
            </c:otherwise>
        </c:choose>
        <br>


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
                    <td>
                                <form:select path="level" items="${levels}" />
                    </td>
                </tr>

                <tr>
                    <td><form:label path="teacher"><b>Monitor </b></form:label></td>

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
                </tr>
<tr></tr>
<tr></tr>

    <tr>
                     <c:if test="${not empty swimmersInGroup}">
                        <td><form:label path="swimmers"><b>Nedadors assignats al grup:  </b></form:label></td>

                                <c:forEach var="swimmer" items="${swimmersInGroup}">
                                    
                                    <c:choose>
                                        <c:when test="${not empty swimmersInGroup}">
                                           <tr>
                                                <td>
                                                    <div id ="optionsform">

                                                        <td><b>Nedador:</b> ${swimmer.getSwimmerName()}</td>
                                                        
                                                        <td>
                                                            <input type ="checkbox" name ="swimmersListId" value="${swimmer.getId()}" checked/>    
                                                        </td>    

                                                    </div>
                                                </td>
                                            </tr>                                           
                                        </c:when>
                                        
                                        <c:otherwise>
                                         <div id ="optionsform">
                                              <td><form:label path="swimmers">Grup sense nedadors assignats.</form:label></td>
                                            </div>                                         
                                        </c:otherwise>
                                    </c:choose>

                                </c:forEach>      
                     </c:if>                      
                    </tr>

                <tr>
                    <c:choose>
                        <c:when test="${not empty swimmers}">
                                <td><form:label path="swimmers"><b>Nedadors per assignar al grup:  </b></form:label></td>
<br>
                                <c:forEach var="swimmer" items="${swimmers}">
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
                                </c:forEach>                            
                        </c:when>
                        <c:otherwise>
                                <div id ="optionsform">
                                    <br>
                                              <td><form:label path="swimmers">Tots els nedadors assignats a un grup.</form:label></td>
                                    <br>
                                </div>
                        </c:otherwise>
                    </c:choose>
                    </tr>
                <tr>
                    <td><input type="submit"  id="addButton" value="Acceptar" /></td>
                </tr>
            </table>
        </form:form>
 

<%@include file="bottom.jsp" %>
