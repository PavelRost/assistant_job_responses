TRUNCATE TABLE company RESTART IDENTITY;

insert into company(id, name, created, done, archive) values
(1, '1', '2022-07-22', false, false),
(2, '2', '2022-07-23', false, false),
(3, '3', '2022-07-20', false, false),
(4, '4', '2022-06-15', true, false),
(5, '5', '2022-07-22', true, false),
(6, '6', '2022-06-10', true, false),
(7, '7', '2022-05-01', false, true),
(8, '8', '2022-05-02', false, true),
(9, '9', '2022-04-17', false, true),
(10, '111', '2021-10-10', false, true);