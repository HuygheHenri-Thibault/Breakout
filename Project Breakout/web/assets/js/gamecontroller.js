/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function linkController(){
    var linkObj = {
        type: "link",
        gameId: getUrl()
    };
}

function getUrl(){
    var current_url = window.location.href;
    var url = new URL(current_url);
    var id = url.searchParams.get("gameID");
    console.log(id);
    return id;
}

$(document).ready(function(){
    console.log("DOM ready");
    linkController();
});