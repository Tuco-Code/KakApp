package dominio;

/**
 * La clase AutomataConstructor se utiliza para transformar los Strings recibidos de persistencia a objetos.
 */

public class AutomataConstructor {

    /**
     * Constructora por defecto.
     */
    public AutomataConstructor() {}

    /**
     * Generamos un Profile mediante el String recibido.
     * @custom.mytag1 El String profile debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Creamos el Profile que se puede describir mediante el String recibido.
     * <p>
     * @custom.mytag3 Generamos un Profile mediante el String recibido.
     * <p>
     * @param profile String que define todas las caracteristicas de un Profile.
     * @return Devolvemos el objeto Profile definido por el String recibido.
     */
    public Profile constructProfile(String profile)
    {
        String[] parameters = profile.split(" ");
        Profile myConstruction = new Profile(parameters[0], parameters[1], Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
        return myConstruction;
    }

    /**
     * Generamos un Kakuro mediante el String recibido.
     * @custom.mytag1 El String Kakuro debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Creamos el Kakuro que se puede describir mediante el String recibido.
     * <p>
     * @custom.mytag3 Generamos un Kakuro mediante el String recibido.
     * <p>
     * @param kakuro String que define todas las caracteristicas de un Kakuro.
     * @return Devolvemos el objeto Kakuro definido por el String recibido.
     */
    public Kakuro constructKakuro(String kakuro)
    {
        String[] parameters = kakuro.split(" ");
        Kakuro myConstruction = new Kakuro(parameters[0], Difficulty.valueOf(parameters[1]), Source.valueOf(parameters[2]));
        return myConstruction;
    }

    /**
     * Generamos unas Stats de un kakuro mediante el String recibido.
     * @custom.mytag1 El String stats debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Creamos las Stats de un kakuro que se puede describir mediante el String recibido.
     * <p>
     * @custom.mytag3 Generamos unas Stats de un kakuro mediante el String recibido.
     * <p>
     * @param stats String que define todas las caracteristicas de las Stats de un kakuro.
     * @return Devolvemos el objeto Stats definido por el String recibido.
     */
    public Stats constructStats(String stats)
    {
        String[] parameters = stats.split(" ");
        int[] topTimes = {Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3])};
        String[] topTimesOwner = new String[] {parameters[4], parameters[5], parameters[6]};
        Stats myConstruction = new Stats(parameters[0], topTimes, topTimesOwner);
        return myConstruction;
    }

    /**
     * Generamos un Game mediante el String recibido.
     * @custom.mytag1 El String game debe estar correctamente formateado.
     * <p>
     * @custom.mytag2 Creamos el Game que se puede describir mediante el String recibido.
     * <p>
     * @custom.mytag3 Generamos un Game mediante el String recibido.
     * <p>
     * @param game String que define todas las caracteristicas de un Game.
     * @return Devolvemos el objeto Game definido por el String recibido.
     */
    public Game constructGame(String game)
    {
        String[] parameters = game.split(" ");
        Game myConstruction = new Game(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]),Float.parseFloat(parameters[5]));
        return myConstruction;
    }
}
