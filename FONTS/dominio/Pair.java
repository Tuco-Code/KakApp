package dominio;

/**
 * Estructura de datos pair con integers.
 */

public class Pair {
    /**
     * Primer valor del Pair.
     */
    int first;
    /**
     * Segundo valor del Pair.
     */
    int second;

    /**
     * Creadora por defecto de Pair.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de Pair con valores a 0.
     * <p>
     * @custom.mytag3 Creadora por defecto de Pair.
     * <p>
     */
    public Pair(){
        first=0;
        second=0;
    }

    /**
     * Creadora de Pair con valores del parametero de entrada.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de Pair con first = f i second = s.
     * <p>
     * @custom.mytag3 Creadora de Pair con valores del parametero de entrada.
     * <p>
     * @param f Entero que representa el primer valor del Pair.
     * @param s Entero que representa el segundo valor del Pair.
     */
    public Pair(int f, int s){
        first=f;
        second=s;
    }

    /**
     * Devuelve el primer valor del Pair.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el primer valor del Pair.
     * <p>
     * @custom.mytag3 Devuelve el primer valor del Pair.
     * <p>
     * @return Entero que representa el primer valor del Pair.
     */
    public int first(){
        return first;
    }

    /**
     * Devuelve el segundo valor del Pair.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el segundo valor del Pair.
     * <p>
     * @custom.mytag3 Devuelve el segundo valor del Pair.
     * <p>
     * @return Entero que representa el segundo valor del Pair.
     */
    public int second(){
        return second;
    }

}
