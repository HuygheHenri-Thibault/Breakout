var express = require('express')
  , http = require('http');
var app = express();
var path = require('path');
var port = 3000;
var server = http.createServer(app);
var io = require('socket.io').listen(server);
server.listen(port)
app.use(express.static('public'));
app.use(express.static('src/views', {index: 'qrcode.html'}))
app.use(express.static('src/views'));
app.use(express.static('js'));

app.get('/books', function(req,res){
  res.send('Hello books ')
});
console.log(__dirname);

io.on('connection', function(){
  console.log('Gebruiker is geconnecteerd');
});
/*app.get('*',function (req, res) {
        res.redirect('src/views');
});*/
/*app.get('/', function(req,res){
  res.send('Hello world ')

});*/
