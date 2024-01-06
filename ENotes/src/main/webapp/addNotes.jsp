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
        <h1 class="text-center my-3"> Add Your Note </h1>
        <div class="container">
        <c:choose>
            <c:when test="${not empty sessionScope['added']}">
                <div class="alert alert-success" role="alert">${sessionScope['added']}</div>
                <%
                    session.removeAttribute("added");
                %>
            </c:when>
        </c:choose>
            <div class="row">
                <div class="col-md-12">
                    <form method="post" action="addNote">
                      <div class="form-group">
                        <label class="h5" for="noteTitle">Note Title</label>
                        <input type="text" class="form-control" id="noteTitle" placeholder="Enter your title here..." name="noteTitle" required="required">
                      </div>
                      <div class="form-group">
                        <label class="h5" for="noteContent">Note Content</label>
                        <textarea rows="11" cols="" class="form-control" placeholder="Enter your content here..." id="noteContent" name="noteContent" required="required" style="resize: none;"></textarea>
                      </div>
                      <div class="container text-center mt-3 mb-5">
                        <button type="submit" class="btn btn-primary">Add Note</button>
                      </div>
                    </form>
                </div>
                    </div>
        </div>
    </div>


    <%@ include file="all_component/footer.jsp" %>
</body>
</html>