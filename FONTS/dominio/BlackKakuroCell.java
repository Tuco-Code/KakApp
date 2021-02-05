package dominio;

/**
 * Representacion de una celda negra en un tablero de Kakuro.
 */


public class BlackKakuroCell extends KakuroCell {
    /**
     * Valor numerico que toma la restriccion de la columna correspondiente, -1 si no existe tal restriccion.
     */
    int sumCol;
    /**
     * Valor numerico que toma la restriccion de la fila correspondiente, -1 si no existe tal restriccion.
     */
    int sumRow;
    /**
     *  Cadena de caracteres que representa la restriccion de la casilla, en caso de no tener restriccion, es 'B'.
     */
    String restriction;


    /**
     * Creadora por defecto de la clase BlackKakuroCell.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de BlackKakuroCell con white = false, sumCol y sumRow a -1 y restriction='B'.
     * <p>
     * @custom.mytag3 Creadora por defecto de la clase BlackKakuroCell.
     * <p>
     */
    public BlackKakuroCell() {
        super(false);
        sumCol = -1;
        sumRow = -1;
        restriction = "B";
    }

    /**
     * Creadora de BlackKakuroCell con las restricciones definidas por el parametro de entrada.
     * @custom.mytag1 restriction es una restriccion valida. (CXFX,CX,FX,B donde X son numeros del 3-45).
     * <p>
     * @custom.mytag2 Nueva instancia de BlackKakuroCell con white = false, sumCol y sumRow son igual a la restriccion correspondiente a restriction y this.restriction= restriction.
     * <p>
     * @custom.mytag3 Creadora de BlackKakuroCell con las restricciones definidas por el parametro de entrada.
     * <p>
     * @param restriction String de una restriccion
     */
    public BlackKakuroCell(String restriction) {
        super(false);

        Pair p = colirow(restriction);
        this.restriction = restriction;
        sumCol = p.first();
        sumRow = p.second();
    }


    /**
     * Funcion que dada una restriccion en formato string devuelve un pair con los valores de las restricciones de columna i fila.
     * @custom.mytag1 rest es una restriccion valida. (CXFX,CX,FX,B donde X son numeros del 3-45).
     * <p>
     * @custom.mytag2 Se devuelve un pair con la restriccion de columna correspondiente en first, i de fila en second.
     * <p>
     * @custom.mytag3 Funcion que dada una restriccion en formato string devuelve un pair con los valores de las restricciones de columna i fila.
     * <p>
     * @param rest String de una restriccion.
     * @return Pair con la restriccion de columna correspondiente en first, i de fila en second.
     */
    private Pair colirow(String rest){
        int n = rest.length();
        if(n<=3){
            if(rest.startsWith("C")){
                int c = Integer.parseInt(rest.substring(1));
                Pair p = new Pair(c,-1);
                return p;
            }
            else if(rest.startsWith("B"))
            {
                Pair p = new Pair(-1,-1);
                return p;
            }
            else{
                int f = Integer.parseInt(rest.substring(1));
                Pair p = new Pair(-1,f);
                return p;
            }
        }
        else{
            int c;
            int f;
            if(rest.startsWith("C")){
                int i = rest.indexOf('F');
                c = Integer.parseInt(rest.substring(1,i));
                f = Integer.parseInt(rest.substring(i+1));

            }
            else{
                int i = rest.indexOf('C');
                c = Integer.parseInt(rest.substring(i+1));
                f = Integer.parseInt(rest.substring(1,i));
            }
            Pair p = new Pair(c,f);
            return p;
        }
    }


    /**
     * Devuelve la restriccion de columna en formato entero.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve la restriccion de columna en formato entero.
     * <p>
     * @custom.mytag3 Devuelve la restriccion de columna en formato entero.
     * <p>
     * @return Entero positivo entre 3-45.
     */
    public int getColumnRestriction(){
        return  sumCol;
    }

    /**
     * Devuelve la restriccion de fila en formato entero.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve la restriccion de fila en formato entero.
     * <p>
     * @custom.mytag3 Devuelve la restriccion de fila en formato entero.
     * <p>
     * @return Entero positivo entre 3-45.
     */
    public int getRowRestriction() {
        return sumRow;
    }

    /**
     * Devuelve una cadena de caracteres con las restricciones de la casilla.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve una cadena de caracteres con las restricciones de la casilla.
     * <p>
     * @custom.mytag3 Devuelve una cadena de caracteres con las restricciones de la casilla.
     * <p>
     * @return String de una restriccion.
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * Funcion que devuelve cierto si la casilla es de restriccion i falso en caso que contrario.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuleve cierto si sumRow o sumCol no son igual a -1, en caso contrario devuelve falso.
     * <p>
     * @custom.mytag3 Funcion que devuelve cierto si la casilla es de restriccion i falso en caso que contrario.
     * <p>
     * @return Boolean.
     */
    public boolean isRestriction(){

        return sumRow != -1 || sumCol != -1;
    }

    /**
     * Cambia el sumCol por el parametro pasado en la entrada.
     * @custom.mytag1 col es un entero que va de 3 a 45.
     * <p>
     * @custom.mytag2 sumCol = col.
     * <p>
     * @custom.mytag3 Cambia el sumCol por el parametro pasado en la entrada.
     * <p>
     * @param col Entero positivo entre 3-45.
     */
    public void setSumCol(int col) {
        sumCol = col;
    }

    /**
     * Cambia el sumRow por el parametro pasado en la entrada.
     * @custom.mytag1 row es un entero que va de 3 a 45.
     * <p>
     * @custom.mytag2 sumRow = row.
     * <p>
     * @custom.mytag3 Cambia el sumRow por el parametro pasado en la entrada.
     * <p>
     * @param row Entero positivo entre 3-45.
     */
    public void setSumRow(int row){
        sumRow = row;
    }

    /**
     * Cambia restriction por el parametro pasado en la entrada.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 restriction = rest.
     * <p>
     * @custom.mytag3 Cambia restriction por el parametro pasado en la entrada.
     * <p>
     * @param rest String de una restriccion.
     */

    public void setRestriction(String rest){
        restriction=rest;
        Pair p = colirow(rest);
        sumCol = p.first();
        sumRow = p.second();
    }

    /**
     * Funcion que genera la restriccion en formato String a partir de sumCol y sumRow.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 El valor devuelto es una string con las restricciones de la casilla, si no tiene devuelve 'B'.
     * <p>
     * @custom.mytag3 Funcion que genera la restriccion en formato String a partir de sumCol y sumRow.
     * <p>
     * @return String de una restriccion.
     */
    public String getSums(){
        String s= new String("");
        if(isRestriction()) {
            if(sumCol!=-1) s+="C"+sumCol;
            if(sumRow!=-1) s+="F"+sumRow;
        }
        else s=s+"B";
    return s;
    }
}
