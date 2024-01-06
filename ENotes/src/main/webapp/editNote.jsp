<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
<title>Edit page</title>
<%@ include file="all_component/allcss.jsp" %>
</head>
<body>
    <div class="container-fluid p-0">
        <%@ include file="all_component/navbar.jsp" %>
        <h1 class="text-center my-3"> Edit Your Note </h1>
        <div class="container">
        <c:choose>
            <c:when test="${not empty sessionScope['edited']}">
                <div class="alert alert-success" role="alert">${sessionScope['edited']}</div>
                <%
                    session.removeAttribute("edited");
                %>
            </c:when>
        </c:choose>
            <div class="row">
                <div class="col-md-12">
                    <form method="post" action="editNotes">
                        <input type="text" value="${noteId}" name="noteId" style="display: none;">
                      <div class="form-group">
                        <label class="h5" for="noteTitle">Note Title</label>
                        <input type="text" class="form-control" id="noteTitle" name="noteTitle" value="${note.title}" required="required">
                      </div>
                      <div class="form-group">
                        <label class="h5" for="noteContent">Note Content</label>
                        <textarea rows="11" cols="" class="form-control" id="noteContent" name="noteContent" required="required" style="resize: none;">${note.content}</textarea>
                      </div>
                      <div class="container text-center mt-3 mb-5">
                        <button type="submit" class="btn btn-primary">Edit Note</button>
                      </div>
                    </form>
                </div>
                    </div>
        </div>
    </div>


    <%@ include file="all_component/footer.jsp" %>
</body>
</html>