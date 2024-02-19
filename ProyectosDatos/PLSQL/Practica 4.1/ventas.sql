--------------------------------------------------------
-- Archivo creado  - lunes-febrero-19-2024   
--------------------------------------------------------
REM INSERTING into ANONYMOUS.TABLAVENTAS
SET DEFINE OFF;
Insert into ANONYMOUS.TABLAVENTAS (IDVENTA,IDCLIENTE,FECHAVENTA,LINEAS) values ('1','ANONYMOUS.TCLIENTE(1, ''Luis García'', ANONYMOUS.TDIRECCION(''C/Dalias,22'', ''Sevilla'', 44330, ''Sevilla''), ''34343434L'', ANONYMOUS.TTELEFONO(950234577, 67899003))',to_date('02/02/24','DD/MM/RR'),ANONYMOUS.TLINEASVENTAS(ANONYMOUS.TLINEAVENTA(1, 'oracle.sql.REF@f22c4334', 3), ANONYMOUS.TLINEAVENTA(2, 'oracle.sql.REF@401e4be4', 1)));
Insert into ANONYMOUS.TABLAVENTAS (IDVENTA,IDCLIENTE,FECHAVENTA,LINEAS) values ('2','ANONYMOUS.TCLIENTE(2, ''Roberto García'', ANONYMOUS.TDIRECCION(''C/Falsa,123'', ''Granada'', 10330, ''Granada''), ''5656565L'', ANONYMOUS.TTELEFONO(58634577, 46329003))',to_date('02/02/24','DD/MM/RR'),ANONYMOUS.TLINEASVENTAS(ANONYMOUS.TLINEAVENTA(2, 'oracle.sql.REF@8e074e3e', 3), ANONYMOUS.TLINEAVENTA(3, 'oracle.sql.REF@bdc43bb', 7), ANONYMOUS.TLINEAVENTA(1, 'oracle.sql.REF@f22c4334', 1)));
