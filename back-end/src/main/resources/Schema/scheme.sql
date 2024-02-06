


 CREATE TABLE customer( id VARCHAR(8)NOT NULL,
                        name VARCHAR (200),
                        address VARCHAR (200),
                        salary DECIMAL (12.2),
                        CONSTRAINT PRIMARY KEY (id));

   CREATE TABLE item( code VARCHAR(8)NOT NULL,
                        description VARCHAR (200),
                        unitPrice DECIMAL (12.2),
                        qtyOnHand INT (10),
                        CONSTRAINT PRIMARY KEY (code));

     CREATE TABLE orders(orderID VARCHAR(8)NOT NULL,
                        date DATE ,
                        cusId VARCHAR(8)NOT NULL,
                        CONSTRAINT PRIMARY KEY (orderID),
                         CONSTRAINT FOREIGN KEY(cusId) REFERENCES customer(id) on Delete Cascade on Update Cascade);

    CREATE TABLE orderDetail( ItemCode VARCHAR(8)NOT NULL,
                              orderID VARCHAR (8)NOT NULL,
                               qtyOnHand INT (10),
                                unitPrice DECIMAL (12.2),
                        CONSTRAINT PRIMARY KEY (ItemCode,orderID),
                          CONSTRAINT FOREIGN KEY(ItemCode)REFERENCES item(code)on Delete Cascade on Update Cascade,
                         CONSTRAINT FOREIGN KEY(orderID)REFERENCES orders(orderID)on Delete Cascade on Update Cascade);


//////////////////////////////////////////////////////////////////////////////////////
