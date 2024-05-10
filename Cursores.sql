DELIMITER //
CREATE FUNCTION artistaPorCancion(cod INT) RETURNS VARCHAR(300) READS SQL DATA
BEGIN
    DECLARE result VARCHAR(300);
    DECLARE nombre VARCHAR(300);
    DECLARE fin BOOL DEFAULT FALSE;
    DECLARE cur CURSOR FOR 
        SELECT a.nombreArtistico 
        FROM artista a, canta c 
        WHERE a.Dni = c.Dni AND codCancion = cod;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = TRUE;
    OPEN cur;
    SET result = '';
    read_loop: LOOP
        FETCH cur INTO nombre;
        IF fin THEN
            LEAVE read_loop;
        END IF;
        
        IF LENGTH(result) > 0 THEN
            SET result = CONCAT(result, ', ', nombre);
        ELSE
            SET result = nombre;
        END IF;
    END LOOP read_loop;
    CLOSE cur;
    RETURN result;
END//
