<%@page import="java.util.Map"%>
<%@page import="be.howest.ti.breakout.domain.game.User"%>
<%@page import="be.howest.ti.breakout.domain.game.MultiPlayerHighscore"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="assets/css/leaderboard.css">
<%@include file="WEB-INF/jspf/header.jspf" %>

  <main>
    <section class="Devonshire">
        <img src="assets/media/scroll.jpg" class="scroll"/>
        <div class="row" id="leaderboard">
              <div class="col s3 offset-s1">
                  <h1>Totalscore:</h1>
                    <ul>
                        <c:forEach var="tsc" items="${totalscore}">
                            <li><c:out value="${tsc.getUsername()}"/> has <c:out value="${tsc.getTotalScore()}"/></li>
                        </c:forEach>
                    </ul>
              </div>
              <div class="col s3">
                  <h1>Singleplayer highscore:</h1>
                    <ul>
                        <c:forEach var="sps" items="${SPscores}">
                            <li><c:out value="${sps.getUser().getUsername()}"/> has <c:out value="${sps.getScore()}"/></li>
                        </c:forEach>
                    </ul>
              </div>
            <div class="col s3">
                  <h1>Multiplayer highscore:</h1>
                  <ul class="collapsible white" data-collapsible="accordion">
                        <c:forEach var="mps" items="${MPscores}">
                            <li><div class="collapsible-header"><i class="material-icons">keyboard_arrow_right</i><c:out value="${mps.getTotalScore()}"/> in total</div>
                                <div class="collapsible-body">
                            <c:forEach var="player" items="${mps.getPlayerList().entrySet()}">
                                -<c:out value="${player.getKey().getUsername()}"/> (<c:out value="${player.getValue()}"/>)</br>
                            </c:forEach>
                                </div></li>
                        </c:forEach>
                    </ul>
              </div>
        </div>
    </section>
  </main>
<%@include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
 