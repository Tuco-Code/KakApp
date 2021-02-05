package dominio;

/**
 * Representacion abstracta de una celda en un tablero de Kakuro.
 */

public class KakuroCell {

    /**
     * Valor booleano que indica si la casilla es blanca, white = true, o si es negra, white = false.
     */
    private boolean white;

    /**
     * Creadora de KakuroCell con parametro d'entrada un bool que representa el color.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Nueva instancia de KakuroCell con this.white = white.
     * <p>
     * @custom.mytag3 Creadora de KakuroCell con parametro d'entrada un bool que representa el color.
     * <p>
     * @param white true si la casilla es blanca y false si es una casilla negra.
     */
    protected KakuroCell(boolean white){
        this.white = white;
    }

    /**
     * Devuelve el true si la casilla es blanca, en caso contrario, false.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Devuelve el true si la casilla es blanca, en caso contrario, false.
     * <p>
     * @custom.mytag3 Devuelve el true si la casilla es blanca, en caso contrario, false.
     * <p>
     * @return True si es una casilla blanca o false si es una casilla negra.
     */
    public boolean isWhite(){
        return white;
    }

}
