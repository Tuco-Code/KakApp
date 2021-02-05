package dominio;

import dominio.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GeneradorDriver {
    private KakuroCell[][] board;

    public static void main(String[] args)   throws Exception{
        int tam = 12;
        int negres = 60;
        Algorithms a = new Algorithms();

        System.out.println("Formato de impresion de kakuros: \n B = casilla negra, CX/FX/CXFX = casilla de restriccion donde X es la suma,  0 = casilla blanca sin numero, numero 1-9 = casilla blanca con dicho numero");
        System.out.println("Formato de Inserción de kakuros: \n Mismo formato al del enunciado,pero se puede introducir un numero en vez de un ? para especificar que la casilla blanca tiene un numero del 1-9.");

        System.out.println("Introduzca el numero de la función a evaluar:");
        System.out.println(" 0: Generador \n 1: more_zeros \n 2: omplir \n 3: calc_sum_uniq \n 4: computa_suma \n 5: boardToCode \n 6: Generarboard \n 7: check_zeros \n 8: omplir_smart2 \n 9: omplir_smart \n 10: sumes_paraleles \n 11: codigo error omplir \n");

        int cas;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Scanner input = new Scanner(System.in);

        cas = Integer.parseInt(reader.readLine());

        int n;
        int resolved = 3;
        String s;
        String l;
        while(cas!=-1) {
            switch (cas) {
                case 0:
                    System.out.println("Caso 0: Generar Kakuro");
                    System.out.println("Introduce el tamaño deseado del Kakuro a Generar:");
                    n = Integer.parseInt(reader.readLine());
                    System.out.println("Introduce la proporción de casillas negras del Kakuro a Generar:");
                    negres = Integer.parseInt(reader.readLine());
                    System.out.println("Introduce la cantidad de casillas resueltas del Kakuro a Generar:");
                    resolved = Integer.parseInt(reader.readLine());
                    System.out.println("Esta operacion puede tardar un rato...");
                    s = a.Generador(n, negres, resolved);
                    Kakuro kakuro = new Kakuro(s);
                    System.out.println("Tablero con la solución única generado:");
                    a.printValues2();
                    System.out.println("String del kakuro generado:");
                    System.out.println(s);
                    break;
                case 1:
                    System.out.println("Caso 1: more_zeros");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s= reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    a.more_zeros(n);
                    System.out.println("Tablero del kakuro una vez ejectuada la función more_zeros:");
                    a.printValues2();
                    break;
                case 2:
                    System.out.println("Caso 2: omplir");
                    System.out.println("Introduce la cantidad de casillas resueltas para el Kakuro:");
                    resolved = Integer.parseInt(reader.readLine());
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    int error = a.omplir(n, resolved);
                    System.out.println("Codigo de error:"+error);
                    if(error==-1) {
                        System.out.println("Error -1, tablero incompleto es:");
                    }
                    else System.out.println("Tablero del kakuro una vez ejectuada la función omplir:");
                    a.printValues2();
                    break;
                case 3:
                    System.out.println("Caso 3: calc_sum_uniq");
                    System.out.println("Introduce el número de casillas:");
                    n = Integer.parseInt(reader.readLine());
                    System.out.println("Introduce el vector de presencia. EJ: true true true false false false false false false. Siempre debe tener 9 elementos.");
                    s = reader.readLine();
                    String[] se = s.split(" ");
                    boolean[] presence = new boolean[9];
                    for (int i = 0; i < 9; i++) {
                        if (se[i].equals("true")) presence[i] = true;
                        else presence[i] = false;
                    }
                    boolean[] res = a.calc_sum_uniq(n, presence);
                    System.out.println("Vector resultante:");
                    for (int i = 0; i < 9; i++) System.out.print(res[i]+" ");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Caso 4: computa_suma");
                    System.out.println("Introduce la String del kakuro, casillas negras o blancas con numero, que desea comprobar:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    a.computa_suma(n);
                    System.out.println("Tablero del kakuro una vez ejectuada la función computa_suma:");
                    a.printValues2();
                    break;
                case 5:
                    System.out.println("Caso 5: boardToCode");
                    System.out.println("Introduce la String del kakuro del que desea obtener su codigo:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    s = a.boardToCode(n);
                    System.out.println("String resultante del kakuruo de la entrada(deberia ser el mismo):");
                    System.out.println(s);
                    break;
                case 6:
                    System.out.println("Caso 6: generarBoard");
                    System.out.println("Introduce el tamaño deseado(>2) del tablero a Generar:");
                    n = Integer.parseInt(reader.readLine());
                    System.out.println("Introduce la cantidad de casillas negras:");
                    negres = Integer.parseInt(reader.readLine());
                    a.generarBoard(n, n*n*negres/100);
                    System.out.println("Tablero generado:");
                    a.printValues2();
                    break;
                case 7:
                    System.out.println("Caso 7: check_zeros");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s= reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    a.check_zeros(n);
                    System.out.println("Tablero del kakuro una vez ejectuada la función check_zeros:");
                    a.printValues2();
                    break;
                case 8:
                    System.out.println("Caso 8: omplir_smart2");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    a.omplir_smart2(n);
                    System.out.println("Tablero del kakuro una vez ejectuada la función omplir_smart2:");
                    a.printValues2();
                    break;
                case 9:
                    System.out.println("Caso 9: omplir_smart");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    int er = a.omplir_smart(n);
                    System.out.println("Codigo de error:"+er);
                    if(er==-1) {
                        System.out.println("Error -1, tablero incompleto es:");
                    }
                    else System.out.println("Tablero del kakuro una vez ejectuada la función omplir_smart:");
                    a.printValues2();
                    break;
                case 10:
                    System.out.println("Caso 10: sumes_paraleles");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    System.out.println("Tablero del kakuro con la String introducida por el usuario:");
                    a.printValues2();
                    boolean sortida = a.sumes_paraleles(n);
                    System.out.println("Codigo de error:"+sortida);
                    if(sortida) {
                        System.out.println("Sumes paralelas ha detectado alguna suma paralela");
                    }
                    else System.out.println("Sumes paralelas NO ha detectado ninguna suma paralela");
                    break;
                    default:
                    System.out.println("Introduzca un entero entre el 0 y el 5");
                    break;
                case 11:
                    System.out.println("Caso 11: código error omplir_smart");
                    System.out.println("Introduce la String del kakuro a evaluar, sin restricciones,solo casillas negras y blancas:");
                    s = reader.readLine();
                    l = "";
                    for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                    n = Integer.parseInt(l);
                    for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                    a.makeBoard(s);
                    int err = a.omplir_smart(n);
                    System.out.println("Codigo de error:"+err);
                    if(err==-1) {
                        System.out.println("Error -1");
                    }
                    else System.out.println("Todo OK");
                    break;
            }

            System.out.println("En caso de detener la ejecución introduce -1");
            cas = Integer.parseInt(reader.readLine());
        }


    }

}
