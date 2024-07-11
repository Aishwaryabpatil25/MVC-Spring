<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Image Upload and Download</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-dark" style="background-color: black;">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="/crisisManagement/logo/xworklogo.png" alt="xworkz" width="140" height="70">
        </a>
        <a class="navbar-brand text-white" href="index.jsp"><b>Dashboard</b></a>

        <div class="dropdown ml-3">
            <a class="nav-link  text-white" href="#" id="crisisDropdown"  data-toggle="dropdown" >
                <b><h5>Crisis Complaints</h5></b>
            </a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="CrisisComplaintRise.jsp">CrisisComplaintRise</a>
                <a class="dropdown-item" href="crisisComplaintRiseList">CrisisViewComplaintRise</a>
            </div>
        </div>

        <div class="ml-auto d-flex align-items-center">
            <img src="${pageContext.request.contextPath}${sessionScope.profile}" width="70" height="70" class="rounded-circle profile-image" id="profileImage" onclick="toggleDropdown()">
            <div class="dropdown-menu dropdown-menu-right" id="dropdownMenu">
                <a class="dropdown-item" href="CrisisProfileEdit.jsp">Edit Profile</a>
                <a class="dropdown-item" href="CrisisPasswordReset.jsp">Crisis Password Reset</a>
                <a class="dropdown-item" href="ProfileView.jsp">View</a>
            </div>
        </div>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function toggleDropdown() {
        var dropdownMenu = document.getElementById("dropdownMenu");
        if (dropdownMenu.style.display === "none" || dropdownMenu.style.display === "") {
            dropdownMenu.style.display = "block";
        } else {
            dropdownMenu.style.display = "none";
        }
    }

    // Close the dropdown menu if clicked outside
    window.onclick = function(event) {
        if (!event.target.matches('.profile-image')) {
            var dropdownMenu = document.getElementById("dropdownMenu");
            if (dropdownMenu.style.display === "block") {
                dropdownMenu.style.display = "none";
            }
        }
    }
</script>
</body>
</html>
