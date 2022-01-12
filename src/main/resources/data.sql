INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('Grand Resort'),
       ('Bad Resort');

INSERT INTO MEAL(name, date, price, restaurant_id)
VALUES ('Суп Том-Ям', CURDATE(), 20, 1),
       ('Рол с лососем', CURDATE(), 30, 1),
       ('Яблочный смузи', CURDATE(), 15, 1),
       ('Гороховый суп', CURDATE(), 5, 2),
       ('Пельмени', CURDATE(), 10, 2),
       ('Компот', CURDATE(), 5, 2),
       ('Старая_еда', '2022-01-10' , 5, 2);

INSERT INTO VOTE(date, restaurant_id, user_id)
VALUES (CURDATE(), 1, 1),
       ('2022-01-10', 2, 1);