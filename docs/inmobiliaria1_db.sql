
/*

		O J O :

		
		Este script debe ser ejecutado como root de MySQL

		Posteriormente convendría crear una conexión con este usuario y localhost
		para gestionar esta base de datos sin afectar accidentalmente al resto

*/



-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS inmobiliaria1_db;

-- 2. Seleccionar la base de datos para trabajar en ella
USE inmobiliaria1_db;

-- 3. Crear la tabla 'inquilino'
CREATE TABLE IF NOT EXISTS inquilino (
 nro_documento VARCHAR(50) PRIMARY KEY NOT NULL UNIQUE,
 nombre VARCHAR(255) NOT NULL
);

-- 4. Crear el usuario 'inmobiliaria_db_root'
-- La parte 'IDENTIFIED BY' define la contraseña.
CREATE USER 'inmobiliaria1_db_root'@'localhost' IDENTIFIED BY 'Tutann12';

-- Si tu MySQL Workbench está conectado a un servidor remoto,
-- o si el usuario necesita conectarse desde cualquier host,
-- cambia 'localhost' por '%'. Ejemplo:
-- CREATE USER 'inmobiliaria_db_root'@'%' IDENTIFIED BY 'Tutann12';

-- 5. Otorgar todos los permisos sobre la base de datos 'inmobiliaria_db' al nuevo usuario
-- Esto le da permisos completos (SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, etc.)
GRANT ALL PRIVILEGES ON inmobiliaria1_db.* TO 'inmobiliaria1_db_root'@'localhost';

-- Si usaste '%' para el usuario, usa '%' aquí también:
-- GRANT ALL PRIVILEGES ON inmobiliaria_db.* TO 'inmobiliaria_db_root'@'%';

-- 6. Recargar los privilegios para que los cambios surtan efecto
FLUSH PRIVILEGES;

-- Opcional: Verificar que el usuario fue creado (desde otra sesión o como root)
-- SELECT user, host FROM mysql.user WHERE user = 'inmobiliaria1_db_root';

-- Opcional: Verificar los permisos del usuario (desde otra sesión o como root)
-- SHOW GRANTS FOR 'inmobiliaria1_db_root'@'localhost'; 