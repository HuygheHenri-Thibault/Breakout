function redirectSingle() {
  window.location = "campaignSelection.html";
}

function redirectCoop() {
  window.location = "coopCampaigns.html";
}

$(document).ready(function() {
  $(".single").on("click", redirectSingle);
  $(".party").on("click", redirectCoop);
});
