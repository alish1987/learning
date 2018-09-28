<html>
<title>World Adventure Airline</title>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="../../resources/css/theme.css">
    <link rel="stylesheet" href="../../resources/css/normalize.css">
</head>
<body>
<h2>Welcome to World Adventure Airline</h2>
<div class="container">
    <div class="title">Add a Passenger</div>
    <%
        if (request.getAttribute("errors") != null) {
    %>
    <fieldset id="error_fieldset">
        <legend align="center">Errors</legend>
        <ul>
            <% if (request.getAttribute("firstName_error") != null) { %>
            <li class="error">FirstName error</li>
            <%}%>
            <% if (request.getAttribute("lastName_error") != null) { %>
            <li class="error">LastName error</li>
            <%}%>
            <% if (request.getAttribute("date_format_error") != null) { %>
            <li class="error">Date of Birth Invalid</li>
            <%}%>
        </ul>
    </fieldset>
    <%}%>
    <fieldset>
        <legend>Passenger Detail</legend>
        <form action="AddPassenger" method="post">
            <div class="inputField">
                <label for="first-name" class="inputLabel">First name:</label>
                <input type="text" name="first-name">
            </div>
            <div class="inputField">
                <label for="last-name" class="inputLabel">Last name:</label>
                <input type="text" name="last-name">
            </div>
            <div class="inputField">
                <label for="dob" class="inputLabel">Date Of Birth:</label>
                <input type="text" name="dob">
            </div>
            <div class="inputField">
                <label for="gender" class="inputLabel">Gender:</label>
                <select name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>
            <div class="inputField" id="submitField">
                <input type="submit" id="submitBtn" value="Add New Passenger"></input>
            </div>
        </form>
    </fieldset>

</div>
</body>
</html>
