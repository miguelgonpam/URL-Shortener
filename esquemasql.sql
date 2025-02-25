select * from url;
update url set access_count=0;
drop table url;


create table url(
        id integer primary key,
	shorturl varchar(100) unique not null,
        url varchar(1000) not null,
        created_at timestamp(6) without time zone default CURRENT_TIMESTAMP,
    	updated_at timestamp(6) without time zone default CURRENT_TIMESTAMP,
    	access_count integer default 0
);

ALTER TABLE url 
ALTER COLUMN updated_at SET DATA TYPE timestamp(6) without time zone 
USING updated_at::timestamp(6) without time zone;

ALTER TABLE url 
ALTER COLUMN created_at SET DATA TYPE timestamp(6) without time zone 
USING created_at::timestamp(6) without time zone;

drop sequence url_id_seq;
create sequence url_id_seq start with 1 increment by 1;
alter table url alter column id set default nextval('url_id_seq');

insert into url(shorturl, url) values ('abd456', 'https://www.ubu.es'),('ex123', 'https://example.com');

drop table sequence_tracker;

CREATE TABLE sequence_tracker (
    id SERIAL PRIMARY KEY,
    letter_part CHAR(3) NOT NULL,
    number_part INT NOT NULL
);

INSERT INTO sequence_tracker (letter_part, number_part) VALUES ('aaa', 0);

CREATE OR REPLACE FUNCTION get_next_custom_id()
RETURNS TEXT AS $$
DECLARE
    current_letters CHAR(3);
    current_number INT;
    next_number INT;
    next_letters CHAR(3);
BEGIN
    -- Obtener la última combinación
    SELECT letter_part, number_part INTO current_letters, current_number
    FROM sequence_tracker
    ORDER BY id DESC
    LIMIT 1;

    -- Incrementar el número
    next_number := current_number + 1;

    -- Si el número llega a 1000, se reinicia y avanzan las letras
    IF next_number = 1000 THEN
        next_number := 0;
        next_letters := next_letter_combination(current_letters);
    ELSE
        next_letters := current_letters;
    END IF;

    -- Insertar la nueva combinación
    INSERT INTO sequence_tracker (letter_part, number_part)
    VALUES (next_letters, next_number);

    -- Retornar el valor en formato LLLNNN
    RETURN next_letters || LPAD(next_number::TEXT, 3, '0');
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION next_letter_combination(letters CHAR(3))
RETURNS CHAR(3) AS $$
DECLARE
    new_letters CHAR(3);
    letter1 CHAR;
    letter2 CHAR;
    letter3 CHAR;
BEGIN
    letter1 := SUBSTRING(letters FROM 1 FOR 1);
    letter2 := SUBSTRING(letters FROM 2 FOR 1);
    letter3 := SUBSTRING(letters FROM 3 FOR 1);

    -- Incrementar la última letra
    IF letter3 < 'z' THEN
        letter3 := CHR(ASCII(letter3) + 1);
    ELSIF letter2 < 'z' THEN
        letter2 := CHR(ASCII(letter2) + 1);
        letter3 := 'a';
    ELSIF letter1 < 'z' THEN
        letter1 := CHR(ASCII(letter1) + 1);
        letter2 := 'a';
        letter3 := 'a';
    ELSE
        RAISE EXCEPTION 'Se han agotado las combinaciones posibles';
    END IF;

    new_letters := letter1 || letter2 || letter3;
    RETURN new_letters;
END;
$$ LANGUAGE plpgsql;

alter table url alter column shorturl set default  get_next_custom_id();

select * from sequence_tracker;
