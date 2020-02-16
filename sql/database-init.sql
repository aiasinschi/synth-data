-- 1 Add  application user
CREATE ROLE synth_adm WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'synthadm';

-- 2 Add database
CREATE DATABASE synth
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- 3 Add privileges to user on database
GRANT ALL ON DATABASE synth TO synth_adm WITH GRANT OPTION;

-- 4 Add table(s) and sequence(s)
CREATE TABLE public.document
(
    id bigint NOT NULL,
    name character varying,
    "shortName" character varying,
    content text,
    access character varying,
    code character varying,
    pages integer,
    CONSTRAINT pk_document_id PRIMARY KEY (id)
);

ALTER TABLE public.document
    OWNER to synth_adm;

CREATE SEQUENCE public.seq_document
    INCREMENT 1
    START 1;

ALTER SEQUENCE public.seq_document
    OWNER TO postgres;
