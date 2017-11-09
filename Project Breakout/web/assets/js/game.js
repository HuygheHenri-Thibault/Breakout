/* OWN CODE */
var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);

function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

socket.onmessage = function(evt) {
  console.log(evt.data);
};

socket.onopen = function () {
  //sendMessage(JSON.stringify({"flppn": 3}));
}

function startGame() {
  var messageObj = {type: "startGame", playerAmount: prompt("How many players")};
  sendMessage(messageObj);
}

$(document).ready(function() {
  startGame();
});
