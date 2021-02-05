package dominio;

/**
 * Estructura de datos pair con integers.
 */

public class Triple {
    /**
     * Primer valor de Triple.
     */
    int first;
    /**
     * Segundo valor de Triple.
     */
    int second;

    /**
     * Tercer valor de Triple.
     */
    int third;

    /**
     * Creadora por defecto de Triple.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de Triple con valores a 0.
     * <p>
     * @custom.mytag3 Creadora por defecto de Triple.
     * <p>
     */
    public Triple(){
        first=0;
        second=0;
        third=0;
    }

    /**
     * Creadora de Triple con valores del parametero de entrada.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de Triple con first = f, second = s y third = t.
     * <p>
     * @custom.mytag3 Creadora de Triple con valores del parametero de entrada.
     * <p>
     * @param f Entero que representa el primer valor de Triple.
     * @param s Entero que representa el segundo valor de Triple.
     * @param t Entero que representa el tercer valor de Triple.
     */
    public Triple(int f, int s,int t){
        first=f;
        second=s;
        third = t;
    }

    /**
     * Devuelve el primer valor de Triple.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el primer valor de Triple.
     * <p>
     * @custom.mytag3 Devuelve el primer valor de Triple.
     * <p>
     * @return Entero que representa el primer valor de Triple.
     */
    public int first(){
        return first;
    }

    /**
     * Devuelve el segundo valor de Triple.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el segundo valor de Triple.
     * <p>
     * @custom.mytag3 Devuelve el segundo valor de Triple.
     * <p>
     * @return Entero que representa el segundo valor de Triple.
     */
    public int second(){
        return second;
    }

    /**
     * Devuelve el tercer valor de Triple.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el tercer valor de Triple.
     * <p>
     * @custom.mytag3 Devuelve el tercer valor de Triple.
     * <p>
     * @return Entero que representa el tercer valor de Triple.
     */
    public int third(){
        return third;
    }

}
