package dominio;

/**
 *Representacion de un tablero de kakuro.
 */

public class Kakuro {
    /**
     * El identificador unico de un kakuro.
     */
    private String code;
    /**
     * La modelizacion de la dificultad de resolver el tablero
     */
    private Difficulty kakuroDifficulty = Difficulty.EASY;
    /**
     * El origen del tablero en el programa
     */
    private Source source = Source.ORIGINAL;
    /**
     * La representacion matricial del tablero de un Kakuro.
     */
    private KakuroCell[][] board;

    /**
     * Constructor de la clase Kakuro:
     * Construye una instancia de Kakuro dada la informacion de su tablero.
     * El argumento rawBoard debe ser un String correctamente formateado segun los criterios de la asignatura para definir un Kakuro.
     * Dado una String definiendo un kakuro el constructor retorna una instancia de Kakuro con sus parametros adecuadamente inicializados por la informacion del parametro rawBoard.
     * @custom.mytag1 rawBoard es un String correctamente formateado para definir un Kakuro.
     * <p>
     * @custom.mytag2 Una instancia de la clase Kakuro ha sido creada, se le ha asignado un identificador unico y se ha generado la representacion de su tablero.
     * <p>
     * @custom.mytag3 Constructor de la clase Kakuro:
     * Construye una instancia de Kakuro dada la informacion de su tablero.
     * El argumento rawBoard debe ser un String correctamente formateado segun los criterios de la asignatura para definir un Kakuro.
     * Dado una String definiendo un kakuro el constructor retorna una instancia de Kakuro con sus parametros adecuadamente inicializados por la informacion del parametro rawBoard.
     * <p>
     * @param  rawBoard  Un kakuro correctamente formateado.
     */
    public Kakuro(String rawBoard)
    {
        code = rawBoard;
        fillBoards(rawBoard);
    }

    /**
     * Constructor de la clase Kakuro:
     * Construye una instancia de Kakuro dada la informacion de su tablero, su dificultad y su origen en la aplicacion.
     * El argumento rawBoard debe ser un String correctamente formateado segun los criterios de la asignatura para definir un Kakuro.
     * Dado una String definiendo un kakuro el constructor retorna una instancia de Kakuro con sus parametros adecuadamente inicializados por la informacion del parametro rawBoard.
     * @custom.mytag1 rawBoard es un String correctamente formateado para definir un Kakuro.
     * <p>
     * @custom.mytag2 Una instancia de la clase Kakuro ha sido creada, se le ha asignado un identificador unico y se ha generado la representacion de su tablero.
     * <p>
     * @custom.mytag3 Constructor de la clase Kakuro:
     * Construye una instancia de Kakuro dada la informacion de su tablero.
     * El argumento rawBoard debe ser un String correctamente formateado segun los criterios de la asignatura para definir un Kakuro.
     * Dado una String definiendo un kakuro el constructor retorna una instancia de Kakuro con sus parametros adecuadamente inicializados por la informacion del parametro rawBoard.
     * <p>
     * @param  rawBoard  Un kakuro correctamente formateado.
     * @param dif La dificultad del Kakuro.
     * @param source El origen del Kakuro en la aplicacion.
     */
    public Kakuro(String rawBoard, Difficulty dif, Source source)
    {
        code = rawBoard;
        fillBoards(rawBoard);
        kakuroDifficulty = dif;
        this.source = source;
    }

    /**
     * Constructor copiador de la clase Kakuro:
     * Construye una instancia de Kakuro inicializando sus campos con una copia por valor de los datos de otro Kakuro
     * @custom.mytag1 kakuro es un Kakuro inicializado.
     * <p>
     * @custom.mytag2 Una instancia de la clase Kakuro ha sido creada, sus atributos son una copia por valor de los del kakuro pasado como parametro.
     * <p>
     * @custom.mytag3 * Constructor copiador de la clase Kakuro:
     * Construye una instancia de Kakuro inicializando sus campos con una copia por valor de los datos de otro Kakuro
     * @param kakuro
     */
    public Kakuro(Kakuro kakuro)
    {
        code = kakuro.code;

        String boardLines[] = code.split("\n");
        String sizes[] = boardLines[0].split(",");
        int boardWidth = Integer.parseInt(sizes[0]);
        int boardHeight = Integer.parseInt(sizes[1]);

        board = new KakuroCell[boardWidth][boardHeight];
        kakuro.copyBoard(board);

        kakuroDifficulty = kakuro.kakuroDifficulty;
        source = kakuro.source;
    }

    /**
     * Este metodo inicializa el atributo board de una instancia de Kakuro.
     * Dado un kakuro correctamente formateado como un string.
     * El atributo board se inicializa con la correspondiente configuracion espacial de las celdas del kakuro.
     * @custom.mytag1 rawBoard es un String correctamente formateado para definir un Kakuro.
     * <p>
     * @custom.mytag2 El objeto board del kakuro sobre el que se llama esta funcion se ha rellenado conforme a la informacion que contenia rawBoard.
     * <p>
     * @custom.mytag3 Este metodo inicializa el atributo board de una instancia de Kakuro.
     * Dado un kakuro correctamente formateado como un string.
     * El atributo board se inicializa con la correspondiente configuracion espacial de las celdas del kakuro.
     * <p>
     * @param  rawBoard  Un kakuro correctamente formateado.
     */
    public void fillBoards(String rawBoard)
    {
        String boardLines[] = rawBoard.split("\n");
        String sizes[] = boardLines[0].split(",");
        int boardWidth = Integer.parseInt(sizes[0]);
        int boardHeight = Integer.parseInt(sizes[1]);

        board = new KakuroCell[boardWidth][boardHeight];

        for(int i = 1; i <= boardHeight; i++)
        {
            int jMatrix = 0;
            for (int j = 0; j < boardLines[i].length(); ++j)
            {
                if (boardLines[i].charAt(j) == '*')
                {
                    board[i-1][jMatrix] = new BlackKakuroCell();
                    jMatrix++;
                }

                else if (boardLines[i].charAt(j) == '?')
                {
                    board[i-1][jMatrix] = new WhiteKakuroCell();
                    jMatrix++;
                }

                else if (boardLines[i].charAt(j) == 'C' || boardLines[i].charAt(j) == 'F')
                {
                    j = addRestriction(rawBoard,i,j,jMatrix);
                    jMatrix++;
                }
                else if (boardLines[i].charAt(j) != ','){
                    int value = Integer.parseInt(boardLines[i].substring(j, j+1));
                    board[i-1][jMatrix] = new WhiteKakuroCell(value);
                    jMatrix++;
                }
            }
        }
    }


    /**
     * Lee una restriccion del string dadas las coordenadas en el string de donde empieza esta, una vez leido inicializa en la posicion correspondiente del objeto board dicha restriccion.
     * @custom.mytag1 Considerando el string RawBoard como una matriz de casillas de kakuro, en la posicion i,j empieza una restriccion de fila, columna o ambas.
     * <p>
     * @custom.mytag2 La restriccion ha sido anadida al objeto board del kakuro sobre el que se llama la funcion en la posicion (i-1),jMatrix.
     * <p>
     * @custom.mytag3 Lee una restriccion del string dadas las coordenadas en el string de donde empieza esta, una vez leido inicializa en la posicion correspondiente del objeto board dicha restriccion.
     * <p>
     * @param rawBoard Una string que define un kakuro correctamente formateado.
     * @param i Linea del string en la que se encuentra el inicio de la restriccion.
     * @param j Columna del string en la que se encuentra el inicio de la restriccion.
     * @param jMatrix Columna de board en la que se introducira la restriccion.
     * @return El valor de j incrementado tantas veces como caracteres tenia la restriccion tratada.
     */
    public int addRestriction(String rawBoard, int i, int j, int jMatrix)
    {
        int jj = j;
        String boardLines[] = rawBoard.split("\n");

        while(jj < boardLines[i].length() && boardLines[i].charAt(jj) != ',')
        {
            ++jj;
        }
        String restriction = boardLines[i].substring(j,jj);
        board[i-1][jMatrix] = new BlackKakuroCell(restriction);
        return jj;
    }

    /**
     * Devuelve el valor del codigo identificador del kakuro.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Retorna el codigo identificador del objeto kakuro sobre el que se llama la funcion.
     * <p>
     * @custom.mytag3 Devuelve el valor del codigo identificador del kakuro.
     * <p>
     * @return Un String que contiene el codigo identificador de un kakuro.
     */
    public String getCode() {return code;}

    /**
     * Devuelve la dificultad del kakuro.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Retorna el valor de la dificultad del objeto kakuro sobre el que se llama la funcion.
     * <p>
     * @custom.mytag3 Devuelve el valor de la dificultad del kakuro.
     * <p>
     * @return Una Dificultad que contiene el valor de la dificultad de un kakuro.
     */
    public Difficulty getDifficulty() {return kakuroDifficulty;}

    /**
     * Devuelve el origen del kakuro.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Retorna el origen del objeto kakuro sobre el que se llama la funcion.
     * <p>
     * @custom.mytag3 Devuelve el origen del kakuro.
     * <p>
     * @return Un Source que contiene el origen de un kakuro.
     */
    public Source getSource() {return source;}

    /**
     * Devuelve el tablero del kakuro.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Retorna el objeto que representa el tablero del objeto kakuro sobre el que se llama la funcion.
     * <p>
     * @custom.mytag3 Devuelve el tablero del kakuro.
     * <p>
     * @return Retorna la estructura de datos utilizada para representar un tablero de kakuro.
     */
    public KakuroCell[][] getBoard() {return board;}

    /**
     * Intercambia el tablero actual del objeto por el tablero del parametro newboard.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado and newboard is Un kakuro correctamente formateado board.
     * <p>
     * @custom.mytag2 El tablero del objeto sobre el que se llama la funcion pasa a ser newboard.
     * <p>
     * @custom.mytag3 Intercambia el tablero actual del objeto por el tablero del parametro newboard.
     * <p>
     * @param newboard Un tablero de kakuro correctamente inicializado.
     */
    public void setBoard(KakuroCell[][] newboard) {board = newboard;}

    /**
     * Copia el tablero actual del objeto en el tablero del parametro copyBoard.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado and copyBoard es un tablero de kakuro con las mismas dimensiones que el board del kakuro.
     * <p>
     * @custom.mytag2 El tablero del objeto sobre el que se llama la funcion se ha copiado en copyBoard.
     * <p>
     * @custom.mytag3 Copia el tablero actual del objeto en el tablero del parametro copyBoard.
     * <p>
     * @param copyBoard Tablero donde se copiara el tablero del kakuro que ha llamado a la funcion. Debe tener las mismas dimensiones que el kakuro que llamo a la funcion.
     */
    public void copyBoard(KakuroCell[][] copyBoard)
    {
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof BlackKakuroCell)
                {
                    copyBoard[i][j] = new BlackKakuroCell(((BlackKakuroCell) board[i][j]).getRestriction());
                }
                else
                {
                    copyBoard[i][j] = new WhiteKakuroCell(((WhiteKakuroCell) board[i][j]).getDigit());
                }
            }
        }
    }

    /**
     * Retorna true si el board del objeto y el el board del parametro contienen los mismos valores, falso en caso contrario.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado y mirrorBoard es un tablero de kakuro con las mismas dimensiones que el board del kakuro.
     * <p>
     * @custom.mytag2 Se ha comprobado si el board el objeto Kakuro sobre el que se ha llamado la funcion y el newBoard pasado como parametro contienen los mismos valores.
     * <p>
     * @custom.mytag3 Retorna true si el board del objeto y el el board del parametro contienen los mismos valores, falso en caso contrario.
     * <p>
     * @param mirrorBoard Una estructura de datos que contiene la representacion de un tablero de kakuro.
     * @return Retorna true si el board del objeto y el el board del parametro contienen los mismos valores, falso en caso contrario.
     */
    public boolean isSameBoard(KakuroCell[][] mirrorBoard)
    {
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof BlackKakuroCell && mirrorBoard[i][j] instanceof BlackKakuroCell)
                {
                    if(!((BlackKakuroCell) board[i][j]).getRestriction().equals(((BlackKakuroCell) mirrorBoard[i][j]).getRestriction()))
                        return false;
                }
                else if (board[i][j] instanceof WhiteKakuroCell && mirrorBoard[i][j] instanceof WhiteKakuroCell)
                {
                    if(((WhiteKakuroCell) board[i][j]).getDigit() != ((WhiteKakuroCell) mirrorBoard[i][j]).getDigit())
                        return false;
                }
                else
                    return false;
            }
        }
        return true;
    }

    /**
     * Retorna un String que codifica el contenido del tablero de kakuro en el formato utilizado en el Jutge de Kakuros.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Se ha retornado el String que codifica el estado actual del tablero.
     * <p>
     * @custom.mytag3 Retorna un String que codifica el contenido del tablero de kakuro en el formato utilizado en el Jutge de Kakuros.
     * <p>
     * @return String en el formato aceptado por el Jutge de kakuros codificando el estado actual de un kakuro.
     */
    public String printJutgeString() {
        String result = "";
        result += (board.length + "," + board[0].length + "\n");
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof BlackKakuroCell)
                {
                    if (((BlackKakuroCell) board[i][j]).isRestriction())
                        result+=(((BlackKakuroCell) board[i][j]).getRestriction());
                    else
                        result +=("*");
                }
                else
                    result +=(((WhiteKakuroCell) board[i][j]).getDigit());

                if (j != board[0].length - 1)
                    result += (",");
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Retorna un String que codifica el contenido del tablero de kakuro en el formato utilizado en el Jutge de Kakuros.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Se ha retornado el String que codifica el estado actual del tablero.
     * <p>
     * @custom.mytag3 Retorna un String que codifica el contenido del tablero de kakuro en el formato utilizado en el Jutge de Kakuros.
     * <p>
     * @return String en el formato aceptado por el Jutge de kakuros codificando el estado actual de un kakuro.
     */
    public String printActualString() {
        String result = "";
        result += (board.length + "," + board[0].length + "\n");
        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof BlackKakuroCell)
                {
                    if (((BlackKakuroCell) board[i][j]).isRestriction())
                        result+=(((BlackKakuroCell) board[i][j]).getRestriction());
                    else
                        result +=("*");
                }
                else {
                    if (((WhiteKakuroCell) board[i][j]).getDigit() != 0)
                        result +=(((WhiteKakuroCell) board[i][j]).getDigit());
                    else
                        result += "?";
                }

                if (j != board[0].length - 1)
                    result += (",");
            }
            result += "\n";
        }
        return result;
    }

    public void addValue(int i, int j, int Value){
        ((WhiteKakuroCell)board[i][j]).setDigit(Value);
        code = codi();
    }

    public String codi(){
        int tam = board.length;
        String result = tam+","+tam+"\n";
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < tam; j++) {
                if (board[i][j].isWhite()) {
                    int val = ((WhiteKakuroCell)board[i][j]).getDigit();
                    if (val!=0) result +=val;
                    else result += '?';
                } else if (!((BlackKakuroCell) board[i][j]).isRestriction()) {
                    result += '*';
                } else {
                    if (((BlackKakuroCell) board[i][j]).getColumnRestriction() != -1) {
                        result += 'C';
                        result += ((BlackKakuroCell) board[i][j]).getColumnRestriction();
                    }
                    if (((BlackKakuroCell) board[i][j]).getRowRestriction() != -1) {
                        result += 'F';
                        result += ((BlackKakuroCell) board[i][j]).getRowRestriction();
                    }
                }
                if (j != tam - 1) result += ",";
            }
            result += "\n";
        }
        result = (String) result.subSequence(0,result.length()-1);
        return result;
    }

}

/**
 * Esta enumeracion representa los posibles valores de dificultad que puede tomar la resolucion de un kakuro.
 */
enum Difficulty
{
    /**
     * Facil
     */
    EASY,

    /**
     * Medio
     */
    MEDIUM,

    /**
     * Dificil
     */
    HARD;
}

/**
 * Esta enumeracion representa el origen de un Kakuro en la aplicacion.
 */
enum Source
{
    /**
     * El Kakuro se encuentra en la version original de la aplicacion, fue introducido por los desarrolladores.
     */
    ORIGINAL,

    /**
     * El Kakuro se ha generado automaticamente bajo la peticion de un usuario.
     */
    GENERATED,

    /**
     * El kakuro ha sido introducido manualmente por un usuario mediante el editor de texto o un archivo.
     */
    USER;
}
