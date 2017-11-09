function clearForm() {
  $("#edit-user-modal form input").val("");
  console.log("clear");
}

function submitForm() {
  document.querySelector("#edit-user-modal form").submit();
  console.log("submit");
}

$(document).ready(function() {
  console.log("DOM is ready, User loaded");
  $("#edit-user-modal").modal('close');
  $("#cancel-edit").on("click", clearForm);
  $("#confirm-edit").on("click", submitForm);
});
