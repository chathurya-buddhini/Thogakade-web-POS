


 CREATE TABLE customer( id VARCHAR(8)NOT NULL,
                        name VARCHAR (200),
                        address VARCHAR (200),
                        salary DECIMAL (12.2),
                        CONSTRAINT PRIMARY KEY (id));
 INSERT INTO customer VALUES("C001","kamal samaranayaka","Galle",1000.00);
  INSERT INTO customer VALUES("C002","samal samaranayaka","Mathara",1400.00);

   CREATE TABLE item( code VARCHAR(8)NOT NULL,
                        description VARCHAR (200),
                        unitPrice DECIMAL (12.2),
                        qtyOnHand INT (10),
                        CONSTRAINT PRIMARY KEY (code));
INSERT INTO item VALUES("I001","Appel",78.00,10);
INSERT INTO item VALUES("I001","chooclet cake",1500,2);

     CREATE TABLE orders(orderID VARCHAR(8)NOT NULL,
                        date DATE ,
                        cusId VARCHAR(8)NOT NULL,
                        CONSTRAINT PRIMARY KEY (orderID),
                         CONSTRAINT FOREIGN KEY(cusId) REFERENCES customer(id) on Delete Cascade on Update Cascade);
INSERT INTO orders VALUES("O001",2024-02.06,"C001");
    CREATE TABLE orderDetail( ItemCode VARCHAR(8)NOT NULL,
                              orderID VARCHAR (8)NOT NULL,
                               qtyOnHand INT (10),
                                unitPrice DECIMAL (12.2),
                        CONSTRAINT PRIMARY KEY (ItemCode,orderID),
                          CONSTRAINT FOREIGN KEY(ItemCode)REFERENCES item(code)on Delete Cascade on Update Cascade,
                         CONSTRAINT FOREIGN KEY(orderID)REFERENCES orders(orderID)on Delete Cascade on Update Cascade);

INSERT INTO orders VALUES("O001",2024-02.06,"C001");
//////////////////////////////////////////////////////////////////////////////////////

     INSERT INTO customer VALUES("Chathu","1234");
          INSERT INTO Login VALUES("Chathurya","12345");

