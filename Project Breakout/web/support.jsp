<%-- 
    Document   : support
    Created on : Dec 19, 2017, 10:09:28 AM
    Author     : Fredr
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<%@include file="WEB-INF/jspf/header.jspf" %>

<main>
<h1 class="northwood center ">CONTACTEER HET SUPPORTTEAM</h1>
     <div class="row">
    <form action="support" method="POST" class="col s10 m10 l10 supportform offset-l1">
      <div class="row">
        <div class="input-field col s6 m6 l6">
          <i class="material-icons prefix">account_circle</i>
          <input id="icon_prefix" type="text" name="username" class="validate">
          <label for="icon_prefix">_ACCOUNT Name_</label>
        </div>
        <div class="input-field col s6 m6 l6">
          <i class="material-icons prefix">phone</i>
          <input id="icon_telephone" type="tel" class="validate" name="phonenumber" placeholder="32474514896">
          <label for="icon_telephone">Telephone</label>
        </div>
      </div>
 
<div class="row">
    <div class="input-field col s12 m12 l12">
    <label for="onderwerp" name="onderwerp">Onderwerp:</label>
    <input type="text" id="onderwerp" name="subject" placeholder="Een korte beschrijving van je supportaanvraag" required/>
    </div>
</div>
    <div class="row">
    <div class="input-field col s12 m12 l12">
    <label for="bericht"><strong>Bericht:</strong></label>
    <textarea id="bericht" name="bericht"></textarea>
    </div>
         </div>
        <div class="center">
            <input type="submit" class="btn" value="Send"/></div>
</div>
    </form>
         </main>

<%@include file="WEB-INF/jspf/footer.jspf" %>
     </body>
     </html>

