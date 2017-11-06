function checkBeforeSignIn(e) {
  e.preventDefault();
  var username = preventJSInjection($("#username").val());
  var password = preventJSInjection($("#password").val());
  if (checkVarsIfEmpty(username, password).length > 0) {
      alert(checkVarsIfEmpty(username, password));
  }else{
      alert("All good.");
  }
}

function signUp(e) {
  e.preventDefault();
  var username = preventJSInjection($("#username").val());
  var password = preventJSInjection($("#password").val());
  if (checkVars(username, password)) {
    // TODO: Send vars to backend
    // (Probably diffrent from login right?, if not meld signIn & signUp and just send type with request.)
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

function checkIfPasswordsMatch(e){
    e.preventDefault();
    if (!passwordsMatch()){
        $("main section div form button").attr('disabled', 'disabled');
    }else{
        $("main section div form button").prop("disabled", false);
    }
};

function passwordsMatch(){
    return ($("#password").val() === $("#passwordCheck").val());
};

$(document).ready(function() {
  console.log("DOM is ready");
  $("#login-area button").on("click", checkBeforeSignIn);
  $("#login-area form").on("submit", checkBeforeSignIn);
  $("#register-area button").on("click", checkBeforeSignIn);
  $("#register-area form").on("submit", checkBeforeSignIn);
  $("#passwordCheck").on("change", checkIfPasswordsMatch);
  // document.documentElement.style.setProperty(`--accent-color`, 'red');
  // TODO: Nice idea for accent color change but needs to be done another way
  // Maybe with add & remove class?
});
