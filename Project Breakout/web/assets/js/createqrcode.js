/**
 * Created by Fredr on 10/2/2017.
 */

$(document).ready(function () {
  console.log("createQrCode.js is loaded");
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
    var qrText = document.getElementById("text");

    if (!qrText.value) {
        alert("Input a text");
        qrText.focus();
        return;
    }

    qrcode.makeCode(qrText.value);
}
