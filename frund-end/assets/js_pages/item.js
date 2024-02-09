

$('#btnUpdateItem').on('click',function (){
    let idval = $(`#upItemId`).val();
    searchItem(idval, function (exists) {
        if (exists) {

           updateItem();
        } else {
            clearUpdateTxt();
            alert("Code doesnot exists. Please choose a existing Code.");
        }
    });
});

function updateItem(){
        let consent = confirm("Do you really want to update this item.?");
        if (consent) {
            let code = $(`#upItemId`).val();
            let description = $(`#upItemdesc`).val();
            let unitPrice = $(`#upUnitPrice`).val();
            let qtyOnHand = $(`#upQty`).val();

            const itemupdateObj = {

                    itemCode: code,
                    itemName: description,
                    itemPrice: unitPrice,
                    itemQty: qtyOnHand
    };

    const jsonitmupdate = JSON.stringify(itemupdateObj);
        
    $.ajax({
        url: "http://localhost:8080/app/items",
        method: "PUT",
        data: jsonitmupdate,
        contentType: "application/json",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            getAllItem();
        },
        error: function (jqxhr, textStatus, error) {
            console.log("error: ", jqxhr);
            console.log("error: ", textStatus);
            console.log("error: ", error);
        }
    })
        clearUpdateTxt();
    }
    
}


$('#btnSaveItem').on('click', function () {
    let id = $('#txtItemId').val();
    searchItem(id, function (exists) {
        if (exists) {
            clearItemTxt();
            alert("Code already exists. Please choose a different Code.");
        } else {
            saveItem();
        }
    });
});

function saveItem() {
    
    let code = $(`#txtItemId`).val();
    let description = $(`#txtItemdec`).val();
    let unitPrice = $(`#txtItemUnitPrice`).val();
    let qtyOnHand = $(`#txtItemQty`).val();

            const itemObj = {
                itemCode: code,
                itemName: description,
                itemPrice: unitPrice,
                itemQty: qtyOnHand
            };

            const jsonitmObj = JSON.stringify(itemObj);

            $.ajax({
                url: "http://localhost:8080/app/items",
                method: "POST",
                data: jsonitmObj,
                contentType: "application/json",
                success: function (resp, textStatus, jqxhr) {
                    console.log("success: ", resp);
                    console.log("success: ", textStatus);
                    console.log("success: ", jqxhr);
                    getAllItem();
                    if (jqxhr.status == 201)
                        alert(jqxhr.responseText);
                        
                },
                error: function (jqxhr, textStatus, error) {
                    console.log("error: ", jqxhr);
                    console.log("error: ", textStatus);
                    console.log("error: ", error);
                }
            });

          
            clearItemTxt();

}


function searchItem(id, callback) {
    $.ajax({
        url: "http://localhost:8080/app/items",
        method: "GET",
        success: function (resp) {

                for (const item of resp) {
                    if(item.itemCode == id) {
                        callback(true);
                        return;
                    }
                }
                callback(false);
        },
        error: function (jqxhr, textStatus, error) {
            // ID does not exist
                callback(false);
                console.log("Error checking ID existence: ", jqxhr, textStatus, error);
        }
    });
}


$('#btnGetAllItem').on('click', function () {
    getAllItem();
});

function getAllItem() {
    $('#Item-body').empty();

    $.ajax({
        url : "http://localhost:8080/app/items",
        method : "GET",
        success : function (resp) {
            console.log("Success: ", resp);
            for (const item of resp) {
                console.log(item.itemCode);
                console.log(item.itemName);
                console.log(item.itemPrice);
                console.log(item.itemQty);

               $(`#Item-body`).append(`<tr>
                                <td>${item.itemCode}</td>
                                <td>${item.itemName}</td>
                                <td>${item.itemPrice}</td>
                                <td>${item.itemQty}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#update-model">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm deleteItem">Delete</button></td>
                   
                             </tr>`);
                setEvent();
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
}

let eventsBound2 = false;


function setEvent() {

    if (!eventsBound2) {
        $('#tblItem').on('click', 'tr', function () {
            var $row = $(this).closest("tr"),
                $tds = $row.find("td:nth-child(1)"),
                $ts = $row.find("td:nth-child(2)"),
                $tt = $row.find("td:nth-child(3)"),
                $tf = $row.find("td:nth-child(4)");

                $(`#upItemId`).val($tds.text());
                $(`#upItemdesc`).val($ts.text());
                $(`#upUnitPrice`).val($tt.text());
                $(`#upQty`).val($tf.text());
        });

        $('#tblItem').on('click', '.deleteItem', function (event) {

            event.stopPropagation(); // Prevent click event from reaching tr elements

            var $row = $(this).closest("tr"),
                $tds = $row.find("td:nth-child(1)");

            searchItem($tds.text(), function (exists) {
                if (exists) {
                    deleteItem($tds.text());
                } else {
                    alert("No such item..please check the code");
                }
            });
        });

        eventsBound2 = true;

    }
}

setEvent();


function deleteItem(code) {
    $.ajax({
        url: "http://localhost:8080/app/items?itemCode=" + code,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            getAllItem();
        },
        error: function (jqxhr, textStatus, error) {
            console.log("error: ", jqxhr);
            console.log("error: ", textStatus);
            console.log("error: ", error);
        }
    })
}



$('#txtSearchItem').on('keyup', function () {
    let txtitmVal = $('#txtSearchItem').val();

    if (txtitmVal === '') {
        getAllItem();
        return;
    }

    $(`#Item-body`).empty();
    const itmType = $("#itemSearch").val();

    $.ajax({
        url: "http://localhost:8080/app/items",
        method: "GET",
        success: function (resp) {
            console.log("Success: ", resp);

            for (const item of resp) {
                const itmText = (itmType === "Code") ? item.itemCode : item.itemName;

                if (itmText.includes($("#txtSearchItem").val())) {
                    const itmRow = `<tr>
                        <td>${item.itemCode}</td>
                        <td>${item.itemName}</td>
                        <td>${item.itemPrice}</td>
                        <td>${item.itemQty}</td>
                        <td>
                        <button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal" data-bs-target="#update-model">Edit</button>
                        <button class="btn btn-danger me-3 btn-sm deleteItem">Delete</button></td>
                    </tr>`;

                    $("#tblItem > tbody").append($(`#Item-body`).append(itmRow));
                    setEvent();
                }
            }
        },
        error: function (error) {
            console.log("error: ", error);
        }
    });
});

getAllItem();