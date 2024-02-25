<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
</head>
<body>
	<div class="container">
		<h1>Enter Todo Details</h1>
		<form:form method="post" modelAttribute="todo">
			<fieldset class="mb-3">
				Name: <form:input type="text" path="name" required="required"/>
				<form:errors path="name" cssClass="text-warning"/>	
			</fieldset>
			<fieldset class="mb-3">
			    Due Date: <form:input type="text" class="datepicker" path="dueDate" required="required"/>
			    <form:errors path="dueDate" cssClass="text-warning"/>
			</fieldset>
			<input type="submit" class="btn btn-success"/>
		</form:form>
	</div>
	<script>
    $(function() {
        // Initialize the Datepicker widget
        $(".datepicker").datepicker({
            dateFormat: 'yy-mm-dd' // Set the date format to match the expected format
        });
    });
</script>
	
</body>
</html>
