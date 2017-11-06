function loggedIn() {
  return document.cookie === "";
  // TODO: make this function check the specific cookie for the user
}

function preventJSInjection(text) {
    var safeText = text.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    return safeText;
};

$(document).ready(function() {
  console.log("DOM is ready");
  // document.documentElement.style.setProperty(`--accent-color`, 'red');
  // TODO: Nice idea for accent color change but needs to be done another way
  // Maybe with add & remove class?
});
