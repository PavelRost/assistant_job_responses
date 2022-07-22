TRUNCATE TABLE note RESTART IDENTITY;

insert into note(id, description, created) values
(1, '1', current_date), (2, '2', current_date), (3, '3', current_date);