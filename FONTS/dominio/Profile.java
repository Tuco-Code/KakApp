package dominio;

/**
 * La clase Profile representa el usuario que accede a la aplicacion de Kakuros.
 */

public class Profile {
    /**
     * Nombre por el cual se identifica el usuario.
     */
    private String name;
    /**
     * Contrasena encriptada, necesaria para acceder al perfil.
     */
    private String coded_password;
    /**
     * Cantidad de Kakuros jugados.
     */
    private int numOfGames;
    /**
     * Cantidad de Kakuros completados.
     */
    private int numOfWins;

    /**
     * Generamos un perfil nuevo mediante los credenciales obtenidos.
     * @custom.mytag1 El String n no puede ser el nombre de algun usuario existente.
     * <p>
     * @custom.mytag2 Creamos el usuario identificado por el String n con su respectiva contrasena p, que sera encriptada. La cantidad de Kakuros jugados y completados la inicializamos a 0.
     * <p>
     * @custom.mytag3 Generamos un perfil nuevo mediante los credenciales obtenidos.
     * <p>
     * @param n Nombre del usuario a crear.
     * @param p Contrasena del usuario a crear previamente hasheada.
     */
    public Profile(String n, String p) {
        name = n;
        numOfGames = 0;
        numOfWins = 0;

        Testing_Hash h = new Testing_Hash();
        coded_password = p;
    }

    /**
     * Generamos un perfil nuevo mediante los credenciales obtenidos, el numero de juegos que ha creado, y cuantos de ellos ha conseguido resolver.
     * @custom.mytag1 El String n no puede ser el nombre de algun usuario existente, numGames y numWins son enteros no negativos.
     * <p>
     * @custom.mytag2 Creamos el usuario identificado por el String n con su respectiva contrasena p, que sera encriptada. La cantidad de Kakuros jugados y completados la inicializamos a 0.
     * <p>
     * @custom.mytag3 Generamos un perfil nuevo mediante los credenciales obtenidos.
     * <p>
     * @param n Nombre del usuario a crear.
     * @param p Contrasena del usuario a crear previamente hasheada.
     * @param numGames Numero de juegos inicializados por el usuario.
     * @param numWins Numero de juegos inicializados y ganados por el usuario.
     */
    public Profile(String n, String p, int numGames, int numWins) {
        name = n;
        numOfGames = numGames;
        numOfWins = numWins;

        Testing_Hash h = new Testing_Hash();
        coded_password = p;
    }

    /**
     * Obtenemos el numero de Kakuros jugados.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros jugados.
     * <p>
     * @custom.mytag3 Obtenemos el numero de Kakuros jugados.
     * <p>
     * @return Numero de Kakuros jugados.
     */
    public int getNumOfGames() {
        return numOfGames;
    }

    /**
     * Obtenemos el numero de Kakuros completados.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros completados.
     * <p>
     * @custom.mytag3 Obtenemos el numero de Kakuros completados.
     * <p>
     * @return Numero de Kakuros completados.
     */
    public int getNumOfWins() {
        return numOfWins;
    }

    /**
     * Obtenemos el nombre del usuario.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el nombre del usuario.
     * <p>
     * @custom.mytag3 Obtenemos el nombre del usuario.
     * <p>
     * @return Nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtenemos la contraseña del usuario.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos la contraseña del usuario.
     * <p>
     * @custom.mytag3 Obtenemos la contraseña del usuario.
     * <p>
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return coded_password;
    }

    /**
     * Cada vez que el jugador vaya a jugar un Kakuro, actulizaremos el numero de Kakuros jugados.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Incrementamos en uno el numero de Kakuros jugados.
     * <p>
     * @custom.mytag3 Cada vez que el jugador vaya a jugar un Kakuro, actulizaremos el numero de Kakuros jugados.
     * <p>
     */
    public void increaseNumOfGames() {
        ++numOfGames;
    }

    /**
     * Cada vez que el jugador complete un Kakuro, actualizaremos el numero de Kakuros completados.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Incrementamos en uno el numero de Kakuros completados.
     * <p>
     * @custom.mytag3 Cada vez que el jugador complete un Kakuro, actualizaremos el numero de Kakuros completados.
     * <p>
     */
    public void increaseNumOfWins() {
        ++numOfWins;
    }


    /**
     * Cambiamos la contrasena actual por newpass, solo si newpass es diferente de la contrasena actual.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado y el String newpass deberia ser diferente a la contrasena actual.
     * <p>
     * @custom.mytag2 Cambiamos la contrasena del usuario, tomando newpass como contrasena actual.
     * <p>
     * @custom.mytag3 Cambiamos la contrasena actual por newpass, solo si newpass es diferente de la contrasena actual.
     * <p>
     * @param newpass Contrasena hasheada que pasara a ser la actual.
     * @return Devolvemos true si hemos cambiado la contrasena, o false si no la hemos cambiado.
     */
    public boolean changePassword(String newpass) {
        String new_coded_password = newpass;

        if(coded_password.equals(new_coded_password)) return false;
        coded_password = new_coded_password;
        return true;
    }
}
