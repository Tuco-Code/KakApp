package dominio;

/**
 * Clase que representa el estado de una partida que juego un usuario en un tablero de Kakuro
 */

public class Game {
    /**
     * Identificador que referencia al tablero de kakuro al que que estamos jugando.
     */
    private String kakuroReference;

    /**
     * Identificador que referencia al usuario que está jugando.
     */
    private String userName;

    /**
     * La representacion matricial del tablero de un Kakuro.
     */
    private Kakuro board;

    /**
     * La representacion matricial de la solución del tablero de un Kakuro.
     */
    private Kakuro solution;

    /**
     * Tiempo que tarda el usuario en resolver el kakuro.
     */
    private int time;

    /**
     * Tiempo que tarda el sistema en resolver el Kakuro.
     */
    private float time_solution;

    /**
     * Creadora de Game, pensada para crear una partida nueva.
     * @custom.mytag1 Los objetos Kakuro y Profile recibidos deben tener todos sus atributos inicializados.
     * <p>
     * @custom.mytag2 Creamos el Game del kakuro recibido para el usuario recibido, ademas calculamos y guardamos la solucion del kakuro y el time_Solution.
     * <p>
     * @custom.mytag3 Creadora de Game, pensada para crear una partida nueva.
     * <p>
     * @param original Kakuro del que se va a crear la partida.
     * @param user Usuario que va a jugar la partida.
     */
    public Game(Kakuro original, Profile user) {
        kakuroReference = original.getCode();
        userName = user.getName();
        board = new Kakuro(original);

        Kakuro aux = new Kakuro(original);

        Algorithms a = new Algorithms();
        long s = System.currentTimeMillis();
        a.howManySolutions(aux);
        time_solution = (float)((System.currentTimeMillis() -s)/1000.0);

        solution = aux;

        time = 0;
    }

    /**
     * Creadora de Game, pensada para crear una partida guardada.
     * @custom.mytag1 El String referencia debe ser el String que referencia un kakuro que se encuentre en nuestro repositorio, user debe ser el nombre
     * de algun perfil registrado, givenBoard debe ser el String que describe el estado guardado del kakuro, givenSolution debe ser el String que describe
     * la solucion del kakuro que se esta jugando y lastTime debe ser los segundos que ha pasado jugando el kakuro.
     * <p>
     * @custom.mytag2 Cargamos el Game del kakuro definido por el String referencia, del usuario user con el tablero tal y como lo dejo cuando salio de
     * la aplicacion (descrito con el String givenBoard).
     * <p>
     * @custom.mytag3 Creadora de Game, pensada para crear una partida guardada.
     * <p>
     * @param referencia String que referencia a uno de los kakuros jugables de nuestra aplicacion.
     * @param user Nombre del usuario que ha dejado una partida a medias.
     * @param givenBoard Estado del tablero del kakuro cuando el usuario salio de nuestra aplicacion.
     * @param givenSolution Solucion del kakuro que se esta jugando.
     * @param lastTime Tiempo que ha pasado el usuario resolviendo el kakuro.
     */
    public Game(String referencia, String user, String givenBoard, String givenSolution, int lastTime, float time_s) {
        kakuroReference = referencia;
        userName = user;
        board = new Kakuro(givenBoard);
        solution = new Kakuro(givenSolution);
        time = lastTime;
        time_solution = time_s;
    }

    /**
     * Obtenemos el String que referencia el kakuro que se esta jugando.
     * @custom.mytag1 El String kakuroReference debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el String que referencia el kakuro que esta jugando el usuario.
     * <p>
     * @custom.mytag3 Obtenemos el String que referencia el kakuro que se esta jugando.
     * <p>
     * @return Devolvemos el String que referencia el kakuro que esta jugando el usuario.
     */
    public String getKakuroReference() {
        return kakuroReference;
    }

    /**
     * Obtenemos el nombre del usuario que esta jugando la partida.
     * @custom.mytag1 El String userName debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el nombre del usuario que esta jugando el kakuro.
     * <p>
     * @custom.mytag3 Obtenemos el nombre del usuario que esta jugando la partida.
     * <p>
     * @return Devolvemos el nombre del usuario que esta jugando el kakuro.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Obtenemos el tablero que esta resolviendo el usuario.
     * @custom.mytag1 El Kakuro board debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el String que describe el estado actual del talero.
     * <p>
     * @custom.mytag3 Obtenemos el tablero que esta resolviendo el usuario.
     * <p>
     * @return Devolvemos el String que describe el estado actual del talero.
     */
    public String getGameBoard() {
        return board.getCode();
    }

    /**
     * Obtenemos el objeto Kakuro, siendo este el Kakuro que esta jugando el usuario.
     * @custom.mytag1 El Kakuro board debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el objeto Kakuro que esta jugando el usuario.
     * <p>
     * @custom.mytag3 Obtenemos el objeto Kakuro, siendo este el Kakuro que esta jugando el usuario.
     * <p>
     * @return Devolvemos el objeto Kakuro que esta jugando el usuario.
     */
    public Kakuro getKakuroOfGame() {
        return board;
    }

    /**
     * Obtenemos el String que describe el tablero solucion del kakuro jugado.
     * @custom.mytag1 El Kakuro solution debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el String que describe el tablero solucion del kakuro jugado.
     * <p>
     * @custom.mytag3 Obtenemos el String que describe el tablero solucion del kakuro jugado.
     * <p>
     * @return Devolvemos el String que describe el tablero solucion del kakuro jugado.
     */
    public String getSolution() {
        return solution.printActualString();
    }

    /**
     * Obtenemos el Kakuro que contiene la solucion del kakuro que se esta jugando.
     * @custom.mytag1 El Kakuro solution debe estar correctamente inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el objeto Kakuro que contiene la solucion del kakuro que se esta jugando.
     * <p>
     * @custom.mytag3 Obtenemos el Kakuro que contiene la solucion del kakuro que se esta jugando.
     * <p>
     * @return Devolvemos el objeto Kakuro que contiene la solucion del kakuro que se esta jugando.
     */
    public Kakuro getKakuroOfSolution() {
        return solution;
    }

    /**
     * Obtenemos el tiempo que lleva el jugador resolviendo el kakuro.
     * @custom.mytag1 El entero time debe estar correctamente inicializado y actualizado.
     * <p>
     * @custom.mytag2 Devolvemos el tiempo que lleva el jugador resolviendo el kakuro.
     * <p>
     * @custom.mytag3 Obtenemos el tiempo que lleva el jugador resolviendo el kakuro.
     * <p>
     * @return Devolvemos el tiempo que lleva el jugador resolviendo el kakuro.
     */
    public int getTime() {
        return time;
    }
    /**
     * Obtenemos el tiempo que tarda el sistema en resolver el kakuro.
     * @custom.mytag1 El entero time_solution debe estar correctamente inicializado y actualizado.
     * <p>
     * @custom.mytag2 Devolvemos el tiempo que tarda el sistema en resolver el kakuro.
     * <p>
     * @custom.mytag3 Obtenemos el tiempo que tarda el sistema en resolver el kakuro.
     * <p>
     * @return Devolvemos el tiempo que tarda el sistema en resolver el kakuro.
     */
    public float getTime_solution() {
        return time_solution;
    }

    /**
     * Escribimos el valor recibido en la posicion i,j del tablero.
     * @custom.mytag1 El entero i debe ser un valor entre 0 y el numero de fila - 1 que contiene el kakuro, el entero j debe ser un valor entre 0 y el
     * numero de columnas - 1 que contiene el kakuro y el entero value es el valor que se va a escribir en la posicion i,j del tablero.
     * <p>
     * @custom.mytag2 Hemos escrito el valor value en la posicion i,j del tablero que esta jugando el usuario.
     * <p>
     * @custom.mytag3 Escribimos el valor recibido en la posicion i,j del tablero.
     * <p>
     * @param i Fila del tablero donde se va a escribir un valor.
     * @param j Columna del tablero donde se va a escribir un valor.
     * @param Value Valor que se va a escribir en la posicion i,j del tablero.
     */
    public void addValue(int i, int j, int Value) {
        board.addValue(i, j, Value);
    }

    /**
     * Actualizamos el tiempo que lleva jugando el kakuro.
     * @custom.mytag1 El entero recibido debe ser positivo y debe representar el tiempo que ha pasado el usuario hasta ahora resolviendo el kakuro.
     * <p>
     * @custom.mytag2 Actualizamos el tiempo que lleva jugando el kakuro.
     * <p>
     * @custom.mytag3 Actualizamos el tiempo que lleva jugando el kakuro.
     * <p>
     * @param t Entero que contiene el tiempo ha pasado el jugador resolviendo el kakuro.
     */
    public void changeTime(int t) {
        time = t;
    }

}
