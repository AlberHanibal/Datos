-- Selecciona cod_vuelo, destino y procedencia de todos los vuelos con más de 50 plazas.
SELECT pasajeros.cod_vuelo, destino, procedencia FROM vuelos
INNER JOIN pasajeros ON pasajeros.COD_VUELO = vuelos.COD_VUELO
WHERE (PLAZAS_FUMADOR + PLAZAS_NO_FUMADOR + PLAZAS_TURISTA + PLAZAS_PRIMERA) > 50

-- Selecciona todos los datos de los vuelos cuya procedencia sea Madrid.
SELECT * FROM vuelos
WHERE procedencia LIKE 'MADRID'

-- Selecciona cod_vuelo y hora de salida de todos los vuelos de procedencia Madrid y destino Roma.
SELECT cod_vuelo, hora_salida FROM vuelos
WHERE procedencia LIKE 'MADRID' AND destino LIKE 'ROMA'

-- Selecciona los NUMs de los pasajeros que van con destino Barcelona.
SELECT p.num FROM pasajeros p
INNER JOIN vuelos v ON v.cod_vuelo = p.cod_vuelo
WHERE v.destino LIKE 'BARCELONA'

-- Selecciona cuántos pasajeros van en el vuelo IB-D5-347
SELECT COUNT(NUM) as num_pasajeros FROM pasajeros
WHERE cod_vuelo LIKE 'IB-D5-347'

-- Selecciona cuántos pasajeros van en el vuelo IB-SP-4567 en plaza turista.
SELECT COUNT(p.NUM) as num_pasajeros FROM pasajeros p
INNER JOIN vuelos v ON v.cod_vuelo = p.cod_vuelo
WHERE p.cod_vuelo LIKE 'IB-SP-4567' AND p.tipo_plaza LIKE 'TU'

-- Inserta un nuevo vuelo en la tabla vuelos.
INSERT INTO vuelos 
VALUES ('BG-WW-PPX', '17/11/23-14:05', 'ALASKA', 'FUENLABRADA', 0, 150, 150, 2)

-- Inserta 2 pasajeros en la tabla pasajeros y asignarlos a ese vuelo
INSERT INTO pasajeros
VALUES (555, 'BG-WW-PPX', 'TU', 'NO')
INSERT INTO pasajeros
VALUES (666, 'BG-WW-PPX', 'PR', 'NO')

-- Modifica la hora de salida del vuelo anterior.
UPDATE vuelos
SET hora_salida = '17/11/23-19:26'
WHERE cod_vuelo LIKE 'BG-WW-PPX'

-- Borra a uno de los dos pasajeros asignados a este vuelo.
DELETE FROM pasajeros
WHERE NUM = 555