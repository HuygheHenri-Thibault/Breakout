var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);

function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

socket.onmessage = function(messageRecieved) {
  console.log(messageRecieved.data);
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
