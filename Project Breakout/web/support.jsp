<%-- 
    Document   : support
    Created on : Dec 19, 2017, 10:09:28 AM
    Author     : Fredr
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="WEB-INF/jspf/head.jspf" %>
<%@include file="WEB-INF/jspf/header.jspf" %>


<h1 class="northwood center">CONTACTEER HET SUPPORTTEAM</h1>
     <div class="row">
    <form class="col s12 m12 l12">
      <div class="row">
        <div class="input-field col s6 m6 l6">
          <i class="material-icons prefix">account_circle</i>
          <input id="icon_prefix" type="text" class="validate">
          <label for="icon_prefix">_ACCOUNT Name_</label>
        </div>
        <div class="input-field col s6 m6 l6">
          <i class="material-icons prefix">phone</i>
          <input id="icon_telephone" type="tel" class="validate">
          <label for="icon_telephone">Telephone</label>
        </div>
      </div>
 
<div class="row">
    <div class="input-field col s12 m12 l12">
    <label for="onderwerp">Onderwerp:</label>
    <input type="text" id="onderwerp" placeholder="Een korte beschrijving van je supportaanvraag" required/>
    </div>
</div>
    <div class="row">
    <div class="input-field col s12 m12 l12">
    <label for="bericht"><strong>Bericht:</strong></label>
    <textarea id="bericht"></textarea>
    </div>
         </div>
</div>
    </form>


<%@include file="WEB-INF/jspf/footer.jspf" %>
     </body>

