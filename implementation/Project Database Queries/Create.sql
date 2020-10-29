DROP TABLE public.trailer Cascade;
DROP TABLE public.truck Cascade;
DROP TABLE public.driver Cascade;
DROP TABLE public."Order" Cascade;
DROP TABLE public.bill Cascade;
DROP TABLE public.customer Cascade;
DROP TABLE public.employe Cascade;
DROP TABLE public.maintainence Cascade;
DROP TABLE public.offer Cascade;
DROP TABLE public.person Cascade;
DROP TABLE public.product Cascade;
DROP TABLE public.workorder Cascade;

CREATE TABLE public.person
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.person
    OWNER to postgres;

CREATE TABLE public.customer
(
    id integer NOT NULL,
    personid integer NOT NULL,
    companyname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer NOT NULL,
    postcode integer NOT NULL,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT fkcustomer220859 FOREIGN KEY (personid)
        REFERENCES public.person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.customer
    OWNER to postgres;
	

CREATE TABLE public.driver
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
	surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer NOT NULL,
    postcode integer NOT NULL,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    driverslicense date NOT NULL,
    "Hazardous License" date NOT NULL,
    CONSTRAINT driver_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.driver
    OWNER to postgres;

CREATE TABLE public.employe
(
    "ID" integer NOT NULL,
    "Name" character varying COLLATE pg_catalog."default",
    "Surname" character varying COLLATE pg_catalog."default",
    "Email" character varying COLLATE pg_catalog."default",
    "Password" character varying COLLATE pg_catalog."default",
    CONSTRAINT employe_pkey PRIMARY KEY ("ID")
)

TABLESPACE pg_default;

ALTER TABLE public.employe
    OWNER to postgres;

CREATE TABLE public.trailer
(
    id integer NOT NULL,
    licenseplate character varying(255) COLLATE pg_catalog."default" NOT NULL,
    maxloadweight integer NOT NULL,
    emptyweight integer NOT NULL,
    CONSTRAINT trailer_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.trailer
    OWNER to postgres;

CREATE TABLE public.product
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    ishazardous integer NOT NULL,
    comment VARCHAR,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;
	
CREATE TABLE public.truck
(
    id integer NOT NULL,
    licenseplate character varying(255) COLLATE pg_catalog."default" NOT NULL,
    maxloadweight integer NOT NULL,
    emptyweight integer NOT NULL,
    milage integer NOT NULL,
    power integer NOT NULL,
    apk date NOT NULL,
    CONSTRAINT truck_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.truck
    OWNER to postgres;
	
CREATE TABLE public.workorder
(
    id integer NOT NULL,
    action character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer NOT NULL,
    postcode integer NOT NULL,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "Product ID" integer NOT NULL,
    CONSTRAINT workorder_pkey PRIMARY KEY (id),
    CONSTRAINT fkworkorder693509 FOREIGN KEY ("Product ID")
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.workorder
    OWNER to postgres;

CREATE TABLE public.bill
(
    id integer NOT NULL,
    customerid integer NOT NULL,
    total double precision NOT NULL,
    paid integer NOT NULL,
    "Date" date NOT NULL,
    orderid1 integer NOT NULL,
    orderid2 integer,
    orderid3 integer,
    CONSTRAINT bill_pkey PRIMARY KEY (id),
    CONSTRAINT fkbill173526 FOREIGN KEY (customerid)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.bill
    OWNER to postgres;
	

CREATE TABLE public."Order"
(
    id integer NOT NULL,
    driverid integer NOT NULL,
    truckid integer NOT NULL,
    trailerid integer NOT NULL,
    workorderid1 integer NOT NULL,
    workorderid2 integer,
    workorderid3 integer,
    billid integer NOT NULL,
    CONSTRAINT "Order_pkey" PRIMARY KEY (id),
    CONSTRAINT fkorder146098 FOREIGN KEY (driverid)
        REFERENCES public.driver (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorder271847 FOREIGN KEY (truckid)
        REFERENCES public.truck (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorder345862 FOREIGN KEY (billid)
        REFERENCES public.bill (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorder582385 FOREIGN KEY (workorderid1)
        REFERENCES public.workorder (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkorder78208 FOREIGN KEY (trailerid)
        REFERENCES public.trailer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public."Order"
    OWNER to postgres;




	
CREATE TABLE public.maintainence
(
    id integer NOT NULL,
    truckid integer,
    trailerid integer,
    "Date" date NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT maintainence_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.maintainence
    OWNER to postgres;
	
CREATE TABLE public.offer
(
    id integer NOT NULL,
    customerid integer NOT NULL,
    total double precision NOT NULL,
    "Date" date NOT NULL,
    workorderid integer NOT NULL,
    workorderid2 integer,
    workorderid3 integer,
    CONSTRAINT offer_pkey PRIMARY KEY (id),
    CONSTRAINT fkoffer201141 FOREIGN KEY (customerid)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkoffer51538 FOREIGN KEY (workorderid)
        REFERENCES public.workorder (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.offer
    OWNER to postgres;