function loggedIn() {
  fetch("CheckLoggedIn")
  .then(res=>res.text())
  .then(function(res){
    $("#user a.dropdown-button").html(res+"<i class='material-icons right'>arrow_drop_down</i>");
  });
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
  console.log("DOM is ready");
  // Init modal
  $('.modal').modal();

  $("#login-area button").on("click", checkBeforeSignIn);
  $("#login-area form").on("submit", checkBeforeSignIn);
  $("#register-area button").on("click", checkBeforeSignIn);
  $("#register-area form").on("submit", checkBeforeSignIn);
  loggedIn();
  // document.documentElement.style.setProperty(`--accent-color`, 'red');
  // TODO: Nice idea for accent color change but needs to be done another way
  // Maybe with add & remove class?
});
