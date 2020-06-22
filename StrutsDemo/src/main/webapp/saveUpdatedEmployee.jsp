<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <html:form action="/saveUpdatedEmployee" method="post" >
 		Id :  <html:text property="employeeId" readonly="true" disabled="true" /><br>
 		
        Name: <html:text property="name" value="${employee.name}"/><br>
        
        salary: <html:text property="salary" value ="${employee.salary}" /><br><br>
        
 							<html:submit value="Update"/>
         
   </html:form>
   
</body>
</html>