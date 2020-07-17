--PostgreSQL no usa los apostrofes
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) values ('usuario1', '$2a$10$WQ.EmHwt7x.lzvpGJMB04upGALA.jgWvoDk3VlPoGvXbwZKcTuRte', true, 'Martin', 'Medina', 'martin@mail.com');
INSERT INTO usuarios(username, password, enabled, nombre, apellido, email) values ('usuario2', '$2a$10$HBWngkJcMy5KapVH4iFDFegJOz79ALGzW91IumyYHu7QMFvSSOzdq', true, 'Martin', 'Medina', 'martin2@mail.com');

INSERT INTO roles(nombre) values ('ROLE_ADMIN');
INSERT INTO roles(nombre) values ('ROLE_USER');

INSERT INTO usuarios_roles(user_id, role_id) values (1, 1);
INSERT INTO usuarios_roles(user_id, role_id) values (1, 2);
INSERT INTO usuarios_roles(user_id, role_id) values (2, 2);

--MySQL usa los apostrofes
--INSERT INTO `usuarios`(username, password, enabled, nombre, apellido, email) values ('usuario1', '$2a$10$WQ.EmHwt7x.lzvpGJMB04upGALA.jgWvoDk3VlPoGvXbwZKcTuRte', 1, 'Martin', 'Medina', 'martin@mail.com');
--INSERT INTO `usuarios`(username, password, enabled, nombre, apellido, email) values ('usuario2', '$2a$10$HBWngkJcMy5KapVH4iFDFegJOz79ALGzW91IumyYHu7QMFvSSOzdq', 1, 'Martin', 'Medina', 'martin2@mail.com');

--INSERT INTO `roles`(nombre) values ('ROLE_ADMIN');
--INSERT INTO `roles`(nombre) values ('ROLE_USER');

--INSERT INTO `usuarios_roles`(user_id, role_id) values (1, 1);
--INSERT INTO `usuarios_roles`(user_id, role_id) values (1, 2);
--INSERT INTO `usuarios_roles`(user_id, role_id) values (2, 2);