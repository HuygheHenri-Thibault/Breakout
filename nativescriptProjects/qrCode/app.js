const express = require('express');
const app = express(); // instance of express
const port =3000;
const path = require('path')

app.use(express.static('public'));
app.use(express.static('src/views', {index: 'qrcode.html'}))
app.use(express.static('src/views'));
app.use(express.static('js'));
/*app.get('/', function(req,res){
  res.send('Hello world ')

});*/
app.get('/books', function(req,res){
  res.send('Hello books ')
});
app.get('*',function (req, res) {
        res.redirect('src/views');
});
console.log(__dirname);
app.listen(port, function(){
  console.log('callback called, server started on 3000')
});
