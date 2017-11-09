var express = require('express'),
    http    = require('http'),
    app     = express(),
    server  = http.createServer(app),
    port    = 8080;
var game_sockets = {};

server.listen(port);

app

  .get('/', function(req, res) {            // Set up index
    res.sendFile(__dirname + '/index.html');
  });
console.log("Server running on port: " + port);

var io = require('socket.io').listen(server);


io.sockets.on('connection', function (socket) {
  socket.on('game_connect', function(){
  console.log("Game connected");
  game_sockets[socket.id] = {
    socket: socket,
    controller_id: undefined
  };
  socket.emit("game_connected");
});
});

var game_connected = function() {
  var url = "http://172.31.14.43:8080?id=" + io.id; // of je ipv4 adres 172.31.14.43
  document.body.innerHTML += url;
  io.removeListener('game_connected', game_connected);
  alert('HEEEEH');
  alert(url);
};

io.on('game_connected', game_connected);
