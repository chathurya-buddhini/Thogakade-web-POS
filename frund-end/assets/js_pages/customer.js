let tableBody = $("#body");


$('#save-customer').click(function () {
    let id = $("#customer-id").val();

    isIdExists(id, function (exists) {
        if (exists) {
            clearCustomerInputFields();
            alert("ID already exists. Please choose a different ID.");
        } else {

            let name = $("#customer-name").val();
            let address = $("#customer-address").val();
            let salary = $("#customer-tp").val();

            const customerObj = {
                cusId: id,
                cusName: name,
                cusAddress: address,
                cusSalary: salary
            };

            const jsonObj = JSON.stringify(customerObj);

            $.ajax({
                url: "http://localhost:8080/app/customers",
                method: "POST",
                data: jsonObj,
                contentType: "application/json",
                success: function (resp, textStatus, jqxhr) {
                    console.log("success: ", resp);
                    console.log("success: ", textStatus);
                    console.log("success: ", jqxhr);

                    getAll();
                },
                error: function (jqxhr, textStatus, error) {
                    console.log("error: ", jqxhr);
                    console.log("error: ", textStatus);
                    console.log("error: ", error);
                }
            });
            clearCustomerInputFields();
        }
    });
});


function isIdExists(id, callback) {
    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "GET",
        success: function (resp) {
            // ID exists
                for (const customer of resp) {
                    if(customer.cusId == id) {
                        callback(true);
                        return;
                    }
                }
                callback(false);
        },
        error: function (jqxhr, textStatus, error) {

                callback(false);
                console.log("Error checking ID existence: ", jqxhr, textStatus, error);
        }
    });
}


$('#updateCustomer').on('click', function () {
    
    let idval = $(`#upCID`).val();
    isIdExists(idval, function (exists) {
        if (exists) {
           updateCustomer();
        } else {
            clearCustomerInputFields();
            alert("ID doesnot exists. Please choose a existing ID.");
        }
    });
});

function updateCustomer() {
    let id = $(`#upCID`).val();
    let name = $(`#upCName`).val();
    let address = $(`#upCAddress`).val();
    let salary = $(`#upCTp`).val();

    const customerupdateObj = {
                cusId:id,
                cusName:name,
                cusAddress:address,
                cusSalary:salary
    };
        
    const jsonObjupdate = JSON.stringify(customerupdateObj);
        
            $.ajax({
                url: "http://localhost:8080/app/customers",
                method: "PUT",
                data: jsonObjupdate,
                contentType: "application/json",
                success: function (resp, textStatus, jqxhr) {
                    console.log("success: ", resp);
                    console.log("success: ", textStatus);
                    console.log("success: ", jqxhr);
                    getAll();
                },
                error: function (jqxhr, textStatus, error) {
                    console.log("error: ", jqxhr);
                    console.log("error: ", textStatus);
                    console.log("error: ", error);
                }
            })
            clearUpdateFiald();
}


$(`#getAllCustomer`).click(function () {
    getAll();
});

function getAll() {

    $(`#body`).empty();

    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "GET",
        success : function (resp) {
            console.log("Success: ", resp);
            for (const customer of resp) {
                console.log(customer.cusId);
                console.log(customer.cusName);
                console.log(customer.cusAddress);
                console.log(customer.cusSalary);

                $(`#body`).append(`<tr>
                                <td>${customer.cusId}</td>
                                <td>${customer.cusName}</td>
                                <td>${customer.cusAddress}</td>
                                <td>${customer.cusSalary}</td>
                                <td><button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                        data-bs-target="#exampleModal2">
                                    Edit
                                </button>
                                <button class="btn btn-danger me-3 btn-sm delete">Delete</button></td>
                   
                             </tr>`);
                setEvent();
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })

    
}

let eventsBound = false;


function setEvent() {
    if (!eventsBound) {
        $('#tblCustomer').on('click', 'tr', function () {
            var $row = $(this).closest("tr"),
                $tds = $row.find("td:nth-child(1)"),
                $ts = $row.find("td:nth-child(2)"),
                $tt = $row.find("td:nth-child(3)"),
                $tf = $row.find("td:nth-child(4)");

            $(`#upCID`).val($tds.text());
            $(`#upCName`).val($ts.text());
            $(`#upCAddress`).val($tt.text());
            $(`#upCTp`).val($tf.text());
        });

        $('#tblCustomer').on('click', '.delete', function (event) {
            event.stopPropagation();

            var $row = $(this).closest("tr"),
                $tds = $row.find("td:nth-child(1)");

            isIdExists($tds.text(), function (exists) {
                if (exists) {
                    deleteFunc($tds.text());
                } else {
                    alert("No such Customer..please check the ID");
                }
            });
        });

        eventsBound = true;
    }
}

setEvent();


function deleteFunc(id){
    $.ajax({
        url: "http://localhost:8080/app/customers?id=" + id,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
            getAll();
        },
        error: function (jqxhr, textStatus, error) {
            console.log("error: ", jqxhr);
            console.log("error: ", textStatus);
            console.log("error: ", error);
        }
    })
}       


$('#txtSearch').on('keyup',function (){

    let txtVal = $('#txtSearch').val();

    if (txtVal === '') {
        getAll();
        return;
    }

    $(`#body`).empty();
    const searchType = $("#cusSearch").val();

    $.ajax({
        url: "http://localhost:8080/app/customers",
        method: "GET",
        success: function (resp) {
            console.log("Success: ", resp);

            for (const customer of resp) {
                const searchText = (searchType === "Customer Id") ? customer.cusId: customer.cusName;

                if (searchText.includes($("#txtSearch").val())) {
                    const customerRow = `<tr>
                        <td>${customer.cusId}</td>
                        <td>${customer.cusName}</td>
                        <td>${customer.cusAddress}</td>
                        <td>${customer.cusSalary}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm me-2" data-bs-toggle="modal"
                                data-bs-target="#exampleModal2">Edit
                            </button>
                            <button class="btn btn-danger me-3 btn-sm delete">Delete</button>
                        </td>
                    </tr>`;

                    $("#tblCustomer > tbody").append($(`#body`).append(customerRow));
                    setEvent();
                }
            }
        },
        error: function (error) {
            console.log("error: ", error);
        }
    });
});

getAll();