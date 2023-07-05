<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	<!-- reference our style sheet -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	
</head>

<body>


<div id="wrapper">

   <div id="header">

       <h2> CRM - Customer Relationship Manager </h2>

   </div>

</div>

<div id="container">

   <div id="content">
   
   <!-- put new button: Add Customer -->
   
   <input type ="button" value="Add Customer" 
   onclick="window.location.href='showFormForAdd'; return false;"
   class="add-button"
   />
   
   <!-- while clicking this link  return false will cancel the default behaviour of the browser -->
   
   <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>
   
   
   
   <!--  add out html tabl.coe here -->
   
   <table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    
     <!--  loop over and print our customers -->
     
     <c:forEach var="tempCustomer" items="${customers}">
     
     <!-- construct an "update" link with customer id 
     
     Output of updateLink:-
     http://localhost:8080/Web_Customer_Tracker_Hyberanate_Spring_MVC/customer/showFormForUpdate?customerId=1
     
     -->
     <c:url var="updateLink" value="/customer/showFormForUpdate">
        <c:param name="customerId" value="${tempCustomer.id}"/> 
     </c:url>
     
     <!-- construct an "delete" link with customer id -->
      <c:url var="deleteLink" value="/customer/delete">
        <c:param name="customerId" value="${tempCustomer.id}"/> 
     </c:url>
     
     <tr> 
         <td>${tempCustomer.firstName}</td>
         <td>${tempCustomer.lastName}</td>
         <td>${tempCustomer.email}</td>
         <td>
         <!-- display the update link -->
         <a href="${updateLink}">Update</a>
         |
         <!-- display the delete link -->
         <a href="${deleteLink}" 
         onclick="if(!confirm('Are you sure you want to delete this customer?')) return false">
         <!-- to catch the occasions where cancel is clicked and return false when it happens to disab;e link -->
         Delete</a>
         </td>
     </tr>
     
     </c:forEach>
    
    
   </table>
   
   </div>

</div>


</body>

</html>









