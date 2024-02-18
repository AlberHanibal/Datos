--------------------------------------------------------
--  DDL for Type TCLIENTE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TCLIENTE" AS OBJECT (IDCLIENTE NUMBER, NOMBRE VARCHAR2(50), DIREC TDIRECCION, NIF VARCHAR2(9), TEL TTELEFONO);

/
--------------------------------------------------------
--  DDL for Type TDIRECCION
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TDIRECCION" AS OBJECT (CALLE VARCHAR2(50), POBLACION VARCHAR2(50), CODPOSTAL NUMBER(5), PROVINCIA VARCHAR2(40));

/

--------------------------------------------------------
--  DDL for Type TLINEASVENTAS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TLINEASVENTAS" AS TABLE OF TLINEAVENTA;

/
--------------------------------------------------------
--  DDL for Type TLINEAVENTA
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TLINEAVENTA" AS OBJECT(
NUMLINEA NUMBER,
IDPRODUCTO REF TPRODUCTO,
CANTIDAD NUMBER

);

/
--------------------------------------------------------
--  DDL for Type TPRODUCTO
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TPRODUCTO" AS OBJECT (IDPRODUCTO NUMBER, DESCRIPCION VARCHAR2(50), PRECIO NUMBER, EXISTENCIAS NUMBER);

/
--------------------------------------------------------
--  DDL for Type TTELEFONO
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TTELEFONO" AS VARRAY(3) OF VARCHAR2(15);

/
--------------------------------------------------------
--  DDL for Type TVENTAS
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TYPE "ANONYMOUS"."TVENTAS" AS OBJECT(
IDVENTA NUMBER,
IDCLIENTE REF TCLIENTE,
FECHAVENTA DATE,
LINEAS TLINEASVENTAS,
MEMBER FUNCTION CALCULATOTALVENTAS RETURN NUMBER)
/
CREATE OR REPLACE NONEDITIONABLE TYPE BODY "ANONYMOUS"."TVENTAS" AS
MEMBER FUNCTION CALCULATOTALVENTAS RETURN NUMBER IS
TOTAL NUMBER := 0;
LINEA TLINEAVENTA;
PRODUCT TPRODUCTO;
BEGIN
    FOR I IN 1 .. LINEAS.COUNT LOOP
        LINEA := LINEAS(I);
        SELECT DEREF(LINEA.IDPRODUCTO) INTO PRODUCT FROM DUAL;
        TOTAL := TOTAL + LINEA.CANTIDAD * PRODUCT.PRECIO;
    END LOOP;
    RETURN TOTAL;
END CALCULATOTALVENTAS;
END;

/

