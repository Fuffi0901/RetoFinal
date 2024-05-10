DELIMITER //
CREATE FUNCTION crearCodigoCancion () RETURNS INT READS SQL DATA
BEGIN
    DECLARE cod INT;
    SELECT IFNULL(MAX(codCancion), 0) + 1 INTO cod FROM cancion;
    SET cod = LPAD(cod, 3, '0');
    IF cod > 999 THEN
        SET cod = 999;
    END IF;
    RETURN cod;
END//


DELIMITER //
CREATE FUNCTION crearCodigoAlbum () RETURNS INT READS SQL DATA
BEGIN
    DECLARE cod INT;
    SELECT IFNULL(MAX(codAlbum), 0) + 1 INTO cod FROM album;
    SET cod = LPAD(cod, 3, '0');
    IF cod > 999 THEN
        SET cod = 999;
    END IF;
    RETURN cod;
END//

DELIMITER //
CREATE FUNCTION crearCodigoPlaylist () RETURNS INT READS SQL DATA
BEGIN
    DECLARE cod INT;
    SELECT IFNULL(MAX(codPlayList), 0) + 1 INTO cod FROM playlist;
    SET cod = LPAD(cod, 3, '0');
    IF cod > 999 THEN
        SET cod = 999;
    END IF;
    RETURN cod;
END//

