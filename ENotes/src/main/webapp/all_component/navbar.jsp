<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-custom nav-custom">
  <a class="navbar-brand" href="#"><i class="fa-solid fa-book"></i> E-Notes</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home.jsp"><i class="fa-solid fa-house"></i> Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addNotes.jsp"><i class="fa-solid fa-plus"></i> Add Notes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="showNotes"><i class="fa-solid fa-list"></i> Show Notes</a>
      </li>
    </ul>

    <c:choose>
        <c:when test="${empty sessionScope['user']}">
            <a href="login.jsp" class="btn btn-light my-2 my-sm-0 mr-2" type="submit"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
            <a href="register.jsp" class="btn btn-light my-2 my-sm-0" type="submit"><i class="fa fa-user-plus"></i> Register</a>
        </c:when>
        <c:when test="${not empty sessionScope['user']}">
            <a href="" class="btn btn-light my-2 my-sm-0 mr-2" type="submit" data-toggle="modal" data-target="#exampleModal"><i class="fa-solid fa-user"></i> ${user.name}</a>
            <a href="login.jsp" class="btn btn-light my-2 my-sm-0" type="submit"><i class="fa-solid fa-right-from-bracket"></i> Logout</a>
        </c:when>
    </c:choose>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">User information</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body text-center">
          <i class="fa-solid fa-user fa-3x mb-4"></i>
          <table class="table">
            <tbody>
              <tr>
                <th scope="row">Name</th>
                <td>${user.name}</td>
              </tr>
              <tr>
                <th scope="row">Email</th>
                <td>${user.email}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</nav>