const CUS_ID_REGEX = /^(C)[0-9]{3}$/;
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_ADDRESS_REGEX = /^[A-Za-z0-9 ]{5,}$/;
const CUS_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationAddress;
var validationSalary;

$("#customer-id").keyup(function (e) {
    let value = $("#customer-id").val();
    if (value.length == 1) {
        $("#save-customer").attr('disabled',true);
        $("#customer-id").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#customer-id").css('border', '2px solid green');
        } else {
            $("#customer-id").css('border', '2px solid red');
        }
    }});

$("#customer-name").keyup(function (e) {
    let value = $("#customer-name").val();
    if (value.length == 0) {
        $("#save-customer").attr('disabled',true);
        $("#customer-name").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#customer-name").css('border', '2px solid green');
        } else {
            $("#customer-name").css('border', '2px solid red');
        }
    }});

$("#customer-address").keyup(function (e) {
    let value = $("#customer-address").val();
    if (value.length == 0) {
        $("#save-customer").attr('disabled',true);
        $("#customer-address").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ADDRESS_REGEX.test(value);
        if (res) {
            validationAddress=1;
            setBtn();
            $("#customer-address").css('border', '2px solid green');
        } else {
            $("#customer-address").css('border', '2px solid red');
        }
    }});

$("#customer-tp").keyup(function (e) {
    let value = $("#customer-tp").val();
    if (value.length == 0) {
        $("#save-customer").attr('disabled',true);
        $("#customer-tp").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validationSalary=1;
            setBtn();
            $("#customer-tp").css('border', '2px solid green');
        } else {
            $("#customer-tp").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationAddress==1 && validationSalary==1){
        $("#save-customer").attr('disabled',false);
        $("#updateCustomer").attr('disabled',false);
    }
}
//////////////////////////////////ITEM////////////////////////////////////

const ITEM_ID_REGEX = /^(P)[0-9]{3}$/;
const ITEM_NAME_REGEX = /^[A-Za-z ]{4,}$/;
const numbersOnlyRegex = /^[0-9]+$/;
const ITEM_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationPrice;
var validationQTY;


$("#btnSaveItem").attr('disabled',true);
$("#btnUpdateItem").attr('disabled',true);

$("#txtItemId").keyup(function (e) {
    let value = $("#txtItemId").val();
    if (value.length == 1) {
        $("#btnSaveItem").attr('disabled',true);
        $("#txtItemId").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#txtItemId").css('border', '2px solid green');
        } else {
            $("#txtItemId").css('border', '2px solid red');
        }
    }});

$("#txtItemdec").keyup(function (e) {
    let value = $("#txtItemdec").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#txtItemdec").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#txtItemdec").css('border', '2px solid green');
        } else {
            $("#txtItemdec").css('border', '2px solid red');
        }
    }});

$("#txtItemUnitPrice").keyup(function (e) {
    let value = $("#txtItemUnitPrice").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#txtItemUnitPrice").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_SALARY_REGEX.test(value);
        if (res) {
            validationPrice=1;
            setBtn();
            $("#txtItemUnitPrice").css('border', '2px solid green');
        } else {
            $("#txtItemUnitPrice").css('border', '2px solid red');
        }
    }});

$("#txtItemQty").keyup(function (e) {
    let value = $("#txtItemQty").val();
    if (value.length == 0) {
        $("#btnSaveItem").attr('disabled',true);
        $("#txtItemQty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validationQTY=1;
            setBtn();
            $("#txtItemQty").css('border', '2px solid green');
        } else {
            $("#txtItemQty").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationPrice==1 && validationQTY==1){
        $("#btnSaveItem").attr('disabled',false);
        $("#btnUpdateItem").attr('disabled',false);
    }
}
///////////////ORDERS////////////////////////////
