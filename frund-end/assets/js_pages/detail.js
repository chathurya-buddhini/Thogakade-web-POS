function loadAllOrderDetails(){

    $(`#order-detail-body`).empty();

    $.ajax({
            url: "http://localhost:8080/app/detail",
            method: "GET",
            success: function (resp) {
                console.log("Success: ", resp);
                for (const detail of resp) {
                    $(`#order-detail-body`).append(`<tr>
                    <td>${detail.orderID}</td>
                    <td>${detail.itemCode}</td>
                    <td>${detail.qty}</td>
                    <td>${detail.unitPrice}</td>
                   </tr>`);
                }
            },
            error: function (error) {
                console.log("Error: ", error);
            }
        });

}

loadAllOrderDetails();


