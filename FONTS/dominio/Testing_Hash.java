package dominio;

import java.security.MessageDigest;

/**
 * Clase encargado de la codificacion, usando tablas de Hash. Codifica en MD5 y SHA-256.
 * MD5 es una tecnica de codificacion no segura (en terminos criptograficos), pero que es usada en la actualidad como "checksum". Por ejemplo, es comun descargar un archivo y ver que este imprime un codigo md5, que se debe comparar con el de la pagina web de descarga, para ver si el archivo se ha descargado integramente.
 * SHA-256 es una tecnica de codificacion usada en la actualidad para la transmision de informacion sensible. Su elevada complejidad para descifrar el resultado hash y obtener los datos originales lo hace muy interesante.
 */

public class Testing_Hash {
    /**
     * Constructor de la clase Testing_Hash.
     * @custom.mytag1 True
     * <p>
     * @custom.mytag2 Creamos una instancia de los posibles Hashes para poder utilizarlos.
     * <p>
     * @custom.mytag3 Constructor de la clase Testing_Hash.
     * <p>
     */
    public Testing_Hash() {
    }

    /**
     * Convierte un array de bytes en un string, con los caracteres representados en hexadecimal.
     * @custom.mytag1 bytes es un array de bytes de nulos.
     * <p>
     * @custom.mytag2 Devuelve un string (en hexadecimal) equivalente al array de bytes.
     * <p>
     * @custom.mytag3 Convierte un array de bytes en un string, con los caracteres representados en hexadecimal.
     * <p>
     * @param bytes es un array de bytes
     * @return Devuelve un String donde se almacenan la transformacion de los bytes recibidos a hexadecimal.
     */
    public String byteToHexa(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * Encripta una string usando SHA-256.
     * Devuelve el codigo hash en hexadecimal, usando una string de 64 caracteres.
     * SHA-256 añade seguridad para almacenar datos sensibles.
     * @custom.mytag1 password es una string no nula
     * <p>
     * @custom.mytag2 Tenemos password codificado.
     * <p>
     * @custom.mytag3 Encripta una string usando SHA-256.
     * Devuelve el codigo hash en hexadecimal, usando una string de 64 caracteres.
     * SHA-256 añade seguridad para almacenar datos sensibles.
     * <p>
     * @param password es el string que contiene la contraseña que queremos encriptar.
     * @return Devuelve la contraseña encriptada.
     */
    public String passwordToHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes());
            return byteToHexa(md.digest());
        } catch (Exception e) {
            //Esta función requiere estructura try-catch
            //porque MessageDigest.getInstance(...) puede lanzar excepción
            return "Internal Error in passwordToHash: bad algorithm name";
        }
    }

}
