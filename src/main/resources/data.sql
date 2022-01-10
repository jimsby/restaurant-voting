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
VALUES ('Суп Том-Ям', '2022-01-10', 20, 1),
       ('Рол с лососем', '2022-01-10', 30, 1),
       ('Яблочный смузи', '2022-01-10', 15, 1),
       ('Гороховый суп', '2022-01-10', 5, 2),
       ('Пельмени', '2022-01-10', 10, 2),
       ('Компот', '2022-01-10', 5, 2);

INSERT INTO VOTE(date, restaurant_id, user_id)
VALUES ('2022-01-10', 1, 1);