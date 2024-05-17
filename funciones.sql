
DELIMITER //
CREATE FUNCTION crearCodigoCancion () RETURNS INT READS SQL DATA
BEGIN
     DECLARE contador INT DEFAULT 1;
    DECLARE maximo INT;

    SELECT COUNT(codCancion) INTO maximo FROM cancion;

    WHILE contador <= maximo + 1 DO
        IF NOT EXISTS (SELECT codCancion FROM cancion WHERE contador = codCancion) THEN
            RETURN contador;
        END IF;
        SET contador = contador + 1;
    END WHILE;

    RETURN 0;
END//


DELIMITER //
CREATE FUNCTION crearCodigoAlbum () RETURNS INT READS SQL DATA
BEGIN
     DECLARE contador INT DEFAULT 1;
    DECLARE maximo INT;

    SELECT COUNT(codAlbum) INTO maximo FROM album;

    WHILE contador <= maximo + 1 DO
        IF NOT EXISTS (SELECT codAlbum FROM album WHERE contador = codAlbum) THEN
            RETURN contador;
        END IF;
        SET contador = contador + 1;
    END WHILE;

    RETURN 0;
END//



DELIMITER //
CREATE FUNCTION crearCodigoPlaylist () RETURNS INT READS SQL DATA
BEGIN
    DECLARE cod INT;
    SELECT MAX(codPlayList) + 1 INTO cod FROM playlist;
    RETURN cod;
END//

DELIMITER //
create procedure modificacionUsuario (dniT varchar(9), nombreT varchar(20), apellidoT varchar(20), paisT varchar(20), edadT int, nomUsu varchar(20), contra varchar(20)) 
begin
	declare exist int;
	select count(*) into exist from usuario where dni = dniT;
    if exist>0 then
		update persona set nombrePersona = nombreT, apellidoPersona = apellidoT, pais = paisT, edad = edadT where dni = dniT;
		update usuario set nombreUsuario = nomUsu, contraseina = contra where dni = dniT;
	end if;
end//

DELIMITER //
create procedure eliminarUsuario (dniT varchar(9)) 
begin
	declare exist int;
	select count(*) into exist from usuario where dni = dniT;
    if exist>0 then
		delete from persona where dni = dniT;
		delete from usuario where dni = dniT;
	end if;
end//