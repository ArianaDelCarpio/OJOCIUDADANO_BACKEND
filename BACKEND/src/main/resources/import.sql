INSERT INTO roles (name) VALUES ('ROLE_CIUDADANO');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_DESARROLLADOR');
INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('ciudadano@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Jose','Perez', '2025-11-28');
INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('admin@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Joseph','Flores', '2025-11-28');
INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('desarrollador@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Sebastian','Galvez', '2025-11-28');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- user1 with ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- admin with ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3); -- admin with ROLE_DESARROLLADOR

