<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/crisisManagement/jscript/CrisisLogin.js"></script>
</head>
<body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="xworkz" width="140" height="70">
                </a>
                &nbsp &nbsp &nbsp &nbsp
                <a class="navbar-brand text-white" href="index.jsp"><b>CrisisDashboard</b></a>
                <a class="navbar-brand text-white" href="CrisisSignUp.jsp"><b>CrisisSignUp</b></a>
                <a class="navbar-brand text-white" href="CrisisPasswordReset.jsp"><b>CrisisPasswordReset</b></a>
            </div>
        </div>
    </nav>
    <div class="container mt-3">
        <div class="row justify-content-center">

            <div class="col-12 col-sm-8 col-md-6 col-lg-4">
                <div class="card p-1">
                    <div class="card-body">
                        <h2>Login</h2>
                         <span style="color:red;">
                         <c:forEach items="${errors}" var="objectError">
                         ${objectError.defaultMessage}<br>
                         </c:forEach>
                         ${dto}
                         </span>
                        <form action="login" method="post">
                        ${message}
                        ${msg}
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" value="${dto.email}" onblur=validateEmail() required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" class="form-control" id="password" name="password" value="${dto.password}" validatePassword() required>
                            </div>
                             <div class="text-center">
                             <button type="submit" class="btn btn-dark" style="width: 200px;" ${action =='edit'?'disabled':' '}>Login</button>
                             </div>
                        </form>
                         <div class="mt-2">
                         <a href="CrisisForgotPassword.jsp">Forgot Password?</a>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                         <a href="CrisisSignUp.jsp">SignUp</a>
                         </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
