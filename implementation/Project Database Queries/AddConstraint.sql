alter table "Order"
add CONSTRAINT fkorder134347 FOREIGN KEY (invoiceid) REFERENCES invoice(id),
add	CONSTRAINT fkorder196725 FOREIGN KEY (productid) REFERENCES product(id),
add	CONSTRAINT fkorderworkorder1 FOREIGN KEY (workorderid1) REFERENCES workorder(id),
add	CONSTRAINT fkorderworkorder2 FOREIGN KEY (workorderid2) REFERENCES workorder(id),
add	CONSTRAINT fkorderworkorder3 FOREIGN KEY (workorderid3) REFERENCES workorder(id),
add CONSTRAINT fkordercustomer FOREIGN KEY (customerid) REFERENCES customer(id);

alter table customer
add CONSTRAINT fkcustomer877279 FOREIGN KEY (contactpersonid) REFERENCES contactperson(id);

alter table invoice
add CONSTRAINT fkinvoice987227 FOREIGN KEY (orderid) REFERENCES "Order"(id);

alter table workorder_order
add	CONSTRAINT fkworkorder_285561 FOREIGN KEY (orderid) REFERENCES "Order"(id),
add	CONSTRAINT fkworkorder_965803 FOREIGN KEY (workorderid) REFERENCES workorder(id);

alter table truckdriver
add	CONSTRAINT fktruckdrive436692 FOREIGN KEY (employeeid) REFERENCES employee(id);

alter table workorder
add CONSTRAINT fkworkorder123 FOREIGN KEY (product) REFERENCES product(id),
add CONSTRAINT fkworkordertoAddress FOREIGN KEY (toAddress) REFERENCES address(id),
add CONSTRAINT fkworkorderfromAddress FOREIGN KEY (fromAddress) REFERENCES address(id);

alter table maintainance
add CONSTRAINT fkmaintainancetruck FOREIGN KEY (truckid) REFERENCES truck(id),
add CONSTRAINT fkmaintainancetrailer FOREIGN KEY (trailerid) REFERENCES trailer(id);