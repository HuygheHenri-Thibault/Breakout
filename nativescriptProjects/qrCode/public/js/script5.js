var qrcode = new QRCode("qrcode");

function makeCode () {
    var text = document.getElementById("text");

    if (!text.value) {
        one.alert("Input a text");
        text.focus();
        return;
    }

    qrcode.makeCode(text.value);
}

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
