package dominio;

import java.util.*;

/**
 * Esta clase se utiliza como repositorio de los algoritmos mas importantes y de mayor peso.
 */

public class Algorithms
{


    /**
     * La representacion matricial de un tablero de un  kakuro.
     * Usado exclusivamente para generar el tablero.
     */
    private KakuroCell[][] board;

    /**
     * Atributo usado para tener una referencia del numero de soluciones de un kakuro.
     * Su uso es exclusivo para las funciones de solucionar kakuros.
     */
    private int sol;

    /**
     * Constructor de la clase Algorithms. Inicializamos el numero de soluciones a 0 y dejamos el tablero vacio.
     * @custom.mytag1 True.
     * <p>
     * @custom.mytag2 Creamos una instancia de los posibles algoritmos para poder utilizarlos.
     * <p>
     * @custom.mytag3 Constructor de la clase Algorithms. Inicializamos el numero de soluciones a 0 y dejamos el tablero vacio.
     * <p>
     */
    public Algorithms()
    {
        sol = 0;
    }

    //////////////////////////////////////////
    //////___SOLVER___////////////////////////
    //////////////////////////////////////////

    /**
     * Eliminamos el elemento value de los valores posibles de las casillas que comparten fila y columna de la casilla i,j.
     * @custom.mytag1 Kakuro debe ser un Kakuro correctamente formateado, el valor de los enteros i,j deben estar entre 0 i la dimension del tablero; value
     * es el valor que hemos puesto en la posicion board[i][j].
     * <p>
     * @custom.mytag2 Quitamos el valor value de los valores posibles a todas las casillas que comparten fila o columna con la casilla i,j.
     * <p>
     * @custom.mytag3 Eliminamos el elemento value de los valores posibles de las casillas que comparten fila o columna de la casilla i,j.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado.
     * @param i Primer valor que define la posicion de una casilla blanca.
     * @param j Segundo valor que defina la posicion de una casilla blanca.
     * @param value Valor colocado en la casilla blanca i,j; y que quitaremos de los valores posibles de las casillas que comparten fila o columna con la casilla i,j.
     */
    private void removePossibilities(Kakuro kakuro, Integer i, Integer j, Integer value)
    {
        KakuroCell[][] board = kakuro.getBoard();
        //ROW
        int itJ = j.intValue() + 1;
        if (itJ >= board[0].length) return;

        while(itJ < board[0].length && board[i][itJ] instanceof WhiteKakuroCell)
        {
            ((WhiteKakuroCell) board[i][itJ]).delPossibleValue(value);
            ++itJ;
        }

        //COLUMN
        int itI = i.intValue() + 1;
        if (itI >= board.length) return;

        while(itI < board.length && board[itI][j] instanceof WhiteKakuroCell)
        {
            ((WhiteKakuroCell) board[itI][j]).delPossibleValue(value);
            ++itI;
        }
    }

    /**
     * Ponemos el elemento value en los valores posibles de las casillas que comparten fila o columna de la casilla i,j.
     * @custom.mytag1 Kakuro debe ser un Kakuro correctamente formateado, el valor de los enteros i,j deben estar entre 0 i la dimension del tablero; value
     * es el valor que hemos puesto en la posicion board[i][j].
     * <p>
     * @custom.mytag2 Ponemos el valor value en los valores posibles a todas las casillas que comparten fila o columna con la casilla i,j.
     * <p>
     * @custom.mytag3 Ponemos el elemento value en los valores posibles de las casillas que comparten fila o columna de la casilla i,j.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado.
     * @param i Primer valor que define la posicion de una casilla blanca.
     * @param j Segundo valor que defina la posicion de una casilla blanca.
     * @param value Valor colocado en la casilla blanca i,j; y que pondremos en los valores posibles de las casillas que comparten fila o columna con la casilla i,j.
     */
    private void recoverPossibilities(Kakuro kakuro, Integer i, Integer j, Integer value)
    {
        KakuroCell[][] board = kakuro.getBoard();
        //ROW
        int itJ = j.intValue() + 1;
        if (itJ >= board[0].length) return;

        while(itJ < board[0].length && board[i][itJ] instanceof WhiteKakuroCell)
        {
            ((WhiteKakuroCell) board[i][itJ]).addPossibleValue(value);
            ++itJ;
        }

        //COLUMN
        int itI = i.intValue() + 1;
        if (itI >= board.length) return;

        while(itI < board.length && board[itI][j] instanceof WhiteKakuroCell)
        {
            ((WhiteKakuroCell) board[itI][j]).addPossibleValue(value);
            ++itI;
        }
    }



    /**
     * A partir del Kakuro recibido, comprobaremos si es resoluble.
     * @custom.mytag1 El Kakuro recibido debe seguir el formato del enunciado.
     * <p>
     * @custom.mytag2 Si el Kakuro recibido como parametro es resoluble, contendra la solucion. En el caso que el Kakuro no sea resoluble, lo devolveremos en el mismo estado que lo hemos recibido.
     * <p>
     * @custom.mytag3 A partir del Kakuro recibido, comprobaremos si es resoluble.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado.
     * @return Devuelve True si el Kakuro recibido es resoluble y False en caso contrario.
     */
    public boolean solveDumbBruteForce(Kakuro kakuro)
    {
        KakuroCell[][] board = kakuro.getBoard();

        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof WhiteKakuroCell && ((WhiteKakuroCell) board[i][j]).getDigit() == 0)
                {
                    Set<Integer> possibleValues = ((WhiteKakuroCell) board[i][j]).getPossibleValues();
                    for (Integer value : possibleValues)
                    {
                        if (isAllowed(board,i,j,value))
                        {
                            ((WhiteKakuroCell) board[i][j]).setDigit(value);
                            //removePossibilities(kakuro,i,j,value);
                            if (solveDumbBruteForce(kakuro))
                                return true;
                            else
                            {
                                ((WhiteKakuroCell) board[i][j]).setDigit(0);
                                //recoverPossibilities(kakuro,i,j,value);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        kakuro.setBoard(board);
        return true;
    }

    /**
     * A partir del Kakuro recibido, calcularemos el numero de soluciones que tiene.
     * @custom.mytag1 El Kakuro recibido debe seguir el formato del enunciado.
     * <p>
     * @custom.mytag2 Si el Kakuro recibido como parametro es resoluble, contendra una de las soluciones. En el caso que el Kakuro no sea resoluble, lo devolveremos en el mismo estado que lo hemos recibido.
     * <p>
     * @custom.mytag3 A partir del Kakuro recibido, calcularemos el numero de soluciones que tiene.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado.
     * @return Devuelve el numero de soluciones del Kakuro
     */
    public int howManySolutions(Kakuro kakuro)
    {
        KakuroCell[][] board = kakuro.getBoard();
        KakuroCell[][] solutionBoard = new KakuroCell[board.length][board[0].length];
        kakuro.copyBoard(solutionBoard);


        WhiteKakuroCell firstWhite = new WhiteKakuroCell();
        int i = 0,j = 0;
        boolean found = false;

        for (int ii = 0; ii < board.length && !found; ++ii) {
            for (int jj = 0; jj < board[0].length && !found; ++jj) {
                if (board[ii][jj] instanceof WhiteKakuroCell && ((WhiteKakuroCell) board[ii][jj]).getDigit() == 0) {
                    firstWhite = (WhiteKakuroCell) board[ii][jj];
                    i = ii;
                    j = jj;
                    found = true;
                }
            }
        }

        if (!found) return 0;

        Set<Integer> possibleValues = firstWhite.getPossibleValues();
        for (Integer value : possibleValues)
        {
            if (isAllowed(board,i,j,value))
            {
                ((WhiteKakuroCell) board[i][j]).setDigit(value);
                if (solveDumbBruteForce(kakuro))
                {
                    ++sol;
                    if (sol >= 2)
                        return 2;
                    kakuro.copyBoard(solutionBoard);

                    for (int ii = 0; ii < board.length; ++ii)
                    {
                        for (int jj = 0; jj < board[0].length; ++jj)
                        {
                            if (board[ii][jj] instanceof WhiteKakuroCell) ((WhiteKakuroCell) board[ii][jj]).setDigit(0);
                        }
                    }
                    ((WhiteKakuroCell) board[i][j]).setDigit(value);
                    if (solveDumbBruteForceWithSolution(kakuro, solutionBoard))
                        return 2;
                }
                else
                {
                    ((WhiteKakuroCell) board[i][j]).setDigit(0);
                }
                for (int ii = 0; ii < board.length; ++ii)
                {
                    for (int jj = 0; jj < board[0].length; ++jj)
                    {
                        if (board[ii][jj] instanceof WhiteKakuroCell) ((WhiteKakuroCell) board[ii][jj]).setDigit(0);
                    }
                }
            }
        }
        kakuro.setBoard(solutionBoard);
        return sol;
    }

    /**
     * A partir del Kakuro recibido, comprobaremos si tiene una solucion distinta a la del parametro foundSolution.
     * @custom.mytag1 El Kakuro recibido debe seguir el formato del enunciado y foundSolution debe ser un tablero de kakuro con las mismas dimensiones que kakuro.
     * <p>
     * @custom.mytag2 Si el Kakuro recibido como parametro es resoluble con una solucion distinta a foundSolution, contendra la solucion. En el caso que el Kakuro no sea resoluble, lo devolveremos en el mismo estado que lo hemos recibido.
     * <p>
     * @custom.mytag3 A partir del Kakuro recibido, comprobaremos si es resoluble con una solucion distinta a foundSolution.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado
     * @param foundSolution Representa un tablero de Kakuro con una solucion a evitar
     * @return Devolvera true si el Kakuro recibido como parametro es resoluble con una solucion distinta a foundSolution y false en caso contrario.
     */
    public boolean solveDumbBruteForceWithSolution(Kakuro kakuro, KakuroCell[][] foundSolution)
    {
        KakuroCell[][] board = kakuro.getBoard();

        for (int i = 0; i < board.length; ++i)
        {
            for (int j = 0; j < board[0].length; ++j)
            {
                if (board[i][j] instanceof WhiteKakuroCell && ((WhiteKakuroCell) board[i][j]).getDigit() == 0)
                {
                    Set<Integer> possibleValues = ((WhiteKakuroCell) board[i][j]).getPossibleValues();
                    for (Integer value : possibleValues)
                    {
                        if (isAllowed(board,i,j,value))
                        {
                            ((WhiteKakuroCell) board[i][j]).setDigit(value);
                            //removePossibilities(kakuro,i,j,value);
                            if (solveDumbBruteForceWithSolution(kakuro, foundSolution) && !kakuro.isSameBoard(foundSolution))
                                return true;
                            else
                            {
                                ((WhiteKakuroCell) board[i][j]).setDigit(0);
                                //recoverPossibilities(kakuro,i,j,value);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        kakuro.setBoard(board);
        if (!kakuro.isSameBoard(foundSolution))
            return true;
        return false;
    }

    /**
     * Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value.
     * @custom.mytag1 Board debe ser un tablero de Kakuro correctamente formateado, el valor de los enteros i,j deben estar entre 0 i la dimension del tablero; value
     * debe ser un valor entre los valores posibles para la celda blanca que se encuentra en la posicion board[i][j].
     * <p>
     * @custom.mytag2 Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion.
     * Dichas restricciones son: Diferentes valores en la misma fila/columna, y que la suma de las casillas blancas de dicha restriccion sea igual al valor de la restriccion.
     * <p>
     * @custom.mytag3 Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value.
     * <p>
     * @param board Representa el tablero de un Kakuro correctamente formateado.
     * @param i Primer valor que define la posicion de una casilla blanca.
     * @param j Segundo valor que defina la posicion de una casilla blanca.
     * @param value Valor que tratamos de colocar a una casilla blanca.
     * @return Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion. Si inflingimos alguna restriccion devolvemos False.
     */
    private boolean isAllowed(KakuroCell[][] board, Integer i, Integer j, Integer value)
    {
        return (isAllowedRow(board,i,j,value) && isAllowedColumn(board,i,j,value));
    }

    /**
     * Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value sin inflingir ninguna restriccion.
     * Dichas restricciones son: Diferentes valores en la misma fila, y que la suma de las casillas blancas de la misma fila sea igual al valor de la restriccion,
     * o menor si en la fila de la restriccion aun quedan casillas blancas con digitos a 0.
     * @custom.mytag1 Board debe ser un tablero de Kakuro correctamente formateado, el valor de los enteros i,j deben estar entre 0 i la dimension del tablero; value
     * debe ser un valor entre los valores posibles para la celda blanca que se encuentra en la posicion board[i][j].
     * <p>
     * @custom.mytag2 Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion.
     * <p>
     * @custom.mytag3 Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value sin inflingir ninguna restriccion.
     * Dichas restricciones son: Diferentes valores en la misma fila, y que la suma de las casillas blancas de la misma fila sea igual al valor de la restriccion,
     * o menor si en la fila de la restriccion aun quedan casillas blancas con digitos a 0.
     * <p>
     * @param board Representa el tablero de un Kakuro correctamente formateado.
     * @param i Primer valor que define la posicion de una casilla blanca.
     * @param j Segundo valor que defina la posicion de una casilla blanca.
     * @param value Valor que tratamos de colocar a una casilla blanca.
     * @return Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion comentada anteriormente. Si inflingimos alguna restriccion devolvemos False.
     */
    private boolean isAllowedRow(KakuroCell[][] board, Integer i, Integer j, Integer value)
    {
        if (j.intValue() == (board[0].length - 1) || board[i][j+1] instanceof BlackKakuroCell)  //CASO ULTIMA CASILLA DE RESTRICCIÓN
        {
            int itJ = j.intValue() - 1;
            if (itJ < 0) return false;
            int sum = value;

            while(itJ >= 0 && board[i][itJ] instanceof WhiteKakuroCell)
            {
                if (((WhiteKakuroCell) board[i][itJ]).getDigit() == value.intValue()) return false;
                sum += ((WhiteKakuroCell) board[i][itJ]).getDigit();
                --itJ;
            }

            if (itJ < 0) return false;
            int rowRestriction = ((BlackKakuroCell) board[i][itJ]).getRowRestriction();
            return (sum == rowRestriction);
        }

        else
        {
            int itJ = j.intValue() - 1;
            if (itJ < 0) return false;
            int sum = value;

            while(itJ >= 0 && board[i][itJ] instanceof WhiteKakuroCell)
            {
                if (((WhiteKakuroCell) board[i][itJ]).getDigit() == value.intValue()) return false;
                sum += ((WhiteKakuroCell) board[i][itJ]).getDigit();
                --itJ;
            }

            if (itJ < 0) return false;
            int rowRestriction = ((BlackKakuroCell) board[i][itJ]).getRowRestriction();
            return (sum < rowRestriction);
        }
    }

    /**
     * Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value sin inflingir ninguna restriccion.
     * Dichas restricciones son: Diferentes valores en la misma columna, y que la suma de las casillas blancas de la misma columna sea igual al valor de la restriccion,
     * o menor si en la columna de la restriccion aun quedan casillas blancas con digitos a 0.
     * @custom.mytag1 Board debe ser un tablero de Kakuro correctamente formateado, el valor de los enteros i,j deben estar entre 0 i la dimension del tablero; value
     * debe ser un valor entre los valores posibles para la celda blanca que se encuentra en la posicion board[i][j].
     * <p>
     * @custom.mytag2 Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion.
     * <p>
     * @custom.mytag3 Comprobamos si en la posicion i,j del tablero podemos poner dicha casilla con un valor igual a value sin inflingir ninguna restriccion.
     * Dichas restricciones son: Diferentes valores en la misma columna, y que la suma de las casillas blancas de la misma columna sea igual al valor de la restriccion,
     * o menor si en la columna de la restriccion aun quedan casillas blancas con digitos a 0.
     * <p>
     * @param board Representa el tablero de un Kakuro correctamente formateado.
     * @param i Primer valor que define la posicion de una casilla blanca.
     * @param j Segundo valor que defina la posicion de una casilla blanca.
     * @param value Valor que tratamos de colocar a una casilla blanca.
     * @return Devolveremos True si poniendo la casilla que se encuentra en la posicion i,j, con un valor igual a value, no inflinge ninguna restriccion comentada anteriormente. Si inflingimos alguna restriccion devolvemos False.
     */
    private boolean isAllowedColumn(KakuroCell[][] board, Integer i, Integer j, Integer value)
    {
        if (i.intValue() == (board.length - 1) || board[i+1][j] instanceof BlackKakuroCell)  //CASO ULTIMA CASILLA DE RESTRICCIÓN
        {
            int itI = i.intValue() - 1;
            if (itI < 0) return false;
            int sum = value;

            while(itI >= 0 && board[itI][j] instanceof WhiteKakuroCell)
            {
                if (((WhiteKakuroCell) board[itI][j]).getDigit() == value.intValue()) return false;
                sum += ((WhiteKakuroCell) board[itI][j]).getDigit();
                --itI;
            }

            if (itI < 0) return false;
            int columnRestriction = ((BlackKakuroCell) board[itI][j]).getColumnRestriction();
            return (sum == columnRestriction);
        }

        else
        {
            int itI = i.intValue() - 1;
            if (itI < 0) return false;
            int sum = value;

            while(itI >= 0 && board[itI][j] instanceof WhiteKakuroCell)
            {
                if (((WhiteKakuroCell) board[itI][j]).getDigit() == value.intValue()) return false;
                sum += ((WhiteKakuroCell) board[itI][j]).getDigit();
                --itI;
            }

            if (itI < 0) return false;
            int columnRestriction = ((BlackKakuroCell) board[itI][j]).getColumnRestriction();
            return (sum < columnRestriction);
        }
    }

    /**
     * Aplicaremos un preprocesado tratando de descubrir los valores que una casilla blanca puede contener, evitando probar valores sin sentido.
     * Dicho preprocesado consiste en encontrar las diferentes formas de sumar la restriccion con tantos numeros como casillas blancas aplican en dicha restriccion.
     * @custom.mytag1 El Kakuro recibido debe seguir el formato del enunciado.
     * <p>
     * @custom.mytag2 Actualizaremos los valores posibles de cada casilla blanca despues de aplicarle a cada uno el preprocesado comentado.
     * <p>
     * @custom.mytag3 Aplicaremos un preprocesado tratando de descubrir los valores que una casilla blanca puede contener, evitando probar valores sin sentido.
     * Dicho preprocesado consiste en encontrar las diferentes formas de sumar la restriccion con tantos numeros como casillas blancas aplican en dicha restriccion.
     * <p>
     * @param kakuro Representa un Kakuro correctamente formateado.
     */

    public void preprocesado(Kakuro kakuro)
    {
        KakuroCell[][] board = kakuro.getBoard();
        for(int i = 0; i < board.length; ++i)
        {
            for(int j = 0; j < board[0].length; ++j)
            {
                if(board[i][j] instanceof BlackKakuroCell && ((BlackKakuroCell) board[i][j]).isRestriction())
                {
                    int c = ((BlackKakuroCell) board[i][j]).getColumnRestriction();
                    int f = ((BlackKakuroCell) board[i][j]).getRowRestriction();
                    if(c != - 1)
                    {
                        int auxi = i+1;
                        int auxj = j;
                        int cont = 0;
                        while(auxi < board.length && board[auxi][auxj] instanceof WhiteKakuroCell)
                        {
                            ++cont;
                            ++auxi;
                        }
                        Set<Integer> possibleValues = new HashSet<>();
                        Set<Integer> aux = new HashSet<>();
                        calcularPossibleValues(c,cont,possibleValues,aux);
                        auxi = i + 1;
                        for(int k = 0; k < cont; ++ k)
                        {
                            ((WhiteKakuroCell) board[auxi+k][auxj]).setPossibleValues(aux);
                        }
                    }
                    if(f != - 1)
                    {
                        int auxi = i;
                        int auxj = j + 1;
                        int cont = 0;
                        while(auxj < board[0].length && board[auxi][auxj] instanceof WhiteKakuroCell)
                        {
                            ++cont;
                            ++auxj;
                        }
                        Set<Integer> possibleValues = new HashSet<>();
                        Set<Integer> aux = new HashSet<>();
                        calcularPossibleValues(f,cont,possibleValues,aux);
                        auxj = j + 1;
                        for(int k = 0; k < cont; ++ k)
                        {
                            ((WhiteKakuroCell) board[auxi][auxj+k]).setPossibleValues(aux);
                        }
                    }
                }
            }
        }
    }

    /**
     * Buscamos las diferentes formas de calcular una suma con valor suma utilizando numeros operandos. Se utiliza para el preproceso.
     * @custom.mytag1 El Kakuro recibido debe seguir el formato del enunciado.
     * <p>
     * @custom.mytag2 En el conjunto total tendremos todos los operandos utilizados para las diferentes formas encontradas de sumar suma con numeros operandos.
     * <p>
     * @custom.mytag3 Buscamos las diferentes formas de calcular una suma con valor suma utilizando numeros operandos. Se utiliza para el preproceso.
     * <p>
     * @param suma Valor de la suma que queremos conseguir con tantos operandos como sea el valor de numeros.
     * @param numeros Numero de operandos utilizados para calcular la suma con valor igual a suma.
     * @param sol Conjunto de operandos utilizados para el calculo actual.
     * @param total Conjunto que contendra todos los valores de los operandos utilizados al encontrar una forma de sumar suma usando numeros operandos.
     * @return Devuelve True si hemos conseguido hacer una suma con numeros operandos con un valor igual a suma, en caso contrario devuelve False.
     */
    public boolean calcularPossibleValues(Integer suma, Integer numeros, Set<Integer> sol, Set<Integer> total)
    {
        if(numeros == 0)
        {
            if(suma == 0)
            {
                Iterator<Integer> it = sol.iterator();
                while(it.hasNext())
                {
                    total.add(it.next());
                }

                return true;
            }
            else return false;
        }
        for (int i = 1; i <= 9; ++i)
        {
            sol.add(i);
            boolean r = calcularPossibleValues(suma - i,numeros - 1, sol, total);
            if (!r) sol.remove(i);
        }
        return false;
    }

    /**
     * Imprime los valores numericos del tablero de un kakuro, las celdas negras se representan como una 'B' y las celdas blancas no inicializadas con un 0.
     * @custom.mytag1 El objeto Kakuro sobre el que se llama esta funcion esta inicializado.
     * <p>
     * @custom.mytag2 Los valores numericos del tablero del kakuro se han impreso por pantalla.
     * <p>
     * @custom.mytag3 Imprime los valores numericos del tablero de un kakuro, las celdas negras se representan como una 'B' y las celdas blancas no inicializadas con un 0.
     * <p>
     */

    public void printValues2() {
        System.out.println();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j].isWhite()) {
                    System.out.print(((WhiteKakuroCell) board[i][j]).getDigit());
                    System.out.print("      ");
                } else if (!board[i][j].isWhite()) {
                    String s = ((BlackKakuroCell) board[i][j]).getRestriction();
                    System.out.print(s);
                    for (int h = 0; h < 7 - s.length(); h++) System.out.print(" ");
                }
            }
            System.out.println();
        }
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
    public void makeBoard(String rawBoard)
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
     * Este metodo se encarga de evaluar si las restricciones de un kakuro estan bien colocadas.
     * @custom.mytag1 El objeto Algorithm sobre el que se aplica esta funcion debe tener el atributo board inicializado.
     * <p>
     * @custom.mytag2 cierto.
     * <p>
     * @custom.mytag3 Este metodo se usa para evaluar si un kakuro esta correctamente formateado, fijandose en las restricciones.
     * <p>
     * @return  Cierto si las restricciones estan bien colocadas. Falso en cualquier otro caso.
     */
    public boolean restrictions_ok()
    {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                if (!board[i][j].isWhite() && ((BlackKakuroCell) board[i][j]).isRestriction()) {
                    if (((BlackKakuroCell) board[i][j]).getColumnRestriction() != -1) {
                        if (i == board.length-1 || !board[i+1][j].isWhite()) return false;
                    }
                    if (((BlackKakuroCell) board[i][j]).getRowRestriction() != -1) {
                        if (j == board.length-1 || !board[i][j+1].isWhite()) return false;
                    }
                } else if (board[i][j].isWhite()) { // mirem que la casella blanca pertanyi a alguna restricció
                    if (i == 0 || j == 0) return false;
                    if (!board[i-1][j].isWhite() && !((BlackKakuroCell) board[i-1][j]).isRestriction()) return false;
                    if (!board[i][j-1].isWhite() && !((BlackKakuroCell) board[i][j-1]).isRestriction()) return false;
                }
            }
        }
        return true;
    }


    /**
     * Lee una restriccion del string dadas las coordenadas en el string de donde empieza esta, una vez leido inicializa en la posicion correspondiente del objeto board dicha restriccion.
     * @custom.mytag1 Considerando el string RawBoard como una matriz de casillas de kakuro, en la posicion i,j empieza una restriccion de fila, columna o ambas.
     * <p>
     * @custom.mytag2 La restriccion ha sido añadida al objeto board del kakuro sobre el que se llama la funcion en la posicion (i-1),jMatrix.
     * <p>
     * @custom.mytag3 Lee una restriccion del string dadas las coordenadas en el string de donde empieza esta, una vez leido inicializa en la posicion correspondiente del objeto board dicha restriccion.
     * <p>
     * @param rawBoard Una string que define un kakuro correctamente formateado.
     * @param i Linea del string en la que se encuentra el inicio de la restriccion.
     * @param j Columna del string en la que se encuentra el inicio de la restriccion.
     * @param jMatrix Columna de board en la que se introducira la restriccion.
     * @return El valor de j incrementado tantas veces como caracteres tenia la restriccion tratada.
     */
    private int addRestriction(String rawBoard, int i, int j, int jMatrix)
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

    //////////////////////////////////////////
    //////___GENERADOR___/////////////////////
    //////////////////////////////////////////

    /**
     * Generador de kakuros:
     * Construye un tablero con solucion unica dado un size determinado, un porcentage de casillas negras, y una cantidad de casillas resueltas.
     * Devuelve el kakuro codificado siguiendo las normas y el estilo definidos en la creadora de Kakuro.
     * @custom.mytag1 tam mayor o igual que 2, percentatge_negres debe valer un minimo de 30 y un maximo de 70, resolved debe ser un valor relativamente inferior al numero de casillas blancas que tendra el board (que se puede calcular a partir de percentatge_negres).
     * <p>
     * @custom.mytag2 Se devuelve el codigo del kakuro generado.
     * <p>
     * @custom.mytag3 Generador de kakuros:
     * Construye un tablero con solucion unica dado un size determinado, un porcentage de casillas negras y una cantidad de casillas resueltas.
     * Devuelve el kakuro codificado siguiendo las normas y el estilo definidos en la creadora de Kakuro.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  percentatge_negres  Valor que, dividido entre 100, da el porcentage de casillas negras, aproximado, que tendrá el kakuro.
     * @param  resolved  Numero de casillas resueltas que tendra la string de kakuro que devuelve la funcion.
     * @return str String con el codigo del kakuro generado.
     */
    public String Generador(int tam, int percentatge_negres, int resolved){
        sol=0;
        int negres = tam*tam*percentatge_negres/100;
        String str = "";
        String str_empty = "";
        while(sol!=1){
            sol = 0;
            do {
                generarBoard(tam, negres);
            }
            while (omplir(tam, resolved) < 0);
            str = boardToCode(tam);
            for (int i = 0; i < tam; i++)
                for (int j = 0; j < tam; j++)
                    if (board[i][j].isWhite())((WhiteKakuroCell) board[i][j]).setDigit(0);
            str_empty = boardToCode(tam);
            Kakuro test = new Kakuro(str_empty);

            sol=howManySolutions(test);
        }
        return str;
    }


    /**
     * Funcion encargada de generar un tablero vacio. Es decir, que solo tiene casillas negras y blancas, pero que no tienen restricciones de suma ni valores, respectivamente.
     * Los tableros que genera son aleatorios, pero tienen ciertas caracteristicas que hemos decidido. Explicacion detallada en la documentacion del algoritmo de generar kakuros.
     * @custom.mytag1 tam es un entero estrictamente superior a 2, negres es un valor mas pequeno que tam*tam pero siempre mas grande que 2*tam.
     * <p>
     * @custom.mytag2 board esta inicializada: distingue casillas blancas de negras. La primera fila y la primera columna son siempre casillas negras.
     * <p>
     * @custom.mytag3 Funcion encargada de generar un tablero vacio. Es decir, que solo tiene casillas negras y blancas, pero que no tienen restricciones de suma ni valores, respectivamente.
     * Los tableros que genera son aleatorios, pero tienen ciertas caracteristicas que hemos decidido. Explicacion detallada en la documentacion del algoritmo de generar kakuros.
     * <p>
     * @param  tam  Size que queremos que tenga nuestro kakuro, que sera cuadrado (tam x tam).
     * @param  negres  Numero de casillas negras, aproximado, que tendra el tablero. Valdra mas de 2*tam y menos que tam*tam.
     */
    public void generarBoard(int tam, int negres) {
        board = new KakuroCell[tam][tam];
        for (int i = 0; i < tam; i++) {
            board[0][i] = new BlackKakuroCell();
            board[i][0] = new BlackKakuroCell();
        }
        for (int i = 1; i < tam; i++) {
            for (int j = 1; j < tam; j++) {
                board[i][j] = new WhiteKakuroCell();
            }
        }
        negres -= 2*tam;

        int variable = tam/2;
        while (negres > 0) {
            while (negres > 0) {
                for (int i = variable; i < tam; ++i) {
                    for (int j = variable; j < tam; ++j) {
                        if (board[i][j].isWhite()) {
                            Random r = new Random();
                            int rnd = r.nextInt(tam * tam - 2 * tam);
                            int probabilitat = 100 * negres / (tam * tam - 2 * tam);
                            boolean posicio_mig = (i >= tam * 0.35 && i <= tam * 0.65) && (j >= tam * 0.35 && j <= tam * 0.65);
                            if (probabilitat == 0) probabilitat = 1;
                            if (posicio_mig) probabilitat *= 3;
                            if (rnd < probabilitat && negres > 0) {
                                board[i][j] = new BlackKakuroCell();
                                negres--;
                            }
                        }
                    }
                }
                variable = 0;
            }
            boolean change = true;
            while (change) {
                change = false;
                for (int i = 0; i < tam; ++i) {
                    for (int j = 0; j < tam; ++j) {
                        if (board[i][j].isWhite()) {
                            if (!board[i][j - 1].isWhite() && (j + 1 == tam || !board[i][j + 1].isWhite())) {
                                change = true;
                                if (j + 1 == tam) board[i][j - 1] = new WhiteKakuroCell();
                                else if (j == 1) board[i][j + 1] = new WhiteKakuroCell();
                                else {
                                    Random r = new Random();
                                    int rnd = r.nextInt(2);
                                    if (rnd == 0) board[i][j + 1] = new WhiteKakuroCell();
                                    else board[i][j - 1] = new WhiteKakuroCell();
                                }
                                negres++;
                            }
                            if (!board[i - 1][j].isWhite() && (i + 1 == tam || !board[i + 1][j].isWhite())) {
                                change = true;
                                if (i + 1 == tam) board[i - 1][j] = new WhiteKakuroCell();
                                else if (i == 1) board[i + 1][j] = new WhiteKakuroCell();
                                else {
                                    Random r = new Random();
                                    int rnd = r.nextInt(2);
                                    if (rnd == 0) board[i + 1][j] = new WhiteKakuroCell();
                                    else board[i - 1][j] = new WhiteKakuroCell();
                                }
                                negres++;
                            }
                        }
                    }
                }
            }
        }
        if(tam>10) {
            negres -= check_zeros(tam);
        }
    }

    /**
     * Funcion encargada de detectar y intentar arreglar una secuencia de mas de 9 ceros seguidos.
     * @custom.mytag1 tam es el numero de filas y columnas del board.
     * <p>
     * @custom.mytag2 board puede tener mas casillas negras.
     * <p>
     * @custom.mytag3 Funcion encargada de detectar y intentar arreglar una secuencia de mas de 9 ceros seguidos en el tablero.
     * Si tam menor que 11, NO aporta ninguna utilidad.
     * Esta funcion puede no solucionar el problema. Dependera del tablero. Pero, en la mayoria de casos, lo soluciona.
     * En caso de no solucionarlo, el control de errores de omplir detectaria la anomalia y avisaria del error, reiniciando el Generador.
     * <p>
     * @param  tam  Size que queremos que tenga nuestro kakuro, que sera cuadrado (tam x tam).
     * @return  numero de casillas negras anadidas al tablero.
     */
    public int check_zeros(int tam) {
        int zeros = 0;
        if(tam>10) {
            boolean change2;
            do {
                change2 = false;
                for (int i = 1; i < tam; i++) {
                    int c = 0;
                    for (int j = 1; j < tam; j++) {
                        if (board[i][j].isWhite()) ++c;
                        else c = 0;
                        if (c > 9) {
                            for (int k = j; k > j - c && c != 0; --k) {
                                boolean dalt = !(board[i - 1][k].isWhite() && !board[i - 2][k].isWhite());
                                boolean baix = (i == tam - 1 || !(board[i + 1][k].isWhite() && (i + 1 == tam - 1 || !board[i + 2][k].isWhite())));
                                boolean dreta = (k == tam - 1 || !(board[i][k + 1].isWhite() && (k + 1 == tam - 1 || !board[i][k + 2].isWhite())));
                                if (dalt && baix && dreta) {
                                    board[i][k] = new BlackKakuroCell();
                                    c = 0;
                                    change2 = true;
                                    zeros++;
                                }
                            }
                        }
                    }
                    c = 0;
                    for (int j = 1; j < tam; j++) {
                        if (board[j][i].isWhite()) ++c;
                        else c = 0;
                        if (c > 9) { // Si no canvia res, encara que estigui malament, surt del do--while -> evitem bucles infinits
                            for (int k = j; k > j - c && c != 0; --k) {
                                boolean esquerra = !(board[k][i - 1].isWhite() && !board[k][i - 2].isWhite());
                                boolean dreta = (i == tam - 1 || !(board[k][i + 1].isWhite() && (i + 1 == tam - 1 || !board[k][i + 2].isWhite())));
                                boolean baix = (k == tam - 1 || !(board[k + 1][i].isWhite() && (k + 1 == tam - 1 || !board[k + 2][i].isWhite())));
                                if (esquerra && baix && dreta) {
                                    board[k][i] = new BlackKakuroCell();
                                    c = 0;
                                    change2 = true;
                                    zeros++;
                                }
                            }
                        }
                    }
                }
            } while (change2);
            // Mirem cas probable de fila==tam-2, columna==tam-2, tercera fila i tercera columna amb més de 9 blanques seguits
            zeros += more_zeros(tam);
        }
        return zeros;
    }

    /**
     * Funcion encargada de detectar y intentar arreglar una secuencia de mas de 9 ceros seguidos en la fila tam-2 y la columna tam-2, en la tercera fila y en la tercera columna.
     * La existencia de esta funcion se debe a que el algoritmo de evitar mas de 9 ceros seguidos puede no funcionar en estos dos lugares. Una explicacion detallada se encuentra en la documentacion del algoritmo de generar kakuros.
     * Si tam es menor que 11, NO aporta ninguna utilidad.
     * Esta funcion puede no solucionar el problema. Dependera del tablero. Pero, en la mayoria de casos, lo soluciona.
     * En caso de no solucionarlo, el control de errores de omplir detectaria la anomalia y avisaria del error.
     * @custom.mytag1 tam es el numero de filas y columnas de board.
     * <p>
     * @custom.mytag2 board puede haber recibido algun cambio en las casillas de la penultima y ultima fila o columna.
     * <p>
     * @custom.mytag3 Funcion encargada de detectar y intentar arreglar una secuencia de mas de 9 ceros seguidos en la fila tam-2 y la columna tam-2, en la tercera fila y en la tercera columna
     * La existencia de esta funcion se debe a que el algoritmo de evitar mas de 9 ceros seguidos puede no funcionar en estos dos lugares. Una explicacion detallada se encuentra en la documentacion del algoritmo de generar kakuros.
     * Si tam es menor que 11, NO aporta ninguna utilidad.
     * Esta funcion puede no solucionar el problema. Dependera del tablero. Pero, en la mayoria de casos, lo soluciona.
     * En caso de no solucionarlo, el control de errores de omplir detectaria la anomalia y avisaria del error.
     * <p>
     * @param tam Size del tablero, que es siempre cuadrado (tam x tam).
     * @return numero de casillas negras anadidas al tablero.
     */
    public int more_zeros(int tam) {
        int c;
        boolean change;
        int zeros = 0;
        do {
            c = 0;
            change = false;
            for (int j = 1; j < tam; j++) { // penúltima fila
                if (board[tam - 2][j].isWhite()) ++c;
                else c = 0;
                if (c > 9 && (j==tam-1 || !board[tam - 2][j + 1].isWhite())) {
                    for (int k = j; k > j - c && !change; --k) {
                        boolean dalt = !(board[tam - 3][k].isWhite() && !board[tam - 4][k].isWhite());
                        boolean esquerra_alt = !(board[tam - 2][k - 1].isWhite() && !board[tam - 2][k - 2].isWhite());
                        boolean esquerra_baix = !(board[tam - 1][k - 1].isWhite() && !board[tam - 1][k - 2].isWhite());
                        boolean dreta_alt = (k == tam - 1 || !(board[tam - 2][k + 1].isWhite() && (k + 1 == tam - 1 || !board[tam - 2][k + 2].isWhite())));
                        boolean dreta_baix = (k == tam - 1 || !(board[tam - 1][k + 1].isWhite() && (k + 1 == tam - 1 || !board[tam - 1][k + 2].isWhite())));
                        if (dalt && dreta_alt && dreta_baix && esquerra_alt && esquerra_baix) {
                            board[tam - 2][k] = new BlackKakuroCell();
                            board[tam - 1][k] = new BlackKakuroCell();
                            change = true;
                            zeros += 2;
                        }
                    }
                }
            }
            c = 0;
            for (int j = 1; j < tam; j++) { // penúltima columna
                if (board[j][tam - 2].isWhite()) ++c;
                else c = 0;
                if (c > 9 && (j==tam-1 || !board[j+1][tam - 2].isWhite())) {
                    for (int k = j; k > j - c && !change; --k) {
                        boolean esquerra = !(board[k][tam - 3].isWhite() && !board[k][tam - 4].isWhite());
                        boolean dalt_esq = !(board[k - 1][tam - 2].isWhite() && !board[k - 2][tam - 2].isWhite());
                        boolean dalt_dre = !(board[k - 1][tam - 1].isWhite() && !board[k - 2][tam - 1].isWhite());
                        boolean baix_esq = (k == tam - 1 || !(board[k + 1][tam - 2].isWhite() && (k + 1 == tam - 1 || !board[k + 2][tam - 2].isWhite())));
                        boolean baix_dre = (k == tam - 1 || !(board[k + 1][tam - 1].isWhite() && (k + 1 == tam - 1 || !board[k + 2][tam - 1].isWhite())));
                        if (esquerra && dalt_esq && dalt_dre && baix_esq && baix_dre) {
                            board[k][tam - 2] = new BlackKakuroCell();
                            board[k][tam - 1] = new BlackKakuroCell();
                            change = true;
                            zeros += 2;
                        }
                    }
                }
            }
            c = 0;
            for (int j = 1; j < tam; j++) { // tercera fila
                if (board[2][j].isWhite()) ++c;
                else c = 0;
                if (c > 9 && (j==tam-1 || !board[2][j + 1].isWhite())) {
                    for (int k = j; k > j - c && !change; --k) {
                        boolean baix = !(board[3][k].isWhite() && !board[4][k].isWhite());
                        boolean esquerra_alt = !(board[2][k - 1].isWhite() && !board[2][k - 2].isWhite());
                        boolean esquerra_baix = !(board[1][k - 1].isWhite() && !board[1][k - 2].isWhite());
                        boolean dreta_alt = (k == tam - 1 || !(board[2][k + 1].isWhite() && (k + 1 == tam - 1 || !board[2][k + 2].isWhite())));
                        boolean dreta_baix = (k == tam - 1 || !(board[1][k + 1].isWhite() && (k + 1 == tam - 1 || !board[1][k + 2].isWhite())));
                        if (baix && dreta_alt && dreta_baix && esquerra_alt && esquerra_baix) {
                            board[2][k] = new BlackKakuroCell();
                            board[1][k] = new BlackKakuroCell();
                            change = true;
                            zeros += 2;
                        }
                    }
                }
            }
            c = 0;
            for (int j = 1; j < tam; j++) { // tercera columna
                if (board[j][2].isWhite()) ++c;
                else c = 0;
                if (c > 9 && (j==tam-1 || !board[j+1][2].isWhite())) {
                    for (int k = j; k > j - c && !change; --k) {
                        boolean dreta = !(board[k][3].isWhite() && !board[k][4].isWhite());
                        boolean dalt_esq = !(board[k - 1][2].isWhite() && !board[k - 2][2].isWhite());
                        boolean dalt_dre = !(board[k - 1][1].isWhite() && !board[k - 2][1].isWhite());
                        boolean baix_esq = (k == tam - 1 || !(board[k + 1][2].isWhite() && (k + 1 == tam - 1 || !board[k + 2][2].isWhite())));
                        boolean baix_dre = (k == tam - 1 || !(board[k + 1][1].isWhite() && (k + 1 == tam - 1 || !board[k + 2][1].isWhite())));
                        if (dreta && dalt_esq && dalt_dre && baix_esq && baix_dre) {
                            board[k][2] = new BlackKakuroCell();
                            board[k][1] = new BlackKakuroCell();
                            change = true;
                            zeros += 2;
                        }
                    }
                }
            }
        } while (change);
        return zeros;
    }

    /**
     * Funcion encargada de intentar llenar un board con casillas negras de restriccion que aseguran un minino de una solucion.
     * Su funcionamiento optimo recae en que board tenga sus casillas blancas a 0 y sus casillas negras sin restriccion puesta cuando se llame a la funcion.
     * Si no hay erorres, la funcion actualiza board con todos los valores de las casillas negras de restriccion, y pone a 0 los valores de todas las casillas blancas, excepto a un numero fijo de casillas (resolved).
     * Esta funcion aplica un filtro de eficiencia: detecta aquellos casos en que hay restricciones de fila o de columna paralelas sin casillas negras de por medio y con el mismo numero de casillas blancas y el mismo valor de suma. Es decir, que nunca tendra solucion unica porque estas dos restricciones paralelas podrian intercambiarse cualquier valor.
     * @custom.mytag1 tam es el numero de filas y columnas de board, resolved es el numero de casillas que se quieren dejar resueltas.
     * board debe estar inicializado, ya sea solo con casillas negras y blancas sin valor, o parcialmente lleno.
     * <p>
     * @custom.mytag2 board esta lleno o parcialmente lleno, dependiendo de si ha tenido exito o no. Los valores anteriores de board se pueden haber sobreescrito. Dependiendo del valor de resolved, tendra ciertas casillas blancas llenas.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar un board con casillas negras de restriccion que aseguran un minino de una solucion.
     * Su funcionamiento optimo recae en que board tenga sus casillas blancas a 0 y sus casillas negras sin restriccion puesta cuando se llame a la funcion.
     * Si no hay erorres, la funcion actualiza board con todos los valores de las casillas negras de restriccion, y pone a 0 los valores de todas las casillas blancas, excepto a un numero fijo de casillas (resolved).
     * Esta funcion aplica un filtro de eficiencia: detecta aquellos casos en que hay restricciones de fila o de columna paralelas sin casillas negras de por medio y con el mismo numero de casillas blancas y el mismo valor de suma. Es decir, que nunca tendra solucion unica porque estas dos restricciones paralelas podrian intercambiarse cualquier valor.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  resolved  Cantidad de casillas resueltas que tendra el kakuro.
     * @return Devuelve 0 si se han llenado todas las casillas negras de restriccion de board con valores que aseguran un minimo de una solucion.
     * Devuelve -1 en caso contrario.
     */
    public int omplir(int tam, int resolved) {
        omplir_smart2(tam);
        if (omplir_smart(tam) < 0) {
            //Error control
            return -1;
        }

        computa_suma(tam);
        if (sumes_paraleles(tam)) {
            // Postprocesado del Generador. Extremadamente util.
            return -1;
        }

        int []value_i = new int[resolved];
        int []value_j = new int[resolved];

        Random r = new Random();
        int rnd = r.nextInt(tam);
        int num = 0;
        while (num < resolved) {
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    if (rnd < 0 && num < resolved && board[i][j].isWhite() && ((WhiteKakuroCell) board[i][j]).getDigit() != 0 && !contained(value_i, value_j, num, i, j)) {
                        value_i[num] = i;
                        value_j[num] = j;
                        num++;
                        rnd = r.nextInt(tam);
                    }
                    if (board[i][j].isWhite()) rnd--;
                }
            }
        }

        for (int i = 0; i < tam; i++)
            for (int j = 0; j < tam; j++) {
                if (board[i][j].isWhite() && !contained(value_i, value_j, resolved, i, j))((WhiteKakuroCell) board[i][j]).setDigit(0);
            }
        return 0;
    }


    /**
     * Funcion que, dado dos vectores y dos parametros, encuentra si hay alguna posicion w en que value_i[w] vale i y value_j[w] vale j, donde w siempre sera menor que num.
     * @custom.mytag1 value_i, value_j inicializados de posicion 0 a num-1, num menor o igual que value_i.size() y mayor que 0.
     * <p>
     * @custom.mytag2 Devuelve si la tupla i,j esta en los vectores value_i value_j en las num primeras posiciones.
     * <p>
     * @custom.mytag3 Funcion que, dado dos vectores y dos parametros, encuentra si hay alguna posicion w en que value_i[w] vale i y value_j[w] vale j, donde w siempre sera menor que num.
     * <p>
     * @param  value_i  Vector con los indices de las filas seleccionadas.
     * @param  value_j  Vector con los indices de las columnas seleccionadas.
     * @param  num  Cantidad de posiciones de value_i y value_j ocupadas.
     * @param  i  Nueva fila seleccionada.
     * @param  j  Nueva columna seleccionada.
     * @return Devuelve true si la tupla i y j pertenece a los vectores value_i y value_j.
     * Devuelve falso en caso contrario.
     */
    public boolean contained(int[] value_i, int[] value_j, int num, int i, int j) {
        for (int w = 0; w < num; w++) {
            if (value_i[w] == i && value_j[w] == j) return true;
        }
        return false;
    }


    /**
     * Esta funcion se encarga de detectar aquellos casos en que hay restricciones de fila o de columna paralelas sin casillas negras de por medio y con el mismo numero de casillas blancas y el mismo valor de suma. Es decir, que nunca tendra solucion unica porque estas dos restricciones paralelas podrian intercambiarse cualquier valor.
     * @custom.mytag1 tam es el numero de filas y columnas de board.
     * <p>
     * @custom.mytag2 Indica si el kakuro tiene sumas paralelas.
     * <p>
     * @custom.mytag3 Esta funcion se encarga de detectar aquellos casos en que hay restricciones de fila o de columna paralelas sin casillas negras de por medio y con el mismo numero de casillas blancas y el mismo valor de suma. Es decir, que nunca tendra solucion unica porque estas dos restricciones paralelas podrian intercambiarse cualquier valor.
     * Por ejemplo:
     * 3,3
     * *,C5,C5
     * F5,a,b
     * F5,c,d
     * Las dos restricciones C5 paralelas implican que este kakuro siempre podra cambiarse a con b y c con d.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @return Devuelve true si hay alguna suma paralela que, por lo tanto, implica que el kakuro no es de solucion unica.
     * Devuelve false en caso contrario.
     */
    public boolean sumes_paraleles(int tam) {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                if (!board[i][j].isWhite() && ((BlackKakuroCell) board[i][j]).isRestriction()) {
                    int rest = ((BlackKakuroCell) board[i][j]).getColumnRestriction();
                    int n = 0;
                    int j2 = i+1;
                    while (j2 < tam && board[j2][j].isWhite())  {
                        n++;
                        j2++;
                    }
                    j2 = j+1;
                    while (j2 < tam && !board[i][j2].isWhite() && ((BlackKakuroCell) board[i][j2]).isRestriction()) {
                        int restr = ((BlackKakuroCell) board[i][j2]).getColumnRestriction();
                        if (restr == rest) {
                            int n2 = 0;
                            int j3 = i+1;
                            while (j3 < tam && board[j3][j2].isWhite()) {
                                n2++;
                                j3++;
                            }
                            if (n2 == n) return true;
                        }
                        j2++;
                    }
                }
            }
        }

        for (int j = 0; j < tam; j++) {
            for (int i = 0; i < tam; i++) {
                if (!board[i][j].isWhite() && ((BlackKakuroCell) board[i][j]).isRestriction()) {
                    int rest = ((BlackKakuroCell) board[i][j]).getRowRestriction();
                    int n = 0;
                    int j2 = j+1;
                    while (j2 < tam && board[i][j2].isWhite())  {
                        n++;
                        j2++;
                    }
                    j2 = i+1;
                    while (j2 < tam && !board[j2][j].isWhite() && ((BlackKakuroCell) board[j2][j]).isRestriction()) {
                        int restr = ((BlackKakuroCell) board[j2][j]).getRowRestriction();
                        if (restr == rest) {
                            int n2 = 0;
                            int j3 = j+1;
                            while (j3 < tam && board[j2][j3].isWhite()) {
                                n2++;
                                j3++;
                            }
                            if (n2 == n) return true;
                        }
                        j2++;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Esta funcion se encarga de devolver un vector de sumas unicas dado un vector de presencia y un numero de casillas.
     * @custom.mytag1 n es el numero de casillas, y presence el vector de presencia.
     * <p>
     * @custom.mytag2 Devuelve un vector que indica una suma unica con n casillas y teniendo en cuenta presence.
     * <p>
     * @custom.mytag3 Esta funcion se encarga de devolver un vector de sumas unicas dado un vector de presencia y un numero de casillas.
     * <p>
     * @param  n  Numero de numeros de la particion.
     * @param  presence  Vector que indica si hay algun valor que debe estar obligatoriamente en el vector resultante.
     * @return Devuelve un vector donde cada posicion indica su presencia (true) o su no presencia (false) en la particion unica calculada.
     * Devuelve un vector con false en todas sus posiciones si no ha encontrado ninguna solucion (debido al vector presence).
     */
    public boolean[] calc_sum_uniq(int n, boolean[] presence){
        Random r = new Random();
        boolean[] v = new boolean[9];
        boolean[] v2 = new boolean[9];

        for(int i=0; i<9; i++){
            if(i<n) v[i] = true;
            else v[i] = false;
        }
        for(int i=0; i<9; i++){
            if(i<n) v2[8-i] = true;
            else v2[8-i] = false;
        }

        int rnd = r.nextInt(4);
        int orig = rnd;

        do {
            boolean b = false;
            if (rnd == 0) {
                for (int i = 0; i < 9; ++i) {
                    b |= presence[i] && !v[i];
                }
                if (!b) return v;
            } else if (rnd == 1) {
                b = false;
                for (int i = 0; i < 9; ++i) {
                    b |= presence[i] && !v2[i];
                }
                if (!b) return v2;
            } else if (rnd == 2) {
                v[n] = true;
                v[n - 1] = false;
                b = false;
                for (int i = 0; i < 9; ++i) {
                    b |= presence[i] && !v[i];
                }
                if (!b) return v;
                v[n] = false;
                v[n - 1] = true;
            } else {
                v2[9 - n - 1] = true;
                v2[9 - n] = false;
                b = false;
                for (int i = 0; i < 9; ++i) {
                    b |= presence[i] && !v2[i];
                }
                if (!b) return v2;
                v2[9 - n - 1] = false;
                v2[9 - n] = true;
            }
            rnd = (rnd + 1)%4;
        } while (orig != rnd);

        return new boolean[] {false,false,false,false,false,false,false,false,false};
    }


    /**
     * Funcion encargada de intentar llenar un board con particiones unicas.
     * Su funcionamiento optimo recae en que board tenga sus casillas blancas a 0 y sus casillas negras sin restriccion puesta cuando se llame a la funcion.
     * Si no hay erorres, la funcion actualiza completamente o parcialmente la board.
     * Sigue una politica de best-effort, es decir, intenta completar la board tanto como puede.
     * @custom.mytag1 tam es el numero de filas y columnas de board, resolved es el numero de casillas que se quieren dejar resueltas.
     * board debe estar inicializado, ya sea solo con casillas negras y blancas sin valor, o parcialmente lleno.
     * <p>
     * @custom.mytag2 board esta lleno o parcialmente lleno, dependiendo de si ha tenido exito o no. Los valores anteriores de board se pueden haber sobreescrito. Dependiendo del valor de resolved, tendra ciertas casillas blancas llenas.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar un board con particiones unicas.
     * Su funcionamiento optimo recae en que board tenga sus casillas blancas a 0 y sus casillas negras sin restriccion puesta cuando se llame a la funcion.
     * Si no hay erorres, la funcion actualiza completamente o parcialmente la board.
     * Sigue una politica de best-effort, es decir, intenta completar la board tanto como puede.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * Devuelve -1 en caso contrario.
     */
    public void omplir_smart2(int tam){
        Random r = new Random();
        for(int it=9; it!=1; it--) {
            for (int i = 1; i < tam; i++) {
                for (int j = 1; j < tam; j++) {
                    if (board[i][j].isWhite() && !board[i][j-1].isWhite()) {
                        int n = 0;
                        boolean[] presence = new boolean[9];
                        Arrays.fill(presence, false);
                        for(int j1=j; j1<tam && board[i][j1].isWhite() ; ++j1) {
                            int dg =((WhiteKakuroCell)board[i][j1]).getDigit();
                            if( dg!=0) {
                                presence[dg - 1] = true;
                            }
                            ++n;
                        }
                        if(n==it){
                            boolean[] v = new boolean[9];
                            if (n == 9) Arrays.fill(v, true);
                            else if (n == 8) {
                                Arrays.fill(v, true);
                                int pos = r.nextInt(9);
                                int prin = -1;
                                while (presence[pos] && prin != pos) {
                                    if (prin == -1) prin = pos;
                                    pos = (pos+1)%9;
                                }
                                if (prin == pos) Arrays.fill(v, false);
                                else v[pos] = false;
                            } else if (n > 9) Arrays.fill(v, false);
                            else v = calc_sum_uniq(n,presence);

                            boolean b = false;
                            for(int k=0; k<9; k++){
                                b |= v[k];
                                if(presence[k]) v[k]=false;
                            }
                            if(b){
                                boolean surt=false;
                                while(j<tam && board[i][j].isWhite() && !surt) {
                                    if (((WhiteKakuroCell) board[i][j]).getDigit() == 0) {
                                        int rnd = r.nextInt(9);
                                        int og = rnd;
                                        boolean inici = true;
                                        if(!(v[rnd] && correct_fil(i, j, rnd + 1))) {
                                            inici = false;
                                            do {
                                                rnd = (rnd + 1) % 9;
                                            } while ((!v[rnd] || !correct_fil(i, j, rnd + 1)) && rnd != og);
                                        }
                                        if (og == rnd && !inici) {
                                            surt = true;
                                            int j_aux = j;
                                            while (board[i][j_aux].isWhite()) {
                                                int dig = ((WhiteKakuroCell) board[i][j_aux]).getDigit();
                                                if (dig != 0 && !presence[dig - 1]) {
                                                    ((WhiteKakuroCell) board[i][j_aux]).setDigit(0);
                                                }
                                                --j_aux;
                                            }
                                        } else {
                                            ((WhiteKakuroCell) board[i][j]).setDigit(rnd + 1);
                                            v[rnd] = false;
                                        }
                                    }
                                    j++;
                                }
                            }
                        }
                    }
                }

                for(int col = 1; col<tam; col++){
                    if (board[col][i].isWhite() && !board[col-1][i].isWhite()) {
                        int n = 1;
                        boolean[] presence = new boolean[9];
                        Arrays.fill(presence, false);
                        for(int j1=col; j1<tam && board[j1][i].isWhite() ; ++j1) {
                            int dg =((WhiteKakuroCell)board[j1][i]).getDigit();
                            if( dg!=0) {
                                presence[dg-1]=true;
                            }
                            ++n;
                        }
                        if(n==it){
                            boolean[] v = new boolean[9];
                            if (n == 9) Arrays.fill(v, true);
                            else if (n == 8) {
                                Arrays.fill(v, true);
                                int pos = r.nextInt(9);
                                int prin = -1;
                                while (presence[pos] && prin != pos) {
                                    if (prin == -1) prin = pos;
                                    pos = (pos+1)%9;
                                }
                                if (prin == pos) Arrays.fill(v, false);
                                else v[pos] = false;
                            } else if (n > 9) Arrays.fill(v, false);
                            else v = calc_sum_uniq(n,presence);

                            boolean b = false;
                            for(int k=0; k<9; k++){
                                b |= v[k];
                                if(presence[k]) v[k]=false;
                            }
                            if(b){
                                boolean surt=false;
                                while(col<tam && board[col][i].isWhite() && !surt) {
                                    if (((WhiteKakuroCell) board[col][i]).getDigit() == 0) {
                                        int rnd = r.nextInt(9);
                                        int og = rnd;
                                        boolean inici = true;
                                        if(!(v[rnd] && correct(col, i, rnd + 1))) {
                                            inici = false;
                                            do {
                                                rnd = (rnd + 1) % 9;
                                            } while ((!v[rnd] || !correct(col, i, rnd + 1)) && rnd != og);
                                        }

                                        if (og == rnd && !inici) {
                                            surt = true;
                                            int j_aux = col;
                                            while (board[j_aux][i].isWhite()) {
                                                int dig = ((WhiteKakuroCell) board[j_aux][i]).getDigit();
                                                if (dig != 0 && !presence[dig - 1]) {
                                                    ((WhiteKakuroCell) board[j_aux][i]).setDigit(0);
                                                }
                                                --j_aux;
                                            }
                                        } else {
                                            ((WhiteKakuroCell) board[col][i]).setDigit(rnd + 1);
                                            v[rnd] = false;
                                        }
                                    }
                                    col++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    /**
     * Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores bajos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila, presence los valores que tiene la restriccion de fila antes de empezar la funcion.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores bajos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @param  presence  Vector que indica los valores que ya hay en la restriccion de fila que se esta mirando.
     * @return Devuelve la ultima posicion de la fila en la que se ha insertado un valor, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_baixos(int tam, int i, int j, boolean[] presence) {
        Random r = new Random();
        while (j < tam && board[i][j].isWhite()) {
            if (((WhiteKakuroCell)board[i][j]).getDigit() == 0) {
                int rnd = r.nextInt(3);
                int original = rnd;
                while (presence[rnd]) {
                    rnd++;
                    rnd %= 9;
                    if (rnd == original) return -1;
                }
                presence[rnd] = true;
                ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
                if (!board[i-1][j].isWhite()) {
                    int error = 0;
                    if (rnd + 1 < 5) error = omplir_baixos_vertical(tam, i, j);
                    else if (rnd + 1 > 5 || rnd % 2 == 0) error = omplir_alts_vertical(tam, i, j);
                    else error = omplir_baixos_vertical(tam, i, j);
                    if (error < 0) return -1;
                }
            }
            j++;
        }
        return j;
    }

    /**
     * Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores altos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila, presence los valores que tiene la restriccion de fila antes de empezar la funcion.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores altos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @param  presence  Vector que indica los valores que ya hay en la restriccion de fila que se esta mirando.
     * @return Devuelve la ultima posicion de la fila en la que se ha insertado un valor, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_alts(int tam, int i, int j, boolean[] presence) {
        Random r = new Random();
        while (j < tam && board[i][j].isWhite()) {
            if (((WhiteKakuroCell)board[i][j]).getDigit() == 0) {
                int rnd = r.nextInt(3);
                rnd += 6;
                int original = rnd;
                while (presence[rnd]) {
                    rnd--;
                    if (rnd == -1) rnd = 8;
                    if (rnd == original) return -1;
                }
                presence[rnd] = true;
                ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
                if (!board[i-1][j].isWhite()) {
                    int error = 0;
                    if (rnd + 1 < 5) error = omplir_baixos_vertical(tam, i, j);
                    else if (rnd + 1 > 5 || rnd % 2 == 0) error = omplir_alts_vertical(tam, i, j);
                    else error = omplir_baixos_vertical(tam, i, j);
                    if (error < 0) return -1;
                }
            }
            j++;
        }
        return j;
    }

    /**
     * Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores bajos y medios, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila, presence los valores que tiene la restriccion de fila antes de empezar la funcion.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores bajos y medios, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @param  presence  Vector que indica los valores que ya hay en la restriccion de fila que se esta mirando.
     * @return Devuelve la ultima posicion de la fila en la que se ha insertado un valor, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_semi_baixos(int tam, int i, int j, boolean[] presence) {
        Random r = new Random();
        while (j < tam && board[i][j].isWhite()) {
            if (((WhiteKakuroCell)board[i][j]).getDigit() == 0) {
                int rnd = r.nextInt(6);
                int original = rnd;
                while (presence[rnd]) {
                    rnd++;
                    rnd %= 9;
                    if (rnd == original) return -1;
                }
                presence[rnd] = true;
                ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
                if (!board[i-1][j].isWhite()) {
                    int error = 0;
                    if (rnd + 1 < 5) error = omplir_baixos_vertical(tam, i, j);
                    else if (rnd + 1 > 5 || rnd % 2 == 0) error = omplir_alts_vertical(tam, i, j);
                    else error = omplir_baixos_vertical(tam, i, j);
                    if (error < 0) return -1;
                }
            }
            j++;
        }
        return j;
    }

    /**
     * Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores medios y altos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila, presence los valores que tiene la restriccion de fila antes de empezar la funcion.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores medios y altos, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @param  presence  Vector que indica los valores que ya hay en la restriccion de fila que se esta mirando.
     * @return Devuelve la ultima posicion de la fila en la que se ha insertado un valor, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_semi_alts(int tam, int i, int j, boolean[] presence) {
        Random r = new Random();
        while (j < tam && board[i][j].isWhite()) {
            if (((WhiteKakuroCell)board[i][j]).getDigit() == 0) {
                int rnd = r.nextInt(5);
                rnd += 4;
                int original = rnd;
                while (presence[rnd]) {
                    rnd--;
                    if (rnd == -1) rnd = 8;
                    if (rnd == original) return -1;
                }
                presence[rnd] = true;
                ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
                if (!board[i-1][j].isWhite()) {
                    int error = 0;
                    if (rnd + 1 < 5) error = omplir_baixos_vertical(tam, i, j);
                    else if (rnd + 1 > 5 || rnd % 2 == 0) error = omplir_alts_vertical(tam, i, j);
                    else error = omplir_baixos_vertical(tam, i, j);
                    if (error < 0) return -1;
                }
            }
            j++;
        }
        return j;
    }

    /**
     * Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores aleatorios, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila, presence los valores que tiene la restriccion de fila antes de empezar la funcion.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de fila y todas las restricciones de columna que empiecen en alguna de las casillas blancas visitadas.
     * Se encarga de llenar las casillas de la restriccion de fila con valores aleatorios, y los de las restricciones de columnas con valores aleatorios.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @param  presence  Vector que indica los valores que ya hay en la restriccion de fila que se esta mirando.
     * @return Devuelve la ultima posicion de la fila en la que se ha insertado un valor, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_random(int tam, int i, int j, boolean[] presence) {
        Random r = new Random();
        while (j < tam && board[i][j].isWhite()) {
            if (((WhiteKakuroCell)board[i][j]).getDigit() == 0) {
                boolean apte = false;
                for (int w = 0; w < 9; w++) apte |= !presence[w];
                if (!apte) return -1;
                int rnd = r.nextInt(9);
                while (presence[rnd]) {
                    rnd = r.nextInt(9);
                }
                presence[rnd] = true;
                ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
                if (!board[i-1][j].isWhite()) {
                    int error = 0;
                    if (rnd + 1 < 5) error = omplir_baixos_vertical(tam, i, j);
                    else if (rnd + 1 > 5 || rnd % 2 == 0) error = omplir_alts_vertical(tam, i, j);
                    else error = omplir_baixos_vertical(tam, i, j);
                    if (error < 0) return -1;
                }
            }
            j++;
        }
        return j;
    }

    /**
     * Funcion encargada de intentar llenar una restriccion de columna.
     * Se encarga de llenar las casillas de la restriccion de columna con valores bajos. Sobreescriba cualquier valor que haya en la board y pertenezca a la restriccion de columna.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de columna.
     * Se encarga de llenar las casillas de la restriccion de columna con valores bajos. Sobreescriba cualquier valor que haya en la board y pertenezca a la restriccion de columna.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @return Devuelve 0 si ha podido insertar todos los valores, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_baixos_vertical(int tam, int i, int j) {
        Random r = new Random();
        boolean[] presence = new boolean[9];
        Arrays.fill(presence, false);
        presence[((WhiteKakuroCell)board[i][j]).getDigit()-1] = true;
        i++;
        while (i < tam && board[i][j].isWhite()) {
            int rnd = r.nextInt(3);
            int original = rnd;
            while (presence[rnd] || !correct(i, j, rnd+1)) {
                rnd++;
                rnd %= 9;
                if (original == rnd) return -1;
            }
            presence[rnd] = true;
            ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
            i++;
        }
        return 0;
    }


    /**
     * Funcion encargada de intentar llenar una restriccion de columna.
     * Se encarga de llenar las casillas de la restriccion de columna con valores altos. Sobreescriba cualquier valor que haya en la board y pertenezca a la restriccion de columna.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * @custom.mytag1 tam es el numero de filas y columnas de board, i es el numero de fila, j es la primera casilla de la restriccion de fila.
     * <p>
     * @custom.mytag2 board puede haber sido modificada.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar una restriccion de columna.
     * Se encarga de llenar las casillas de la restriccion de columna con valores altos. Sobreescriba cualquier valor que haya en la board y pertenezca a la restriccion de columna.
     * Es a prueba de errores; si no puede completar la board, lo notifica.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @param  i  Fila donde se quiere anadir el valor.
     * @param  j  Columna donde se quiere empezar a anadir el valor.
     * @return Devuelve 0 si ha podido insertar todos los valores, o -1 si no ha sido capaz de llenar la board.
     */
    int omplir_alts_vertical(int tam, int i, int j) {
        Random r = new Random();
        boolean[] presence = new boolean[9];
        Arrays.fill(presence, false);
        presence[((WhiteKakuroCell)board[i][j]).getDigit()-1] = true;
        i++;
        while (i < tam && board[i][j].isWhite()) {
            int rnd = r.nextInt(3);
            rnd += 6;
            int original = rnd;
            while (presence[rnd] || !correct(i, j, rnd+1)) {
                rnd--;
                if (rnd == -1) rnd = 8;
                if (original == rnd) return -1;
            }
            presence[rnd] = true;
            ((WhiteKakuroCell) board[i][j]).setDigit(rnd+1);
            i++;
        }
        return 0;
    }


    /**
     * Funcion encargada de intentar llenar un board.
     * Su funcionamiento esta pensado para actuar en los casos en que omplir_smart2 no ha sido capaz de llenar ciertas casillas.
     * Es inteligente: intenta llenar la board teniendo en cuenta los valores que ya hay puestos, intentando anadir valores latos o bajos, para fomentar que las sumas de las restricciones tengan el minimo numero de particiones.
     * Si no hay erorres, la funcion llena completamente la board.
     * Tiene y usa el control de erores de las funciones omplir_baixos, omplir_alts, omplir_semi_alts, omplir_semi_baixos i omplir_random.
     * Puede actuar sobre un board parcialmente lleno, pero podria sobreescribir alguna de sus casillas blancas.
     * @custom.mytag1 tam es el numero de filas y columnas de board.
     * <p>
     * @custom.mytag2 board esta lleno o parcialmente lleno, dependiendo de si ha tenido exito o no. Los valores anteriores de board se pueden haber sobreescrito.
     * <p>
     * @custom.mytag3 Funcion encargada de intentar llenar un board.
     * Su funcionamiento esta pensado para actuar en los casos en que omplir_smart2 no ha sido capaz de llenar ciertas casillas.
     * Es inteligente: intenta llenar la board teniendo en cuenta los valores que ya hay puestos, intentando anadir valores latos o bajos, para fomentar que las sumas de las restricciones tengan el minimo numero de particiones.
     * Si no hay erorres, la funcion llena completamente la board.
     * Tiene y usa el control de erores de las funciones omplir_baixos, omplir_alts, omplir_semi_alts, omplir_semi_baixos i omplir_random.
     * Puede actuar sobre un board parcialmente lleno, pero podria sobreescribir alguna de sus casillas blancas.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @return Devuelve 0 si llena completamente el board. Devuelve -1 en caso contrario.
     */
    public int omplir_smart(int tam) {
        boolean[] presence = new boolean[9];
        for (int i = 1; i < tam; i++) {
            for (int j = 1; j < tam; j++) {
                if (board[i][j].isWhite()) {
                    Arrays.fill(presence, false);
                    for (int w = j; w < tam && board[i][w].isWhite(); w++) {
                        if (((WhiteKakuroCell)board[i][w]).getDigit() != 0) {
                            presence[((WhiteKakuroCell) board[i][w]).getDigit() - 1] = true;
                        }
                    }
                    boolean isbaix, ismig, isalt;
                    isbaix = ismig = isalt = false;
                    for (int w = 0; w < 3; w++) if (presence[w]) isbaix = true;
                    for (int w = 3; w < 6; w++) if (presence[w]) ismig = true;
                    for (int w = 6; w < 9; w++) if (presence[w]) isalt = true;
                    if (!isbaix && !isalt && !ismig) {
                        // Si no hi ha cap valor en la fila -> toca escollir si anem per alts o per baixos
                        Random r = new Random();
                        int rnd = r.nextInt(2);
                        if (rnd == 0) {
                            j = omplir_baixos(tam, i, j, presence);
                            if (j == -1) return -1;
                        } else {
                            j = omplir_alts(tam, i, j, presence);
                            if (j == -1) return -1;
                        }
                    } else if (isbaix && !isalt && !ismig) {
                        j = omplir_baixos(tam, i, j, presence);
                        if (j == -1) return -1;

                    } else if (!isbaix && isalt && !ismig) {
                        j = omplir_alts(tam, i, j, presence);
                        if (j == -1) return -1;
                    } else if (!isbaix && isalt && ismig) {
                        j = omplir_semi_alts(tam, i, j, presence);
                        if (j == -1) return -1;
                    } else if (isbaix && !isalt && ismig) {
                        j = omplir_semi_baixos(tam, i, j, presence);
                        if (j == -1) return -1;
                    } else {
                        j = omplir_random(tam, i, j, presence);
                        if (j == -1) return -1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Funcion encargada de determinar si anadir el valor value a la posicion [fil][col] de board mantiene las restricciones de fila del kakuro.
     * @custom.mytag1 fil y col son valores superiores a 0 y menores que el size del board.
     * value pertenece al intervalo [1,9].
     * La variable board contiene un tablero de kakuro.
     * <p>
     * @custom.mytag2  Se devuelve un bool que indica si se puede anadir value en la posicion fil col al kakuro fijandonos en la fila.
     * <p>
     * @custom.mytag3 Funcion encargada de determinar si anadir el valor value a la posicion [fil][col] de board mantiene las restricciones de fila del kakuro.
     * <p>
     * @param  fil  fila donde se quiere evaluar si se puede insertar value.
     * @param  col  columna donde se quiere evaluar si se puede insertar value.
     * @param  value  valor que se quiere ver si se puede insertar en board[fil][col].
     * @return Devuelve true si se puede insertar sin romper la regla de no repeticion de valores de fila del kakuro en curso.
     * Si no se puede, devuelve false.
     */
    public boolean correct(int fil, int col, int value) {
        for (int j = col-1; board[fil][j].isWhite(); j--) {
            if (((WhiteKakuroCell) board[fil][j]).getDigit() == value) return false;
        }
        for (int j = col+1; (j < board[0].length) && board[fil][j].isWhite(); j++) {
            if (((WhiteKakuroCell) board[fil][j]).getDigit() == value) return false;
        }
        return true;
    }


    /**
     * Funcion encargada de determinar si anadir el valor value a la posicion [fil][col] de board mantiene las restricciones de columna del kakuro.
     * @custom.mytag1 fil y col son valores superiores a 0 y menores que el size del board.
     * value pertenece al intervalo [1,9].
     * La variable board contiene un tablero de kakuro.
     * <p>
     * @custom.mytag2  Se devuelve un bool que indica si se puede anadir value en la posicion fil col al kakuro fijandonos en las columnas.
     * <p>
     * @custom.mytag3 Funcion encargada de determinar si anadir el valor value a la posicion [fil][col] de board mantiene las restricciones de columna del kakuro.
     * <p>
     * @param  fil  fila donde se quiere evaluar si se puede insertar value.
     * @param  col  columna donde se quiere evaluar si se puede insertar value.
     * @param  value  valor que se quiere ver si se puede insertar en board[fil][col].
     * @return Devuelve true si se puede insertar sin romper la regla de no repeticion de valores de columna del kakuro en curso.
     * Si no se puede, devuelve false.
     */
    public boolean correct_fil(int fil, int col, int value) {
        for (int j = fil-1; board[j][col].isWhite(); j--) {
            if (((WhiteKakuroCell) board[j][col]).getDigit() == value) return false;
        }
        for (int j = fil+1; (j < board[0].length) && board[j][col].isWhite(); j++) {
            if (((WhiteKakuroCell) board[j][col]).getDigit() == value) return false;
        }
        return true;
    }

    /**
     * Funcion encargada de anadir las restricciones de fila y columna de board.
     * @custom.mytag1 tam es el numero de filas y columnas de board.
     * La variable board contiene un tablero de kakuro con todas sus casillas blancas ya llenas.
     * <p>
     * @custom.mytag2 board tiene todas sus casillas negras de restricciones con los valores que corresponden.
     * <p>
     * @custom.mytag3 Funcion encargada de anadir las restricciones de fila y columna de board.
     * <p>
     * @param tam Size del tablero, que es siempre cuadrado (tam x tam).
     */
    public void computa_suma(int tam) {
        for (int i = 0; i < tam; i++) {
            int pos_j = 0;
            int suma = 0;
            for (int j = 0; j < tam; j++) {
                if (!board[i][j].isWhite() && j < tam - 1 && board[i][j + 1].isWhite()) {
                    pos_j = j;
                    suma = 0;
                } else if (board[i][j].isWhite()) {
                    suma += ((WhiteKakuroCell) board[i][j]).getDigit();
                    if ((j == tam - 1) || !board[i][j + 1].isWhite()) {
                        ((BlackKakuroCell) board[i][pos_j]).setSumRow(suma);
                    }
                }
                ((BlackKakuroCell) board[i][pos_j]).setRestriction(((BlackKakuroCell) board[i][pos_j]).getSums());
            }
        }

        for (int j = 0; j < tam; j++) {
            int pos_i = 0;
            int suma = 0;
            for (int i = 0; i < tam; i++) {
                if (!board[i][j].isWhite() && i < tam - 1 && board[i + 1][j].isWhite()) {
                    pos_i = i;
                    suma = 0;
                } else if (board[i][j].isWhite()) {
                    suma += ((WhiteKakuroCell) board[i][j]).getDigit();
                    if ((i == tam - 1) || !board[i + 1][j].isWhite()) {
                        ((BlackKakuroCell) board[pos_i][j]).setSumCol(suma);
                    }
                }
                ((BlackKakuroCell) board[pos_i][j]).setRestriction(((BlackKakuroCell) board[pos_i][j]).getSums());
            }
        }
    }

    /**
     * Funcion encargada de computar el codigo del kakuro generado.
     * @custom.mytag1 tam es el numero de filas y columnas de board.
     * La variable board contiene un tablero de kakuro.
     * <p>
     * @custom.mytag2 Se devuelve la codificacion de un kakuro en formato String.
     * <p>
     * @custom.mytag3 Funcion encargada de computar el codigo del kakuro generado.
     * <p>
     * @param  tam  Size del tablero, que es siempre cuadrado (tam x tam).
     * @return String con el valor del kakuro codificado siguiendo las normas y el estilo definidos en la creadora de Kakuro.
     */
    public String boardToCode(int tam) {
        String result = tam+","+tam+"\n";
        for (int i = 0; i < tam; ++i) {
            for (int j = 0; j < tam; j++) {
                if (board[i][j].isWhite()) {
                    if (((WhiteKakuroCell) board[i][j]).getDigit() != 0) result += ((WhiteKakuroCell) board[i][j]).getDigit();
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
