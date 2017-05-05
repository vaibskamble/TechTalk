<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
	.container
{
background-color: #edf7f7;
}
	
	</style>
</head>
<body class="container">

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
    response.sendRedirect("Login.jsp");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
    sessionID = session.getId();
}
%>
<h3>Hi <%=userName %>,</h3>
<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<input type="submit" value="Logout" >
</form>
<div>
	<center>
		<h1>Admin Dashboard</h1>
        <h2>
        	<a href="anew">Add TechTalk</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="alist">List All TechTalks</a>
        	
        </h2>
	</center>
    <div align="center">
		<c:if test="${techTalk != null}">
			<form action="aupdate" method="post">
        </c:if>
        <c:if test="${techTalk == null}">
			<form action="ainsert" method="post">
        </c:if>
        <table border="1" cellpadding="5" background="gray">
            <caption>
            	<h2>
            		<c:if test="${techTalk != null}">
            			Edit TechTalk
            		</c:if>
            		<c:if test="${techTalk == null}">
            			Add TechTalk
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${techTalk != null}">
        			<input type="hidden" name="id" value="<c:out value='${techTalk.id}' />" />
        		</c:if>      
        		  <tr>
                <th>Date: </th>
                <td>
                	<input type="text" name="date" size="45"
                			value="<c:out value='${techTalk.date}' />"
                		/>
                </td>
            </tr>
            <tr>      
            <tr>
                <th>Title: </th>
                <td>
                	<input type="text" name="title" size="45"
                			value="<c:out value='${techTalk.title}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>Description: </th>
                <td>
                	<input type="text" name="description" size="45"
                			value="<c:out value='${techTalk.description}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>Presenter: </th>
                <td>
                	<input type="text" name="presenter" size="45"
                			value="<c:out value='${techTalk.presenter}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
        </div>
    </div>	
</body>
</html>
