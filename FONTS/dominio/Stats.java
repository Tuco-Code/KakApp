package dominio;

/**
 * Representación de las estadisticas de un kakuro.
 */

public class Stats {
    /**
     * Atributo, fijo, que indica el numero de mejores tiempos guardados.
     */
    public final static int MAX_pos = 3;

    /**
     * Identificador que referencia al kakuro al que estas estadsiticas describen.
     */
    private String kakuroReference;
    
    /**
     * Vector que contiene los mejores tiempos, almacenados en orden ascendente.
     * Si una posicion vale -1, significa que está vacía. Se llenara a medida que mas usuarios completen el kakuro.
     */
    private int[] topTimes;
    
     /**
     * Vector que contiene los nombres de los usuarios con los mejores tiempos. topTimesOwner[i] ha alcanzado un tiempo de topTimes[i].
     * Si una posicion no tiene valor, es decir, vale "", significa que está vacía. Se llenara a medida que mas usuarios completen el kakuro.
     */
    private String[] topTimesOwner;

    /**
     * Constructora de la clase. Decide el numero de estadisticas a guardar e inicializa las estadisticas vacias.
     * @custom.mytag1 Cierto
     * <p>
     * @custom.mytag2 Se ha creado un objeto Stats, con valores "vacios".
     * <p>
     * @custom.mytag3 Constructora de la clase. Decide el numero de estadisticas a guardar e inicializa las estadisticas vacias.
     * @param kakuro La string que identifica al Kakuro al que se refieren las estadisticas.
     */
    public Stats(String kakuro) {
        kakuroReference = kakuro;
        topTimes = new int[MAX_pos];
        topTimesOwner = new String[MAX_pos];
        topTimes[0] = topTimes[1] = topTimes [2] = 5000;
        topTimesOwner[0] = "Marta";
        topTimesOwner[1] = "David";
        topTimesOwner[2] = "Edgar";
    }

    /**
     * Constructora de la clase. Decide el numero de estadisticas a guardar e inicializa las estadisticas con los valores dados por parametro.
     * @custom.mytag1 Cierto
     * <p>
     * @custom.mytag2 Se ha creado un objeto Stats, con los valores de su numeor de juegos inicializados.
     * <p>
     * @custom.mytag3 Constructora de la clase. Decide el numero de estadisticas a guardar e inicializa las estadisticas con los valores dados por parametro.
     * @param kakuro La string que identifica el Kakuro al que se refieren las estadisticas.
     * @param topTimes Vector con los 3 mejores tiempos conseguidos en el kakuro.
     * @param topTimesOwner Vector con los 3 nombres d elos usuarios que han conseguido los mejores tiempos.
     */
    public Stats(String kakuro, int[] topTimes, String[] topTimesOwner) {
        kakuroReference = kakuro;
        this.topTimes = new int[3];
        this.topTimesOwner = new String[3];
        for (int i = 0; i < MAX_pos; i++) {
            this.topTimes[i] = topTimes[i];
            this.topTimesOwner[i] = topTimesOwner[i];
        }
    }

    /**
     * @custom.mytag1 time es un atribito no nulo. owner es el identificador de un usuario del sistema.
     * <p>
     * @custom.mytag2 Actualiza las estadisticas del kakuro. Si time no es mayor que el peor tiempo del top, el objeto Stats no se modifica.
     * <p>
     * @custom.mytag3 Funcion encargada de actualizar las estadisticas del kakuro. Se encarga de comprobar si el tiempo es suficientemente bueno como para estar en el top. Si lo esta, lo anade a la posicion correspondiente.
     * La estadistica favorece al tiempo mas antiguo. En otras palabras, si time es identico al tiempo de una posicion top, time no reemplazara esa posicion.
     * <p>
     * @param owner Identificador del usuario que ha acabado la partida.
     * @param time Tiempo empleado por el usuario owner en completar el kakuro.
     */
    public void addGame(String owner, int time) {
        boolean found = false;
        for (int i = 0; i < MAX_pos && !found; i++) {
            if (topTimes[i] > time || topTimes[i] == -1) {
                add_Top(owner, time, i);
                found = true;
            }
        }
    }

    /**
     * Devuelve el identificador del kakuro de las estadisticas.
     * @custom.mytag1 El objeto Stats sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Retorna el identificador del kakuro de las estadisticas del objeto Stats sobre el que se llama la funcion.
     * <p>
     * @custom.mytag3 Devuelve el identificador del kakuro de las estadisticas.
     * <p>
     * @return Un String que contiene el identificador de un kakuro.
     */
    public String getCode() {return kakuroReference;}

    /**
     * @custom.mytag1 pos vale entre 1 y MAX_pos;
     * <p>
     * @custom.mytag2 Se ha devuelto el mejor tiempo de la posicion solicitada de la estadistica.
     * <p>
     * @custom.mytag3 Funcion encargada de devolver el tiempo de una de las mejores posiciones de la estadistica.
     * <p>
     * @param pos posicion de la estadistica de la que se quiere obtener el tiempo.
     * @return Devuelve el tiempo de la posicion que se pide. Devuelve -1 si esa posicion no esta ocupada aun.
     */
    public int getPosTime(int pos) {
        return topTimes[pos-1];
    }

    /**
     * @custom.mytag1 pos vale entre 1 y MAX_pos;
     * <p>
     * @custom.mytag2 Se ha devuelto el nombre del usuario que ha logrado el mejor tiempo de la posicion solicitada de la estadistica.
     * <p>
     * @custom.mytag3 Funcion encargada de devolver el usuario que ha logrado el tiempo de una de las mejores posiciones de la estadistica.
     * <p>
     * @param pos posicion de la estadistica (entre 1 y MAX_pos) de la que se quiere obtener el usuario.
     * @return Devuelve el usuario de la posicion que se pide. Devuelve string vacia ("") si esa posicion no esta ocupada aun.
     */
    public String getPosOwner(int pos) {
        return topTimesOwner[pos-1];
    }

    /**
     * @custom.mytag1 pos vale entre 1 y MAX_pos. time debe ser estricamente inferior al tiempo almacenado en topTimes[pos] y superior al tiempo almacenado en topTimes[pos-1], en caso de pos ser estrictamente superior a 0.
     * <p>
     * @custom.mytag2 Se actualiza la tabla de estadisticas.
     * <p>
     * @custom.mytag3 Funcion encargada de actualizar el top, anadiendo el tiempo y el nombre del usuario que lo ha alcanzado en la posicion pos de los vectores.
     * Actualiza las posiciones entre pos y MAX_pos-1 de la estadistica.
     * <p>
     * @param owner Nombre del usuario que ha alcanzado el tiempo time.
     * @param time Tiempo alcanzado por el usuario owner.
     * @param pos Posicion donde ahora ira el nuevo tiempo.
     */
    private void add_Top(String owner, int time, int pos) {
        for (int i = MAX_pos - 1; i > pos; i--) {
            topTimes[i] = topTimes[i - 1];
            topTimesOwner[i] = topTimesOwner[i - 1];
        }
        topTimes[pos] = time;
        topTimesOwner[pos] = owner;
    }
}
