<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#emp {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 80%;
  margin-left: 150px;
}

#emp td, #emp th {
  border: 1px solid #ddd;
  padding: 8px;
}

#emp tr:nth-child(even){background-color: #f2f2f2;}

#emp tr:hover {background-color: #ddd;}

#emp th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #0000e6;
  color: white;
}
</style>
<script type="text/javascript">
        var subcategory = {
            Hindi: ["Hindi-Lit", "History", "Geog"],
            English: ["Grammer", "Eng-Lit", "Poem"],
            Computer: ["BCA", "BBA", "B.Tech."]
        	
        }

        function makeSubmenu(value) {
            if (value.length == 0) document.getElementById("categorySelect").innerHTML = "<option></option>";
            else {
                var dept = "";
                for (categoryId in subcategory[value]) {
                	dept += "<option>" + subcategory[value][categoryId] + "</option>";
                }
                document.getElementById("dept").innerHTML=dept;
            }
        }
 
        function displaySelected() {
            var country = document.getElementById("category").value;
            var city = document.getElementById("categorySelect").value;
            alert(country + "\n" + city);
        }

        function resetSelection() {
            document.getElementById("category").selectedIndex = 0;
            document.getElementById("categorySelect").selectedIndex = 0;
        }
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
 <html:form action="/addEmployee1" method="post" >
<h1>Add New Employee :</h1>
<table id="emp">
<tr>
    <td>    Enter your Name: <html:text property="name" /><br></td>
</tr>       
 <tr>
    <td>           Enter your salary: <html:text property="salary"/><br></td>
 </tr>
 <tr>
    <td>      
      	    Department:  <select id="category" size="1" onchange="makeSubmenu(this.value)">
				<option value="" disabled selected>Choose Category</option>
				<option>Hindi</option>
				<option>English</option>
				<option>Computer</option>
				</select>
				
				    <select id="dept" name="dept" size="1" class="EmployeeForm" >
				<option value="" disabled selected>Choose Subcategory</option>
				<option></option>
				</select></td>
</tr>
 </table>
 							<html:submit value="Submit"/>
         
   </html:form>
</body>
</html>