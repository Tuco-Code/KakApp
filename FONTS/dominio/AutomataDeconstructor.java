package dominio;

/**
 * La clase AutomataDeconstructor se utiliza para crear un String que define las caracteristicas de un objeto y asi poder guardar sus instancias en persistencia.
 */

public class AutomataDeconstructor {

    /**
     * Constructora por defecto.
     */
    public AutomataDeconstructor() {}

    /**
     * Generamos el String que describe el Profile recibido.
     * @custom.mytag1 El objeto Profile recibido debe tener todos sus atributos inicializados.
     * <p>
     * @custom.mytag2 Creamos el String que describe el Profile recibido.
     * <p>
     * @custom.mytag3 Generamos el String que describe el Profile recibido.
     * <p>
     * @param profile Objeto Profile el cual se va a convertir sus atributos en String para guardarlo en persistencia.
     * @return Devolvemos el String que describe el objeto Profile recibido como parametro.
     */
    public String deconstructProfile(Profile profile)
    {
        String result = "";

        result += profile.getName() + " ";
        result += profile.getPassword() + " ";
        result += String.valueOf(profile.getNumOfGames()) + " ";
        result += String.valueOf(profile.getNumOfWins());

        return result;
    }

    /**
     * Generamos el String que describe el Kakuro recibido.
     * @custom.mytag1 El objeto Kakuro recibido debe tener todos sus atributos inicializados.
     * <p>
     * @custom.mytag2 Creamos el String que describe el Kakuro recibido.
     * <p>
     * @custom.mytag3 Generamos el String que describe el Kakuro recibido.
     * <p>
     * @param kakuro Objeto Kakuro el cual se va a convertir sus atributos en String para guardarlo en persistencia.
     * @return Devolvemos el String que describe el objeto Profile recibido como parametro.
     */
    public String deconstructKakuro(Kakuro kakuro)
    {
        String result = "";

        result += kakuro.getCode() + " ";
        result += kakuro.getDifficulty().name() + " ";
        result += kakuro.getSource().name();

        return result;
    }

    /**
     * Generamos el String que describe los Stats recibidos.
     * @custom.mytag1 El objeto Stats recibido debe tener todos sus atributos inicializados.
     * <p>
     * @custom.mytag2 Creamos el String que describe los Stats recibidos.
     * <p>
     * @custom.mytag3 Generamos el String que describe los Stats recibidos.
     * <p>
     * @param stat Objeto Stats el cual se va a convertir sus atributos en String para guardarlo en persistencia.
     * @return Devolvemos el String que describe el objeto Stats recibido como parametro.
     */
    public String deconstructStats(Stats stat)
    {
        String result = "";

        result += stat.getCode() + " ";

        for (int i = 1; i <= 3; ++i)
        {
            result += String.valueOf(stat.getPosTime(i)) + " ";
        }

        for (int i = 1; i <= 2; ++i)
        {
            result += stat.getPosOwner(i) + " ";
        }

        result += stat.getPosOwner(3);

        return result;
    }

    /**
     * Generamos el String que describe el Game recibido.
     * @custom.mytag1 El objeto Game recibido debe tener todos sus atributos inicializados.
     * <p>
     * @custom.mytag2 Creamos el String que describe el Game recibido.
     * <p>
     * @custom.mytag3 Generamos el String que describe el Game recibido.
     * <p>
     * @param game Objeto Game el cual se va a convertir sus atributos en String para guardarlo en persistencia.
     * @return Devolvemos el String que describe el objeto Game recibido como parametro.
     */
    public String deconstructGame(Game game)
    {
        String result = "";

        result += game.getKakuroReference() + " ";
        result += game.getUserName() + " ";
        result += game.getGameBoard() + " ";
        result += game.getSolution() + " ";
        result += game.getTime() + " ";
        result += game.getTime_solution();

        return result;
    }
}
