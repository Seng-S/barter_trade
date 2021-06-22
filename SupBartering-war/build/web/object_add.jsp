<%-- 
    Document   : object_add
    Created on : Feb 14, 2016, 2:19:50 PM
    Author     : Oudomsieng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:if test="${sessionScope.user == null}">
   <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SupBartering - Bartering Service</title>

	<link href="css/scss.css" rel="stylesheet">
	
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/shop-homepage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">SupBartering</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="isLoggedin">
                        <a href="user_objects.jsp">Your Objects</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-md-3">
                <div id="signOut" class="isLoggedin">
                    <p class="lead">Hello there!<br><a id="username" href="user_edit.jsp">Edit User</a></p>
                    <center>
                        <a href="UserConnection?logout=true">
                            <button type="button" class="btn btn-lg btn-info">Sign out</button>
                        </a>
                    </center>
                </div>  
            </div>
            <div class="col-md-9">

                <h2>
                    <a href="#">Add New Object</a>
                </h2>

                <br>

                <div class="row">
                    <form id="addObject" method="POST" 
                          enctype="multipart/form-data" 
                          action="/SupBartering-war/admin/CreateProductServlet">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Name</label>
                                    <input type="text" name="name" placeholder="Enter name Here.." class="form-control" required>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Price</label>
                                    <input type="number" step="0.01" min="0"  name="price" placeholder="Enter price Here.." class="form-control" required>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label>Picture</label>
                                    <input type="file" name="file" id="file" class="form-control" accept="image/*" required>
                                </div>
                            </div>					
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" placeholder="Enter Description.." rows="3" class="form-control" required></textarea>
                            </div>
                            
                            <div class="alert alert-danger isAddObjectError">
                                <strong>Make sure your inputs are valid</strong>
                            </div>
                            <input type="submit" class="btn btn-lg btn-info" value="Add">					
                        </div>
                    </form> 
                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; SupBartering - 2016</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <script src="js/jscript.js"></script>

    <script>
        $(".isAddObjectError").hide();
        $("#addObject").submit(addProduct);
    </script>
</body>

</html>
