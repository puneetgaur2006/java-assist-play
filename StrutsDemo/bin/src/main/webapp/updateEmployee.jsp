<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employee</title>
</head>
<body>
	<html:form action="/updateEmployee1" method="post" >
        Enter Employee Id: <html:text property="employeeId"/><br>
        
 			<html:submit value="Get Employee"/>
   </html:form>
    
</body>
</html>