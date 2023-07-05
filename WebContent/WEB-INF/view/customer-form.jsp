<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>Save Customer</title>
	<!-- reference our style sheet -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
	
</head>

<body>

<div id="wrapper">
  <div id="header">
  
      <h2>CRM - Customer Relationship Manager</h2>
      
  </div>
</div>

<div id="container">

<h3>Save Customer</h3>

<!-- modelAttribute="customer" is used to prepopulate the data on form loading for update part -->

<form:form action="saveCustomer" modelAttribute="customer" method="POST">

<!-- 

Right now it's gonna send data over to saveCustomer,but we need to add some additional information here.
What we need to do here is to associate this data with a given customer.And we can accomplish that by adding a hidden form
field and providing that customer's id.
So I'll set up form colon hidden,and here the actual path will be id.
So this is a hidden form field.So when this from is loadedthey actually talk to the customer obviously.
They'll say customer dot get id,place it here in this hidden form field.
Then when they do a submit they'll actually submit this data by saying customer dot set id with the appropriate data.
This line is very important.Without this line, you'd actually lose context or you actually lose the id of the original customer.
So you need to use this line to actually track the customer just so the back end system knows which customer
to form the update operation on. 

-->


<!-- need to associate this data with customer id -->
<form:hidden path="id"/>

<table>
 <tbody>
  <tr>
      <td><label>First Name:</label></td>
       <td><form:input path="firstName" /></td>
   </tr>
  <tr>
      <td><label>Last Name:</label></td>
       <td><form:input path="lastName" /></td>
   </tr>
  <tr>
      <td><label>Email:</label></td>
       <td><form:input path="email" /></td>
   </tr>
   <tr>
      <td><label></label></td>
       <td><input type="submit" value="Save" class="save" /></td>
   </tr>
  
  
  </tbody>



</table>


</form:form>

<div style="clear ; both;"></div>

<p>
       
       <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>

</p>
</body>

</html>









