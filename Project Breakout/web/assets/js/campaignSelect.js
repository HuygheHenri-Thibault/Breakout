function redirectSingle() {
  window.location = "campaignSelection.html";
}

function redirectCoop() {
  window.location = "multiplayerCampaigns.html";
}

$(document).ready(function() {
  $(".single").on("click", redirectSingle);
  $(".party").on("click", redirectCoop);
});
