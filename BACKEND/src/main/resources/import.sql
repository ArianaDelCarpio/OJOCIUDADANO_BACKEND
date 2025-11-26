INSERT INTO roles (name) VALUES ('ROLE_CIUDADANO');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_DESARROLLADOR');
INSERT INTO users(username, password,nombre,apellido) VALUES ('ciudadano@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Jose','Perez');
INSERT INTO users(username, password,nombre,apellido) VALUES ('admin@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Joseph','Flores');
INSERT INTO users(username, password,nombre,apellido) VALUES ('desarrollador@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Sebastian','Galvez');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- user1 with ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- admin with ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3); -- admin with ROLE_DESARROLLADOR

