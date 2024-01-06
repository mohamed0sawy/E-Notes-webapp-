<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
<title>login page</title>
<%@ include file="all_component/allcss.jsp" %>
</head>
<body>
    <% session.removeAttribute("user"); %>
    <%@ include file="all_component/navbar.jsp" %>
    <div class="container-fluid div-color">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card mt-5 md-6">
                    <div class="card-header text-center text-white bg-custom">
                        <i class="fa-solid fa-right-to-bracket fa-3x"></i>
                        <h4> Login </h4>
                    </div>
                    <c:choose>
                        <c:when test="${not empty sessionScope['loginfail']}">
                            <div class="alert alert-danger" role="alert">${sessionScope['loginfail']}</div>
                            <%
                                session.removeAttribute("loginfail");
                            %>
                        </c:when>
                        <c:when test="${not empty sessionScope['loginMustMsg']}">
                            <div class="alert alert-danger" role="alert">${sessionScope['loginMustMsg']}</div>
                            <%
                                session.removeAttribute("loginMustMsg");
                            %>
                        </c:when>
                    </c:choose>
                    <div class="card-body">
                        <form method="post" action="logUser">
                          <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" name="userEmail">
                          </div>
                          <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" name="userPassword">
                          </div>
                          <button type="submit" class="btn btn-primary badge-pill btn-block">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="all_component/footer.jsp" %>
</body>
</html>