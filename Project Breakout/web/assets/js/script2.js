/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
    hidePathsIslandMap();
    hidePathsCrossroadsMap();
    $("#leftupperIsland").on("click", function(){
       alert("user clicked found") 
    });
     $("#oceania").on("click", function(){
       alert("user clicked found") 
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