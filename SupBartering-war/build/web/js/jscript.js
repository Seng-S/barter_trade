var logInSuccess = function logInSuccess(result){
	if(result.connected){
		return isLoggedIn();
	}
	return isLogInFailed();
};

var addObjectSuccess = function addObjectSuccess(result){
	window.location.href = '/SupBartering-war/user_objects.jsp';
}

var addObjectError = function addObjectError(xhr,status,error){
	return $(".isAddObjectError").show();
}

var signUpSuccess = function signUpSuccess(result){
	window.location.href = '/SupBartering-war/index.jsp';
};

var signUpError = function signUpError(xhr,status,error){
	return $(".isSignUpError").show();
}

var logInError = function logInError(xhr,status,error){
	return isLogInFailed();
}

var isLogInFailed = function isLogInFailed(){
	$(".isLogInFailed").show();
};

var isLoggedIn = function isLoggedIn(result){
	$(".isLoggedin").show();
	$(".isLoggedout").hide();
	$(".isLogInFailed").hide();
};

var isLoggedOut = function isLoggedOut(xhr,status,error){
	if(xhr.status === 404){
		return isLoggedIn();
	}
	$(".isLoggedout").show();
	$(".isLoggedin").hide();
	$(".isLogInFailed").hide();
};

var updateProductList = function updateProductList(products){
	$("#productListing").html("");
	products.forEach(function each(product){
		$("#productListing").append(
			"<div class=\"col-sm-4 col-lg-4 col-md-4\">" +
				"<div class=\"thumbnail\">" +
					"<img src=\"UploadedImages/" + product.picture + "\">" +
					"<div class=\"caption\">"+
						"<h4 class=\"pull-right\">" + product.price + "€</h4>"+
						"<h4><a href=\"#\">" + product.name + "</a></h4>"+
						"<p> By " + product.user.firstName + " " + product.user.lastName + "</p>"+
						"<p> Contact " + product.user.email + "</p>"+
						"<p> Description: " + product.description + "</p>"+
					"</div>" +
				"</div>" +
			"</div>"
		);
	});
};

var getMyProductList = function getMyProductList(products){
	$("#productListing").html("");
	products.forEach(function each(product){
		$("#productListing").append(
			"<div class=\"col-sm-4 col-lg-4 col-md-4\">" +
				"<div class=\"thumbnail\">" +
					"<img src=\"UploadedImages/" + product.picture + "\">" +
					"<div class=\"caption\">"+
						"<h4 class=\"pull-right\">" + product.price + "€</h4>"+
						"<h4><a href=\"object_edit.jsp?id=" + product.id + "\">" + product.name + "</a></h4>"+
						"<p> Description: " + product.description + "</p>"+
						"<button type=\"button\" class=\"btn btn-danger\" onClick=\"deleteProduct(" + product.id + ")\">Delete</button>"+
					"</div>" +
				"</div>" +
			"</div>"
		);
	});
};

var deleteProduct = function deleteProduct(id){
	$.ajax({
        url: "/SupBartering-war/admin/DeleteProductServlet",
        type: "POST",
        data: {"productId": id},
        success: function(result){
			location.reload();
		},
		error: function(xhr,status,error){
			location.reload();
		}
    });
};

var signIn = function signIn(event){
	event.preventDefault();
	var form = $("#signInForm");
	submitForm(form, logInSuccess, logInError);
};

var editUser = function editUser(event){
	event.preventDefault();
	var form = $("#editUserForm");
	submitForm(form, signUpSuccess, signUpError);
};

var signUp = function signUp(event){
	event.preventDefault();
	var form = $("#signUpForm");
	submitForm(form, signUpSuccess, signUpError);
}

var addProduct = function addProduct(event){
	event.preventDefault();
	var form = $("#addObject");
	submitFormData(form, addObjectSuccess, addObjectError);
}

var editProduct = function editProduct(event){
	event.preventDefault();
	var form = $("#updateObject");
	submitFormData(form, addObjectSuccess, addObjectError);
}

var submitFormData = function submitFormData(form, success, error){
	var formData = new FormData(form[0]);
	$.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: formData,
		cache: false,
		contentType: false,
		processData: false,
        success: success,
		error: error
    });
};

var submitForm = function submitForm(form, success, error){
	$.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
		contentType: form.attr("enctype"),
        data: form.serialize(),
        success: success,
		error: error
    });
};

var searchProduct = function searchProduct(){
	var searchInput = $("#searchInput").val();
	$.ajax({
		url: "/SupBartering-war/ProductServlet?description=" + searchInput, 
		success: updateProductList
	});
};

var filterProduct = function filterProduct(price){
	var searchInput = $("#searchInput").val();
	$.ajax({
		url: "/SupBartering-war/ProductServlet?description=" + searchInput + "&price=" + price, 
		success: updateProductList
	});
};
















