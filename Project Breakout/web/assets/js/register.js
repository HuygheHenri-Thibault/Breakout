function checkIfPasswordsMatch(e){
    e.preventDefault();
    if (!passwordsMatch()){
        $("main section div button").attr('disabled', 'disabled');
    }else{
        $("main section div button").prop("disabled", false);
    }
};

function passwordsMatch(){
    return ($("#password").val() === $("#passwordCheck").val());
};

$(document).ready(function() {
  console.log("DOM is ready");
  $("#passwordCheck").on("change", checkIfPasswordsMatch);
  // document.documentElement.style.setProperty(`--accent-color`, 'red');
  // TODO: Nice idea for accent color change but needs to be done another way
  // Maybe with add & remove class?
});


