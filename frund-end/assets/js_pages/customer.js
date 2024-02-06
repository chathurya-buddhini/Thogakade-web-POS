let tableBody = $("#body");


$('#getAllCustomer').click(function () {
    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "GET",
        success : function (resp) {
            console.log("Success: ", resp);
            for (const customer of resp) {
                console.log(customer.id);
                console.log(customer.name);
                console.log(customer.address);
                console.log(customer.salary);

                const row = `<tr>
                                <td>${customer.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.address}</td>
                                <td>${customer.salary}</td>
                            </tr>`;
                $('#tblCustomer').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
});
$('#save-customer').click(function () {

    const id =$('customer-id').val();
    const name =$('customer-name').val();
    const address =$('customer-address').val();
    const salary =$('customer-tp').val();
    const customerObj ={
        id:id,
        name:name,
        address:address,
        salary:salary
    };
    let jsonobj =JSON.stringify(customerObj);

    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "POST",
        data:jsonobj,
        contentType:'application/json',
        success : function (jqxhr,resp) {
            console.log("resp: "+jqxhr);
            console.log("resp: "+resp);
            if(jqxhr.status ==201)
                alert(jqxhr.responseText);


        },
        error : function (jqXHR,textStatus,error) {
            console.log("resp: "+jqxhr);
            console.log("textStatus: "+textStatus);
            console.log("resp: "+resp);

        }
    })
});
$('#delete-custumer').click(function () {
    const id =$('customer-id').val();

    $.ajax({
        url : "http://localhost:8080/app/customers"+id,
        method : "DELETE",
        data:jsonobj,
        contentType:'application/json',
        success : function (jqxhr,textStatus,resp) {
            console.log("resp: "+jqxhr);
            console.log("textStatus: "+textStatus);
            console.log("resp: "+resp);
            if(jqxhr.status ==201)
                alert(jqxhr.responseText);


        },
        error : function (jqXHR,textStatus,error) {
            console.log("resp: "+jqxhr);
            console.log("textStatus: "+textStatus);
            console.log("resp: "+resp);

        }
    })
});
$('#updateCustomer').click(function () {
    const id =$('customer-id').val();
    const name =$('customer-name').val();
    const address =$('customer-address').val();
    const salary =$('customer-tp').val();
    const customerObj ={
        id:id,
        name:name,
        address:address,
        salary:salary
    };
    let jsonobj =JSON.stringify(customerObj);

    $.ajax({
        url : "http://localhost:8080/app/customers",
        method : "PUT",
        data:jsonobj,
        contentType:'application/json',
        success : function (jqxhr,resp) {
            console.log("resp: "+jqxhr);
            console.log("resp: "+resp);
            if(jqxhr.status ==201)
                alert(jqxhr.responseText);


        },
        error : function (jqXHR,textStatus,error) {
            console.log("resp: "+jqxhr);
            console.log("textStatus: "+textStatus);
            console.log("resp: "+resp);

        }
    })
});
