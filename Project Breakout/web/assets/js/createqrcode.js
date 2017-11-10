/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
makeCode();

$("#text").
    on("blur", function () {
        makeCode();
    }).
    on("keydown", function (e) {
        if (e.keyCode == 13) {
            makeCode();
        }
    });
});
 var qrcode = new QRCode("qrcode");

function makeCode () {
    var text = document.getElementById("text");

    if (!text.value) {
        alert("Input a text");
        text.focus();
        return;
    }

    qrcode.makeCode(text.value);
}