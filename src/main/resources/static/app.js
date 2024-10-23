// 1. stompClient -> initilized w/brokerURL, refers to path '/ws' which is where our websocket server awaits for connections

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket',
});

// 2. upon successful connection, client subscribes to /topic/chat-{roomId} destination where the server will publish greeting messages
// 2.1 once the greeting message is received on that destination, it will append a paragraph element to the DOM to display the greeting message
stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    let topic = "/topic/chat-1110"
    stompClient.subscribe(topic, (message) => {
        console.log(`Message received from ${topic}:`, message.body);
        showGreeting(JSON.parse(message.body).message);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

// 3. retrieve message entered by the user and use the STOMP client to send it to the /app/hello destination (where GreetingController.greeting() will receive it)
function sendName() {
    const message = {
        senderId: 152,
        message: $("#name").val()
    }
    stompClient.publish({
        destination: "/app/chatlog/1110",
        body: JSON.stringify(message)
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});