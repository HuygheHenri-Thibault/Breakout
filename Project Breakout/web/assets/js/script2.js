/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
    hidePathsIslandMap();
    hidePathsCrossroadsMap();
    $(".continents").click(function() {
        var myClass = $(this).attr("id");
        console.log(myClass);
        $(".succesor").html(myClass);
    
    switch($(".succesor").text()) {
    case "leftupperIsland":
        console.log("LEFT UPPER CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/mapTextured.html"); 
        break;
    case "rightUpperIsland":
        console.log("RIGHT UPPER CLICKED");
        break;
    case "oceania":
        console.log("OCEANIA CLICKED");
        window.location.assign("http://localhost:8080/Project_Breakout/index4.html"); 
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
    
});

var hidePathsIslandMap = function(){
    $("#PathToLonelyRock").hide();
    $("#PathToExplodingMountain").hide();
    $("#PathToSignPost").hide();
    $("#PathToDragonsDen").hide();
    $("#PathToSpire").hide();
    $("#PathToTheBlueKeep").hide();
};

var hidePathsCrossroadsMap = function(){
    $("#pathLighthouse").hide();
    $("#pathOasis").hide();
    $("#pathMines").hide();
};
