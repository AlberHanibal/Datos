# Gestión ficheros y directorios
----------------
## java.io File
----------------
- mkdir()
- createNewFile()
- list(), listFiles() listar directorio
- delete()
- renameTo(File)
- getAbsolutePath(), getPath()
- getNme()
- isDirectoriy(), isFile(), canRead(), canWrite()

## java.nio
------------------
### Paths
- Paths.get(String ruta)
### Files
Estáticos
- exists(ruta)
- createFile(ruta), createDirectory(ruta)
- copy(ruta1, ruta2), move(ruta1, ruta2)
- readAllLines(ruta) returns List<String>

## Acceso secuencial
------------------
### Fichero texto
- Clase FileWriter(ruta o File) abrir el flujo de salida hacia el fichero
- Clase FileReader(ruta o File) para leer
- Clase BufferedReader con readLine()
- Clase PrintWriter con println() para escribir
- Clase Scanner con nextInt(), nextBoolean(), nextChar(), nextDouble(), nextLine() con useDelimiter()
- .close()

### Fichero binario
- Clase DataOutputStream(new BufferedOutputStream(new FileOutputStream(.dat))) con writeInt(), writeBoolean(), writeDouble(), writeChar(), writeUTF() para strings
- Clase DataInputStream(new BufferedInputStream(new FileInputStream(.dat))) con readInt(), readBoolean(), readChar(), readDouble(), readUTF() para strings
usando el EOFException
#### con serialización
- Clase ObjectOutputStream(new FileOutputStream(.obj)) con writeObject(objeto)
- Clase ObjectInputStream(new FileInputStream(.obj)) con readObject()
#### acceso aleatorio
Clase RandomAccessFile(ruta o File, r o rw)
- getFilePointer() posición actial en el fichero
- seek(position) para colocar en donde quieras con bytes
- length() tamaño del fichero en bytes
- skipBytes(desplaz) para saltar
- writeInt(), writeDouble(), writeChar() , read igual


# XML
## DOM
DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = builderFactory.newDocumentBuilder();
Document document = builder.parse(File));
- document.getDocumentElement() raiz del árbol
- getNodeName(), getTagName(), getTextContent(), setTextContent()
- getElementsByTagName(tag)
- getLength(), item(i), getChildNodes(), removeChild()
- createElement(), createTextNode(), appendChild()
------------
para grabar los cambios
Source source= new DOMSource(document);
Result result = new StreamResult(File));
Transformer transformer=TransformerFactory.newInstance().newTransformer();
transformer.transform(source,result);

## XStream
xtream-numeros.jar, kxml-numeros.jar
serializar primero
- XStream(), alias(), toXML(lista, FileOutputStream())

## HTML
os = FileOutputStream(File .html)
Source estilos = new StreamSource(.xsl)
Source datos = new StreamSource(.xml)
Result result = new StreamResult(os)
Transformer transformer=TransformerFactory.newInstance().newTransformer(estilos);
transformer.transform(source,result);


# JSON
JSONParser parser = new JSONParser();
Object obj = parser.parse(new FileReader("newjson.json"));
JSONObject jsonObject =(JSONObject) obj;
- JSONArray para leer Iterator <String> iterator = tags(es JSONArray).iterator()
ver los ejercicios
