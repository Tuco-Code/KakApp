package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Controlador de la capa de persistencia.
 */

public class ControladorPersistencia
{
    /**
     * La ruta relativa hasta el directorio que contiene los ficheros de persistencia.
     */
    String folder = "../../DATA/";

    /**
     * Constructor de la clase ControladorPersistencia:
     * Construye un controlador de persistencia.
     * No deberia crearse ni utilizarse mas de una instancia o podrian ocurrir problemas de concurrencia.
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 -
     * <p>
     * @custom.mytag3 Constructor de la clase Kakuro:
     * Constructor de la clase ControladorPersistencia:
     * Construye un controlador de persistencia.
     * No deberia crearse ni utilizarse mas de una instancia o podrian ocurrir problemas de concurrencia.
     */
    public ControladorPersistencia() {}

    /**
     * Lee el contenido de un archivo de texto localizado en la ruta del parametro y retorna su contenido dividiendolo por cada doble salto de linea.
     * @custom.mytag1 El parametro path corresponde a la ruta de un archivo de texto correctamente formateado.
     * <p>
     * @custom.mytag2 Se leido el contenido del archivo y dividido cada doble de salto de linea en un String independiente de un ArrayList de Strings.
     * <p>
     * @custom.mytag3 Lee el contenido de un archivo de texto localizado en la ruta del parametro y retorna su contenido dividiendolo por cada doble salto de linea.
     * @param path La ruta a un archivo de texto correctamente formateado.
     * @return El contenido del archivo, dividido cada doble de salto de linea en un String independiente de un ArrayList de Strings.
     */
    private ArrayList<String> load(String path)
    {
        try {
            String text = Files.readString(Path.of(path));
            String textlines[] = text.split("\n\n");
            ArrayList<String> al = new ArrayList<>(Arrays.asList(textlines));

            return al;
        } catch (Exception e) {
            System.out.println("An error has occurred");
            ArrayList<String> empty = new ArrayList<>();
            return empty;
        }
    }

    /**
     * Elimina un archivo de texto localizado en la ruta del parametro.
     * @custom.mytag1 El parametro path corresponde a la ruta de un archivo de texto correctamente formateado.
     * <p>
     * @custom.mytag2 Se ha eliminado el archivo en la ruta especificada.
     * <p>
     * @custom.mytag3 Elimina un archivo de texto localizado en la ruta del parametro.
     * @param path La ruta a un archivo de texto correctamente formateado.
     */
    private void delete(String path)
    {
        File file = new File(path);
        file.delete();
    }

    /**
     * Anaade el contenido del parametro line al final del archivo que se encuentra en el parametro path.
     * @custom.mytag1 El parametro path corresponde a la ruta de un archivo de texto correctamente formateado.
     * <p>
     * @custom.mytag2 Se ha anadido al final del archivo especificado en la ruta path la linea line.
     * <p>
     * @custom.mytag3 Anaade el contenido del parametro line al final del archivo que se encuentra en el parametro path.
     * @param path La ruta a un archivo de texto correctamente formateado.
     * @param line La linea a anadir al final del archivo de texto
     */
    private static void save(String path, String line)
    {
        try {
            FileWriter myWriter = new FileWriter(path, true);
            myWriter.write(line);
            myWriter.write("\n\n");
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Lee el contenido de un archivo de texto localizado en la ruta del parametro y retorna su contenido.
     * @custom.mytag1 El parametro path corresponde a la ruta de un archivo de texto correctamente formateado.
     * <p>
     * @custom.mytag2 Se ha leido el contenido del archivo se retorna en una String.
     * <p>
     * @custom.mytag3 Lee el contenido de un archivo de texto localizado en la ruta del parametro y retorna su contenido.
     * @param path La ruta a un archivo de texto correctamente formateado.
     * @return Los contenidos del archivo de texto.
     * @throws IOException En caso de no existir el archivo o ocurrir algun problema durante su apertura.
     */
    public String loadFileContent(String path) throws IOException {
        String text = Files.readString(Path.of(path));
        return text.trim();
    }

    /**
     * Lee todos los perfiles guardados y retorna el conjunto de Strings que los identifican
     * @custom.mytag1 Existe el archivo de persistencia de perfiles.
     * <p>
     * @custom.mytag2 Se ha leido el contenido del archivo se retorna en ArrayList de Strings, cads String contiene el contenido de un perfil.
     * <p>
     * @custom.mytag3 Lee todos los perfiles guardados y retorna el conjunto de Strings que los identifican.
     * @return Un ArrayList de Strings, cads String contiene el contenido de un perfil.
     */
    public ArrayList<String> loadProfiles()
    {
        try {
            return load(folder + "profiles");
        } catch (Exception e) {
            System.out.println("An error has occurred");
            ArrayList<String> empty = new ArrayList<>();
            return empty;
        }
    }

    /**
     * Lee todos los kakuros guardados y retorna el conjunto de Strings que los identifican
     * @custom.mytag1 Existe el archivo de persistencia de kakuros.
     * <p>
     * @custom.mytag2 Se ha leido el contenido del archivo se retorna en ArrayList de Strings, cads String contiene el contenido de un kakuro.
     * <p>
     * @custom.mytag3 Lee todos los kakuros guardados y retorna el conjunto de Strings que los identifican.
     * @return Un ArrayList de Strings, cads String contiene el contenido de un kakuro.
     */
    public ArrayList<String> loadKakuros()
    {
        try {
            return load(folder + "kakuros");
        } catch (Exception e) {
            System.out.println("An error has occurred");
            ArrayList<String> empty = new ArrayList<>();
            return empty;
        }
    }

    /**
     * Lee todos las estadisticas de kakuro guardadas y retorna el conjunto de Strings que las identifican
     * @custom.mytag1 Existe el archivo de persistencia de estadisticas de kakuros.
     * <p>
     * @custom.mytag2 Se ha leido el contenido del archivo se retorna en ArrayList de Strings, cads String contiene el contenido de una estadistica de kakuro.
     * <p>
     * @custom.mytag3 Lee todos las estadisticas de kakuro guardadas y retorna el conjunto de Strings que las identifican
     * @return Un ArrayList de Strings, cads String contiene el contenido de una estadistica de kakuro.
     */
    public ArrayList<String> loadStats()
    {
        try {
            return load(folder + "stats");
        } catch (Exception e) {
            System.out.println("An error has occurred");
            ArrayList<String> empty = new ArrayList<>();
            return empty;
        }
    }

    /**
     * Lee todos los juegos guardados y retorna el conjunto de Strings que los identifican
     * @custom.mytag1 Existe el archivo de persistencia de juegos.
     * <p>
     * @custom.mytag2 Se ha leido el contenido del archivo se retorna en ArrayList de Strings, cads String contiene el contenido de un juego.
     * <p>
     * @custom.mytag3 Lee todos los juegos guardados y retorna el conjunto de Strings que los identifican.
     * @return Un ArrayList de Strings, cads String contiene el contenido de un juego.
     */
    public ArrayList<String> loadGames()
    {
        try {
            return load(folder + "games");
        } catch (Exception e) {
            System.out.println("An error has occurred");
            ArrayList<String> empty = new ArrayList<>();
            return empty;
        }
    }

    /**
     * Dado el conjunto de Strings que identifican un conjunto de perfiles, almacena estos en su archivo de persistencia.
     * @custom.mytag1 El contenido de la ArrayList esta correctamente inicializado para representar perfiles.
     * <p>
     * @custom.mytag2 Se ha guardado el contenido de la ArrayList en el archivo de persistencia de perfiles.
     * <p>
     * @custom.mytag3 Dado el conjunto de Strings que identifican un conjunto de perfiles, almacena estos en su archivo de persistencia.
     * @param profiles Un ArrayList de Strings que contiene tantos Strings como perfiles se quieren almacenar.
     * @return True si el proceso de guardado ha sido correcto, False en caso contrario.
     */
    public boolean saveProfiles(ArrayList<String> profiles)
    {
        try {
            Set<String> profileNames = new HashSet<String>();

            for (int i = 0; i < profiles.size(); ++i) {
                String[] words = profiles.get(i).split(" ");
                profileNames.add(words[0]);
            }

            if (profileNames.size() != profiles.size()) return false;

            if (profiles.size() > 10) {
                System.out.println("Limit on the number of profiles reached");
                return false;
            }
            delete(folder + "profiles");

            for (int i = 0; i < profiles.size(); ++i) {
                save(folder + "profiles", profiles.get(i));
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error has occurred");
            return false;
        }
    }

    /**
     * Dado el conjunto de Strings que identifican un conjunto de kakuros, almacena estos en su archivo de persistencia.
     * @custom.mytag1 El contenido de la ArrayList esta correctamente inicializado para representar kakuros.
     * <p>
     * @custom.mytag2 Se ha guardado el contenido de la ArrayList en el archivo de persistencia de kakuros.
     * <p>
     * @custom.mytag3 Dado el conjunto de Strings que identifican un conjunto de kakuros, almacena estos en su archivo de persistencia.
     * @param kakuros Un ArrayList de Strings que contiene tantos Strings como kakuros a almacenar.
     * @return True si el proceso de guardado ha sido correcto, False en caso contrario.
     */
    public boolean saveKakuros(ArrayList<String> kakuros)
    {
        try {
            Set<String> kakuroCodes = new HashSet<String>();

            for (int i = 0; i < kakuros.size(); ++i) {
                String[] words = kakuros.get(i).split(" ");
                kakuroCodes.add(words[0]);
            }

            if (kakuroCodes.size() != kakuros.size()) return false;

            delete(folder + "kakuros");

            for (int i = 0; i < kakuros.size(); ++i) {
                save(folder + "kakuros", kakuros.get(i));
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error has occurred");
            return false;
        }
    }

    /**
     * Dado el conjunto de Strings que identifican un conjunto de estadisticas de Kakuro, almacena estos en su archivo de persistencia.
     * @custom.mytag1 El contenido de la ArrayList esta correctamente inicializado para representar estadisticas de kakuro.
     * <p>
     * @custom.mytag2 Se ha guardado el contenido de la ArrayList en el archivo de persistencia de estadisticas de kakuro.
     * <p>
     * @custom.mytag3 Dado el conjunto de Strings que identifican un conjunto de estadisticas de kakuro, almacena estos en su archivo de persistencia.
     * @param stats Un ArrayList de Strings que contiene tantos Strings como estadisticas a almacenar.
     * @return True si el proceso de guardado ha sido correcto, False en caso contrario.
     */
    public boolean saveStats(ArrayList<String> stats)
    {
        try {
            Set<String> kakuroCodes = new HashSet<String>();

            for (int i = 0; i < stats.size(); ++i) {
                String[] words = stats.get(i).split(" ");
                kakuroCodes.add(words[0]);
            }

            if (kakuroCodes.size() != stats.size()) return false;

            delete(folder + "stats");

            for (int i = 0; i < stats.size(); ++i) {
                save(folder + "stats", stats.get(i));
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error has occurred");
            return false;
        }
    }

    /**
     * Dado el conjunto de Strings que identifican un conjunto de juegos, almacena estos en su archivo de persistencia.
     * @custom.mytag1 El contenido de la ArrayList esta correctamente inicializado para representar juegos.
     * <p>
     * @custom.mytag2 Se ha guardado el contenido de la ArrayList en el archivo de persistencia de juegos.
     * <p>
     * @custom.mytag3 Dado el conjunto de Strings que identifican un conjunto de juegos, almacena estos en su archivo de persistencia.
     * @param games Un ArrayList de Strings que contiene tantos Strings como juegos a almacenar.
     * @return True si el proceso de guardado ha sido correcto, False en caso contrario.
     */
    public boolean saveGames(ArrayList<String> games)
    {
        try {

            delete(folder + "games");

            for (int i = 0; i < games.size(); ++i) {
                save(folder + "games", games.get(i));
            }
            return true;
        } catch (Exception e) {
            System.out.println("An error has occurred");
            return false;
        }
    }
}