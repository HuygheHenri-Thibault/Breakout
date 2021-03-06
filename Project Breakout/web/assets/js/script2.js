/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
  console.log("script2.js is loaded");
  
    $("#PathToVillage").on("click", function (){
        $("ul#slide-out").toggle();
    });
   
    hidePathsIslandMap();
    hidePathsCrossroadsMap();
    $('.succesor').hide();
    $(".continents").click(function() {
        var myClass = $(this).attr("id");
        $(".succesor").html(myClass);

    switch($(".succesor").text()) {
    case "leftupperIsland":
        console.log("LEFT UPPER CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/raiderCamp.html");
        break;
    case "rightUpperIsland":
        console.log("RIGHT UPPER CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/factionwarMap.html");
        break;
    case "oceania":
        console.log("OCEANIA CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/BreakersBayCampaign.html");
        break;
    case "rightDown":
       console.log("RIGHT DOWN CLICKED");
       window.location.assign("http://localhost:8080/Project_Breakout/multiplayerCampaigns.html");
        break;
    default:
         console.log("nothing clicked yet");
    }
    });
    
    $("g").on("click", function(){
        console.log($(this).attr("id"));
        var selectedLevel = $(this).attr("id");
        $(".succesor").html(selectedLevel);
        switch($(".succesor").text()){
            case "selectLighthouse":
                console.log("Lighthouse selected");
                break;
            case "selectRaider":
                console.log("Raider selected");
                break;
            case "selectMines":
                console.log("Mines selected");
                break;
            case "selectWargest":
                console.log("Wargest selected");
                break;
        }

    });

 $('g#toAlorum').on("click", function () {
       window.location.assign("http://localhost:8080/Project_Breakout/multiplayerCampaigns.html");
    });
 $('g#toBreakersBay').on("click", function () {
       window.location.assign("http://localhost:8080/Project_Breakout/BreakersBayCampaign.html");
    });
    
$('.toastButton').on('click',toast);
$('.toastButton').click(Materialize.toast("You've earned the Achievement: Starting a journey !", 4000));

});

var toast = function(){
  console.log("Toast clicked");
  var $toastContent = $('<span>Achievement get!</span>');
  Materialize.toast($toastContent, 5000);
  hidePathsIslandMap();
};

var hidePathsIslandMap = function(){
    $("#PathToLonelyRock").toggle();
    $("#PathToExplodingMountain").toggle();
    $("#PathToSignPost").toggle();
    $("#PathToDragonsDen").toggle();
    $("#PathToSpire").toggle();
    $("#PathToTheBlueKeep").toggle();  
    //hide sidebar with story
    $('ul#slide-out').toggle();
};

var hidePathsCrossroadsMap = function(){
    $("#pathLighthouse").toggle();
    $("#pathOasis").toggle();
    $("#pathMines").toggle();
};

//socket
var socket = function() {
  // Private
  var url = "ws://localhost:8080/Project_Breakout/campaignpoint";
  var socket = new WebSocket(url);
  socket.onopen = function() {
    console.log("socket works");
      //socket.sendMessage((JSON.stringify({"campaignTest": 500})));
  };
  socket.onmessage = function(messageRecieved) {
    var message = JSON.parse(messageRecieved.data);
    switch (message.type) {
      case "posistionOnMap":
        break;
      case "campaignInfo":
        break;
    }
  };
  // Public
  function sendMessage(message) {
    socket.send(JSON.stringify(message));
  }
  return {sendMessage};
}();

var comms = function() {
  // Private
  var getCampaignInfo = function() {
    var messageObj = {type: "campaignInfo"};
    socket.sendMessage(messageObj);
  };
  // Public
  var getUpdate = function() {
  };
  return {/*TODO*/};
}();