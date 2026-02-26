INSERT INTO users (id, firstname, lastname, email, password, phone, is_admin, is_active)
VALUES
    (nextval('person_sequence'), 'Jean', 'Dupont', 'jean.dupont@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', null, true, true),
    (nextval('person_sequence'), 'Pierre', 'Marchal', 'pierre.marchal@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0612234556', false, true),
    (nextval('person_sequence'), 'Sylvie', 'Tellier', 'sylvie.tellier@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0546474849', false, false);

INSERT INTO employee (id, firstname, lastname, email, password, phone, is_admin, is_active)
VALUES
    (nextval('person_sequence'), 'Jacky', 'Bernard', 'jacky.bernard@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0607080910', false, true),
    (nextval('person_sequence'), 'Jocelyne', 'Bernard', 'jocelyne.bernard@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0607080910', false, false),
    (nextval('person_sequence'), 'Théo', 'Servant', 'theo.servantd@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0548152648', false, true),
    (nextval('person_sequence'), 'Amandine', 'Servant', 'amandine.servant@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0548121548', false, true),
    (nextval('person_sequence'), 'Raymond', 'Lunon', 'raymond.lunond@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0629184744', false, false),
    (nextval('person_sequence'), 'Ginette', 'Lunon', 'ginette.lunon@mail.com', '$2a$12$rQIjbCPbCgG0aIm49iloe.AFJoDb8xCHosWDajN0dmqRf3rs3f8T6', '0629184744', false, true);

INSERT INTO pool (id, max_capacity)
VALUES (nextval('pool_sequence'), 150);

INSERT INTO subscription_kind (id, name, duration_days, price)
VALUES
    (nextval('subscription_kind_sequence'), 'mensuel', 30, 50),
    (nextval('subscription_kind_sequence'), 'trimestriel', 180, 150),
    (nextval('subscription_kind_sequence'), 'annuel', 365, 250);

INSERT INTO ticket_kind (id, name, price)
VALUES
    (nextval('ticket_kind_sequence'), 'personnel', 10),
    (nextval('ticket_kind_sequence'), 'duo', 15),
    (nextval('ticket_kind_sequence'), 'famille', 25);
