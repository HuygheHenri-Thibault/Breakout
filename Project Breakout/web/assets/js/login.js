function signIn() {
  e.preventDefault();
  var username = preventJSInjection($("#username").val());
  var password = preventJSInjection($("#password").val());
  if (checkVars(username, password)) {
    // TODO: Send vars to backend
    // check signup for comment.
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

function checkVars(username, password) {
  if (username === "") {
    console.log("username can't be empty!");
    return false;
  } else if (password === "") {
    console.log("password can't be empty!");
    return false;
  }
  return true;
} // TODO: Can be done better???

function preventJSInjection(text) {
    var safeText = text.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    return safeText;
};

function checkIfPasswordsMatch(e){
    e.preventDefault();
    if (passwordsMatch()){
        $('main section div form button').prop("disabled", false);
    }
};

function passwordsMatch(){
    return ($("#password").val() === $("#passwordCheck").val());
};

$(document).ready(function() {
  console.log("DOM is ready");
  $("#login-area button").on("click", signIn);
  $("#login-area form").on("submit", signIn);
  $("#passwordCheck").on("change", checkIfPasswordsMatch);
  // document.documentElement.style.setProperty(`--accent-color`, 'red');
  // TODO: Nice idea for accent color change but needs to be done another way
  // Maybe with add & remove class?
});
