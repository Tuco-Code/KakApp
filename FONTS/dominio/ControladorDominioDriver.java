package dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ControladorDominioDriver {
    public static void main(String[] args) throws Exception {
        ControladorDominio cd = new ControladorDominio();

        System.out.println(" 0: LoadAll y SaveAll \n 1: AddProfile \n 2: AddKakuro y addStats  \n 3: Loadfromfile \n 4: GenerarKakuro \n 5: DeleteKakuro \n 6: GenerarGame \n 7: Pista \n 8: Update stats \n 9: Delete game \n 10: Cambio password \n 11: Dificultad kakuro \n 12: Delete perfil \n 13: Kakuro correcto ");

        int cas;
        String path;
        File file;
        FileWriter myWriter;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        cas = Integer.parseInt(reader.readLine());


        switch (cas) {
            case 0:
                path = "Case0";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso loadAll y SaveAll:");
                myWriter.write("Caso loadAll y SaveAll:\n\n");
                System.out.println("Imprimiremos el identificador de cada classe");
                myWriter.write("Imprimiremos el identificador de cada classe\n\n");
                cd.loadAll();
                String k = "2,2\n" +
                        "*,C1\n" +
                        "F1,?";
                int lo = cd.loginProfile("Patata","Fregida");
                if(lo == 0) cd.deleteProfile();
                cd.deleteKakuro(k);
                cd.loadAll();
                ArrayList<Kakuro> programKakuros = cd.getProgramKakuros();
                ArrayList<Profile> programProfiles = cd.getProgramProfiles();
                ArrayList<Stats> programStats = cd.getProgramStats();
                ArrayList<Game> programGames = cd.getProgramGames();
                System.out.println("Kakuros:");
                myWriter.write("Kakuros:\n");
                for (int i = 0; i < programKakuros.size(); ++i) {
                    myWriter.write(programKakuros.get(i).getCode() + "\n" + "\n");
                    System.out.println(programKakuros.get(i).getCode());
                    System.out.println();
                }
                System.out.println("Profiles:");
                myWriter.write("Profiles:\n");
                for (int i = 0; i < programProfiles.size(); ++i) {
                    myWriter.write(programProfiles.get(i).getName() + "\n" + "\n");
                    System.out.println(programProfiles.get(i).getName());
                    System.out.println();
                }
                System.out.println("Stats:");
                myWriter.write("Stats:\n");
                for (int i = 0; i < programStats.size(); ++i) {
                    myWriter.write(programStats.get(i).getCode() + "\n" + "\n");
                    System.out.println(programStats.get(i).getCode());
                    System.out.println();
                }
                System.out.println("Games:");
                myWriter.write("Games:\n");
                for (int i = 0; i < programGames.size(); ++i) {
                    myWriter.write(programGames.get(i).getKakuroReference() + "\n" + "\n");
                    System.out.println(programGames.get(i).getKakuroReference());
                    System.out.println();
                }



                Kakuro ka = new Kakuro(k);
                Testing_Hash tes = new Testing_Hash();
                String pas = "Fregida";
                pas = tes.passwordToHash(pas);
                Profile p = new Profile("Patata",pas);
                programKakuros.add(ka);
                programProfiles.add(p);
                programStats.add(new Stats(k));
                programGames.add(new Game(ka, p));

                cd.setProgramGames(programGames);
                cd.setProgramKakuros(programKakuros);
                cd.setProgramStats(programStats);
                cd.setProgramProfiles(programProfiles);

                cd.saveAll();
                cd.loadAll();

                programKakuros = cd.getProgramKakuros();
                programProfiles = cd.getProgramProfiles();
                programStats = cd.getProgramStats();
                programGames = cd.getProgramGames();

                myWriter.write("Datos con los nuevos perfiles,kakuros,games y stats\n" + "\n" + "Kakuros:\n");
                System.out.println("Datos con los nuevos perfiles,kakuros,games y stats");
                System.out.println("Kakuros:");
                for (int i = 0; i < programKakuros.size(); ++i) {
                    myWriter.write(programKakuros.get(i).getCode() + "\n" + "\n");
                    System.out.println(programKakuros.get(i).getCode());
                    System.out.println();
                }
                System.out.println("Profiles:");
                myWriter.write("Profiles:\n");
                for (int i = 0; i < programProfiles.size(); ++i) {
                    myWriter.write(programProfiles.get(i).getName() + "\n" + "\n");
                    System.out.println(programProfiles.get(i).getName());
                    System.out.println();
                }
                System.out.println("Stats:");
                myWriter.write("Stats:\n");
                for (int i = 0; i < programStats.size(); ++i) {
                    myWriter.write(programStats.get(i).getCode() + "\n" + "\n");
                    System.out.println(programStats.get(i).getCode());
                    System.out.println();
                }
                System.out.println("Games:");
                myWriter.write("Games:\n");
                for (int i = 0; i < programGames.size(); ++i) {
                    myWriter.write(programGames.get(i).getKakuroReference() + "\n" + "\n");
                    System.out.println(programGames.get(i).getKakuroReference());
                    System.out.println();
                }
                myWriter.close();

                break;
            case 1:
                path = "Case1";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso addProfile:");
                myWriter.write("Caso addProfile:\n\n");
                int res = cd.loginProfile("prueba","123");
                if (res == 0) cd.deleteProfile();
                cd.loadAll();
                System.out.println("Old profiles:");
                myWriter.write("Old profiles:\n");
                ArrayList<Profile> programProf = cd.getProgramProfiles();
                for (int i = 0; i < programProf.size(); ++i) {
                    myWriter.write(programProf.get(i).getName() + "\n\n");
                    System.out.println(programProf.get(i).getName());
                }
                System.out.println();
                cd.addProfile("prueba", "123");
                programProf = cd.getProgramProfiles();
                System.out.println("Old + new profiles:");
                myWriter.write("\n" + "Old + new profiles:\n\n");
                for (int i = 0; i < programProf.size(); ++i) {
                    myWriter.write(programProf.get(i).getName() + "\n\n");
                    System.out.println(programProf.get(i).getName());
                }
                myWriter.close();
                break;
            case 2:
                path = "Case2";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso addKakuro y addStats:");
                myWriter.write("Caso addKakuro y addStats:\n\n");
                String rawData3 = "2,2\n"
                        + "*,C4\n"
                        + "F4,?\n";
                cd.deleteKakuro(rawData3);
                cd.loadAll();
                System.out.println("Old kakuros:");
                myWriter.write("Old kakuros:\n\n");
                ArrayList<Kakuro> kak = cd.getProgramKakuros();
                for (int i = 0; i < kak.size(); ++i) {
                    myWriter.write(kak.get(i).getCode() + "\n\n");
                    System.out.println(kak.get(i).getCode());
                    System.out.println();
                }
                System.out.println();
                cd.addKakuro(rawData3, Source.USER);
                kak = cd.getProgramKakuros();
                System.out.println("Old + new kakuros:");
                myWriter.write("\n" + "Old + new kakuros:\n\n");
                for (int i = 0; i < kak.size(); ++i) {
                    myWriter.write(kak.get(i).getCode() + "\n\n");
                    System.out.println(kak.get(i).getCode());
                    System.out.println();
                }
                myWriter.close();
                break;
            case 3:
                path = "Case3";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso loadFromFile:");
                myWriter.write("Caso loadFromFile:\n\n");
                String pat = new String("../../DATA/boards/test.txt");

                System.out.println("Old Kakuros:");
                myWriter.write("Old Kakuros:\n\n");
                cd.loadAll();
                String del = "8,8\n" +
                        "*,*,*,*,C5,C7,*,*\n" +
                        "*,*,C10,C5F3,?,?,C6,*\n" +
                        "*,C6F15,?,?,?,?,?,*\n" +
                        "F6,?,?,?,F3,?,?,*\n" +
                        "F5,?,?,C16,C23,*,*,*\n" +
                        "F20,?,?,?,?,C10,C13,C15\n" +
                        "*,*,F34,?,?,?,?,?\n" +
                        "*,*,*,F30,?,?,?,?";
                cd.deleteKakuro(del);
                cd.loadAll();
                ArrayList<Kakuro> programKaku = new ArrayList<>(cd.getProgramKakuros());
                for (int i = 0; i < programKaku.size(); ++i) {
                    myWriter.write(programKaku.get(i).getCode() + "\n\n");
                    System.out.println(programKaku.get(i).getCode());
                    System.out.println();
                }

                int error = cd.loadFromFile(pat);
                System.out.println(error);

                cd.loadAll();
                programKaku = new ArrayList<>(cd.getProgramKakuros());
                myWriter.write("Old + new Kakuros:\n\n");
                System.out.println("Old + new Kakuros:");
                for (int i = 0; i < programKaku.size(); ++i) {
                    myWriter.write(programKaku.get(i).getCode() + "\n\n");
                    System.out.println(programKaku.get(i).getCode());
                    System.out.println();
                }
                myWriter.close();
                break;
            case 4:
                path = "Case4";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                myWriter.write("Caso Generar Kakuro:\n\n");
                System.out.println("Caso Generar Kakuro:");
                cd.loadAll();
                ArrayList<Kakuro> programKakuros1 = cd.getProgramKakuros();
                myWriter.write("Old kakuros:\n\n");
                System.out.println("Old kakuros:");
                for (int i = 0; i < programKakuros1.size(); ++i) {
                    myWriter.write(programKakuros1.get(i).getCode() + "\n\n");
                    System.out.println(programKakuros1.get(i).getCode());
                    System.out.println();
                }
                cd.generarKakuro(4, 40, 0);
                System.out.println();
                programKakuros1 = cd.getProgramKakuros();
                System.out.println("Old + new 4x4 kakuro:");
                myWriter.write("Old + new 4x4 kakuro:\n\n");
                for (int i = 0; i < programKakuros1.size(); ++i) {
                    myWriter.write(programKakuros1.get(i).getCode() + "\n\n");
                    System.out.println(programKakuros1.get(i).getCode());
                    System.out.println();
                }
                myWriter.close();
                break;
            case 5:
                path = "Case5";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso Delete Kakuro:");
                myWriter.write("Caso Delete Kakuro:\n\n");
                cd.loadAll();
                ArrayList<Kakuro> programKakuros2 = cd.getProgramKakuros();
                System.out.println("Old kakuros:");
                myWriter.write("Old kakuros:\n\n");
                for (int i = 0; i < programKakuros2.size(); ++i) {
                    myWriter.write(programKakuros2.get(i).getCode() + "\n\n");
                    System.out.println(programKakuros2.get(i).getCode());
                    System.out.println();
                }
                System.out.println();
                String aux = programKakuros2.get(0).getCode();
                cd.deleteKakuro(aux);
                programKakuros2 = cd.getProgramKakuros();
                System.out.println("First kakuro deleted:");
                myWriter.write("First kakuro deleted:\n\n");
                for (int i = 0; i < programKakuros2.size(); ++i) {
                    myWriter.write(programKakuros2.get(i).getCode() + "\n\n");
                    System.out.println(programKakuros2.get(i).getCode());
                    System.out.println();
                }
                myWriter.close();
                break;
            case 6:
                path = "Case6";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso Crear Game:");
                myWriter.write("Caso Crear Game:\n\n");
                cd.loadAll();
                System.out.println("Old Games:");
                myWriter.write("Old Games:\n\n");
                ArrayList<Game> programGames1 = cd.getProgramGames();
                for (int i = 0; i < programGames1.size(); ++i) {
                    myWriter.write(programGames1.get(i).getGameBoard() + "\n\n");
                    System.out.println(programGames1.get(i).getGameBoard());
                    System.out.println();
                }
                System.out.println();
                String rawData2 = "2,2\n"
                        + "*,C8\n"
                        + "F8,?\n";
                System.out.println("Kakuro con el que se va a crear la partida:");
                myWriter.write("\n" + "Kakuro con el que se va a crear la partida:\n\n" + rawData2 + "\n\n");
                System.out.println(rawData2);
                System.out.println();
                cd.loginProfile("test", "test");
                cd.crearGame(rawData2);
                String aux1 = cd.getCurrentGame();
                myWriter.write("Old + new game:");
                System.out.println("Old + new game:");
                for (int i = 0; i < programGames1.size(); ++i) {
                    myWriter.write(programGames1.get(i).getGameBoard() + "\n\n");
                    System.out.println(programGames1.get(i).getGameBoard());
                    System.out.println();
                }
                myWriter.write(aux1 + "\n\n");
                System.out.println(aux1);
                System.out.println();
                myWriter.close();
                break;
            case 7:
                path = "Case7";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso Pista:");
                myWriter.write("Caso Pista:\n\n");
                String rawData = "9,9\n"
                        + "*,*,C19,C12,*,*,*,C7,C10\n"
                        + "*,F14,?,?,C4,C11,C17F4,?,?\n"
                        + "*,C7F36,?,?,?,?,?,?,?\n"
                        + "F12,?,?,F10,?,?,?,C25,C14\n"
                        + "F3,?,?,C20,C11F20,?,?,?,?\n"
                        + "F17,?,?,?,?,C8,F6,?,?\n"
                        + "*,C11,C7F13,?,?,?,C4F10,?,?\n"
                        + "F28,?,?,?,?,?,?,?,*\n"
                        + "F6,?,?,*,*,F8,?,?,*\n";
                System.out.println("Kakuro del que se va a dar una pista:");
                System.out.println(rawData);
                myWriter.write("Kakuro del que se va a dar una pista:\n\n" + rawData + "\n\n");
                cd.loginProfile("test", "test");
                cd.crearGame(rawData);
                String saux1 = cd.getCurrentGame();
                Triple taux = cd.pista();
                System.out.println("Fila: " + (taux.first() + 1) + " Columna: " + (taux.second() + 1) + " Valor: " + taux.third());
                System.out.println();
                System.out.println("Solucion del kakuro:");
                System.out.println(cd.getSolutionCurrentGame());
                myWriter.write("Fila: " + (taux.first() + 1) + " Columna: " + (taux.second() + 1) + " Valor: " + taux.third() + "\n\n" + "Solucion del kakuro:" + "\n");
                myWriter.write(cd.getSolutionCurrentGame() + "\n");
                myWriter.close();
                break;
            case 8:
                path = "Case8";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                myWriter.write("Caso update stats:\n" + "Nos conectamos con el usuario test\n");
                System.out.println("Caso update stats:");
                System.out.println("Nos conectamos con el usuario test");
                cd.loadAll();
                cd.addProfile("test","test");
                cd.loginProfile("test", "test");
                String rawData32 = "2,2\n"
                        + "*,C4\n"
                        + "F4,?";
                cd.deleteKakuro(rawData32);
                cd.addKakuro(rawData32, Source.USER);
                ArrayList<Stats> sta = cd.getProgramStats();
                myWriter.write("Stats actuales:\n");
                System.out.println("Stats actuales:");
                for (int i = 0; i < sta.size(); ++i) {
                    System.out.println("Kakuro");
                    myWriter.write("Kakuro\n" + sta.get(i).getCode() + "\n" + "Top times\n");
                    myWriter.write("   " + sta.get(i).getPosTime(1) + " " + sta.get(i).getPosTime(2) + " " + sta.get(i).getPosTime(3) + "\n" + "Top times owner\n");
                    myWriter.write("   " + sta.get(i).getPosOwner(1) + " " + sta.get(i).getPosOwner(2) + " " + sta.get(i).getPosOwner(3) + "\n");
                    System.out.println(sta.get(i).getCode());
                    System.out.println("Top times");
                    System.out.println("   " + sta.get(i).getPosTime(1) + " " + sta.get(i).getPosTime(2) + " " + sta.get(i).getPosTime(3));
                    System.out.println("Top times owner");
                    System.out.println("   " + sta.get(i).getPosOwner(1) + " " + sta.get(i).getPosOwner(2) + " " + sta.get(i).getPosOwner(3));
                    System.out.println();
                    myWriter.write("\n\n");
                }
                System.out.println();
                System.out.println("Kakuro del qual se van a modificar sus stats:");
                System.out.println(rawData32);
                System.out.println();
                myWriter.write("\n" + "Kakuro del qual se van a modificar sus stats:\n" + rawData32 + "\n\n");
                cd.crearGame(rawData32);
                cd.updateStats(rawData32);
                sta = cd.getProgramStats();
                myWriter.write("Stats modificadas:\n\n");
                System.out.println("Stats modificadas:");
                for (int i = 0; i < sta.size(); ++i) {
                    System.out.println("Kakuro");
                    myWriter.write("Kakuro\n" + sta.get(i).getCode() + "\n" + "Top times\n");
                    myWriter.write("   " + sta.get(i).getPosTime(1) + " " + sta.get(i).getPosTime(2) + " " + sta.get(i).getPosTime(3) + "\n" + "Top times owner\n");
                    myWriter.write("   " + sta.get(i).getPosOwner(1) + " " + sta.get(i).getPosOwner(2) + " " + sta.get(i).getPosOwner(3) + "\n");
                    System.out.println(sta.get(i).getCode());
                    System.out.println("Top times");
                    System.out.println("   " + sta.get(i).getPosTime(1) + " " + sta.get(i).getPosTime(2) + " " + sta.get(i).getPosTime(3));
                    System.out.println("Top times owner");
                    System.out.println("   " + sta.get(i).getPosOwner(1) + " " + sta.get(i).getPosOwner(2) + " " + sta.get(i).getPosOwner(3));
                    System.out.println();
                    myWriter.write("\n\n");
                }
                myWriter.write("Como se puede ver, el tiempo se ha actualizado a 0\n\n");
                System.out.println("Como se puede ver, el tiempo se ha actualizado a 0");
                myWriter.close();
                break;
            case 9:
                path = "Case9";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                myWriter.write("Caso delete Game:\n\n" + "Old Games:\n\n");
                System.out.println("Caso delete Game:");
                String rawData4 = "2,2\n"
                        + "*,C3\n"
                        + "F3,?\n";
                cd.deleteKakuro(rawData4);
                cd.loadAll();
                System.out.println("Old Games:");
                ArrayList<Game> programGames12 = cd.getProgramGames();
                for (int i = 0; i < programGames12.size(); ++i) {
                    myWriter.write(programGames12.get(i).getGameBoard() + "\n\n");
                    System.out.println(programGames12.get(i).getGameBoard());
                    System.out.println();
                }
                System.out.println();
                System.out.println("Kakuro con el que se va a crear la partida:");
                System.out.println(rawData4);
                System.out.println();
                myWriter.write("\n\n" + "Kakuro con el que se va a crear la partida:\n\n" + rawData4 + "\n\n");
                cd.loginProfile("test", "test");
                cd.crearGame(rawData4);
                String aux1a = cd.getCurrentGame();
                System.out.println("Old + new game:");
                myWriter.write("Old + new game:\n\n");
                for (int i = 0; i < programGames12.size(); ++i) {
                    myWriter.write(programGames12.get(i).getGameBoard() + "\n\n");
                    System.out.println(programGames12.get(i).getGameBoard());
                    System.out.println();
                }
                System.out.println(aux1a);
                System.out.println();
                System.out.println("Ahora vamos a borrar la partida creada");
                myWriter.write(aux1a + "\n\n" + "Ahora vamos a borrar la partida creada\n\n");
                cd.deleteGame(rawData4);
                programGames12 = cd.getProgramGames();
                for (int i = 0; i < programGames12.size(); ++i) {
                    myWriter.write(programGames12.get(i).getGameBoard() + "\n\n");
                    System.out.println(programGames12.get(i).getGameBoard());
                    System.out.println();
                }
                myWriter.close();
                break;
            case 10:
                path = "Case10";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso Cambio de password:");
                myWriter.write("Caso Cambio de password:\n\n");
                Testing_Hash t = new Testing_Hash();
                cd.addProfile("kakuro", "prop9.3");
                System.out.println("Creamos un perfil con nombre kakuro y contrasena prop9.3");
                myWriter.write("Creamos un perfil con nombre kakuro y contrasena prop9.3\n\n");
                System.out.println("Tratamos de cambiar la contrasena a kak");
                myWriter.write("Tratamos de cambiar la contrasena a kak\n");
                if (cd.changePassword("prop9.3", "kak") != 0) {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                if (cd.loginProfile("kakuro","kak") == 0) {
                    System.out.println("Password changed to kak!");
                    myWriter.write("Password changed to kak!\n\n");
                }
                else {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                System.out.println("Vamos a intentar cambiar la contrasena poniendo la contrasena actual de forma erronea, asi que deberia saltar un error");
                myWriter.write("Vamos a intentar cambiar la contrasena poniendo la contrasena actual de forma erronea, asi que deberia saltar un error\n");
                if (cd.changePassword("kakuriosities", "kakk") == -1) {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                else {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                System.out.println("Vamos a intentar cambiar la contrasena poniendo la misma, deberia saltar un error");
                myWriter.write("Vamos a intentar cambiar la contrasena poniendo la misma, deberia saltar un error\n");
                if (cd.changePassword("kak", "kak") == -2) {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                else {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                cd.deleteProfile();
                myWriter.close();
                break;
            case 11:
                path = "Case11";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso obtener dificultad de kakuro:");
                myWriter.write("Caso obtener dificultad de kakuro:\n");
                String kk = "2,2\n*,C1\nF1,?";
                System.out.println("Vamos a calcular la dificultad del kakuro:");
                System.out.println(kk);
                System.out.println();
                System.out.println("Deberia darnos dificultad facil");
                myWriter.write("Vamos a calcular la dificultad del kakuro:\n\n" + kk + "\n\n" + "Deberia darnos dificultad facil\n");
                Difficulty dif = cd.getDifficulty(kk);
                if (dif != Difficulty.EASY) {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                else {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                kk = "8,8\n*,*,*,*,C5,C7,*,*\n*,*,C10,C5F3,?,?,C6,*\n" +
                        "*,C6F15,?,?,?,?,?,*\nF6,?,?,?,F3,?,?,*\nF5,?,?,C16,C23,*,*,*\n" +
                        "F20,?,?,?,?,C10,C13,C15\n*,*,F34,?,?,?,?,?\n*,*,*,F30,?,?,?,?";
                dif = cd.getDifficulty(kk);
                System.out.println("Vamos a calcular la dificultad del kakuro:");
                System.out.println(kk);
                System.out.println();
                System.out.println("Deberia darnos dificultad media");
                myWriter.write("Vamos a calcular la dificultad del kakuro:\n\n" + kk + "\n\n" + "Deberia darnos dificultad media\n");
                if (dif != Difficulty.MEDIUM) {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                else {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                kk = "6,6\n*,C13,C7,C9,*,*\nF7,?,?,?,C29,*\n" +
                        "F30,?,?,?,?,C21\n*,C9,C5,C10F17,?,?\n" +
                        "F34,?,?,?,?,?\nF15,?,?,?,?,?";
                dif = cd.getDifficulty(kk);
                System.out.println("Vamos a calcular la dificultad del kakuro:");
                System.out.println(kk);
                System.out.println();
                System.out.println("Deberia darnos dificultad dificil");
                myWriter.write("Vamos a calcular la dificultad del kakuro:\n\n" + kk + "\n\n" + "Deberia darnos dificultad dificil\n");
                if (dif != Difficulty.HARD) {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                else {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                kk = "6,6\n*,*,*,C6,C15,*\n*,*,C10F3,2,1,C8\n" +
                        "*,C8F10,3,?,?,?\nF6,5,1,C4F5,3,?\n" +
                        "F15,1,?,?,?,?\nF12,2,4,1,?,*";
                dif = cd.getDifficulty(kk);
                System.out.println("Vamos a calcular la dificultad del kakuro:");
                System.out.println(kk);
                System.out.println();
                System.out.println("Deberia darnos dificultad facil");
                myWriter.write("Vamos a calcular la dificultad del kakuro:\n\n" + kk + "\n\n" + "Deberia darnos dificultad facil\n");
                if (dif != Difficulty.EASY) {
                    System.out.println("Error!");
                    myWriter.write("Error!\n\n");
                }
                else {
                    System.out.println("Well done!");
                    myWriter.write("Well done!\n\n");
                }
                myWriter.close();
                break;
            case 12:
                path = "Case12";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso Eliminar Perfil:");
                myWriter.write("Caso Eliminar Perfil:\n" + "Current profiles before deleting:\n");
                cd.loadAll();
                System.out.println("Current profiles before deleting:");
                ArrayList<Profile> programPr = cd.getProgramProfiles();
                cd.addProfile("prop", "1234");
                for (int i = 0; i < programPr.size(); ++i) {
                    myWriter.write(programPr.get(i).getName() + "\n");
                    System.out.println(programPr.get(i).getName());
                }
                System.out.println();
                System.out.println("We login with our profile: prop");
                myWriter.write("\n\n" + "We login with our profile: prop\n\n" + "After deleting our profile:\n\n");
                cd.loginProfile("prop", "1234");
                cd.deleteProfile();
                programPr = cd.getProgramProfiles();
                System.out.println("After deleting our profile:");
                for (int i = 0; i < programPr.size(); ++i) {
                    myWriter.write(programPr.get(i).getName() + "\n");
                    System.out.println(programPr.get(i).getName());
                }
                myWriter.close();
                break;
            case 13:
                path = "Case13";
                file = new File(path);
                file.delete();
                myWriter = new FileWriter(path,true);
                System.out.println("Caso isCorrect de un Kakuro:");
                System.out.println("Introduzca la String del Kakuro:");
                myWriter.write("Caso isCorrect de un Kakuro:\n\n" + "Introduzca la String del Kakuro:\n\n");
                int n;
                String s;
                String l;
                s = reader.readLine();
                l = "";
                for (int i = 0; i < s.length() && s.charAt(i) != ','; ++i) l += s.charAt(i);
                n = Integer.parseInt(l);
                for (int i = 0; i < n; ++i) s += "\n" + reader.readLine();
                System.out.println("Vamos a comprobar si el kakuro introducido esta correctamente formateado");
                myWriter.write("Kakuro recibido:\n" + s + "\n\n");
                myWriter.write("Vamos a comprobar si el kakuro introducido esta correctamente formateado\n\n");
                if (cd.isCorrect(s)) {
                    System.out.println("Correct!");
                    myWriter.write("Correct!\n");
                }
                else {
                    System.out.println("Incorrect!");
                    myWriter.write("Incorrect!\n");
                }
                myWriter.close();
                break;
        }
    }
}

