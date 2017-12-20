function loggedIn() { // TODO: do this with JSP pages instead???
  $.ajax({url: "CheckLoggedIn", success: function(result){
    $("#user a.dropdown-button").html(result+"<i class='material-icons right'>arrow_drop_down</i>");
    $(".nav-wrapper").append("<ul id='user-options' class='dropdown-content'>"+
    "<li><a class='green-text' href='play.jsp'>Play</a></li></ul>")
    if(result === "Guest") {
      $(".nav-wrapper ul#user-options")
      .append("<li><a href='login.html' class='black-text'>Login</a></li>"+
      "<li><a href='register.html' class='black-text'>Register</a></li>")
      $(".login").hide();
    } else {
      $(".nav-wrapper ul#user-options")
      .append("<li><a href='userPage.jsp' class='black-text'>Account</a></li>"+
      "<li class='divider'></li>"+
      "<li><a href='LogOutUser' class='red-text'>Log out</a></li>")
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
    if(document.getElementById("register-form") !== null) {
      document.getElementById("register-form").submit();
    } else {
      document.getElementById("login-form").submit();
    }
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

function loginQuote() {
  var lines = ["This is where the fun begins", "Hasta la vista baby",
  "Member breakout? I member breakout", "Yo dawg I heard you like breakout"];
}

function enableSecondDropdown(){
    if ($('select[name="modus"]').val() === "sp"){
        alert('yup');
        $('select[name="score"]').prop("disabled", false);
        $('select[name="score"]').material_select();
    }else{
        $('select[name="score"]').prop("disabled", true);
        $('select[name="score"]').material_select();
    }
}

$(document).ready(function() {
  console.log("script.js is loaded");
  $('select[name="modus"]').material_select();
  $('select[name="score"]').material_select();
  $('select[name="modus"]').on("change", enableSecondDropdown);
  // Init Page
  loggedIn();
  $('.modal').modal();

  $("#login-area button").on("click", checkBeforeSignIn);
  $("#login-area form").on("submit", checkBeforeSignIn);
  $("#register-area button").on("click", checkBeforeSignIn);
  $("#register-area form").on("submit", checkBeforeSignIn);
});
