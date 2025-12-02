INSERT INTO roles (name) VALUES ('ROLE_CIUDADANO');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_DESARROLLADOR');
SELECT setval(pg_get_serial_sequence('roles', 'id'), coalesce(max(id),0) + 1, false) FROM roles;

INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('ciudadano@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Jose','Perez', '2025-11-28');
INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('admin@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Joseph','Flores', '2025-11-28');
INSERT INTO users(username, password,nombre,apellido, fecha_registro) VALUES ('desarrollador@gmail.com','$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06','Sebastian','Galvez', '2025-11-28');
INSERT INTO users(username, password, nombre, apellido, fecha_registro) VALUES ('maria@gmail.com', '$2a$12$1k34YdrmxBkVborQvZLh2OUvX1S80GVVQjZJ5H55y1eez7XV.nV06', 'Maria', 'Gomez', '2025-11-29');
SELECT setval(pg_get_serial_sequence('users', 'id'), coalesce(max(id),0) + 1, false) FROM users;

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- user1 with ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- admin with ROLE_ADMIN
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3); -- admin with ROLE_DESARROLLADOR
INSERT INTO user_roles (user_id, role_id) VALUES (4, 1); -- Maria -> Ciudadano
SELECT setval(pg_get_serial_sequence('user_roles', 'id'), coalesce(max(id),0) + 1, false) FROM user_roles;
-- 4. GOBIERNOS REGIONALES
INSERT INTO gobierno_regional (id, nombre, ubicacion, contacto)VALUES (1, 'Gobierno Regional de Lima', 'Av. Kennedy 123, Lima', 'contacto@regionlima.gob.pe');

INSERT INTO gobierno_regional (id, nombre, ubicacion, contacto) VALUES (2, 'Gobierno Regional de Cusco', 'Plaza de Armas S/N, Cusco', 'obras@regioncusco.gob.pe');

INSERT INTO gobierno_regional (id, nombre, ubicacion, contacto) VALUES (3, 'Municipalidad de Arequipa', 'Calle Mercaderes 404, Arequipa', 'alcaldia@muniarequipa.gob.pe');
SELECT setval(pg_get_serial_sequence('gobierno_regional', 'id'), coalesce(max(id),0) + 1, false) FROM gobierno_regional;

-- 5. EXPEDIENTES TÉCNICOS
INSERT INTO expediente_tecnico (id, documento_ur, fecha_carga) VALUES (1, 'https://docs.google.com/expediente-carretera-central.pdf', '2025-01-10');

INSERT INTO expediente_tecnico (id, documento_ur, fecha_carga) VALUES (2, 'https://gob.pe/archivos/hospital-lorena-v2.pdf', '2025-02-15');

INSERT INTO expediente_tecnico (id, documento_ur, fecha_carga) VALUES (3, 'https://drive.google.com/file/d/puente-chilina.pdf', '2025-03-20');
SELECT setval(pg_get_serial_sequence('expediente_tecnico', 'id'), coalesce(max(id),0) + 1, false) FROM expediente_tecnico;

-- 6. OBRAS PÚBLICAS
-- Obra 1: En Ejecución (Lima)
INSERT INTO obra_publica (id, nombre_obra, descripcion, estado, fecha_inicio, fecha_fin, gobierno_regional_id, expediente_id) VALUES (1, 'Mejoramiento Carretera Central', 'Ampliación a 4 carriles tramo Km 10-50', 'En Ejecución', '2025-01-20', '2026-01-20', 1, 1);

-- Obra 2: Paralizada (Cusco)
INSERT INTO obra_publica (id, nombre_obra, descripcion, estado, fecha_inicio, fecha_fin, gobierno_regional_id, expediente_id) VALUES (2, 'Hospital Antonio Lorena', 'Construcción del nuevo pabellón de emergencia', 'Paralizada', '2025-02-20', '2025-12-01', 2, 2);

-- Obra 3: Finalizada (Arequipa)
INSERT INTO obra_publica (id, nombre_obra, descripcion, estado, fecha_inicio, fecha_fin, gobierno_regional_id, expediente_id) VALUES (3, 'Mantenimiento Puente Chilina', 'Refacción de estructura y pintura', 'Finalizada', '2025-03-25', '2025-06-30', 3, 3);
SELECT setval(pg_get_serial_sequence('obra_publica', 'id'), coalesce(max(id),0) + 1, false) FROM obra_publica;

-- 7. DENUNCIAS
-- Denuncia 1: Jose denuncia la Carretera Central
INSERT INTO denuncia (id, titulo, descripcion, estado, usuario_id, obra_publica_id) VALUES (1, 'Materiales de mala calidad', 'He observado que el cemento usado se está rajando a los 2 días.', false, 1, 1);

-- Denuncia 2: Maria denuncia el Hospital (Paralizada)
INSERT INTO denuncia (id, titulo, descripcion, estado, usuario_id, obra_publica_id) VALUES (2, 'Obra abandonada hace meses', 'No hay trabajadores en la obra desde febrero y hay basura acumulada.', true, 4, 2);

-- Denuncia 3: Sebastian reporta sobre la Carretera Central
INSERT INTO denuncia (id, titulo, descripcion, estado, usuario_id, obra_publica_id) VALUES (3, 'Falta de señalización nocturna', 'Es peligroso transitar de noche, no hay luces de advertencia.', false, 3, 1);
SELECT setval(pg_get_serial_sequence('denuncia', 'id'), coalesce(max(id),0) + 1, false) FROM denuncia;

-- 8. EVIDENCIAS (Fotos/Videos de las denuncias)
-- Evidencia para Denuncia 1
INSERT INTO evidencias (id, tipo, url_archivo, denuncia_id) VALUES (1, 'Foto', 'https://i.imgur.com/rajadura-cemento.jpg', 1);

-- Evidencia para Denuncia 2
INSERT INTO evidencias (id, tipo, url_archivo, denuncia_id) VALUES (2, 'Video', 'https://youtube.com/watch?v=obra-abandonada', 2);

-- Evidencia para Denuncia 2 (Documento)
INSERT INTO evidencias (id, tipo, url_archivo, denuncia_id) VALUES (3, 'Documento', 'https://drive.google.com/carta-vecinos.pdf', 2);
SELECT setval(pg_get_serial_sequence('evidencias', 'id'), coalesce(max(id),0) + 1, false) FROM evidencias;

-- 9. NOTIFICACIONES
-- Notificación para Jose (Usuario 1)
INSERT INTO notificacion (id, mensaje, fecha_envio, leida, usuario_id) VALUES (1, 'Bienvenido a Ojo Ciudadano. Gracias por registrarte.', '2025-11-28', true, 1);

-- Notificación para Jose sobre su denuncia
INSERT INTO notificacion (id, mensaje, fecha_envio, leida, usuario_id) VALUES (2, 'Su denuncia sobre la Carretera Central ha sido recibida y está en revisión.', '2025-11-29', false, 1);

-- Notificación para el Admin (Joseph)
INSERT INTO notificacion (id, mensaje, fecha_envio, leida, usuario_id) VALUES (3, 'Alerta: Se han registrado 3 nuevas denuncias esta semana.', '2025-11-30', false, 2);
SELECT setval(pg_get_serial_sequence('notificacion', 'id'), coalesce(max(id),0) + 1, false) FROM notificacion;

-- 10. INVERSIONES
-- Inversión para la Carretera Central (Obra 1)
INSERT INTO inversion (id, "monto_total", "fuente financiamiento", "fecha aprobacion", "obra_publica_id") VALUES (1, 'S/. 15,000,000.00', 'Canon Minero', '2025-01-25', 1);

-- Inversión para el Hospital Antonio Lorena (Obra 2)
INSERT INTO inversion (id, "monto_total", "fuente financiamiento", "fecha aprobacion", "obra_publica_id") VALUES (2, 'S/. 42,500,000.00', 'Tesoro Público', '2025-02-28', 2);

-- Inversión para el Puente Chilina (Obra 3)
INSERT INTO inversion (id, "monto_total", "fuente financiamiento", "fecha aprobacion", "obra_publica_id") VALUES (3, 'S/. 8,200,000.00', 'Recursos Ordinarios', '2025-03-15', 3);

-- Inversión adicional para la Carretera Central (Segunda etapa)
INSERT INTO inversion (id, "monto_total", "fuente financiamiento", "fecha aprobacion", "obra_publica_id") VALUES (4, 'S/. 5,000,000.00', 'Cooperación Internacional', '2025-04-10', 1);
SELECT setval(pg_get_serial_sequence('inversion', 'id'), coalesce(max(id),0) + 1, false) FROM inversion;

-- 11. AVANCES DE OBRA (ACTUALIZADO)
-- Tabla: avance_obra (Por defecto de la clase AvanceObra)
-- Columnas: Coinciden con tus @Column(name="...")
INSERT INTO avance_obra (id, "fecha_reporte", "porcentaje_avance", "descripcion", "obra_publica_id") VALUES (1, '2025-05-01', '45%', 'Conclusión de cimentación y base del tramo 1', 1);

INSERT INTO avance_obra (id, "fecha_reporte", "porcentaje_avance", "descripcion", "obra_publica_id") VALUES (2, '2025-03-01', '15%', 'Avance detenido por problemas en la licencia municipal', 2);

INSERT INTO avance_obra (id, "fecha_reporte", "porcentaje_avance", "descripcion", "obra_publica_id") VALUES (3, '2025-06-10', '52%', 'Inicio de la fase de pavimentación y asfaltado', 1);

INSERT INTO avance_obra (id, "fecha_reporte", "porcentaje_avance", "descripcion", "obra_publica_id") VALUES (4, '2025-07-01', '100%', 'Obra completada y entregada', 3);
SELECT setval(pg_get_serial_sequence('avance_obra', 'id'), coalesce(max(id),0) + 1, false) FROM avance_obra;

-- 12. COMENTARIOS
-- Tabla: comentario (generada por defecto)
-- Columnas: Coinciden con tus @Column y @JoinColumn

-- Comentarios para la Obra 1 (Carretera Central)
INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (1, 'Perfecto justo lo que necesitábamos en estas calles.', '2025-05-02', 1, 1);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (2, 'El tiempo de construcción se me hace exagerado, espero cumplan los plazos.', '2025-05-03', 4, 1);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (3, 'Solo espero que no hagan mucho ruido jejeje.', '2025-05-05', 3, 1);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (4, 'Broer justo lo que necesitaba mi cerro, excelente iniciativa.', '2025-05-06', 1, 1);


-- Comentarios para la Obra 2 (Hospital Antonio Lorena - Paralizada)
INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (5, 'Es increíble que esta obra siga paralizada. Necesitamos el hospital ya.', '2025-03-10', 4, 2);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (6, 'Solo Dios sabe si el presupuesto fue bien calculado.', '2025-03-12', 3, 2);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (7, 'He visto que sacaron maquinaria ayer, ¿qué está pasando?', '2025-03-15', 1, 2);


-- Comentarios para la Obra 3 (Puente Chilina)
INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (8, 'Quedó muy bien el mantenimiento, ahora se ve más seguro.', '2025-07-05', 2, 3);

INSERT INTO comentario (id, "contenido", "fecha_comentario", "usuario_id", "obra_publica_id") VALUES (9, 'Buen trabajo del gobierno regional, sigan así.', '2025-07-06', 4, 3);
SELECT setval(pg_get_serial_sequence('comentario', 'id'), coalesce(max(id),0) + 1, false) FROM comentario;

-- 13. SEGUIMIENTO DE OBRA
-- Tabla: seguimiento_obra (Generada por defecto por la clase SeguimientoObra)

INSERT INTO seguimiento_obra (id, fecha_inicio, activo, obra_publica_id, usuario_id) VALUES (1, '2025-01-20', true, 1, 2);

INSERT INTO seguimiento_obra (id, fecha_inicio, activo, obra_publica_id, usuario_id) VALUES (2, '2025-02-22', false, 2, 3);

INSERT INTO seguimiento_obra (id, fecha_inicio, activo, obra_publica_id, usuario_id) VALUES (3, '2025-03-26', true, 3, 1);

INSERT INTO seguimiento_obra (id, fecha_inicio, activo, obra_publica_id, usuario_id) VALUES (4, '2025-06-15', true, 1, 4);
SELECT setval(pg_get_serial_sequence('seguimiento_obra', 'id'), coalesce(max(id),0) + 1, false) FROM seguimiento_obra;