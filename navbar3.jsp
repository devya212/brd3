<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/bootstrap.css">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<div class="container-fluid">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <!-- <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    
    </div>
 -->
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1 container-fluid">
      <ul class="nav navbar-nav">
      <li class="active"><a href="#" target="_parent">Welcome ${uid}<span class="glyphicon glyphicon-user"></span> <span class="sr-only">(current)</span></a></li>
        <!-- <li class="active"><a href="add.jsp" target="_parent">Add Details<span class="glyphicon glyphicon-plus"></span> <span class="sr-only">(current)</span></a></li>
        <li><a href="delete.jsp">Delete Details<span class="glyphicon glyphicon-trash"></span></a></li>
        <li><a href="updateform.jsp">Edit Details<span class="glyphicon glyphicon-edit"></span></a></li>
        <li><a href="CustomerController?b1=1">View All<span class="glyphicon glyphicon-list"></span></a></li>
         -->
         <li><a href="CustomerController?b1=checkerview">View All<span class="glyphicon glyphicon-list"></span></a></li>
        
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
       <li><a href="CustomerController?b1=logout">Logout<span class="glyphicon glyphicon-off"></span></a></li>
       
      </ul>
    </div> 
  </div>
</nav>
</div>
</body>
</html>