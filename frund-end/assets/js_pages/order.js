let allCustomers;
let allItems;

loadAllCusDet();
loadAllItemDet();

$('#txtCash').val("0");
$('#txtDiscount').val("0");
$('#txtBalnce').val("0");

function loadAllCusDet(){
    $.ajax({
        url : "http://localhost:8080/app/customers",
        success : function(res){
            allCustomers = $(res);

            let optionCus;

            for(let i = 0; i < allCustomers.length; i++){
                let id = allCustomers[i].id;
                optionCus += '<option value="' + id + '">' + id + '</option>';
            }

            $('#selCusId').append(optionCus);
        }
    });
}



function loadAllItemDet(){
    $.ajax({
        url : "http://localhost:8080/app/items",
        success : function(res){
            allItems = $(res);

            let optionItem;

            for(let i = 0; i < allItems.length; i++){
                let code = allItems[i].code;
                optionItem += '<option value="' + code + '">' + code + '</option>';
            }

            $('#selItemId').append(optionItem);
        }
    });
}



function getItemDetails() {
    let rows = $("#order-tbl-body").children().length;
    var cart = [];

    for (let i = 0; i < rows; i++) {
        let itCode = $("#order-tbl-body").children().eq(i).children(":eq(0)").text();
        let avQty = $("#order-tbl-body").children().eq(i).children(":eq(3)").text();
        let itQty = $("#order-tbl-body").children().eq(i).children(":eq(4)").text();
        let itPrice = $("#order-tbl-body").children().eq(i).children(":eq(2)").text();
        cart.push({code: itCode, avQty:avQty, qty: itQty, price: itPrice});
    }

    return cart;
}

$('#selectCusID').change(function() {
    let id = $(this).val();

    for(let i = 0; i < allCustomers.length; i++){
        if(allCustomers[i].id == id){
            $('#orderCusName').val(allCustomers[i].name);
            $('#orderCusAddres').val(allCustomers[i].address);
            $('#orderCusTp').val(allCustomers[i].salary);
        }
    }
});

$('#selItemId').change(function() {
    let code = $(this).val();

    for(let i = 0; i < allItems.length; i++){
        if(allItems[i].code == code){
            $('#orderItemDesc').val(allItems[i].name)
            $('#orderItemPrice').val(allItems[i].price)
            $('#orderQty').val(allItems[i].qty)
        }
    }
});

$("#btnAddOrder").click(function () {

    let code = $("#selItemId").val();
    let description = $("#orderItemDesc").val();
    let itemPrice = $("#orderItemPrice").val();
    let buyQty = $("#orderQty").val();
    let avQty = $("#getQty").val();
    let total = parseFloat(itemPrice) * parseFloat(buyQty);
    $("#order-tbl-body").append(`<tr><td>${code}</td><td>${description}</td><td>${itemPrice}</td><td>${avQty}</td><td>${buyQty}</td><td>${total}</td></tr>`);

    let tot = Number($('#txtCash').val()) + total;
    let subTot = Number($('#txtCash').val()) + total - Number($('#txtDiscount').val());

    $('#total').text(tot);
    $('#subTotal').text(subTot);

});

$("#orderItemDesc").on("change paste keyup", function() {

    $("#subTotal").text(parseInt($('#total').text()) - parseInt($("#orderItemDesc").val()));

    if(parseInt($("#subTotal").text()) < 0){
        $("#subTotal").text("0");
    }

    $("#txtBalnce").val(parseInt($('#txtCash').val()) - parseInt($("#subTotal").text()));

    if(parseInt($("#txtBalnce").val()) < 0){
        $("#txtBalnce").val("0");
    }

});


$("#txtCash").on("change paste keyup", function() {

    $("#txtBalnce").val(parseInt($('#txtCash').val()) - parseInt($("#subTotal").text()));

    if(parseInt($("#txtBalnce").val()) < 0){
        $("#txtBalnce").val("0");
    }

    $("#subTotal").text(parseInt($('#total').text()) - parseInt($("#orderItemDesc").val()));

    if(parseInt($("#subTotal").text()) < 0){
        $("#subTotal").text("0");
    }

});


$('#btnPlaceOrder').click(function(){

    let orderId = $('#txtOrderId').val();
    let date = $('#txtDate').val();
    let cusId = $('#selectCusID').val();
    let itemD = getItemDetails();

    let allData = {
        orderId : orderId,
        date : date,
        cusId : cusId,
        itemDet : itemD
    }

    $.ajax({
        url: "http://localhost:8080/app/orders",
        method: "post",
        dataType: "json",
        data: JSON.stringify(allData),
        contentType: "application/json",
        success: function (resp) {

        },
        error: function (error) {

        }
    });

});
