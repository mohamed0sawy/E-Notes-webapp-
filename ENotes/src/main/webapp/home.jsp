<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
<title>home page</title>
<%@ include file="all_component/allcss.jsp" %>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope['user']}">
            <c:set var="loginMustMsg" value="Please Login first." scope="session" />
            <c:url var="redirectURL" value="login.jsp" />
            <c:redirect url="${redirectURL}" />
        </c:when>
    </c:choose>
    <div class="container-fluid p-0">
        <%@ include file="all_component/navbar.jsp" %>
        <div class="card text-center">
            <div class="card-body">
                <img alt="" src="img/edit.png" class="img-fluid ml-5 mb-4" style="width:400px">
                <h1 class="mb-4" style="color:#0277bd">START TAKING YOUR NOTES</h1>
                <a href="addNotes.jsp" class="btn btn-primary mr-4 mb-4">Start Here</a>
            </div>
        </div>
    </div>



    <%@ include file="all_component/footer.jsp" %>
</body>
</html>