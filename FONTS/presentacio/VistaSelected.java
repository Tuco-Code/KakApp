package presentacio;

import dominio.BlackKakuroCell;
import dominio.Kakuro;
import dominio.KakuroCell;
import dominio.WhiteKakuroCell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class VistaSelected extends JFrame{
    JButton exit,play,delete;

    public VistaSelected(String Kakuros){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Center = new JPanel(new GridLayout(1,2,10,10));
        cp.add(p_Center,"Center");

        Kakuro k = new Kakuro(Kakuros);
        KakuroCell[][] board = k.getBoard();
        int tam = board[0].length;



        JPanel p_West = new JPanel();
        p_West.setLayout(new GridLayout(tam,tam,1,1));
        p_West.setSize(240,240);
        p_Center.add(p_West);


        JTextField[][] tablero = new JTextField[tam][tam];

        for(int i=0; i<tam; i++){
            for(int j =0; j<tam; j++){
                tablero[i][j] = new JTextField("");
                Font f = tablero[i][j].getFont();
                tablero[i][j].setFont(new Font(f.getName(),f.getStyle(),12));
                if(!board[i][j].isWhite()){
                    tablero[i][j].setBackground(Color.BLACK);
                    tablero[i][j].setForeground(Color.WHITE);
                    if(((BlackKakuroCell) board[i][j]).isRestriction()) tablero[i][j].setText(((BlackKakuroCell) board[i][j]).getRestriction());
                }
                else{
                    int n = ((WhiteKakuroCell) board[i][j]).getDigit();
                    if(n!=0){
                        tablero[i][j].setForeground(Color.BLACK);
                        tablero[i][j].setText( n+"");
                    }

                }
                tablero[i][j].setEditable(false);
                p_West.add(tablero[i][j]);
            }

        }


        String[] Stats = ControladorPresentacio.getStatsOfKakuro(Kakuros);

        JPanel graella_stats = new JPanel(new GridLayout(4,2,50,10));
        p_Center.add(graella_stats);

        graella_stats.add(new JLabel("User"));
        graella_stats.add(new JLabel("Time"));

        JLabel[][] labels = new JLabel[3][2];

        int cont =0;
        for(int i=0; i<3; ++i){
            for(int j=0; j<2; ++j){
                labels[i][j] = new JLabel(Stats[cont]);
                graella_stats.add(labels[i][j]);
                ++cont;
            }
        }


        //PART SUD

        JPanel p_South = new JPanel(new BorderLayout());
        cp.add(p_South,"South");

        exit =new JButton("Exit");
        p_South.add(exit,"West");


        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p_South.add(p,"East");

        play =new JButton("Play / Solve");
        p.add(play);

        delete =new JButton("Delete Kakuro");
        p.add(delete);


        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean b =ControladorPresentacio.existeGame(Kakuros);
                if(b){
                    ControladorPresentacio.VistaExiste("You have already started a game with this Kakuro. Do you want to continue it?",Kakuros);
                }
                else{
                    ControladorPresentacio.crearGame(Kakuros);
                    ControladorPresentacio.VistaKakuro(Kakuros,0);

                }
                setVisible(false);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaRepo(0,0);
                setVisible(false);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaDeleteKakuro("Are you sure you want to delete the Kakuro?",Kakuros);
                setVisible(false);

            }
        });

        try {
            InputStream imgStream = VistaSelected.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(500,800);
        setVisible(true);




    }

}
