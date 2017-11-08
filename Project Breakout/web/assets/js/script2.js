/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
    hidePathsIslandMap();
    hidePathsCrossroadsMap();
    var myClass = "oceania";
    $(".continents").click(function() {
        var myClass = $(this).attr("id");
        console.log(myClass);
        $(".succesor").html(myClass);
    
    switch($(".succesor").text()) {
    case "leftupperIsland":
        console.log("LEFT UPPER CLICKED");
        break;
    case "rightUpperIsland":
        console.log("RIGHT UPPER CLICKED");
        break;
    case "oceania":
        console.log("OCEANIA CLICKED");
        break;
    case "rightDown":
       console.log("RIGHT DOWN CLICKED");
        break;
    default:
        myClass = "leftupperIsland";
}
    });


});

var hidePathsIslandMap = function(){
    $("#PathToVillage").hide();
    $("#PathToLonelyRock").hide();
    $("#PathToExplodingMountain").hide();
    $("#PathToSignPost").hide();
    $("#PathToDragonsDen").hide();
    $("#PathToSpire").hide();
    $("#PathToTheBlueKeep").hide();
};

var hidePathsCrossroadsMap = function(){
    $("#pathToLighthouse").hide();
    $("#pathOasis").hide();
    $("#pathRaiderCamp").hide();
    $("#pathMines").hide();
};
