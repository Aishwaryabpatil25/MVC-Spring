
function validateEmail() {
    var emailInput = document.getElementById("email").value;
    var emailError = document.getElementById("emailError");
    var sendButton = document.getElementById("submitBtn");

    $.ajax({
        type: "GET",
        url: "/api/validateEmail",
        data: { email: emailInput },
        success: function(response) {
            emailError.innerHTML = response;
            emailError.style.color = 'green';
            sendButton.removeAttribute("disabled");
        },
        error: function(xhr, status, error) {
            emailError.innerHTML = xhr.responseText;
            emailError.style.color = 'red';
            sendButton.setAttribute("disabled", "");
        }
    });
}


function validatePhone() {
    var phoneInput = document.getElementById("phone").value;
    var phoneError = document.getElementById("phoneError");
    var sendButton = document.getElementById("submitBtn");

    $.ajax({
        type: "GET",
        url: "/api/validatePhone",
        data: { phone: phoneInput },
        success: function(response) {
            phoneError.innerHTML = response;
            phoneError.style.color = 'green';
            sendButton.removeAttribute("disabled");
        },
        error: function(xhr, status, error) {
            phoneError.innerHTML = xhr.responseText;
            phoneError.style.color = 'red';
            sendButton.setAttribute("disabled", "");
        }
    });
}


function validatePassword() {
    var passwordInput = document.getElementById("password").value;
    var passwordError = document.getElementById("passwordError");
    var sendButton = document.getElementById("submitBtn");

    var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

    if (passwordPattern.test(passwordInput)) {
        passwordError.innerHTML = "Password is valid";
        passwordError.style.color = 'green';
        sendButton.removeAttribute("disabled");
    } else {
        passwordError.innerHTML = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit";
        passwordError.style.color = 'red';
        sendButton.setAttribute("disabled", "");
    }
}
