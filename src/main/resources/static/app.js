var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    var socket = new SockJS('/testing');
    stompClient = Stomp.over(socket);
    stompClient.connect();
    setConnected(true);
    console.log("Connected!");
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected!");
}

function send() {
    stompClient.send("/app/players");
    console.log("Message sent!");
}

function subscribe() {
    stompClient.subscribe('/topic/players', function (player) {
        showMessage(JSON.parse(player.body))
    });
    console.log("subscribed to players!");

    stompClient.subscribe('/queue/errors', function (error) {
        showMessage(JSON.parse(error.body))
    });
    console.log("subscribed to errors!");
}

function sendName() {
    stompClient.send("/app/player", {}, ($("#pid").val()));
}

function showMessage(message) {
console.log(message);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { send(); });
    $( "#subscribe" ).click(function() { subscribe(); });
    $( "#sendID" ).click(function() { sendName(); });
});

