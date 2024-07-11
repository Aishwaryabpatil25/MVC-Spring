<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="/crisisManagement/jscript/CrisisSignUp.js"></script>


</head>
<body>
  <nav class="navbar navbar-dark "style="background-color:black">
          <div class="container-fluid">
              <div class="navbar-header">
                  <a class="navbar-brand" href="#">
                    <img src="/crisisManagement/logo/xworklogo.png" alt="xworkz" width="140" height="70">
                </a>
                &nbsp &nbsp &nbsp &nbsp
              <a class="navbar-brand text-white" href="index.jsp"><b>CrisisDashboard</b></a>
                <a class="navbar-brand text-white" href="CrisisLogin.jsp"><b>CrisisLogin</b></a>
            </div>
        </div>
    </nav>
    <div class="container mt-3">
        <div class="row justify-content-center">
            <div class="col-12 col-sm-10 col-md-8 col-lg-6">
                <div class="card p-1">
                    <div class="card-body">
                        <h2>Signup Form</h2>
                        <span style="color:red;">
                            <c:forEach items="${errors}" var="objectError">
                                ${objectError.defaultMessage}<br>
                            </c:forEach>
                          
                        </span>
                         <strong>${message}</strong>
                        <form action="submitSignup" method="post" onsubmit="return validateForm()">
                            <div class="form-group">

                                <label for="firstName">First Name:</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" value="${dto.firstName}" onblur="validateFirstName()">
                                <span class="error" style="color:red" id="firstNameError"></span>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name:</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" value="${dto.lastName}" onblur="validateLastName()">
                                <span class="error" style="color:red" id="lastNameError"></span>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" value="${dto.email}" onchange="validEmail()" onblur="validateEmail()">
                                <span class="error" style="color:red" id="emailError"></span>
                            </div>
                            <div class="form-group">
                                <label for="contactNumber">Contact Number:</label>
                                <input type="text" class="form-control" id="contactNumber" name="contactNumber" value="${dto.contactNumber}" onchange="validContactNumber()">
                                <span class="error" style="color:red" id="contactNumberError"></span>
                            </div>
                            <div class="form-group">
                                <label for="altContactNumber">Alternative Contact Number:</label>
                                <input type="text" class="form-control" id="alternativeContactNumber" name="alternativeContactNumber" value="${dto.alternativeContactNumber}" onblur="validateAltContactNumber()">
                                <span class="error" style="color:red" id="alternativeContactNumberError"></span>
                            </div>
                            <div class="form-group">
                                <label for="address">Address:</label>
                                <textarea class="form-control" id="address" name="address" onblur="validateAddress()">${dto.address}</textarea>
                                <span class="error" style="color:red" id="addressError"></span>
                            </div>
                            <div class="form-group form-check">
                                <input type="checkbox" class="form-check-input" id="agreement" name="agreement" onblur="validateAgreement()">
                                <label class="form-check-label" for="agreement">Agree</label>
                                <span class="error" style="color:red" id="agreementError"></span>
                            </div>

                            <button type="submit" class="btn btn-dark" id="submitBtn" >Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
   <script>
          function validEmail() {
              console.log("Validate email");
              let email = document.getElementById("email").value.trim();

              console.log(email);
              let error = document.getElementById("emailError");
              const request = new XMLHttpRequest();
              request.open("GET", "http://localhost:8080/crisisManagement//validateEmail/"+email);
              request.send();
              request.onload = function() {
                  var ref = this.responseText;
                  console.log(ref);
                  error.innerHTML = ref;

              }
          }


          function validContactNumber() {
              console.log("Validate contact number");
              let contactNumber = document.getElementById("contactNumber").value.trim();
              console.log(contactNumber);
              let error = document.getElementById("contactNumberError");
              const request = new XMLHttpRequest();
              request.open("GET", "http://localhost:8080/crisisManagement//validateContactNumber/"+contactNumber);
              request.send();
              request.onload = function() {
                  var ref = this.responseText;
                  console.log(ref);
                  error.innerHTML = ref;
              }
          }
  </script>

</body>
</html>
