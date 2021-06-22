<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SupBartering - Bartering Service</title>

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
                <a class="navbar-brand" href="#">SupBartering</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">
                            <input id="searchInput" name="textinput" placeholder="Search" class="form-control input-md" type="text">
                        </a>
                    </li>
                    <li class="isLoggedin">
                        <a href="object_add.jsp">Add Object</a>
                    </li>
                    <li class="isLoggedin">
                        <a href="user_objects.jsp">Your Objects</a>
                    </li>
                    <li class="isLoggedout">
                        <a href="user_registration.jsp">Registration</a>
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
                <br>
                <div id="signIn" class="isLoggedout">
                    <p class="lead">Sign in</p>
                    <form id="signInForm" class="form-horizontal" method="POST" 
                          enctype="application/x-www-form-urlencoded" 
                          action="/SupBartering-war/UserConnection">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-5 control-label" for="textinput">Username</label>  
                                <div class="col-md-7">
                                    <input id="textinput" name="username" placeholder="" class="form-control input-md" type="text" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-5 control-label" for="passwordinput">Password</label>
                                <div class="col-md-7">
                                    <input id="passwordinput" name="password" placeholder="" class="form-control input-md" type="password" required>
                                </div>
                            </div>
                            
                            <div class="alert alert-danger isLogInFailed">
                                <strong>Credential Error!</strong>
                            </div>
                            
                            <center>
                                <input type="submit" class="btn btn-lg btn-info" value="Sign In">
                            </center>
                        </fieldset>
                        <br>
                    </form>
                </div>
                
                <div id="priceRange">
                    <p class="lead">Price-range</p>

                    <div class="list-group">
                        <a href="#" class="list-group-item priceInput" price="10">0-20€</a>
                        <a href="#" class="list-group-item priceInput" price="30">20-40€</a>
                        <a href="#" class="list-group-item priceInput" price="50">40-60€</a>
                        <a href="#" class="list-group-item priceInput" price="70">60-80€</a>
                        <a href="#" class="list-group-item priceInput" price="90">80-100€</a>
                        <a href="#" class="list-group-item priceInput" price="110">100-120€</a>
                        <a href="#" class="list-group-item priceInput" price="130">120-140€</a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">

                <h2>
                    <a href="#">Lastest Products</a>
                </h2>

                <br>

                <div class="row">

                    <div id="productListing">
                        
                    </div>

                </div>
                <div class="col-sm-4 col-lg-4 col-md-4">
                    <h4><a href="#">Statistics</a>
                    </h4>
                    <p>
                        <li> Users : <span id="usersCounted"></span>
                        <li> Objects : <span id="objectsCounted"></span>
                    </p>
                </div>

                <div class="col-sm-4 col-lg-4 col-md-4">
                    <h4>
                        <a href="#">About SupBartering</a>
                    </h4>
                    <p>
                        SupBartering provides a bartering service for the users. So you can share your objects with ease.
                    </p>
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
        $.ajax({
            url: "/SupBartering-war/admin", 
            success: isLoggedIn,
            error: isLoggedOut
        });

        $.ajax({
            url: "/SupBartering-war/ProductServlet", 
            success: updateProductList
        });
        
        $.ajax({
            url: "/SupBartering-war/User", 
            success: function(result){
                $("#usersCounted").html(result.usersCounted);
            }
        });
        
        $.ajax({
            url: "/SupBartering-war/ProductServlet?stats=true", 
            success: function(result){
                $("#objectsCounted").html(result.productsCounted);
            }
        });
        
        $("#signInForm").submit(signIn);
        $("#searchInput").keypress(function(e){
            if(e.which === 13) {
                searchProduct();
            }
        });
        $(".priceInput").click(function(e){
            filterProduct($(this).attr("price"));
        });
    </script>
</body>

</html>
