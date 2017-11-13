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
  console.log("register.js is loaded");
  $("#passwordCheck").on("change", checkIfPasswordsMatch);
});
