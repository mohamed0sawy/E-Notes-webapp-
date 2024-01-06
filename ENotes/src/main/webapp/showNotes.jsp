<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
<title>Show Notes page</title>
<%@ include file="all_component/allcss.jsp" %>

<!-- JavaScript function to set the noteId value -->
    <script>
        function setNoteId(id) {
            document.getElementById('noteIdInput').value = id;
        }

        function deleteNotes() {
            var noteId = document.getElementById('noteIdInput').value;
            window.location.href = 'deleteNotes?note_id=' + noteId;
        }
    </script>
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
        <c:choose>
            <c:when test="${not empty sessionScope['deleted']}">
                <div class="alert alert-success" role="alert">${sessionScope['deleted']}</div>
                <%
                    session.removeAttribute("deleted");
                %>
            </c:when>
        </c:choose>
        <div class="container">
            <h2 class="text-center">All Notes</h2>
            <div class="row">
                <div class="col-md-12">
                <c:choose>
                    <c:when test="${empty requestScope['list']}">
                        <div class="container mt-5">
                            <div class="d-flex align-items-center justify-content-center" style="height: 490px;">
                                <div class="text-center">
                                    <p class="display-4 fw-bold">There are no notes yet</p>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
                <c:forEach var="note" items="${requestScope.list}">
                    <div class="card mt-3">
                        <img alt="" src="img/edit.png" class="card-img-top mt-2 mx-auto" style="max-width: 100px;">
                        <div class="card-body p-4">
                            <h5 class="card-title ">${note.title}</h5>
                            <p>${note.content}</p>
                            <p>
                                <b class="text-success">Published By: ${user.name} </b> </br> <b class="text-primary"> </b>
                            </p>
                            <p>
                                <b class="text-success">Published Date: ${note.dateFormatted} </b> </br> <b class="text-primary"> </b>
                            </p>
                            <div class="container text-center mt-2">
                                <a class="btn btn-danger" onclick="setNoteId(${note.id})" data-toggle="modal" data-target="#deleteModal" >Delete</a>
                                <a href="editNotes?note_id=${note.id}" class="btn btn-primary">Edit</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>


<!-- Modal -->
  <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Delete Confirm</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body text-center">
          <h4 class="text-danger">Are you sure you want to delete this note !</h4>
        </div>
        <div class="modal-footer">
          <input type="hidden" id="noteIdInput">
          <!-- Use the hidden input value in the href -->
          <a class="btn btn-danger" onclick="deleteNotes()">Delete</a>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

    <%@ include file="all_component/footer.jsp" %>
</body>
</html>