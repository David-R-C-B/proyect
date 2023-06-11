CREATE TABLE Usuario (
  id SERIAL PRIMARY KEY,
  contraseña TEXT,
  tarjetadeacceso TEXT,
  nombres TEXT,
  apellidos TEXT,
  ocupacion TEXT
);

CREATE TABLE Administradores (
  id SERIAL PRIMARY KEY,
  cargo TEXT,
  estado_actividad BOOLEAN,
  CONSTRAINT administrador_usuario_fk FOREIGN KEY (id) REFERENCES Usuario (id)
);

CREATE TABLE Atleta (
  id INTEGER PRIMARY KEY,
  tamaño_centimetros INTEGER,
  peso_gramos INTEGER,
  fechadenacimiento DATE,
  edad INTEGER,
  categoria TEXT,
  disiplina TEXT,
  dirección TEXT,
  correoelectronico TEXT,
  CONSTRAINT atleta_usuario_fk FOREIGN KEY (id) REFERENCES Usuario (id)
);

CREATE TABLE Nutriologo (
  id INTEGER PRIMARY KEY,
  fechadenacimiento DATE,
  edad INTEGER,
  dirección TEXT,
  correoelectronico TEXT,
  CONSTRAINT nutriologo_usuario_fk FOREIGN KEY (id) REFERENCES Usuario (id)
);

CREATE TABLE Entrenador (
  id INTEGER PRIMARY KEY,
  fechadenacimiento DATE,
  edad INTEGER,
  dirección TEXT,
  correoelectronico TEXT,
  CONSTRAINT entrenador_usuario_fk FOREIGN KEY (id) REFERENCES Usuario (id)
);

CREATE TABLE enlace (
  id SERIAL PRIMARY KEY,
  id_atleta INTEGER,
  id_nutriologo INTEGER,
  id_entrenador INTEGER,
  CONSTRAINT seguimiento_atleta_fk FOREIGN KEY (id_atleta) REFERENCES Atleta (id),
  CONSTRAINT seguimiento_nutriologo_fk FOREIGN KEY (id_nutriologo) REFERENCES Nutriologo (id),
  CONSTRAINT seguimiento_entrenador_fk FOREIGN KEY (id_entrenador) REFERENCES Entrenador (id)
);



CREATE TABLE Rutina (
  id SERIAL PRIMARY KEY,
  id_entrenador INTEGER,
  id_atleta INTEGER,
  tipo TEXT,
  fecha_asignacion DATE,
  comentario TEXT,
  CONSTRAINT rutina_entrenador_fk FOREIGN KEY (id_entrenador) REFERENCES Entrenador (id),
  CONSTRAINT rutina_atleta_fk FOREIGN KEY (id_atleta) REFERENCES Atleta (id)
);



CREATE TABLE Dieta (
  id SERIAL PRIMARY KEY,
  id_nutriologo INTEGER,
  id_atleta INTEGER,
  tipo TEXT,
  fecha_asignacion DATE,
  comentario TEXT,
  CONSTRAINT dieta_nutriologo_fk FOREIGN KEY (id_nutriologo) REFERENCES Nutriologo (id),
  CONSTRAINT dieta_atleta_fk FOREIGN KEY (id_atleta) REFERENCES Atleta (id)
);

CREATE TABLE Alimento (
  id SERIAL PRIMARY KEY,
  nombre TEXT,
  proteinas INTEGER,
  calorias INTEGER,
  descripcion TEXT
);

CREATE TABLE Seguimiento (
  id SERIAL PRIMARY KEY,
  id_atleta INTEGER,
  id_nutriologo INTEGER,
  id_entrenador INTEGER,
  id_rutina INTEGER,
  id_dieta INTEGER,
  CONSTRAINT seguimiento_atleta_fk FOREIGN KEY (id_atleta) REFERENCES Atleta (id),
  CONSTRAINT seguimiento_nutriologo_fk FOREIGN KEY (id_nutriologo) REFERENCES Nutriologo (id),
  CONSTRAINT seguimiento_entrenador_fk FOREIGN KEY (id_entrenador) REFERENCES Entrenador (id),
  CONSTRAINT seguimiento_rutina_fk FOREIGN KEY (id_rutina) REFERENCES Rutina (id),
  CONSTRAINT seguimiento_dieta_fk FOREIGN KEY (id_dieta) REFERENCES Dieta (id)
);

CREATE TABLE Historial (
  id SERIAL PRIMARY KEY,
  tabla_afectada TEXT,
  accion TEXT,
  fecha_hora TIMESTAMP DEFAULT current_timestamp
);



-- Trigger para inserción en todas las tablas
CREATE OR REPLACE FUNCTION registrar_historial_insercion()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO Historial (tabla_afectada, accion) VALUES (TG_TABLE_NAME, 'inserción');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para actualización en todas las tablas
CREATE OR REPLACE FUNCTION registrar_historial_actualizacion()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO Historial (tabla_afectada, accion) VALUES (TG_TABLE_NAME, 'actualización');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger para eliminación en todas las tablas
CREATE OR REPLACE FUNCTION registrar_historial_eliminacion()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO Historial (tabla_afectada, accion) VALUES (TG_TABLE_NAME, 'eliminación');
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Crear triggers para todas las tablas

-- Tabla: Usuario
CREATE TRIGGER usuario_historial_insert_trigger
AFTER INSERT ON Usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER usuario_historial_update_trigger
AFTER UPDATE ON Usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER usuario_historial_delete_trigger
AFTER DELETE ON Usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Atleta
CREATE TRIGGER atleta_historial_insert_trigger
AFTER INSERT ON Atleta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER atleta_historial_update_trigger
AFTER UPDATE ON Atleta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER atleta_historial_delete_trigger
AFTER DELETE ON Atleta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Nutriologo
CREATE TRIGGER nutriologo_historial_insert_trigger
AFTER INSERT ON Nutriologo
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER nutriologo_historial_update_trigger
AFTER UPDATE ON Nutriologo
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER nutriologo_historial_delete_trigger
AFTER DELETE ON Nutriologo
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Entrenador
CREATE TRIGGER entrenador_historial_insert_trigger
AFTER INSERT ON Entrenador
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER entrenador_historial_update_trigger
AFTER UPDATE ON Entrenador
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER entrenador_historial_delete_trigger
AFTER DELETE ON Entrenador
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Rutina
CREATE TRIGGER rutina_historial_insert_trigger
AFTER INSERT ON Rutina
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER rutina_historial_update_trigger
AFTER UPDATE ON Rutina
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER rutina_historial_delete_trigger
AFTER DELETE ON Rutina
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Dieta
CREATE TRIGGER dieta_historial_insert_trigger
AFTER INSERT ON Dieta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER dieta_historial_update_trigger
AFTER UPDATE ON Dieta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER dieta_historial_delete_trigger
AFTER DELETE ON Dieta
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Alimento
CREATE TRIGGER alimento_historial_insert_trigger
AFTER INSERT ON Alimento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER alimento_historial_update_trigger
AFTER UPDATE ON Alimento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER alimento_historial_delete_trigger
AFTER DELETE ON Alimento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Tabla: Seguimiento
CREATE TRIGGER seguimiento_historial_insert_trigger
AFTER INSERT ON Seguimiento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER seguimiento_historial_update_trigger
AFTER UPDATE ON Seguimiento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER seguimiento_historial_delete_trigger
AFTER DELETE ON Seguimiento
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();


-- Trigger para la tabla Administradores
CREATE TRIGGER administradores_historial_insert_trigger
AFTER INSERT ON Administradores
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER administradores_historial_update_trigger
AFTER UPDATE ON Administradores
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER administradores_historial_delete_trigger
AFTER DELETE ON Administradores
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();

-- Trigger para  la tabla enlace
CREATE TRIGGER enlace_historial_insert_trigger
AFTER INSERT ON enlace
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_insercion();

CREATE TRIGGER enlace_historial_update_trigger
AFTER UPDATE ON enlace
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_actualizacion();

CREATE TRIGGER enlace_historial_delete_trigger
AFTER DELETE ON enlace
FOR EACH ROW
EXECUTE FUNCTION registrar_historial_eliminacion();



--inserta losdatos ingresados en rutinas de manera automatica a la tabla seguimiento
CREATE OR REPLACE FUNCTION insertar_seguimiento_rutina()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO Seguimiento (id_atleta, id_entrenador, id_rutina)
  VALUES (NEW.id_atleta, NEW.id_entrenador, NEW.id);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insertar_seguimiento_rutina_trigger
AFTER INSERT ON Rutina
FOR EACH ROW
EXECUTE PROCEDURE insertar_seguimiento_rutina();



--inserta losdatos ingresados en dietas de manera automatica a la tabla seguimiento
CREATE OR REPLACE FUNCTION insertar_seguimiento_dieta()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO Seguimiento (id_atleta, id_nutriologo, id_dieta)
  VALUES (NEW.id_atleta, NEW.id_nutriologo, NEW.id);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insertar_seguimiento_dieta_trigger
AFTER INSERT ON Dieta
FOR EACH ROW
EXECUTE PROCEDURE insertar_seguimiento_dieta();




--cursor(funcion) que muestra una tabla con los datos de los atletas 
CREATE OR REPLACE FUNCTION mostrar_atletas()
RETURNS TABLE (
  usuario_id INTEGER,
  contraseña TEXT,
  tarjetadeacceso TEXT,
  nombres TEXT,
  apellidos TEXT,
  ocupacion TEXT,
  tamaño_centimetros INTEGER,
  peso_gramos INTEGER,
  atleta_fechadenacimiento DATE,
  atleta_edad INTEGER,
  categoria TEXT,
  disiplina TEXT,
  atleta_dirección TEXT,
  atleta_correoelectronico TEXT
) AS $$
DECLARE
  cursor_atletas CURSOR FOR
    SELECT u.id AS usuario_id, u.contraseña, u.tarjetadeacceso, u.nombres, u.apellidos, u.ocupacion,
    a.tamaño_centimetros, a.peso_gramos, a.fechadenacimiento AS atleta_fechadenacimiento, a.edad AS atleta_edad,
    a.categoria, a.disiplina, a.dirección AS atleta_dirección, a.correoelectronico AS atleta_correoelectronico
    FROM Usuario u
    JOIN Atleta a ON u.id = a.id;
BEGIN
  OPEN cursor_atletas;
  LOOP
    FETCH cursor_atletas INTO usuario_id, contraseña, tarjetadeacceso, nombres, apellidos, ocupacion,
    tamaño_centimetros, peso_gramos, atleta_fechadenacimiento, atleta_edad,
    categoria, disiplina, atleta_dirección, atleta_correoelectronico;
    EXIT WHEN NOT FOUND;
    -- Aquí puedes hacer lo que quieras con los datos del cursor
    -- Por ejemplo, puedes devolverlos como una fila de la tabla
    RETURN NEXT;
  END LOOP;
  CLOSE cursor_atletas;
END;
$$ LANGUAGE plpgsql;


--para llamar al cursor (funcion)
SELECT * FROM mostrar_atletas();

select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,m.tamaño_centimetros,m.peso_gramos,m.atleta_fechadenacimiento,
m.atleta_edad,m.categoria,m.disiplina,m.atleta_dirección,m.atleta_correoelectronico
from mostrar_atletas() m
order by m.usuario_id;


--cursor(funcion) que muestra una tabla con los datos de los entrenadores
CREATE OR REPLACE FUNCTION mostrar_entrenadores()
RETURNS TABLE (
  usuario_id INTEGER,
  contraseña TEXT,
  tarjetadeacceso TEXT,
  nombres TEXT,
  apellidos TEXT,
  ocupacion TEXT,
  entrenador_fechadenacimiento DATE,
  entrenador_edad INTEGER,
  entrenador_dirección TEXT,
  entrenador_correoelectronico TEXT
) AS $$
DECLARE
  cursor_entrenadores CURSOR FOR
    SELECT u.id AS usuario_id, u.contraseña, u.tarjetadeacceso, u.nombres, u.apellidos, u.ocupacion,
    e.fechadenacimiento AS entrenador_fechadenacimiento, e.edad AS entrenador_edad,
    e.dirección AS entrenador_dirección, e.correoelectronico AS entrenador_correoelectronico
    FROM Usuario u
    JOIN Entrenador e ON u.id = e.id;
BEGIN
  OPEN cursor_entrenadores;
  LOOP
    FETCH cursor_entrenadores INTO usuario_id, contraseña, tarjetadeacceso, nombres, apellidos, ocupacion,
    entrenador_fechadenacimiento, entrenador_edad,
    entrenador_dirección, entrenador_correoelectronico;
    EXIT WHEN NOT FOUND;
    -- Aquí puedes hacer lo que quieras con los datos del cursor
    -- Por ejemplo, puedes devolverlos como una fila de la tabla
    RETURN NEXT;
  END LOOP;
  CLOSE cursor_entrenadores;
END;
$$ LANGUAGE plpgsql;


--para llamar al cursor (funcion)
SELECT * FROM mostrar_entrenadores();

select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,m.entrenador_fechadenacimiento,
m.entrenador_edad,m.entrenador_dirección,m.entrenador_correoelectronico
from mostrar_entrenadores() m
order by m.usuario_id;


--cursor(funcion) que muestra una tabla con los datos de los Nutriologo
CREATE OR REPLACE FUNCTION mostrar_nutriologos()
RETURNS TABLE (
  usuario_id INTEGER,
  contraseña TEXT,
  tarjetadeacceso TEXT,
  nombres TEXT,
  apellidos TEXT,
  ocupacion TEXT,
  nutriologo_fechadenacimiento DATE,
  nutriologo_edad INTEGER,
  nutriologo_dirección TEXT,
  nutriologo_correoelectronico TEXT
) AS $$
DECLARE
  cursor_nutriologos CURSOR FOR
    SELECT u.id AS usuario_id, u.contraseña, u.tarjetadeacceso, u.nombres, u.apellidos, u.ocupacion,
    n.fechadenacimiento AS nutriologo_fechadenacimiento, n.edad AS nutriologo_edad,
    n.dirección AS nutriologo_dirección, n.correoelectronico AS nutriologo_correoelectronico
    FROM Usuario u
    JOIN Nutriologo n ON u.id = n.id;
BEGIN
  OPEN cursor_nutriologos;
  LOOP
    FETCH cursor_nutriologos INTO usuario_id, contraseña, tarjetadeacceso, nombres, apellidos, ocupacion,
    nutriologo_fechadenacimiento, nutriologo_edad,
    nutriologo_dirección, nutriologo_correoelectronico;
    EXIT WHEN NOT FOUND;
    -- Aquí puedes hacer lo que quieras con los datos del cursor
    -- Por ejemplo, puedes devolverlos como una fila de la tabla
    RETURN NEXT;
  END LOOP;
  CLOSE cursor_nutriologos;
END;
$$ LANGUAGE plpgsql;

--para llamar al cursor (funcion)
SELECT * FROM cursor_nutriologos();

select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,m.nutriologo_fechadenacimiento,
m.nutriologo_edad ,m.nutriologo_dirección ,m.nutriologo_correoelectronico
from mostrar_nutriologos() m
order by m.usuario_id;




--cursor(funcion) que muestra una tabla con los datos de los administradores
CREATE OR REPLACE FUNCTION mostrar_administradores()
RETURNS TABLE (
  usuario_id INTEGER,
  contraseña TEXT,
  tarjetadeacceso TEXT,
  nombres TEXT,
  apellidos TEXT,
  ocupacion TEXT,
  administrador_cargo TEXT,
  administrador_estado_actividad BOOLEAN
) AS $$
DECLARE
  cursor_administradores CURSOR FOR
    SELECT u.id AS usuario_id, u.contraseña, u.tarjetadeacceso, u.nombres, u.apellidos, u.ocupacion,
    a.cargo AS administrador_cargo, a.estado_actividad AS administrador_estado_actividad
    FROM Usuario u
    JOIN Administradores a ON u.id = a.id;
BEGIN
  OPEN cursor_administradores;
  LOOP
    FETCH cursor_administradores INTO usuario_id, contraseña, tarjetadeacceso, nombres, apellidos, ocupacion,
    administrador_cargo, administrador_estado_actividad;
    EXIT WHEN NOT FOUND;
    -- Aquí puedes hacer lo que quieras con los datos del cursor
    -- Por ejemplo, puedes devolverlos como una fila de la tabla
    RETURN NEXT;
  END LOOP;
  CLOSE cursor_administradores;
END;
$$ LANGUAGE plpgsql;


--para llamar al cursor (funcion)
SELECT * FROM mostrar_administradores();

select m.usuario_id,m.contraseña,m.tarjetadeacceso,m.nombres,m.apellidos,m.ocupacion,
m.administrador_cargo,m.administrador_estado_actividad
from mostrar_administradores() m
order by m.usuario_id;





--trigger (funcion)
CREATE OR REPLACE FUNCTION actualizar_edad()
RETURNS TRIGGER AS $$
BEGIN
  NEW.edad := date_part('year', age(NEW.fechadenacimiento));
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--enlazamos el trigger (funcion) a las tablas atleta, entrenador y nutriologo
CREATE TRIGGER actualizar_edad_atleta
BEFORE INSERT OR UPDATE ON Atleta
FOR EACH ROW
EXECUTE FUNCTION actualizar_edad();

CREATE TRIGGER actualizar_edad_nutriologo
BEFORE INSERT OR UPDATE ON Nutriologo
FOR EACH ROW
EXECUTE FUNCTION actualizar_edad();

CREATE TRIGGER actualizar_edad_entrenador
BEFORE INSERT OR UPDATE ON Entrenador
FOR EACH ROW
EXECUTE FUNCTION actualizar_edad();





--muestra los id y nombres de los enlaces
SELECT enlace.id,Atleta.id,UsuarioAtleta.nombres,UsuarioAtleta.apellidos,Entrenador.id,UsuarioEntrenador.nombres,
       UsuarioEntrenador.apellidos,Nutriologo.id,UsuarioNutriologo.nombres,UsuarioNutriologo.apellidos
FROM enlace
JOIN Atleta ON enlace.id_atleta = Atleta.id
JOIN Entrenador ON enlace.id_entrenador = Entrenador.id
JOIN Nutriologo ON enlace.id_nutriologo = Nutriologo.id
JOIN Usuario AS UsuarioAtleta ON Atleta.id = UsuarioAtleta.id
JOIN Usuario AS UsuarioEntrenador ON Entrenador.id = UsuarioEntrenador.id
JOIN Usuario AS UsuarioNutriologo ON Nutriologo.id = UsuarioNutriologo.id;


--muestra id  noombre apellido y ocupacion
SELECT Usuario.id,
       Usuario.nombres,
       Usuario.apellidos,
       Usuario.ocupacion
FROM Usuario
JOIN Atleta ON Usuario.id = Atleta.id
UNION
SELECT Usuario.id,
       Usuario.nombres,
       Usuario.apellidos,
       Usuario.ocupacion
FROM Usuario
JOIN Entrenador ON Usuario.id = Entrenador.id
UNION
SELECT Usuario.id,
       Usuario.nombres,
       Usuario.apellidos,
       Usuario.ocupacion
FROM Usuario
JOIN Nutriologo ON Usuario.id = Nutriologo.id
ORDER BY ocupacion;


--procedimiento

CREATE OR REPLACE PROCEDURE insertar_atleta(
    contrasena TEXT,
    tarjeta_de_acceso TEXT,
    nombres TEXT,
    apellidos TEXT,
    ocupacion TEXT,
    tamano_centimetros INTEGER,
    peso_gramos INTEGER,
    fecha_de_nacimiento DATE,
    edad INTEGER,
    categoria TEXT,
    disciplina TEXT,
    direccion TEXT,
    correo_electronico TEXT
) AS $$
BEGIN
    INSERT INTO Usuario (contraseña, tarjetadeacceso, nombres, apellidos, ocupacion)
    VALUES (contrasena, tarjeta_de_acceso, nombres, apellidos, ocupacion);
    
    INSERT INTO Atleta (id, tamaño_centimetros, peso_gramos, fechadenacimiento, edad, categoria, disiplina, dirección, correoelectronico)
    VALUES (currval('usuario_id_seq'), tamano_centimetros, peso_gramos, fecha_de_nacimiento, edad, categoria, disciplina, direccion, correo_electronico);
END;
$$ LANGUAGE plpgsql;


--esta es una frma de llamar el procedimiento
CALL insertar_atleta('contraseña123','123456789','Juan','Pérez','Atleta',180,80000,'1999-01-01',24,'Profesional','Natación',
'Calle Falsa 123','juan.perez@example.com'
);

