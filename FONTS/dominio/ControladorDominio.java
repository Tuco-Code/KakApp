/*
LOADALL Y SAVEALL SOLO GUARDAN Y CARGAN, IGUAL QUE LA CAPA DE PERSISTENCIA
COMPROBAR QUE SE MANTIENEN LAS CONDICIONES ES RESPONSABILIDAD DE LAS FUNCIONES DE ADD, DELETE, ETC
PRO EJEMPLO SI YA EXISTE UN USER CON ESE NOMBRE; SI YA HAY 4 PERFILES (EL MAXIMO) ANTES DE INTENTAR CREAR OTRO; ETC
 */
package dominio;

import jdk.dynalink.linker.support.DefaultInternalObjectFilter;
import persistencia.ControladorPersistencia;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que agrupa todas las funcionalidades de Dominio, ademas de comunicarse con la capa de Persistencia y de Presentacion.
 */

public class ControladorDominio {
    /**
     * Instancia que nos permite comunicarnos con persistencia.
     */
    private ControladorPersistencia controladorpersistencia;
    /**
     * Conjunto donde guardamos todos los kakuros de la aplicacion.
     */
    private ArrayList<Kakuro> programKakuros;
    /**
     * Conjunto donde guardamos todos los perfiles registrados de la aplicacion.
     */
    private ArrayList<Profile> programProfiles;
    /**
     * Conjunto donde guardamos todas las estadisticas de los kakuros de nuestra aplicacion.
     */
    private ArrayList<Stats> programStats;
    /**
     * Conjunto donde guardamos todas las partidas guardadas.
     */
    private ArrayList<Game> programGames;
    /**
     * Conjunto donde guardamos un conjunto de kakuros despues de aplicar un filtrado por quien guardo el kakuro y/o dificultad.
     */
    private ArrayList<Kakuro> programFiltredKkr;
    /**
     * Usuario que esta usando la aplicacion.
     */
    private Profile currentProfile;
    /**
     * Partida que esta jugando el usuario.
     */
    private Game currentGame;

    /*
    /////////////////////////////////////////////////////////////////////////
    CREATION & PERSISTENCY LOAD/SAVE
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * Constructora por defecto, cargamos todos los datos de la capa de Persistencia.
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 Cargamos todos los objetos que se estaban guardando en persistencia.
     * <p>
     * @custom.mytag3 Constructora por defecto, cargamos todos los datos de la capa de Persistencia.
     * <p>
     */
    public ControladorDominio() {
        controladorpersistencia = new ControladorPersistencia();
        loadAll();
        programFiltredKkr = new ArrayList<>(programKakuros);
    }

    /**
     * Cargamos todos los datos que se han guardado en la capa de Persistencia.
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 Cargamos todos los datos que se han guardado en la capa de Persistencia.
     * <p>
     * @custom.mytag3 Cargamos todos los datos que se han guardado en la capa de Persistencia.
     * <p>
     */
    public void loadAll() {
        programKakuros = new ArrayList<>();
        programProfiles = new ArrayList<>();
        programStats = new ArrayList<>();
        programGames = new ArrayList<>();

        ArrayList<String> kakuroData = controladorpersistencia.loadKakuros();
        ArrayList<String> profileData = controladorpersistencia.loadProfiles();
        ArrayList<String> statsData = controladorpersistencia.loadStats();
        ArrayList<String> gameData = controladorpersistencia.loadGames();


        AutomataConstructor builder = new AutomataConstructor();

        for (int i = 0; i < kakuroData.size(); ++i) {
            if (!kakuroData.get(i).equals(""))
            programKakuros.add(builder.constructKakuro(kakuroData.get(i)));
        }

        for (int i = 0; i < profileData.size(); ++i) {
            if (!profileData.get(i).equals(""))
                programProfiles.add(builder.constructProfile(profileData.get(i)));
        }

        for (int i = 0; i < statsData.size(); ++i) {
            if (!statsData.get(i).equals(""))
            programStats.add(builder.constructStats(statsData.get(i)));
        }

        for (int i = 0; i < gameData.size(); ++i) {
            if (!gameData.get(i).equals(""))
                programGames.add(builder.constructGame(gameData.get(i)));
        }
    }

    /**
     * Guardamos el estado de todos los objetos relevantes en la capa de Persistencia.
     * @custom.mytag1 Los conjuntos que guardan todos los objetos de la aplicacion deben estar correctamente formateados.
     * <p>
     * @custom.mytag2 Guardamos el estado de todos los objetos relevantes en la capa de Persistencia.
     * <p>
     * @custom.mytag3 Guardamos el estado de todos los objetos relevantes en la capa de Persistencia.
     * <p>
     */
    public void saveAll() {
        ArrayList<String> kakuroData = new ArrayList<>();
        ArrayList<String> profileData = new ArrayList<>();
        ArrayList<String> statsData = new ArrayList<>();
        ArrayList<String> gameData = new ArrayList<>();

        AutomataDeconstructor deconstructor = new AutomataDeconstructor();

        for (int i = 0; i < programKakuros.size(); ++i) {
            kakuroData.add(deconstructor.deconstructKakuro(programKakuros.get(i)));
        }
        controladorpersistencia.saveKakuros(kakuroData);

        for (int i = 0; i < programProfiles.size(); ++i) {
            profileData.add(deconstructor.deconstructProfile(programProfiles.get(i)));
        }
        controladorpersistencia.saveProfiles(profileData);

        for (int i = 0; i < programStats.size(); ++i) {
            statsData.add(deconstructor.deconstructStats(programStats.get(i)));
        }
        controladorpersistencia.saveStats(statsData);

        for (int i = 0; i < programGames.size(); ++i) {
            gameData.add(deconstructor.deconstructGame(programGames.get(i)));
        }
        controladorpersistencia.saveGames(gameData);
    }

    /*
    /////////////////////////////////////////////////////////////////////////
    INSTANCE CREATION
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * Vamos a guardar el kakuro recibido si no se encuentra en nuestro repositorio y en este caso lo ha anadido un usuario.
     * @custom.mytag1 El String recibido debe estar formateado correctamente.
     * <p>
     * @custom.mytag2 Si no ha habido ningun problema se habra anadido el kakuro en nuestro repositorio.
     * <p>
     * @custom.mytag3 Vamos a guardar el kakuro recibido si no se encuentra en nuestro repositorio y en este caso lo ha anadido un usuario.
     * <p>
     * @param k String que referencia un kakuro.
     * @return Devolvemos 0 si hemos anadido el kakuro correctamente, -1 si el kakuro se encuentra en nuestro repositorio, -2 si el String esta
     * mal formateado, -3 si el kakuro contiene alguna casilla blanca con un 0 o el kakuro tiene mas de una solucion.
     */
    public int addKakuro2(String k){
        return addKakuro(k,Source.USER);
    }

    /**
     * Vamos a guardar el kakuro recibido si no se encuentra en nuestro repositorio.
     * @custom.mytag1 El String recibido debe estar formateado correctamente.
     * <p>
     * @custom.mytag2 Si no ha habido ningun problema se habra anadido el kakuro en nuestro repositorio.
     * <p>
     * @custom.mytag3 Vamos a guardar el kakuro recibido si no se encuentra en nuestro repositorio.
     * <p>
     * @param kk String que referencia un kakuro.
     * @param src Enummeration que dice si el kakuro lo ha anadido un usuario, se ha generado o es original de la aplicacion.
     * @return Devolvemos 0 si hemos anadido el kakuro correctamente, -1 si el kakuro se encuentra en nuestro repositorio, -2 si el String esta
     * mal formateado, -3 si el kakuro contiene alguna casilla blanca con un 0 o el kakuro tiene mas de una solucion.
     */
    public int addKakuro(String kk, Source src)
    {
        try {
            String kak = kk.trim(); // Esborrar enters de més

            for (int i = 0; i < programKakuros.size(); ++i) {
                String currentLine = programKakuros.get(i).getCode();
                if (currentLine.equals(kak)) return -1;
            }

            if (!isCorrect(kak)) return -2;
            Difficulty dif = getDifficulty(kak);

            Kakuro newKak = new Kakuro(kak, dif, src);
            Algorithms a = new Algorithms();

            int sols = a.howManySolutions(newKak);

            if (sols != 1) return -3;

            programKakuros.add(newKak);
            addStats(kak);
            saveAll();
            return 0;
        } catch (Exception e) {
            return -2; // If any exception is catched, the reason may be a bad input
        }
    }

    /**
     * Vamos a crear unas estadisticas para el kakuro que se ha anadido al repositorio.
     * @custom.mytag1 El String que referencia un kakuro debe estar formateado correctamente y debe ser un kakuro que se encuentre en nuestro repositorio.
     * <p>
     * @custom.mytag2 Vamos a crear unas estadisticas para el kakuro que se ha anadido al repositorio.
     * <p>
     * @custom.mytag3 Vamos a crear unas estadisticas para el kakuro que se ha anadido al repositorio.
     * <p>
     * @param kakuro String que referencia un kakuro.
     */
    public void addStats(String kakuro) {
        Stats newStats = new Stats(kakuro);
        programStats.add(newStats);
        //saveAll(); NOT NEEDED AS STATS AR ONLY CREATED ALONG WITH A KAKURO
    }

    /**
     * Vamos a registrar a un nuevo usuario con los Strings recibidos.
     * @custom.mytag1 El String que referencia un kakuro debe estar formateado correctamente y debe ser un kakuro que se encuentre en nuestro repositorio.
     * <p>
     * @custom.mytag2 Se registrara un nuevo usuario si no ha ocurrido ningun problema.
     * <p>
     * @custom.mytag3 Vamos a registrar a un nuevo usuario con los Strings recibidos.
     * <p>
     * @param username String que contiene el nombre del usuario a crear.
     * @param password String que contiene la contrasena del usuario a crear.
     * @return Devolvemos 0 si se ha creado el usuario correctamente, 1 si se ha llegado al numero maximo de perfiles que soporta nuestra aplicacion,
     * 2 si existe un usuario con el mismo nombre o -1 si ha habiado otro tipo de error.
     */
    public int addProfile(String username, String password) {
        try {
            if(programProfiles.size()>=10) return 1;
            for(Profile se:programProfiles){
                if(se.getName().equals(username)){
                    return 2;
                }
            }
            Testing_Hash aux = new Testing_Hash();
            String passaux = aux.passwordToHash(password);
            Profile p = new Profile(username,passaux);
            programProfiles.add(p);
            saveAll();
            currentProfile = p;
            return 0;
        } catch (Exception e) {return -1;}
    }

    /**
     * Vamos a anadir un nuevo kakuro a partir de un fichero.
     * @custom.mytag1 El String que describe el path debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Se anadira el kakuro que se encuentra en el path recibido si no ha habido ningun problema.
     * <p>
     * @custom.mytag3 Vamos a anadir un nuevo kakuro a partir de un fichero.
     * <p>
     * @param path String que define el camino a encontrar el fichero que contiene el kakuro que se quiere anadir.
     * @return Devolvemos 0 si hemos anadido el kakuro correctamente, -1 si el kakuro se encuentra en nuestro repositorio, -2 si el String esta
     * mal formateado, -3 si el kakuro contiene alguna casilla blanca con un 0 o el kakuro tiene mas de una solucion, -4 si no se ha podido
     * cargar el archivo.
     */
    public int loadFromFile(String path)
    {
        try {
            String input = controladorpersistencia.loadFileContent(path);
            return addKakuro(input, Source.USER);
        } catch (Exception e) {
            //shouldn't happen
            System.out.println("File load failed");
            return -4;
        }
    }

    /**
     * Vamos a generar un nuevo kakuro de un tamano en concreto con un porcentage de celdas blancas y un numero de casillas resueltas.
     * @custom.mytag1 El entero tam debe ser positivo, blanques debe ser positivo y resolved debe ser positivo y un numero razonable para el tamano
     * del kakuro a crear.
     * <p>
     * @custom.mytag2 Se anadira el kakuro que se acaba de generar, a no ser que el kakuro se encuentre en el repositorio.
     * <p>
     * @custom.mytag3 Vamos a generar un nuevo kakuro de un tamano en concreto con un porcentage de celdas blancas y un numero de casillas resueltas.
     * <p>
     * @param tam Entero que define el tamano del kakuro a crear.
     * @param blanques Entero que describe el porcentage de casillas blancas con las que se va a generar el kakuro.
     * @param resolved Numero de casillas resueltas que contendra el kakuro generado.
     * @return Devolvemos el String que referencia el kakuro que se acaba de generar.
     */
    public String generarKakuro(int tam, int blanques, int resolved){
        Algorithms a = new Algorithms();

        int dif = dificultat(tam,blanques, resolved);
        Difficulty d;
        if(dif==1) d= Difficulty.EASY;
        else if(dif==2) d=Difficulty.MEDIUM;
        else d = Difficulty.HARD;

        Kakuro kkr = new Kakuro(a.Generador(tam,100-blanques, resolved),d,Source.GENERATED);        
	if(esta(kkr)) System.out.println("Kakuro generated exists");
	else{
		programKakuros.add(kkr);
		addStats(kkr.getCode());
		saveAll();
	}
        return kkr.getCode();
    }

    /**
     * Vamos a eliminar un kakuro del repositorio y con el todas sus partidas guardadas y sus estadisticas.
     * @custom.mytag1 El String recibido debe referenciar un kakuro.
     * <p>
     * @custom.mytag2 Si el kakuro se encuentra en el repositorio, se eliminara el kakuro, sus estadisticas y todas las partidas guardadas de ese kakuro.
     * <p>
     * @custom.mytag3 Vamos a eliminar un kakuro del repositorio y con el todas sus partidas guardadas y sus estadisticas.
     * <p>
     * @param Kakuro String que referencia un kakuro
     * @return Devuelve 0 si no ha habido ningun error al eliminar el kakuro o sus estadisticas o las partidas de dicho kakuro.
     */
    public int deleteKakuro(String Kakuro)
    {
        String myCode = Kakuro;

        //deleting games
        ArrayList<Game> toDelete = new ArrayList<Game>();

        for (int i = 0; i < programGames.size(); ++i)
        {
            if (programGames.get(i).getKakuroReference().equals(myCode)) toDelete.add(programGames.get(i));
        }

        for (int i = 0; i < toDelete.size(); ++i)
        {
            programGames.remove(toDelete.get(i));
        }

        //deleting stat file

        int stat = 0;

        for (int i = 0; i < programStats.size(); ++i)
        {
            if (programStats.get(i).getCode().equals(myCode)) stat = i;
        }

        programStats.remove(stat);

        //deleting kakuro

        int me = 0;

        for (int i = 0; i < programKakuros.size(); ++i)
        {
            if (programKakuros.get(i).getCode().equals(myCode)) me = i;
        }

        programKakuros.remove(me);

        saveAll();

        return 0;
    }

    /*
    /////////////////////////////////////////////////////////////////////////
    GAMING
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * Vamos a crear una partida del kakuro recibido para el usuario que esta actualmente conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe referenciar un kakuro.
     * <p>
     * @custom.mytag2 Creamos la partida del kakuro recibido.
     * <p>
     * @custom.mytag3 Vamos a crear una partida del kakuro recibido para el usuario que esta actualmente conectado a la aplicacion.
     * <p>
     * @param kkr String que referencia a un kakuro del repositorio.
     * @return Devuelve 0 si no ha habido ningun problema al crear la partida.
     */
    public int crearGame(String kkr){
        String username = getUser();
        for(Game g: programGames){
            if(g.getKakuroReference().equals(kkr) && username.equals(g.getUserName())) return 1;
        }
        currentGame = new Game(new Kakuro(kkr),currentProfile);
        currentProfile.increaseNumOfGames();
        saveAll();
        return 0;
    }

    /**
     * Obtenemos el String que describe el tablero de la partida actual.
     * @custom.mytag1 Debe haber una partida jugandose.
     * <p>
     * @custom.mytag2 Devolvemos el tablero de la partida actual en un String.
     * <p>
     * @custom.mytag3 Obtenemos el String que describe el tablero de la partida actual.
     * <p>
     * @return String que define el tablero de la partida actual.
     */
    public String getCurrentGame() {
        return currentGame.getGameBoard();
    }

    /**
     * Obtenemos el String que describe la solucion de la partida actual.
     * @custom.mytag1 Debe haber una partida jugandose.
     * <p>
     * @custom.mytag2 Devolvemos la solucion de la partida actual en un String.
     * <p>
     * @custom.mytag3 Obtenemos el String que describe la solucion de la partida actual.
     * <p>
     * @return String que define la solucion de la partida actual.
     */
    public String getSolutionCurrentGame() {
        return currentGame.getSolution();
    }


    /**
     * Obtenemos el numero de Kakuros jugados por el usuario actual.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros jugados por el usuario actual.
     * <p>
     * @custom.mytag3 Obtenemos el numero de Kakuros jugados por el usuario actual.
     * <p>
     * @return Numero de Kakuros jugados por el perfil actual.
     */
    public int getNumOfGames(){ return currentProfile.getNumOfGames(); }


    /**
     * Obtenemos el numero de Kakuros completados por el usuario actual.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros completados por el usuario actual.
     * <p>
     * @custom.mytag3 Obtenemos el numero de Kakuros completados por el usuario actual.
     * <p>
     * @return Numero de Kakuros completados por el usuario actual.
     */
    public int getNumOfWins(){ return currentProfile.getNumOfWins(); }

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
    public void addValue(int i, int j, int Value){
        currentGame.addValue(i,j,Value);
    }

    /**
     * Comprobamos si el tablero de la partida actual es correcto.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Si el tablero es correcto, comprobaremos si el tiempo es mejor para actualizar las estadisticas del kakuro resuelto.
     * <p>
     * @custom.mytag3 Comprobamos si el tablero de la partida actual es correcto.
     * <p>
     * @return Devolvemos true si el tablero es correcto o false en caso contrario.
     */
    public Boolean validate(){
        Boolean res = false;

        Kakuro actualGame = currentGame.getKakuroOfGame();
        KakuroCell [][] actualBoardOfSolution = (currentGame.getKakuroOfSolution()).getBoard();

        res = actualGame.isSameBoard(actualBoardOfSolution);
        if (res) updateStats(currentGame.getKakuroReference());

        return res;
    }

    /**
     * Damos el valor de una casilla blanca vacia aleatoria.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Devolvemos la posicion de la casilla blanca que vamos a dar la pista y el valor a escribir.
     * <p>
     * @custom.mytag3 Damos el valor de una casilla blanca vacia aleatoria.
     * <p>
     * @return Devolvemos una tripleta, el primer valor es la fila del tablero, el segundo valor es la columna del tablero y el tercer valor es el
     * valor es correcto en la posicion i,j del kakuro.
     */
    public Triple pista(){
        // TRIPLE ES COM PAIR PERO AMB 3 ATR FIRST SECOND I THIRD
        // ENCHUFA EN LA FIRST LA i, de la casella, A SECOND LA j, I A THIRD EL VALUES
        Triple res = new Triple(-1,-1,-1);
        int counter = 0;
        int auxCounter = 0;
        KakuroCell [][] actualBoardOfGame = (currentGame.getKakuroOfGame()).getBoard();
        KakuroCell [][] actualBoardOfSolution = (currentGame.getKakuroOfSolution()).getBoard();

        for (int i = 0; i < actualBoardOfGame.length; ++i) {
            for (int j = 0; j < actualBoardOfGame[0].length; ++j) {
                if (actualBoardOfGame[i][j] instanceof WhiteKakuroCell && ((WhiteKakuroCell) actualBoardOfGame[i][j]).getDigit() == 0) {
                    ++counter;
                }
            }
        }

        if(counter == 0) return res;
        Random r = new Random();
        int random = r.nextInt(counter)+1;

        for (int i = 0; i < actualBoardOfGame.length; ++i) {
            for (int j = 0; j < actualBoardOfGame[0].length; ++j) {
                if (actualBoardOfGame[i][j].isWhite() && ((WhiteKakuroCell) actualBoardOfGame[i][j]).getDigit() == 0) {
                    ++auxCounter;
                    if(auxCounter == random) {
                        int value = ((WhiteKakuroCell) actualBoardOfSolution[i][j]).getDigit();
                        return new Triple(i,j,value);
                    }
                }
            }
        }

        return res;
    }

    /**
     * Comprobamos si el usuario conectado tiene una partida guardada del kakuro recibido.
     * @custom.mytag1 El String recibido debe referenciar correctamente a un kakuro.
     * <p>
     * @custom.mytag2 Devolvemos true si existe una partida del kakuro recibido para el usuario conectado o false en caso contrario.
     * <p>
     * @custom.mytag3 Comprobamos si el usuario conectado tiene una partida guardada del kakuro recibido.
     * <p>
     * @param kakuro String que referencia un kakuro.
     * @return Devolvemos true si existe una partida del kakuro recibido para el usuario conectado o false en caso contrario.
     */
    public Boolean existsGame (String kakuro) {
        String username = getUser();
        for(Game g: programGames){
            if(g.getKakuroReference().equals(kakuro) && username.equals(g.getUserName())) return true;
        }
        return false;
    }

    /**
     * Cargamos la partida del kakuro recibido para el usuario que esta conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe referenciar correctamente a un kakuro.
     * <p>
     * @custom.mytag2 Cargamos la partida como la partida actual y devolvemos el String que describe el tablero de la partida tal y como se guardo.
     * <p>
     * @custom.mytag3 Comprobamos si el usuario conectado tiene una partida guardada del kakuro recibido y entonces si es asi la cargamos.
     * <p>
     * @param kakuro String que referencia un kakuro.
     * @return Devolvemos el tablero tal y como se guardo en forma de String.
     */
    public String loadGame (String kakuro) {

        String username = getUser();
        String res;
        for(Game g: programGames){
            if(g.getKakuroReference().equals(kakuro) && username.equals(g.getUserName())) currentGame = g;
        }
        res = currentGame.getGameBoard();
        return res;
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
    public String getReferenceGame() { return currentGame.getKakuroReference(); }

    /**
     * Obtenemos la solucion del kakuro que se esta resolviendo en forma de String.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Devolvemos la solucion del kakuro que se esta jugando.
     * <p>
     * @custom.mytag3 Obtenemos la solucion del kakuro que se esta resolviendo en forma de String.
     * <p>
     * @return Devolvemos la solucion del kakuro que se esta jugando en forma de String.
     */
    public String getKkrSolved(){
        return currentGame.getSolution();
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
    public int getGameTime() { return currentGame.getTime(); }

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
    public float getGameTime_solution() { return currentGame.getTime_solution(); }

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
    public void changeGameTime(int t){ currentGame.changeTime(t); }

    /**
     * Vamos a guardar la partida que se esta jugando.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Guardamos la partida que se esta jugando.
     * <p>
     * @custom.mytag3 Vamos a guardar la partida que se esta jugando.
     * <p>
     */
    public void saveCurrentGame(){
        deleteGame(currentGame.getKakuroReference());
        programGames.add(currentGame);
        saveAll();
    }

    /**
     * Vamos a eliminar la partida del kakuro recibido como parametro para el usuario que esta actualmente conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe s estar correctamente formateado para referenciar un kakuro.
     * <p>
     * @custom.mytag2 Si el usuario conectado tiene una partida guardada del kakuro que se referencia con el String recibido, la eliminaremos.
     * <p>
     * @custom.mytag3 Vamos a eliminar la partida del kakuro recibido como parametro para el usuario que esta actualmente conectado a la aplicacion.
     * <p>
     * @param kakuro String que referencia un kakuro.
     */
    public void deleteGame(String kakuro) {
        String username = getUser();
        int counter = 0;
        Boolean find = false;
        for(Game g: programGames){
            if(g.getKakuroReference().equals(kakuro) && username.equals(g.getUserName())) {
                find = true;
            }
            if(!find) ++counter;
        }
        if(find) programGames.remove(counter);
        saveAll();
    }

    /*
    /////////////////////////////////////////////////////////////////////////
    PROFILE RELATED FUNCTIONS
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * Vamos a iniciar sesion en la aplicacion con un usuario, dicho usuario se debe haber registrado previamente.
     * @custom.mytag1 El nombre y contrasena recibidos deberian pertenecer a un usuario registrado en la aplicacion.
     * <p>
     * @custom.mytag2 Si los credenciales son correctos se iniciara sesion.
     * <p>
     * @custom.mytag3 Vamos a iniciar sesion en la aplicacion con un usuario, dicho usuario se debe haber registrado previamente.
     * <p>
     * @param username Nombre del usuario que quiere acceder a la aplicacion.
     * @param password Contrasena del usuario que quiere acceder a la aplicacion.
     * @return Devolvemos 0 si se ha iniciado sesion correctamente, 1 si no existe un usuario con ese nombre, 2 si la contrasena no es correcta y
     * -1 si ha habido otro tipo de error.
     */
    public int loginProfile(String username, String password) {
        try {
            Testing_Hash aux = new Testing_Hash();
            String passHash = aux.passwordToHash(password);
            for(Profile se:programProfiles){
                if(se.getName().equals(username)){
                    if(!se.getPassword().equals(passHash)) return 2;
                    else{
                        currentProfile = se;
                        return 0;
                    }
                }
            }
            return 1;
        } catch (Exception e) {return -1;}
    }

    /**
     * Vamos a tratar de cambiar la contrasena del usuario conectado.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 Si los credenciales son correctos se le cambiara la contrasena.
     * <p>
     * @custom.mytag3 Vamos a tratar de cambiar la contrasena del usuario conectado.
     * <p>
     * @param old_ps Contrasena antigua.
     * @param new_ps Contrasena a reemplazar.
     * @return Devolvemos 0 si se ha cambiado con exito, -1 si al escribir la contrasena actual se ha equivocado, -2 si la contrasena nueva es la misma
     * que la antigua
     */
    public int changePassword(String old_ps, String new_ps) {
        Testing_Hash t = new Testing_Hash();
        String old_ps_coded = t.passwordToHash(old_ps);
        if (currentProfile.getPassword().equals(old_ps_coded)) {
            String new_ps_coded = t.passwordToHash(new_ps);
            if (currentProfile.changePassword(new_ps_coded)) {
                saveAll();
                return 0;
            }
            if (old_ps.equals(new_ps)) return -2;
            return 0; // password es diferente pero coincide su hash (extremadamente improbable)
        }
        return -1;
    }

    /**
     * Devolvemos el nombre del usuario que se ha conectado a la aplicacion.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 Devolvemos el nombre del usuario conectado a la aplicacion.
     * <p>
     * @custom.mytag3 Devolvemos el nombre del usuario que se ha conectado a la aplicacion.
     * <p>
     * @return El String devuelto es el nombre del usuario conectado.
     */
    public String getUser(){
        return currentProfile.getName();
    }

    /**
     * Vamos a eliminar el perfil que esta conectado a la aplicacion.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 Eliminamos el perfil conectado y con ello todas sus partidas guardadas, ademas le enviamos a la vista de iniciar sesion.
     * <p>
     * @custom.mytag3 Vamos a eliminar el perfil que esta conectado a la aplicacion.
     * <p>
     * @return Devolvemos 0 si no ha habido ningun error.
     */
    public int deleteProfile()
    {
        String myName = currentProfile.getName();

        ArrayList<Game> toDelete = new ArrayList<Game>();

        for (int i = 0; i < programGames.size(); ++i)
        {
            if (programGames.get(i).getUserName().equals(myName)) toDelete.add(programGames.get(i));
        }

        for (int i = 0; i < toDelete.size(); ++i)
        {
            programGames.remove(toDelete.get(i));
        }

        int me = 0;

        for (int i = 0; i < programProfiles.size(); ++i)
        {
            if (programProfiles.get(i).getName().equals(myName)) me = i;
        }

        programProfiles.remove(me);

        saveAll();

        return 0;
    }

    /*
    /////////////////////////////////////////////////////////////////////////
    AUXILIAR
    ////////////////////////////////////////////////////////////////////////
     */

    /**
     * Vamos a actualizar las estadisticas de un kakuro que alguien acaba de resolver.
     * @custom.mytag1 Un usuario debe haber resuleto el kakuro que se recibe como parametro.
     * <p>
     * @custom.mytag2 Actualizamos las estadisticas del kakuro que el usuario acaba de resolver si su tiempo es menor de los que se encuentran guardados.
     * <p>
     * @custom.mytag3 Vamos a actualizar las estadisticas de un kakuro que alguien acaba de resolver.
     * <p>
     * @param kakuro String que referencia el kakuro que el usuario conectado acaba de resolver.
     */
    public void updateStats (String kakuro) {
        String username = getUser();
        int auxTime = currentGame.getTime();
        for (Stats s : programStats) {
            if (s.getCode().equals(kakuro)) {
                s.addGame(username,auxTime);
            }
        }
        currentProfile.increaseNumOfWins();
        saveAll();
    }

    /**
     * Vamos a comprobar si el String recibido es el formato correcto con el que se debe definir un kakuro.
     * @custom.mytag1 Un usuario debe estar intentando anadir un nuevo kakuro en el repositorio.
     * <p>
     * @custom.mytag2 Comprobamos si el String recibido cumple el formato a seguir para definir un kakuro.
     * <p>
     * @custom.mytag3 Vamos a comprobar si el String recibido es el formato correcto con el que se debe definir un kakuro.
     * <p>
     * @param kk String del cual se va a comprobar si define de manera correcta el kakuro que se esta intentando anadir.
     * @return Devolvemos true si el formato es correcto o false en caso contrario.
     */
    public boolean isCorrect(String kk) {
        try {
            int MAX_SIZE = 50; // Exemple, com a límit per evitar valors excessivaments grans que el nostre programa no pot tractar bé

            if (kk.length() < 13) return false; // Tam minimo de kakuro es de un 2x2, que es 13

            int i = 0;
            int boardWidth = kk.charAt(0) - '0';
            while (kk.charAt(++i) != ',') {
                int elem = kk.charAt(i) - '0';
                if (elem >= 0 && elem <= 9) boardWidth = boardWidth * 10 + elem;
                else return false;
                if (boardWidth > MAX_SIZE) return false;
            }
            int elem = kk.charAt(i + 1) - '0';
            int boardHeight = 0;
            while (elem >= 0 && elem <= 9) {
                int prev_elem = kk.charAt(++i) - '0';
                elem = kk.charAt(i + 1) - '0';
                boardHeight = boardHeight * 10 + prev_elem;
                if (boardHeight > boardWidth) return false;
            }

            if (boardHeight != boardWidth || boardHeight < 2 || boardHeight > MAX_SIZE) return false;

            if (kk.charAt(++i) != '\n' || kk.charAt(++i) != '*' || kk.charAt(++i) != ',')
                return false; //Board en [0][0] sempre té *
            int count = 1;

            char c;
            boolean last = false;
            for (i = i + 1; i < kk.length(); i++) {
                c = kk.charAt(i);
                if (count % boardHeight == 0 && !last) { // End of a line
                    if (c != '\n') return false;
                    last = true;
                } else {
                    last = false;
                    if (c == 'C' || c == 'F') {
                        int value = 0;
                        while (i + 1 < kk.length() && (!(kk.charAt(i + 1) == ',' || kk.charAt(i + 1) == '\n'))) {
                            char aux = kk.charAt(i + 1);
                            if ((!(aux >= '0' && aux <= '9')) && (!(aux == 'F' && c == 'C'))) return false;
                            if (aux == 'F') {
                                if (value > 45 || value < 1) return false;
                                value = 0;
                            } else value = value * 10 + aux - '0';
                            i++;
                        }
                        if (value > 45) return false;
                        if (!(kk.charAt(i) >= '0' && kk.charAt(i) <= '9'))
                            return false; // últim valor ha de ser un char d'enter
                        count++;
                        if (c == 'F' && (count % boardHeight == 0 || count < boardHeight))
                            return false; // última columna i primera fila NO pot tenir restricció columna
                        if (c == 'C' && (count % boardHeight == 1 || (count % boardHeight != 0 && count / boardHeight == boardHeight - 1)))
                            return false; // primera columna NO pot tenir restricció fila
                    } else if (c != '*' && c != '?' && !(c > '0' && c <= '9')) {
                        return false;
                    } else count++;

                    if (count % boardHeight != 0) {
                        if (i + 1 < kk.length() && kk.charAt(i + 1) != ',') return false;
                        i++;
                    }
                }
            }

            if (count != boardWidth * boardHeight) return false;

            Algorithms aux = new Algorithms();
            aux.makeBoard(kk);

            return aux.restrictions_ok();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Funcion auxiliar para calcular la dificultad de un kakuro a partir de su tamano, porcentaje de casillas blancas y numero de casillas resueltas.
     * @custom.mytag1 Los enteros recibidos deben ser positivos ademas, tanto white como resolved deben adecuarse al tamano recibido.
     * <p>
     * @custom.mytag2 Devolvemos un entero equivalente a la dificultad del kakuro definido por su tamano, su porcentage de casillas blancas y numero
     * de casillas blancas.
     * <p>
     * @custom.mytag3 Funcion auxiliar para calcular la dificultad de un kakuro a partir de su tamano, porcentaje de casillas blancas y numero de casillas resueltas.
     * <p>
     * @param size Entero que define el tamano del kakuro que se quiere calcular su dificultad.
     * @param white Porcentaje de casillas blancas del kakuro que se quiere calcular su dificultad.
     * @param resolved Numero de casillas blancas resultas del kakuro que se quiere calcular su dificultad.
     * @return Devolvemos 1 si su dificultad es baja, 2 si es intermedia y 3 si es alta.
     */
    public static int dificultat(int size, int white, int resolved){
        if(size <=3) return 1;
        else {
            int proportion = (resolved*100)/((size*size*white)/100);
            if(white >45 && proportion < 10) return 3;
            else if(white >= 35 && proportion < 20) return 2;
            return 1;
        }
    }

    /**
     * Funcion para calcular la dificultad de un kakuro.
     * @custom.mytag1 El String recibido debe codificar correctamente un kakuro.
     * <p>
     * @custom.mytag2 Devolvemos la dificultad del kakuro recibido como String.
     * <p>
     * @custom.mytag3 Funcion para calcular la dificultad de un kakuro.
     * <p>
     * @param kak String que referencia un kakuro.
     * @return Devolvemos la dificultad del kakuro recibido como String.
     */
    public static Difficulty getDifficulty(String kak) {
        int boardWidth = kak.charAt(0)-'0'; // Not error because kak is trimmed and isCorrect.
        int blanques = 0;
        int resolved = 0;
        for (int i = 4; i < kak.length(); i++) {
            if (kak.charAt(i) == '?') blanques++;
            if (kak.charAt(i-1) == ',' && kak.charAt(i)-'0' > 0 && kak.charAt(i)-'0' < 10) resolved++;
        }
        blanques = blanques*100/(boardWidth*boardWidth);
        int r = dificultat(boardWidth, blanques, resolved);
        if (r == 1) return Difficulty.EASY;
        if (r == 2) return Difficulty.MEDIUM;
        return Difficulty.HARD;
    }

    /**
     * Funcion para calcular cuantas casillas blancas resueltas como maximo podemos poner en un kakuro sin resolverlo al completo.
     * @custom.mytag1 Los enteros recibidos deben ser positivos.
     * <p>
     * @custom.mytag2 Calculamos el numero maximo de casillas blancas resueltas que puede tener el kakuro dependiendo de su tamano y el porcentaje de
     * casillas blancas sin resolverlo al completo.
     * <p>
     * @custom.mytag3 Funcion para calcular cuantas casillas blancas resueltas como maximo podemos poner en un kakuro sin resolverlo al completo.
     * <p>
     * @param size Tamano del kakuro
     * @param white Porcentaje de casillas blancas del kakuro.
     * @return Numero maximo de casillas blancas resueltas que puede tener el kakuro dependiendo de su tamano y el porcentaje de
     * casillas blancas sin resolverlo al completo.
     */
    public static int max_resoltes(int size, int white){
        if (size == 2 || white*size*size/100 < 3) return 0;
        else return white*size*size/200;
    }

    /**
     * Funcion para comprobar si el kakuro recibido se encuentra en el repositorio.
     * @custom.mytag1 Los enteros recibidos deben ser positivos.
     * <p>
     * @custom.mytag2 Devolvemos true si el kakuro se encuentra en el repositorio o false en caso contrario.
     * <p>
     * @custom.mytag3 Funcion para comprobar si el kakuro recibido se encuentra en el repositorio.
     * <p>
     * @param kkr Objeto kakuro correctamente formateado.
     * @return Devolvemos true si el kakuro se encuentra en el repositorio o false en caso contrario.
     */
    public Boolean esta(Kakuro kkr){
        for(Kakuro k: programKakuros){
            if(k.getCode().equals(kkr.getCode())) return true;
        }
     return false;
    }


    /**
     * Funcion para encontrar el kakuro que se ha elegido en el repositorio.
     * @custom.mytag1 El entero recibido debe ser entre 0 y el numero de kakuros que hay en el repositorio - 1.
     * <p>
     * @custom.mytag2 Devolvemos el String de referencia del Kakuro que se encuentra en la posicion index de nuestro repositorio.
     * <p>
     * @custom.mytag3 Funcion para encontrar el kakuro que se ha elegido en el repositorio.
     * <p>
     * @param index Entero entre 0 y el numero de kakuros que hay en el repositorio - 1.
     * @return Devolvemos el String de referencia del Kakuro que se encuentra en la posicion index de nuestro repositorio.
     */
    public String selectKakuro (int index) {
        Kakuro selectedKakuro = programFiltredKkr.get(index);
        return selectedKakuro.getCode();
    }

    /**
     * Devolvemos las estadisticas del Kakuro referenciado por el String recibido.
     * @custom.mytag1 El String recibido debe referenciar a un kakuro de nuestro repositorio.
     * <p>
     * @custom.mytag2 Devolvemos las caracteristicas del kakuro referenciado por el String recibido.
     * <p>
     * @custom.mytag3 Devolvemos las estadisticas del Kakuro referenciado por el String recibido.
     * <p>
     * @param kakuro String que referencia un kakuro que se encuentra en nuestro repositorio.
     * @return Devolvemos las caracteristicas del kakuro referenciado por el String recibido.
     */
    public String[] getStatsOfKakuro (String kakuro) {
        String[] res = new String[6];
        Stats aux = programStats.get(0);
        for(Stats s: programStats) {
            if(s.getCode().equals(kakuro)) aux = s;
        }
        res[0] = aux.getPosOwner(1);
        res[1] = String.valueOf(aux.getPosTime(1));
        res[2] = aux.getPosOwner(2);
        res[3] = String.valueOf(aux.getPosTime(2));
        res[4] = aux.getPosOwner(3);
        res[5] = String.valueOf(aux.getPosTime(3));
        return res;
    }

    /**
     * Devolvemos la cantidad de kakuros que han pasado el filtrado de dificultad o de quien los anadio al repositorio.
     * @custom.mytag1 Tenemos que haber cargado los kakuros.
     * <p>
     * @custom.mytag2 Devolvemos el numero de kakuros que han pasado el filtrado.
     * <p>
     * @custom.mytag3 Devolvemos la cantidad de kakuros que han pasado el filtrado de dificultad o de quien los anadio al repositorio.
     * <p>
     * @return Numero de kakuros que han pasado el filtrado.
     */
    public int getSizeKkr(){
    return programFiltredKkr.size();
    }

    /**
     * Aplicamos un filtrado por dificultad y sobre quien anadio los kakuros al repositorio.
     * @custom.mytag1 Tenemos que haber cargado los kakuros.
     * <p>
     * @custom.mytag2 Guardamos el conjunto que ha pasado el filtrado en programFiltredKkr.
     * <p>
     * @custom.mytag3 Aplicamos un filtrado por dificultad y sobre quien anadio un kakuro al repositorio.
     * <p>
     * @param dif Dificultad por la cual queremos filtrar los kakuros.
     * @param propi Valor que nos sirve para filtrar los kakuros por quien anadio los kakuros al repositorio.
     */
    public void applyFilter(int dif, int propi){
        programFiltredKkr = new ArrayList<Kakuro>();

        Difficulty d = Difficulty.EASY;
        Source s = Source.GENERATED;

        if(dif==1) d = Difficulty.EASY;
        else if(dif==2) d = Difficulty.MEDIUM;
        else if(dif==3) d = Difficulty.HARD;

        if(propi==1) s = Source.GENERATED;
        else if(propi==2) s= Source.USER;
        else if(propi==3) s= Source.ORIGINAL;


        for(int i=0; i<programKakuros.size(); i++){
            Kakuro k=programKakuros.get(i);
            if(dif==0){
                if(propi==0) programFiltredKkr.add(k);
                else if(s==k.getSource()) programFiltredKkr.add(k);
                }
            else if (k.getDifficulty()==d){
                if(propi==0) programFiltredKkr.add(k);
                else if(s==k.getSource()) programFiltredKkr.add(k);
             }
         }
        }

    /**
     * Devolvemos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 Tenemos que haber cargado los datos desde persistencia.
     * <p>
     * @custom.mytag2 Devolvemos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Devolvemos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @return Devolvemos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     */
    public ArrayList<Kakuro> getProgramKakuros(){
        return programKakuros;
    }

    /**
     * Devolvemos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 Tenemos que haber cargado los datos desde persistencia.
     * <p>
     * @custom.mytag2 Devolvemos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Devolvemos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * <p>
     * @return Devolvemos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     */
    public ArrayList<Profile> getProgramProfiles(){
        return programProfiles;
    }

    /**
     * Devolvemos el conjunto de partidas guardadas que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 Tenemos que haber cargado los datos desde persistencia.
     * <p>
     * @custom.mytag2 Devolvemos el conjunto de partidas guardadas que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Devolvemos el conjunto de partidas guardadas que se encuentran actualmente en la aplicacion.
     * <p>
     * @return Devolvemos el conjunto de partidas guardadas que se encuentran actualmente en la aplicacion.
     */
    public ArrayList<Game> getProgramGames(){
        return programGames;
    }

    /**
     * Devolvemos el conjunto de estadisticas de kakuros que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 Tenemos que haber cargado los datos desde persistencia.
     * <p>
     * @custom.mytag2 Devolvemos el conjunto de estadisticas de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Devolvemos el conjunto de estadisticas de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @return Devolvemos el conjunto de estadisticas de kakuros que se encuentran actualmente en la aplicacion.
     */
    public ArrayList<Stats> getProgramStats(){
        return programStats;
    }

    /**
     * Actualizamos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 En el conjunto de kakuros recibidos, cada objeto debe haberse inicializado de forma correcta.
     * <p>
     * @custom.mytag2 Actualizamos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Actualizamos el conjunto de kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @param k Conjunto de kakuros que reemplazaran los que tenemos actualmente en la aplicacion
     */
    public void setProgramKakuros(ArrayList<Kakuro> k){
        programKakuros = new ArrayList<>(k);
    }

    /**
     * Actualizamos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 En el conjunto de perfiles recibidos, cada objeto debe haberse inicializado de forma correcta.
     * <p>
     * @custom.mytag2 Actualizamos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Actualizamos el conjunto de perfiles que se encuentran actualmente en la aplicacion.
     * <p>
     * @param k Conjunto de perfiles que reemplazaran los que tenemos actualmente en la aplicacion
     */
    public void setProgramProfiles(ArrayList<Profile> k){
        programProfiles = new ArrayList<>(k);
    }

    /**
     * Actualizamos el conjunto de estadisticas de los kakuros que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 En el conjunto de estadisticas recibidas, cada objeto debe haberse inicializado de forma correcta.
     * <p>
     * @custom.mytag2 Actualizamos el conjunto de estadisticas de los kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Actualizamos el conjunto de estadisticas de los kakuros que se encuentran actualmente en la aplicacion.
     * <p>
     * @param k Conjunto de estadisticas de los kakuros que reemplazaran los que tenemos actualmente en la aplicacion
     */
    public void setProgramStats(ArrayList<Stats> k){
        programStats = new ArrayList<>(k);
    }

    /**
     * Actualizamos el conjunto de partidas que se encuentran actualmente en la aplicacion.
     * @custom.mytag1 En el conjunto de partidas recibidas, cada objeto debe haberse inicializado de forma correcta.
     * <p>
     * @custom.mytag2 Actualizamos el conjunto de partidas que se encuentran actualmente en la aplicacion.
     * <p>
     * @custom.mytag3 Actualizamos el conjunto de partidas que se encuentran actualmente en la aplicacion.
     * <p>
     * @param k Conjunto de partidas que reemplazaran los que tenemos actualmente en la aplicacion
     */
    public void setProgramGames(ArrayList<Game> k){
        programGames = new ArrayList<>(k);
    }

}
