create table company (
    id serial primary key,
    name text,
    created date,
    done boolean,
    archive boolean
);