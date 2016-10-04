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
    stompClient.send("/app/hello");
    console.log("Message sent!");
}

function subscribe() {
    stompClient.subscribe('/topic/players', function (player) {
        showMessage(JSON.parse(player.body))
    });
    console.log("subscribed!")
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
});

