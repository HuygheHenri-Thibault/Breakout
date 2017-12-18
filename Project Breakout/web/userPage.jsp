<%@page import="be.howest.ti.breakout.domain.game.User"%>
<%@page import="be.howest.ti.breakout.data.Repositories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="assets/css/user.css">
<title>Breakout</title>
<%@include file="WEB-INF/jspf/header.jspf" %>
<%
    String username = (String)session.getAttribute("username");
    User u = Repositories.getUserRepository().getUserWithUsername(username);   
%>
<div><div class="row separator"></div></div>
<main>
    <div class="row">
      <div class="col s4 offset-s4">
          <h1 class="white-text center-align"><% out.print(u.getUsername()); %></h1>
      </div>
      <div class="col s1 offset-s3">
        <a class="modal-trigger" href="#edit-user-modal"><i class="material-icons white-text small">settings</i></a>
      </div>

      <!-- MODAL START -->
      <div id="edit-user-modal" class="modal">
        <div class="modal-content med-grey white-text">
          <h4>Edit user</h4>
          <form class="accented" action="editUser" method="post">
          <div class="row">
            <div class="col s5">
                <div class="input-field">
                  <input type="email" id="email" name="email" />
                  <label for="email" class="active">Email</label>
                </div>

                <div class="input-field">
                  <input type="text" id="username" name="username" />
                  <label for="username" class="active">Username</label>
                </div>
            </div>
            <div class="col s5 offset-s1">
              <div class="input-field">
                <input type="password" id="password" name="password" />
                <label for="password" class="active">Password</label>
              </div>

              <div class="input-field">
                <input type="password" id="passwordCheck" name="passwordCheck" />
                <label for="passwordCheck" class="active">Password (again)</label>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col s6 offset-s3 input-field">
              <input type="text" id="bio" name="bio">
              <label for="bio" class="active">Bio</label>
            </div>
          </div>
          </form>
        </div>
        <div class="modal-footer dark-grey">
          <a href="#!" id="cancel-edit" class="modal-action modal-close waves-effect waves-red btn-flat">
            <i class="material-icons red-text small">cancel</i>
          </a>
          <a href="#!" id="confirm-edit" class="modal-action modal-close waves-effect waves-green btn-flat">
            <i class="material-icons green-text small">check_circle</i>
          </a>
        </div>
      </div>
      <!-- MODAL END -->

    </div>
    <div class="row">
      <div class="col s2 offset-s3">
        <p class="white-text center-align">Level <% out.print(u.getLevel()); %></p>
      </div>
      <div class="col s2">
        <p class="white-text center-align">Gems: _VALUE_</p>
      </div>
      <div class="col s2">
        <p class="white-text center-align">Gold: _VALUE_</p>
      </div>
    </div>
    <div class="row">
      <div class="col s5 offset-s1">
        <div class="card-panel"><% out.print(u.getBio()); %></div>
      </div>
      <div class="col s3 offset-s2">
        <div class="card-panel">_CLANNAME_</div>
      </div>
    </div>
    <div class="row spacing">
      <div class="col s4 offset-s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle profile-picture">
      </div>
      <div id="recent-activity" class="col s6">
        <ul class="collection with-header">
          <li class="collection-item">
            <h2 class="center-align">Recent activity</h2>
          </li>
          <li class="collection-item">one</li>
          <li class="collection-item">two</li>
          <li class="collection-item">three</li>
          <li class="collection-item">four</li>
          <li class="collection-item">five</li>
        </ul>
      </div>
    </div>
    <div class="row spacing">
      <div class="col s1 offset-s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle">
      </div>
      <div class="col s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle">
      </div>
      <div class="col s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle">
      </div>
      <div class="col s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle">
      </div>
      <div class="col s1">
        <img src="https://tinyurl.com/y8zv9vu8" alt="" class="responsive-img circle">
      </div>
    </div>
  </main>
<div><div class="row separator"></div></div>
<%@include file="WEB-INF/jspf/footer.jspf" %>
  <script type="text/javascript" src="assets/js/user.js"></script>
</body>

</html>