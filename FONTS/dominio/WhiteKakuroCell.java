package dominio;

import java.util.*;

/**
 *Representacion  de una celda blanca en un tablero de Kakuro
 */

public class WhiteKakuroCell extends KakuroCell {
    /**
     * Valor numerico que toma la celda blanca
     */
    int digit;
    /**
     * Conjunto de valores posibles que puede tomar una celda blanca siguiendo las reglas lógicas de un kakuro
     */
    Set<Integer> possibleValues = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    /**
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 Se ha creado una instancia de casilla de kakuro blanca con valor 0
     * <p>
     * @custom.mytag3 Crea una casilla de kakuro blanca sin inicializar
     * <p>
     */
    public WhiteKakuroCell(){
        super(true);
        digit = 0;
    }

    /**
     * @custom.mytag1 El parametro dg es un digito entero no negativo
     * <p>
     * @custom.mytag2 Se ha creado una instancia de casilla de kakuro blanca con valor dg
     * <p>
     * @custom.mytag3 Crea una casilla de kakuro blanca con valor dg
     * <p>
     * @param dg Un entero no negativo que representa el valor que contendra la casilla creada
     */
    public WhiteKakuroCell(int dg){
        super(true);
        digit = dg;
    }

    /**
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 Se ha devuelto el valor de la casilla blanca sobre la que se llamo la funcion
     * <p>
     * @custom.mytag3 Devuelve el valor contenido en la casilla blanca
     * <p>
     * @return El digito contenido en la casilla blanca, 0 si no esta inicializada
     */
    public int getDigit(){
        return  digit;
    }

    /**
     * @custom.mytag1 El parametro dg es un digito entero no negativo
     * <p>
     * @custom.mytag2 El valor de la casilla blanca ha pasado a ser el contenido en dg
     * <p>
     * @custom.mytag3 Cambia el valor de la casilla por el valor del parametro
     * <p>
     * @param dg Un digito entero no negativo
     */
    public void setDigit(int dg) {
        digit = dg;
    }

    /**
     * @custom.mytag1 -
     * <p>
     * @custom.mytag2 Devuelve el conjunto de valores posibles de la casillas blanca sobre la que se llama el objeto
     * <p>
     * @custom.mytag3 Devuelve el conjunto de valores posibles de la casillas blanca sobre la que se llama el objeto
     * <p>
     * @return El conjunto de valores posibles de la casillas blanca sobre la que se llama el objeto
     */
    public Set<Integer> getPossibleValues() {
        return possibleValues;
    }


    /**
     * @custom.mytag1 sol es un conjunto de digitos enteros no negativos
     * <p>
     * @custom.mytag2 El conjunto de valores posibles para la celda blanca ha pasado a ser la interseccion entre los valores posibles de la celda y los del parametro
     * <p>
     * @custom.mytag3 Intersecciona los valores posibles para la celda blanca con los del parametro
     * <p>
     * @param sol Un conjunto de digitos enteros no negativos
     */
    public void setPossibleValues(Set<Integer> sol)
    {
        possibleValues.retainAll(sol);
    }


    /**
     * @custom.mytag1 sol es un digito entero no negativo
     * <p>
     * @custom.mytag2 El conjunto de valores posibles para la celda blanca ha pasado a ser la interseccion entre los valores posibles de la celda y el del parametro
     * <p>
     * @custom.mytag3 Intersecciona los valores posibles para la celda blanca con los del parametro
     * <p>
     * @param value Un digito entero no negativo
     */
    public void addPossibleValue(Integer value)
    {
        possibleValues.add(value);
    }

    /**
     * @custom.mytag1 sol es un digito entero no negativo
     * <p>
     * @custom.mytag2 Se elimina el valor del parametro del conjunto de valores posibles de la celda
     * <p>
     * @custom.mytag3 Elimina un valor posible de una celda blanca
     * <p>
     * @param value Un digito entero no negativo
     */
    public void delPossibleValue(Integer value)
    {
        possibleValues.remove(value);
    }
}
