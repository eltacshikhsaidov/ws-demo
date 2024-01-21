let socket = new WebSocket('ws://172.105.85.188:8080/ws');
let stompClient = Stomp.over(socket);
console.log('connected - hello world');

stompClient.connect({}, function () {
    stompClient.subscribe('/topic/activity', function (message) {
        let request = JSON.parse(message.body);
        console.log('Response :', request);

        if (request['action'] === 'REFRESH') {
            showPopupMessage('Restored all data');
            location.reload();
        } else {
            $("#item-" + request['docNo']).remove();
            showPopupMessage('Response details updated');
        }
    });
});

function showPopupMessage(message) {
    let popupMessage = $('#popupMessage');
    popupMessage.text(message).fadeIn();

    setTimeout(function () {
        popupMessage.fadeOut();
    }, 1500);
}

function removeItem(docNo) {
    if (confirm('Məlumatı silmək istədiyinizdən əminsiniz?')) {
        fetch(`/remove/${docNo}`, {
            method: 'GET',
        })
            .then(response => {
                if (response.ok) {
                    console.info("row with id: ", 'item-', docNo, ' removed')
                } else {
                    console.error('Failed to remove item.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}
