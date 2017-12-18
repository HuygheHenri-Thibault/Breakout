/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
  console.log("script2.js is loaded");
  
    $("#PathToVillage").on("click", function (){
        $("ul#slide-out").toggle();
        //Materialize.fadeInImage('#img1');
        //fadeinImage <a href="#!" class="btn" onclick="Materialize.fadeInImage('#image-test')">Click Me</a>
    });
    
    hidePathsIslandMap();
    hidePathsCrossroadsMap();
    
    $(".continents").click(function() {
        var myClass = $(this).attr("id");
        console.log(myClass);
        $(".succesor").html(myClass);

    switch($(".succesor").text()) {
    case "leftupperIsland":
        console.log("LEFT UPPER CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/raiderCamp.html");
        break;
    case "rightUpperIsland":
        console.log("RIGHT UPPER CLICKED");
        break;
    case "oceania":
        console.log("OCEANIA CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/BreakersBayCampaign.html");
        break;
    case "rightDown":
       console.log("RIGHT DOWN CLICKED");
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
       alert("user wants to go to alorum");
    });
 $('g#toBreakersBay').on("click", function () {
       alert("user wants to go to Breakers Bay");
    });
    
$('.toastButton').on('click',toast);
$('.toastButton').click(Materialize.toast("You've earned the Achievement: -PLACEHOLDER- !", 4000));

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
    $('ul#slide-out').hide();
};

var hidePathsCrossroadsMap = function(){
    $("#pathLighthouse").hide();
    $("#pathOasis").hide();
    $("#pathMines").hide();
};
