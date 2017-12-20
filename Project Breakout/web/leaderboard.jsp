<%@page import="java.util.Map"%>
<%@page import="be.howest.ti.breakout.domain.game.User"%>
<%@page import="be.howest.ti.breakout.domain.game.MultiPlayerHighscore"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<%@include file="WEB-INF/jspf/header.jspf" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <main>
    <div class="row">
          <div class="col s3 offset-s1 northwood">
              <h5>Singleplayer Total score:</h5>
                <ul>
                    <c:forEach var="tsc" items="${totalscore}">
                        <li><c:out value="${tsc.getUsername()}"/> has <c:out value="${tsc.getTotalScore()}"/></li>
                    </c:forEach>
                </ul>
          </div>
          <div class="col s3 northwood">
              <h5>Singleplayer highscore:</h5>
                <ul>
                    <c:forEach var="sps" items="${SPscores}">
                        <li><c:out value="${sps.getUser().getUsername()}"/> has <c:out value="${sps.getScore()}"/></li>
                    </c:forEach>
                </ul>
          </div>
        <div class="col s3 northwood">
              <h5>Multiplayer highscore:</h5>
                <ul class="collapsible" data-collapsible="accordion">
                    <c:forEach var="mps" items="${MPscores}">
                        <li><div class="collapsible-header"><c:out value="${mps.getTotalScore()}"/></div>
                            <div class="collapsible-body">
                        <c:forEach var="player" items="${mps.getPlayerList().entrySet()}">
                            -<c:out value="${player.getKey().getUsername()}"/> scored <c:out value="${player.getValue()}"/></br>
                        </c:forEach>
                            </div>
                    </c:forEach>
                </ul>
          </div>
    </div>
  </main>
<%@include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
 