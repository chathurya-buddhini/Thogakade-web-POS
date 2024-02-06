

$('#btnGetAllItem').click(function () {
    $.ajax({
        url : "http://localhost:8080/app/items",
        method : "GET",
        success : function (resp) {
            console.log("Success: ", resp);
            for (const item of resp) {

                console.log(item.code);
                console.log(item.description);
                console.log(item.unitPrice);
                console.log(item.qtyOnHand);

                const row = `<tr>
                                <td>${item.code}</td>
                                <td>${item.description}</td>
                                <td>${item.unitPrice}</td>
                                <td>${item.qtyOnHand}</td>
                            </tr>`;
                $('#tblItem').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
});
$('#btnSaveItem').click(function () {

    const code =$('txtItemId').val();
    const description =$('txtItemdec').val();
    const qtyOnHand =$('txtItemQty').val();
    const unitPrice =$('txtItemUnitPrice').val();
    const itemObj ={
        code:code,
        description:description,
        qtyOnHand:qtyOnHand,
        unitPrice:unitPrice
    };
    let jsonobj =JSON.stringify(itemObj);

    $.ajax({
        url : "http://localhost:8080/app/items",
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
$('#delete-item').click(function () {
    const id =$('txtItemId').val();

    $.ajax({
        url : "http://localhost:8080/app/items"+id,
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
$('#btnUpdateItem').click(function () {
    const code =$('txtItemId').val();
    const description =$('txtItemdec').val();
    const qtyOnHand =$('txtItemQty').val();
    const unitPrice =$('txtItemUnitPrice').val();
    const itemObj ={
        code:code,
        description:description,
        qtyOnHand:qtyOnHand,
        unitPrice:unitPrice
    };
    let jsonobj =JSON.stringify(customerObj);

    $.ajax({
        url : "http://localhost:8080/app/items",
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