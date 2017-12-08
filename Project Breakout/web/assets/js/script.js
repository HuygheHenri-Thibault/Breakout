function loggedIn() { // TODO: do this with JSP pages instead???
  $.ajax({url: "CheckLoggedIn", success: function(result){
    $("#user a.dropdown-button").html(result+"<i class='material-icons right'>arrow_drop_down</i>");
    $(".nav-wrapper").append("<ul id='user-options' class='dropdown-content light-grey'>"+
    "<li><a class='white-text green' href='game.html'>Play</a></li></ul>")
    if(result === "Guest") {
      $(".nav-wrapper ul#user-options")
      .append("<li><a class='white-text' href='login.html'>Login</a></li>"+
      "<li><a href='register.html' class='white-text'>Register</a></li>")
      $(".login").hide();
    } else {
      $(".nav-wrapper ul#user-options")
      .append("<li><a href='userPage.jsp' class='white-text'>Account</a></li>"+
      "<li class='divider'></li>"+
      "<li><a href='LogOutUser' class='white-text red'>Log out</a></li>")
      $(".no-login").hide();
    }

    $('.dropdown-button').dropdown();
  }});
}

function preventJSInjection(text) {
    var safeText = text.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    return safeText;
};

function checkBeforeSignIn(e) {
  e.preventDefault();
  var username = preventJSInjection($("#username").val());
  var password = preventJSInjection($("#password").val());
  if (checkVarsIfEmpty(username, password).length > 0) {
      alert(checkVarsIfEmpty(username, password));
  }else{
      document.getElementById("register-form").submit();
  }
}

function checkVarsIfEmpty(username, passwd) {
    var err = "";
    if (username === ""){
        err += "Username can't be empty";
    }
    if(passwd === ""){
        if(err.length > 0) {
            err += ", ";
        }
        err += "Password can't be empty";
    }
    return err;
}

$(document).ready(function() {
  console.log("script.js is loaded");
  // Init Page
  loggedIn();
  $('.modal').modal();

  $("#login-area button").on("click", checkBeforeSignIn);
  $("#login-area form").on("submit", checkBeforeSignIn);
  $("#register-area button").on("click", checkBeforeSignIn);
  $("#register-area form").on("submit", checkBeforeSignIn);
});
