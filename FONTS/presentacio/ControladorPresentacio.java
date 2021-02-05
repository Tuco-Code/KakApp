package presentacio;

import dominio.ControladorDominio;

import dominio.Triple;

public class ControladorPresentacio {
    private static ControladorDominio controladorDominio= new ControladorDominio();

    public ControladorPresentacio(){
    }

    /**
     * Inicializamos la Vista Principal.
     * @custom.mytag1 true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista principal.
     * <p>
     * @custom.mytag3 Inicializamos la Vista Principal.
     * <p>
     */
    public static void VistaPrincipal(){VistaPrincipal vista = new VistaPrincipal();}

    /**
     * Inicializamos la vista de opciones.
     * @custom.mytag1 username representa al usuario que hace log in.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de opciones.
     * <p>
     * @custom.mytag3 Inicializamos la vista de opciones.
     * <p>
     * @param username String que representa el usuario que hace log in.
     */
    public static void VistaOpcions(String username){ VistaOpcions vista = new VistaOpcions();}

    /**
     * Inicializamos la vista de previsualizacion de un kakuro y su ranking.
     * @custom.mytag1 kakuro es una string correcta de un kakuro.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de previsualizacion de un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista de previsualizacion de un kakuro.
     * <p>
     * @param kakuro String con el formato correcto de un kakuro
     */
    public static void VistaSelected(String kakuro){ VistaSelected vista = new VistaSelected(kakuro);}

    /**
     * Inicializamos la vista de existe un game empezado con un kakuro y un perfil concretos.
     * @custom.mytag1 tex es la string que se dessea mostrar en la vista y k y es una string correcta de un kakuro.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de existe un game empezado con un kakuro y un perfil concretos, que tiene como texto principal tex.
     * <p>
     * @custom.mytag3 Inicializamos la vista de existe un game empezado con un kakuro y un perfil concretos.
     * <p>
     * @param tex String
     * @param k String con el formato correcto de un kakuro
     */
    public static void VistaExiste(String tex, String k){ VistaExiste vista = new VistaExiste(tex, k);}

    /**
     * Inicializamos la vista de los datos de un perfil y configuraciones de un perfil.
     * @custom.mytag1  true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de los datos y configuraciones de un perfil.
     * <p>
     * @custom.mytag3 Inicializamos la vista de los datos de un perfil y configuraciones de un perfil.
     * <p>
     */
    public static void VistaProfile(){ VistaProfile vista = new VistaProfile();}

    /**
     * Inicializamos la vista de cambio de contraseña.
     * @custom.mytag1  true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de cambio de contraseña.
     * <p>
     * @custom.mytag3 Inicializamos la vista de cambio de contraseña.
     * <p>
     */
    public static void VistaPwd(){ VistaPwd vista = new VistaPwd();}

    /**
     * Inicializamos la vista de la wokshop de creacion de kakuros.
     * @custom.mytag1  t es un int entre 2 y 20
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de la wokshop de creacion de kakuros.
     * <p>
     * @custom.mytag3 Inicializamos la vista de la wokshop de creacion de kakuros.
     * <p>
     * @param t es un int  entre 2 y 20
     */
    public static void VistaWorkshop(int t){ VistaWorkshop vista = new VistaWorkshop(t);}

    /**
     * Inicializamos la vista de la wokshop de creacion de kakuros.
     * @custom.mytag1  k es una string con el formato de un kakuro
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de la wokshop de creacion de kakuros.
     * <p>
     * @custom.mytag3 Inicializamos la vista de la wokshop de creacion de kakuros.
     * <p>
     * @param k String con el formato de un kakuro
     */
    public static void VistaWorkshop2(String k){ VistaWorkshop vista = new VistaWorkshop(k);}

    /**
     * Inicializamos la vista de aviso de kakuro valido in/valido.
     * @custom.mytag1  text es el mensaje de error que se quiere ensenar y k es una string con el formato de un kakuro
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de aviso de kakuro in/valido.
     * <p>
     * @custom.mytag3 Inicializamos la vista de aviso de kakuro in/valido.
     * <p>
     * @param text  String con el mensaje que se quiere introducir en la vista
     * @param k String con el formato de un kakuro
     */
    public static void VistaWrongKakuro(String text,String k){ VisitaWrongKakuro vista = new VisitaWrongKakuro(text,k);}

    /**
     * Inicializamos la vista de ver la solucion de un kakuro.
     * @custom.mytag1  sol y given_sol tienen el formato de kakuro y kak es 0 o 1
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de ver la solucion de un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista de ver la solucion de un kakuro .
     * <p>
     * @param sol String con el formato de kakuro que representa la solucion que se ensena.
     * @param given_sol String con el formato de kakuro que representa el estado de la partida cuando el usuario le ha dado a ver la solucion.
     * @param kak Int que decide si ver la solucion de la maquina o la solucion parcial del jugador, 0 y 1 respectivamente
     */
    public static void VistaSolution(String sol, String given_sol, int kak){ VistaSolution vista = new VistaSolution(sol,given_sol,kak);}

    /**
     * Inicializamos la vista de aviso de guardar y salir de una partida.
     * @custom.mytag1  text es el mensaje de error que se quiere ensenar y t es un int.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de aviso de guardar y salir de una partida.
     * <p>
     * @custom.mytag3 Inicializamos la vista de aviso de guardar y salir de una partida.
     * <p>
     * @param text String con el mensaje que se quiere introducir en la vista.
     * @param t Int que representa el tiempo que lleva intentando resolver el kakuro
     */
    public static void VistaPopup(String text,int t){
        VistaPopup vista = new VistaPopup(text, t);
    }

    /**
     * Inicializamos la vista de generador de un nuevo kakuro.
     * @custom.mytag1  true
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de generador de un nuevo kakuro
     * <p>
     * @custom.mytag3 Inicializamos la vista de generador de un nuevo kakuro.
     * <p>
     */
    public static void VistaNouKakuro(){
        VistaNouKakuro vista = new VistaNouKakuro();
    }

    /**
     * Inicializamos la vista de opciones para proponer un kakuro.
     * @custom.mytag1  true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de opciones para proponer un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista de opciones para proponer un nuevo kakuro.
     * <p>
     */
    public static void VistaProposarKakuro(){ VistaProposarKakuro vista = new VistaProposarKakuro(); }

    /**
     * Inicializamos la vista de visualizacion del repositorio de kakuros.
     * @custom.mytag1  d y p son ints del 0 al 3 .
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de visualizacion del repositorio de kakuros.
     * <p>
     * @custom.mytag3 Inicializamos la vista de visualizacion del repositorio de kakuros.
     * <p>
     * @param d Int del 0 al 3 que representa la dificultat del filtrado del repositorio
     * @param p Int del 0 al 3 que representa la source del filtrado del repositorio
     */
    public static void VistaRepo(int d, int p){ VistaRepo vista = new VistaRepo(d,p); }

    /**
     * Inicializamos la vista de visualizacion de una partida.
     * @custom.mytag1  Kakuro es una string con el formato de un kakuro y time es un int que representa el timepo en segundos.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de juagar una partida.
     * <p>
     * @custom.mytag3 Inicializamos la vista de jugar una partida.
     * <p>
     * @param Kakuro String con el formato de un kakuro que se usara para el game.
     * @param time Int que representa el tiempo que lleva en la partida en segundos.
     */
    public static void VistaKakuro(String Kakuro,int time){
        VistaKakuro vista = new VistaKakuro(Kakuro,time);
    }

    /**
     * Inicializamos la vista del aviso de borrar un kakuro.
     * @custom.mytag1  K es una string con el formato de un kakuro y text es una string que se vera en la vista.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista del aviso de borrar un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista del aviso de borrar un kakuro.
     * <p>
     * @param text String con el mensaje que se quiere introducir en la vista.
     * @param k String con el formato de un kakuro que se eliminara.
     */
    public static void VistaDeleteKakuro(String text,String k){VistaDeleteKakuro vista = new VistaDeleteKakuro(text,k); }

    /**
     * Inicializamos la vista del aviso de borrar un usuario.
     * @custom.mytag1  text es una string que se vera en la vista.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista del aviso de borrar un usuario.
     * <p>
     * @custom.mytag3 Inicializamos la vista del aviso de borrar un usuario.
     * <p>
     * @param text String con el mensaje que se quiere introducir en la vista.
     */
    public static void VistaDeleteUser(String text){
        VistaDeleteUser vista = new VistaDeleteUser(text);
    }

    /**
     * Inicializamos la vista de finalizacion exitosa de un kakuro.
     * @custom.mytag1  true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de finalizacion exitosa de un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista de finalizacion exitosa de un kakuro.
     * <p>
     */
    public  static void VistaCongrats(){
        VistaCongrats vista = new VistaCongrats();
    }
    /**
     * Inicializamos la vista de finalizacion fallida de un kakuro.
     * @custom.mytag1  true.
     * <p>
     * @custom.mytag2 El usuario puede interaccionar con la vista de finalizacion fallida de un kakuro.
     * <p>
     * @custom.mytag3 Inicializamos la vista de finalizacion fallida de un kakuro.
     * <p>
     */
    public  static void VistaWrong(){
        VistaWrong vista = new VistaWrong();
    }


    /**
     *  Funcion que devuelve el codigo de un kakuro generado con los parametros de entrada.
     * @custom.mytag1  tam es un entero, percentage es un nentero menor a 100 y resoltes es otro entero
     * <p>
     * @custom.mytag2 Se devuelve el codigo de un kakuro generado con los parametros de entrada.
     * <p>
     * @custom.mytag3 Funcion que devuelve el codigo de un kakuro generado con los parametros de entrada.
     * <p>
     * @param tam Entero que representa el tamano del kakuro generado.
     * @param percentatge Entero que representa el porcentaje de casillas blancas del kakuro generado.
     * @param resoltes Entero que representa el numero de casillas resueltas del kakuro generado.
     * @return String en formato Kakuro del kakuro generado.
     */
    public static String generarKakuro(int tam,int percentatge, int resoltes){return controladorDominio.generarKakuro(tam,percentatge,resoltes); }

    /**
     *  Funcion que se encarga de hacer log in con usando los parametros de entrada.
     * @custom.mytag1  username y password son strings no nulos
     * <p>
     * @custom.mytag2 Se hace log in del usuario con los parametros de entrada.
     * <p>
     * @custom.mytag3 Funcion que se encarga de hacer log in con usando los parametros de entrada..
     * <p>
     * @param username String que representa el username del perfil del log in.
     * @param password String que representa el password del perfil del log in.
     * @return codigo de error. Entero del 0 al 2.
     */
    public static int AccessUser(String username, String password) { return controladorDominio.loginProfile(username,password); }

    /**
     *  Funcion que se encarga de registrar un usuario con usando los parametros de entrada.
     * @custom.mytag1  username y password son strings no nulos
     * <p>
     * @custom.mytag2 Se hace registra el usuario con los parametros de entrada.
     * <p>
     * @custom.mytag3 Funcion que se encarga de de registrar un usuario usando los parametros de entrada..
     * <p>
     * @param username String que representa el username del perfil a registrar.
     * @param password String que representa el password del perfil a registrar.
     * @return codigo de error. Entero del 0 al 2.
     */
    public static int Register(String username, String password){return controladorDominio.addProfile(username,password);}

    /**
     * Funcion que devuelve la dificultad de un kakuro a partir de su tamano, porcentaje de casillas blancas y numero de casillas resueltas.
     * @custom.mytag1 Los enteros recibidos deben ser positivos ademas, tanto white como resolved deben adecuarse al tamano recibido.
     * <p>
     * @custom.mytag2 Devolvemos un entero equivalente a la dificultad del kakuro definido por su tamano, su porcentage de casillas blancas y numero
     * de casillas blancas.
     * <p>
     * @custom.mytag3 Funcion que devuelve la dificultad de un kakuro a partir de su tamano, porcentaje de casillas blancas y numero de casillas resueltas.
     * <p>
     * @param size Entero que define el tamano del kakuro que se quiere calcular su dificultad.
     * @param white Porcentaje de casillas blancas del kakuro que se quiere calcular su dificultad.
     * @param resolved Numero de casillas blancas resultas del kakuro que se quiere calcular su dificultad.
     * @return Devolvemos 1 si su dificultad es baja, 2 si es intermedia y 3 si es alta.
     */
    public static int dificultat(int size, int white, int resolved){return controladorDominio.dificultat(size,white, resolved); }

    /**
     * Funcion que devuelve la cantidad maxima de casillas blancas resueltas que podemos poner en un kakuro sin resolverlo al completo.
     * @custom.mytag1 Los enteros recibidos deben ser positivos.
     * <p>
     * @custom.mytag2 Calculamos el numero maximo de casillas blancas resueltas que puede tener el kakuro dependiendo de su tamano y el porcentaje de
     * casillas blancas sin resolverlo al completo.
     * <p>
     * @custom.mytag3 Funcion que devuelve la cantidad maxima de casillas blancas resueltas que podemos poner en un kakuro sin resolverlo al completo.
     * <p>
     * @param size Tamano del kakuro
     * @param white Porcentaje de casillas blancas del kakuro.
     * @return Numero maximo de casillas blancas resueltas que puede tener el kakuro dependiendo de su tamano y el porcentaje de
     * casillas blancas sin resolverlo al completo.
     */
    public static int max_resoltes(int size, int white) {
        return controladorDominio.max_resoltes(size, white);
    }


    /**
     * Funcion que devuelve el nombre del usuario que se ha conectado a la aplicacion.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 Devolvemos el nombre del usuario conectado a la aplicacion.
     * <p>
     * @custom.mytag3 Funcion que devuelve el nombre del usuario que se ha conectado a la aplicacion.
     * <p>
     * @return El String devuelto es el nombre del usuario conectado.
     */
    public static String getUser(){
        return controladorDominio.getUser();
    }


    /**
     * Funcion usada para notificar a la capa de dominio que se ha escrito un valor en una posicion especifica del kakuro.
     * @custom.mytag1 El entero i debe ser un valor entre 0 y el numero de fila - 1 que contiene el kakuro, el entero j debe ser un valor entre 0 y el
     * numero de columnas - 1 que contiene el kakuro y el entero value es el valor que se va a escribir en la posicion i,j del tablero.
     * <p>
     * @custom.mytag2 Hemos escrito el valor value en la posicion i,j del tablero que esta jugando el usuario.
     * <p>
     * @custom.mytag3 Funcion usada para notificar a la capa de dominio que se ha escrito un valor en una posicion especifica del kakuro.
     * <p>
     * @param i Fila del tablero donde se va a escribir un valor.
     * @param j Columna del tablero donde se va a escribir un valor.
     * @param Value Valor que se va a escribir en la posicion i,j del tablero.
     */
    public static void addValue(int i, int j,int Value){
        controladorDominio.addValue(i,j,Value);
    }


    /**
     * Funcion que se encarga de validar una solucion introducida por un usuario y anadirla a las estadisticas si tiene un tiempo top del kakuro.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Si el tablero es correcto, comprobaremos si el tiempo es mejor para actualizar las estadisticas del kakuro resuelto.
     * <p>
     * @custom.mytag3 Funcion que se encarga de validar una solucion introducida por un usuario y anadirla a las estadisticas si tiene un tiempo top del kakuro.
     * <p>
     * @return Devolvemos true si el tablero es correcto o false en caso contrario.
     */
    public static Boolean validate(){
        return controladorDominio.validate();
    }


    /**
     * Funcion encargada de dar una pista para un kakuro: da el valor de una casilla blanca vacia aleatoria.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Devolvemos la posicion de la casilla blanca que vamos a dar la pista y el valor a escribir.
     * <p>
     * @custom.mytag3 Funcion encargada de dar una pista para un kakuro: da el valor de una casilla blanca vacia aleatoria.
     * <p>
     * @return Devolvemos una tripleta, el primer valor es la fila del tablero, el segundo valor es la columna del tablero y el tercer valor es el
     * valor es correcto en la posicion i,j del kakuro.
     */
    public static Triple pista(){
        return controladorDominio.pista();
    }

    /**
     * Funcion encargada de devolver la cantidad de kakuros que han pasado el filtrado de dificultad o de quien los anadio al repositorio.
     * @custom.mytag1 Tenemos que haber cargado los kakuros.
     * <p>
     * @custom.mytag2 Devolvemos el numero de kakuros que han pasado el filtrado.
     * <p>
     * @custom.mytag3 Funcion encargada de devolver la cantidad de kakuros que han pasado el filtrado de dificultad o de quien los anadio al repositorio.
     * <p>
     * @return Numero de kakuros que han pasado el filtrado.
     */
    public static int getSizeKkr(){
            return controladorDominio.getSizeKkr();
    }


    /**
     * Funcion encargada de pedir a la capa de dominio que filtre los kakuros segun su dificultad y quien los anadio al repositorio.
     * @custom.mytag1 Tenemos que haber cargado los kakuros.
     * <p>
     * @custom.mytag2 Guardamos el conjunto que ha pasado el filtrado en programFiltredKkr.
     * <p>
     * @custom.mytag3 Funcion encargada de pedir a la capa de dominio que filtre los kakuros segun su dificultad y quien los anadio al repositorio.
     * <p>
     * @param dif Dificultad por la cual queremos filtrar los kakuros.
     * @param propi Valor que nos sirve para filtrar los kakuros por quien anadio los kakuros al repositorio.
     */
    public static void applyFilter(int dif, int propi){
        controladorDominio.applyFilter(dif,propi);
    }


    /**
     * Funcion encargada de obtener la solucion del kakuro que se esta resolviendo en forma de String.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Devolvemos la solucion del kakuro que se esta jugando.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener la solucion del kakuro que se esta resolviendo en forma de String.
     * <p>
     * @return Devolvemos la solucion del kakuro que se esta jugando en forma de String.
     */
    public static String getKkrSolved(){
        return controladorDominio.getKkrSolved();
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
    public static String getReferenceGame(){
        return controladorDominio.getReferenceGame();
    }

    /**
     * Funcion encargada de obtener el String que describe el tablero de la partida actual.
     * @custom.mytag1 Debe haber una partida jugandose.
     * <p>
     * @custom.mytag2 Devolvemos el tablero de la partida actual en un String.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener el String que describe el tablero de la partida actual.
     * <p>
     * @return String que define el tablero de la partida actual.
     */
    public static String getBoardGame(){
        return controladorDominio.getCurrentGame();
    }

    /**
     * Funcion encargada de comunicarse con dominio para guardar una partida que se esta jugando.
     * @custom.mytag1 Debe estar jugandose una partida.
     * <p>
     * @custom.mytag2 Guardamos la partida que se esta jugando.
     * <p>
     * @custom.mytag3 Funcion encargada de comunicarse con dominio para guardar una partida que se esta jugando.
     * <p>
     */
    public static void saveCurrentGame(){
        controladorDominio.saveCurrentGame();
    }


    /**
     * Funcion encargdada de encontrar el kakuro que se ha elegido en el repositorio.
     * @custom.mytag1 El entero recibido debe ser entre 0 y el numero de kakuros que hay en el repositorio - 1.
     * <p>
     * @custom.mytag2 Devolvemos el String de referencia del Kakuro que se encuentra en la posicion index de nuestro repositorio.
     * <p>
     * @custom.mytag3 Funcion encargdada de encontrar el kakuro que se ha elegido en el repositorio.
     * <p>
     * @param i Entero entre 0 y el numero de kakuros que hay en el repositorio - 1.
     * @return Devolvemos el String de referencia del Kakuro que se encuentra en la posicion index de nuestro repositorio.
     */
    public static String selectKkr(int i){
        return controladorDominio.selectKakuro(i);
    }


    /**
     * Funcion encargada de devolver las estadisticas del Kakuro referenciado por el String recibido.
     * @custom.mytag1 El String recibido debe referenciar a un kakuro de nuestro repositorio.
     * <p>
     * @custom.mytag2 Devolvemos las caracteristicas del kakuro referenciado por el String recibido.
     * <p>
     * @custom.mytag3 Funcion encargada de devolver las estadisticas del Kakuro referenciado por el String recibido.
     * <p>
     * @param k String que referencia un kakuro que se encuentra en nuestro repositorio.
     * @return Devolvemos las caracteristicas del kakuro referenciado por el String recibido.
     */
    public static String[] getStatsOfKakuro(String k){
        return controladorDominio.getStatsOfKakuro(k);
    }


    /**
     * Funcion encargada de comprobar si el usuario conectado tiene una partida guardada del kakuro recibido.
     * @custom.mytag1 El String recibido debe referenciar correctamente a un kakuro.
     * <p>
     * @custom.mytag2 Devolvemos true si existe una partida del kakuro recibido para el usuario conectado o false en caso contrario.
     * <p>
     * @custom.mytag3 Funcion encargada de comprobar si el usuario conectado tiene una partida guardada del kakuro recibido.
     * <p>
     * @param k String que referencia un kakuro.
     * @return Devolvemos true si existe una partida del kakuro recibido para el usuario conectado o false en caso contrario.
     */
    public static  Boolean existeGame(String k){
        return controladorDominio.existsGame(k);
    }

    /**
     * Funcion encargada de crear una partida del kakuro recibido para el usuario que esta actualmente conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe referenciar un kakuro.
     * <p>
     * @custom.mytag2 Creamos la partida del kakuro recibido.
     * <p>
     * @custom.mytag3 Funcion encargada de crear una partida del kakuro recibido para el usuario que esta actualmente conectado a la aplicacion.
     * <p>
     * @param k String que referencia a un kakuro del repositorio.
     * @return Devuelve 0 si no ha habido ningun problema al crear la partida.
     */
    public static int crearGame(String k){
        return controladorDominio.crearGame(k);
    }

    /**
     * Funcion encargada de cargar la partida del kakuro recibido para el usuario que esta conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe referenciar correctamente a un kakuro.
     * <p>
     * @custom.mytag2 Cargamos la partida como la partida actual y devolvemos el String que describe el tablero de la partida tal y como se guardo.
     * <p>
     * @custom.mytag3 Si el usuario conectado tiene una partida guardada del kakuro recibido, la cargamos.
     * <p>
     * @param k String que referencia un kakuro.
     * @return Devolvemos el tablero tal y como se guardo en forma de String.
     */
    public static String loadGame(String k){
        return controladorDominio.loadGame(k);
    }

    /**
     * Funcion encargada de cambiar la contrasena del usuario conectado.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 Si los credenciales son correctos se le cambiara la contrasena.
     * <p>
     * @custom.mytag3 Funcion encargada de cambiar la contrasena del usuario conectado.
     * <p>
     * @param old_ps Contrasena antigua.
     * @param new_ps Contrasena nueva.
     * @return Devuelve 0 si se ha cambiado con exito, -1 si al escribir la contrasena actual se ha equivocado, -2 si la contrasena nueva es la misma
     * que la antigua
     */
    public static int changePwd(String old_ps, String new_ps){ return controladorDominio.changePassword(old_ps,new_ps); }

    /**
     * Funcion encargada de obtener el tiempo que lleva el jugador resolviendo el kakuro.
     * @custom.mytag1 El entero time debe estar correctamente inicializado y actualizado.
     * <p>
     * @custom.mytag2 Devolvemos el tiempo que lleva el jugador resolviendo el kakuro.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener el tiempo que lleva el jugador resolviendo el kakuro.
     * <p>
     * @return Devolvemos el tiempo que lleva el jugador resolviendo el kakuro.
     */
    public static int getGameTime(){
        return  controladorDominio.getGameTime();
    }

    /**
     * Funcion encargada de obtener el tiempo que tarda el sistema en resolver el kakuro.
     * @custom.mytag1 El entero time_solution debe estar correctamente inicializado y actualizado.
     * <p>
     * @custom.mytag2 Devolvemos el tiempo que tarda el sistema en resolver el kakuro.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener el tiempo que tarda el sistema en resolver el kakuro.
     * <p>
     * @return Devolvemos el tiempo que tarda el sistema en resolver el kakuro.
     */
    public static float getGameTime_solution(){
        return  controladorDominio.getGameTime_solution();
    }

    /**
     * Funcion encargada de actualizar el tiempo que lleva jugando el kakuro.
     * @custom.mytag1 El entero recibido debe ser positivo y debe representar el tiempo que ha pasado el usuario hasta ahora resolviendo el kakuro.
     * <p>
     * @custom.mytag2 Actualizamos el tiempo que lleva jugando el kakuro.
     * <p>
     * @custom.mytag3 Funcion encargada de actualizar el tiempo que lleva jugando el kakuro.
     * <p>
     * @param t Entero que contiene el tiempo ha pasado el jugador resolviendo el kakuro.
     */
    public static void changeGameTime(int t){
        controladorDominio.changeGameTime(t);
    }


    /**
     * Funcion encargada de obtener el numero de Kakuros jugados por el usuario actual.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros jugados por el usuario actual.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener el numero de Kakuros jugados por el usuario actual.
     * <p>
     * @return Numero de Kakuros jugados por el perfil actual.
     */
    public  static int getNumOfGames(){
        return  controladorDominio.getNumOfGames();
    }

    /**
     * Funcion encargada de obtener el numero de Kakuros completados por el usuario actual.
     * @custom.mytag1 El objeto Profile sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Devolvemos el numero de Kakuros completados por el usuario actual.
     * <p>
     * @custom.mytag3 Funcion encargada de obtener el numero de Kakuros completados por el usuario actual.
     * <p>
     * @return Numero de Kakuros completados por el usuario actual.
     */
    public  static int getNumOfWins(){
        return  controladorDominio.getNumOfWins();
    }


    /**
     * Funcion encargada de eliminar el perfil que esta conectado a la aplicacion.
     * @custom.mytag1 Un usuario debe haber accedido a la aplicacion.
     * <p>
     * @custom.mytag2 El perfil conectado ha sido eliminado del sistema y con ello todas sus partidas guardadas, ademas le enviamos a la vista de iniciar sesion.
     * <p>
     * @custom.mytag3 Funcion encargada de eliminar el perfil que esta conectado a la aplicacion.
     * <p>
     */
    public static void deleteProfile(){
        controladorDominio.deleteProfile();
    }

    /**
     * Funcion encargada de eliminar un kakuro del repositorio y con el todas sus partidas guardadas y sus estadisticas.
     * @custom.mytag1 El String recibido debe referenciar un kakuro.
     * <p>
     * @custom.mytag2 Si el kakuro se encuentra en el repositorio, se eliminara el kakuro, sus estadisticas y todas las partidas guardadas de ese kakuro.
     * <p>
     * @custom.mytag3 Funcion encargada de eliminar un kakuro del repositorio y con el todas sus partidas guardadas y sus estadisticas.
     * <p>
     * @param Kakuro String que referencia un kakuro
     */
    public static void deleteKakuro(String Kakuro){
        controladorDominio.deleteKakuro(Kakuro);
    }

    /**
     * Funcion encargada de eliminar la partida del kakuro recibido como parametro para el usuario que esta actualmente conectado a la aplicacion.
     * @custom.mytag1 El String recibido debe s estar correctamente formateado para referenciar un kakuro.
     * <p>
     * @custom.mytag2 Si el usuario conectado tiene una partida guardada del kakuro que se referencia con el String recibido, la eliminaremos.
     * <p>
     * @custom.mytag3 Funcion encargada de eliminar la partida del kakuro recibido como parametro para el usuario que esta actualmente conectado a la aplicacion.
     * <p>
     * @param Kakuro String que referencia un kakuro.
     */
    public static void deleteGame(String Kakuro){
        controladorDominio.deleteGame(Kakuro);
    }


    /**
     * Funcion encargada de anadir un nuevo kakuro a partir de un fichero.
     * @custom.mytag1 El String que describe el path debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Se anadira el kakuro que se encuentra en el path recibido si no ha habido ningun problema.
     * <p>
     * @custom.mytag3 Funcion encargada de anadir un nuevo kakuro a partir de un fichero.
     * <p>
     * @param path String que define el camino a encontrar el fichero que contiene el kakuro que se quiere anadir.
     * @return Devuelve 0 si hemos anadido el kakuro correctamente, -1 si el kakuro se encuentra en nuestro repositorio, -2 si el String esta
     * mal formateado, -3 si el kakuro contiene alguna casilla blanca con un 0 o el kakuro tiene mas de una solucion, -4 si no se ha podido
     * cargar el archivo.
     */
    public static int loadFromFile(String path){
        return controladorDominio.loadFromFile(path);
    }


    /**
     * Funcion encargada de guardar el kakuro recibido si no se encuentra en nuestro repositorio. En este caso, lo ha anadido un usuario.
     * @custom.mytag1 El String recibido debe estar formateado correctamente.
     * <p>
     * @custom.mytag2 Si no ha habido ningun problema se habra anadido el kakuro en nuestro repositorio.
     * <p>
     * @custom.mytag3 Funcion encargada de guardar el kakuro recibido si no se encuentra en nuestro repositorio. En este caso, lo ha anadido un usuario.
     * <p>
     * @param codi String que referencia un kakuro.
     * @return Devuelve 0 si hemos anadido el kakuro correctamente, -1 si el kakuro se encuentra en nuestro repositorio, -2 si el String esta
     * mal formateado, -3 si el kakuro contiene alguna casilla blanca con un 0 o el kakuro tiene mas de una solucion.
     */
    public static int addKakuro(String codi){
        return controladorDominio.addKakuro2(codi);
    }


}
