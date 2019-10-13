CREATE TABLE IF NOT EXISTS public.ticket (

    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ticket_number varchar (40),
    name varchar(100),
    email varchar(100),
    description varchar (2000),
    status varchar (10),
    priority int,
    active boolean,
    create_date timestamp ,
    update_date timestamp
);

CREATE SEQUENCE IF NOT EXISTS public.ticket_id_seq
    MINVALUE 1
    MAXVALUE 99999999999999999
    START WITH 1
    INCREMENT BY 1
    CACHE 10;