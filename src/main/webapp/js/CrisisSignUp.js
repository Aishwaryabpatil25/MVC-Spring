function validateFirstName() {
    var firstNameInput = document.getElementById("firstName").value;
    var firstNameError = document.getElementById("firstNameError");
    var sendButton = document.getElementById("submitBtn");


    var namePattern = /^[a-zA-Z\s]*$/;

    if (namePattern.test(firstNameInput) && firstNameInput.length >= 1 && firstNameInput.length <= 20) {
//        firstNameError.innerHTML = "First name is valid";
//        firstNameError.style.color = 'green';
//        sendButton.removeAttribute("disabled");
    } else {
        firstNameError.innerHTML = "First name must be between 1 and 20 letters, no numbers";
        firstNameError.style.color = 'red';
        sendButton.setAttribute("disabled", "");
    }
}



function validateLastName() {
    var lastNameInput = document.getElementById("lastName").value;
    var lastNameError = document.getElementById("lastNameError");
    var sendButton = document.getElementById("submitBtn");


    var namePattern = /^[a-zA-Z\s]*$/;

    if (namePattern.test(lastNameInput) && lastNameInput.length >= 1 && lastNameInput.length <= 20) {
//        lastNameError.innerHTML = "Last name is valid";
//        lastNameError.style.color = 'green';
//        sendButton.removeAttribute("disabled");
    } else {
        lastNameError.innerHTML = "Last name must be between 1 and 20 letters, no numbers";
        lastNameError.style.color = 'red';
        sendButton.setAttribute("disabled", "");
    }
}


function validateEmail() {
           var emailInput = document.getElementById("email").value;
           var emailError = document.getElementById("emailError");
           var sendButton = document.getElementById("submitBtn");


           var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
           if (emailPattern.test(emailInput)) {
//               emailError.innerHTML = "Email is valid";
//               emailError.style.color = 'green';
//               sendButton.removeAttribute("disabled");
           } else {
               emailError.innerHTML = "Please enter a valid email address";
               emailError.style.color = 'red';
               sendButton.setAttribute("disabled", "");
           }
       }

        function validateContactNumber() {
            var contactNumberInput = document.getElementById("contactNumber").value;
            var contactNumberError = document.getElementById("contactNumberError");
            var sendButton = document.getElementById("submitBtn");


            var numberPattern = /^\d{10}$/;
            if (numberPattern.test(contactNumberInput)) {
//                contactNumberError.innerHTML = "Contact number is valid";
//                contactNumberError.style.color = 'green';
//                sendButton.removeAttribute("disabled");
            } else {
                contactNumberError.innerHTML = "Contact number must be 10 digits";
                contactNumberError.style.color = 'red';
                sendButton.setAttribute("disabled", "");
            }
        }

        function validateAltContactNumber() {
            var altContactNumberInput = document.getElementById("alternativeContactNumber").value;
            var altContactNumberError = document.getElementById("alternativeContactNumberError");
            var sendButton = document.getElementById("submitBtn");


            var numberPattern = /^\d{10}$/;
            if (altContactNumberInput.length === 0 || numberPattern.test(altContactNumberInput)) {
//                altContactNumberError.innerHTML = "Alternative contact number is valid";
//                altContactNumberError.style.color = 'green';
//                sendButton.removeAttribute("disabled");
            } else {
                altContactNumberError.innerHTML = "Alternative contact number must be 10 digits";
                altContactNumberError.style.color = 'red';
                sendButton.setAttribute("disabled", "");
            }
        }


        function validateAddress() {
            var addressInput = document.getElementById("address").value;
            var addressError = document.getElementById("addressError");
            var sendButton = document.getElementById("submitBtn");

            if (addressInput.length >= 2 && addressInput.length <= 100) {
//                addressError.innerHTML = "Address is valid";
//                addressError.style.color = 'green';
//                sendButton.removeAttribute("disabled");
            } else {
                addressError.innerHTML = "Address must be between 1 and 100 characters";
                addressError.style.color = 'red';
                sendButton.setAttribute("disabled", "");
            }
        }
function validateAgreement() {
    var agreementCheckbox = document.getElementById("agreement");
    var agreementError = document.getElementById("agreementError");
    var sendButton = document.getElementById("submitBtn");

    if (agreementCheckbox.checked) {
//        agreementError.innerHTML = "Agreement is checked";
//        agreementError.style.color = 'green';
//        sendButton.removeAttribute("disabled");
    } else {
        agreementError.innerHTML = "Please agree to continue";
        agreementError.style.color = 'red';
        sendButton.setAttribute("disabled", "");
    }

//     function validateForm() {
//                // Add logic to validate captcha
//                var response = grecaptcha.getResponse();
//                if(response.length == 0) {
//                    document.getElementById('captchaError').innerHTML = 'Captcha is required';
//                    return false;
//                }
//                return true;
//            }
}


