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
