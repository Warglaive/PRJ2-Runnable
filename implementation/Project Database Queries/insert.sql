INSERT INTO public.person(
	id, name, surname)
	VALUES (1, 'Andreas' ,'Gooren');
	
INSERT INTO public.person(
	id, name, surname)
	VALUES (2 ,'Dieter', 'Neeten');
	
INSERT INTO public.person(
	id, name, surname)
	VALUES (3 ,'Hans' ,'Peters');
	
INSERT INTO public.customer(
	id, personid, companyname, street, "number", postcode, city)
	VALUES (1, 2, 'Wolanski GmbH', 'An der Bleiche', 69, 47608, 'Geldern');
	
INSERT INTO public.customer(
	id, personid, companyname, street, "number", postcode, city)
	VALUES (2, 1, 'Badchicken', 'Promstreet', 666, 48074, 'GTown');
	
INSERT INTO public.customer(
	id, personid, companyname, street, "number", postcode, city)
	VALUES (3, 1, 'Goodchicken', 'ihatechickenstreet', 4, 53119, 'Tannenbusch');
	
INSERT INTO public.driver(
	id, name, surname, street, "number", postcode, city, driverslicense, "Hazardous License")
	VALUES (1, 'Abdel', 'Rahim', 'The road', 2, 53119, 'Bonn', '2022-04-04', '2022-05-06');
	
INSERT INTO public.driver(
	id, name, surname, street, "number", postcode, city, driverslicense, "Hazardous License")
	VALUES (2, 'Knut', 'Hansen', 'Oppolner street', 2, 53119, 'Bonn', '2022-12-13', '2021-05-04');
	
INSERT INTO public.employe(
	"ID", "Name", "Surname", "Email", "Password")
	VALUES (1, 'Achim', 'Daamen', 'daamen@t-online.de', Null);
	
INSERT INTO public.trailer(
	id, licenseplate, maxloadweight, emptyweight)
	VALUES (1, 'Gel-GW-999', 20, 7);
	
INSERT INTO public.product(
	id, name, ishazardous, comment)
	VALUES (1, 'milk', 0, 'Keep fresh');
	
INSERT INTO public.product(
	id, name, ishazardous, comment)
	VALUES (2, 'beer', 0, 'drink asap');

INSERT INTO public.product(
	id, name, ishazardous, comment)
	VALUES (3, 'antimatter', 1, 'we are already dead');
	
INSERT INTO public.truck(
	id, licenseplate, maxloadweight, emptyweight, milage, power, apk)
	VALUES (1, 'Gel-QW-874', 40, 6, 200000, 600, '2020-06-05');
	
INSERT INTO public.truck(
	id, licenseplate, maxloadweight, emptyweight, milage, power, apk)
	VALUES (2, 'Gel-D-123', 20, 5, 250402, 400, '2020-11-05');
	
INSERT INTO public.truck(
	id, licenseplate, maxloadweight, emptyweight, milage, power, apk)
	VALUES (3, 'Gel-DZ-666', 40, 7, 154320, 700, '2020-06-12');
	
INSERT INTO public.workorder(
	id, action, name, street, "number", postcode, city, "Product ID")
	VALUES (1,'load', 'ISIS', 'desertstreet', 2, 7968, 'Kabul', 3);
	
INSERT INTO public.workorder(
	id, action, name, street, "number", postcode, city, "Product ID")
	VALUES (2,'unload', 'MiracleCompany', 'miraclestreet', 2, 7968, 'Kabul', 3);
	
INSERT INTO public.bill(
	id, customerid, total, paid, "Date", orderid1, orderid2, orderid3)
	VALUES (1, 1, 200, 1, '2020-04-04', 1, Null, Null);

INSERT INTO public."Order"(
	id, driverid, truckid, trailerid, workorderid1, workorderid2, workorderid3, billid)
	VALUES (1, 1, 1, 1, 1, 2, Null, 1);	
	
INSERT INTO public.maintainence(
	id, truckid, trailerid, "Date", description)
	VALUES (1, Null, 1, '2020-12-13', 'Everything is fine');

INSERT INTO public.maintainence(
	id, truckid, trailerid, "Date", description)
	VALUES (2, 1, Null, '2020-12-13', 'Everything is fine');
	
INSERT INTO public.maintainence(
	id, truckid, trailerid, "Date", description)
	VALUES (3, 2, Null, '2020-12-13', 'Everything is fine');
	
INSERT INTO public.offer(
	id, customerid, total, "Date", workorderid, workorderid2, workorderid3)
	VALUES (1, 1, 200, '2020-04-04', 1, 2, Null);