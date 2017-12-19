<%@page import="be.howest.ti.breakout.data.Repositories"%>
<%@page import="be.howest.ti.breakout.domain.game.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<title>Breakout</title>
<%@include file="WEB-INF/jspf/header.jspf" %>
<%
    String username = (String)session.getAttribute("username");
    User u = Repositories.getUserRepository().getUserWithUsername(username);
%>
<div><div class="row separator"></div></div>
  <main>
    <div class="row">
      <div class="col s2 offset-s5 crystal-btn-container">
        <a href="game.html" class="laser white-text crystal-btn-link">
          <p class="btn-text">Arcade</p>
          <img src="assets/media/btn-blue-1.png" alt="arcade" class="crystal-btn">
        </a>
      </div>
    </div>
    <div class="row">
      <div class="col s2 offset-s5 crystal-btn-container">
        <a href="campaignTypeSelect.html" class="laser white-text crystal-btn-link">
          <p class="btn-text">Campaign</p>
          <img src="assets/media/btn-blue-1.png" alt="campaign" class="crystal-btn">
        </a>
      </div>
    </div>
      <div class="row">
      <div class="col s2 offset-s5 crystal-btn-container">
        <a href="War.html" class="laser white-text crystal-btn-link">
          <p class="btn-text">War</p>
          <img src="assets/media/btn-blue-1.png" alt="War" class="crystal-btn">
        </a>
      </div>
    </div>
  </main>
  <div><div class="row separator"></div></div>
  <%@include file="WEB-INF/jspf/footer.jspf" %>
</body>

</html>
