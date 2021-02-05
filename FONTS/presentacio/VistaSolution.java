package presentacio;

import dominio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaSolution extends JFrame {
    //ATR
    JButton show, exit;
    JTextField[][] tablero;
    JLabel text;


    public VistaSolution(String sol,String given_sol,int kak){

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        /////////////////////////////////////
        /////        North Part        /////
        ///////////////////////////////////

        JPanel p_North = new JPanel();
        p_North.setLayout(new BoxLayout(p_North,1));
        cp.add(p_North,BorderLayout.NORTH);

        JPanel p_text = new JPanel(new FlowLayout());
        JPanel p_temps = new JPanel(new FlowLayout());
        p_North.add(p_text);
        p_North.add(p_temps);

        text = new JLabel();
        if(kak==0) {
            text.setText("Solution:");
            p_text.add(text);
            p_temps.add(new JLabel("Automatic solving time is "+ControladorPresentacio.getGameTime_solution()+ " seconds"));

        }
        else {
            text.setText("Your Kakuro state:");
            p_text.add(text);
            p_temps.add(new JLabel("Your solving time is "+ControladorPresentacio.getGameTime()+" seconds"));
        }




        /////////////////////////////////////
        /////      Central Part        /////
        ///////////////////////////////////

        Kakuro k = new Kakuro(sol);
        KakuroCell[][] board = k.getBoard();
        int tam = board[0].length;


        JPanel p_Center = new JPanel();
        p_Center.setLayout(new GridLayout(tam,tam,1,1));
        cp.add(p_Center,BorderLayout.CENTER);

        tablero = new JTextField[tam][tam];

        for(int i=0; i<tam; i++){
            for(int j =0; j<tam; j++){
                tablero[i][j] = new JTextField("");
                Font f = tablero[i][j].getFont();
                tablero[i][j].setFont(new Font(f.getName(),f.getStyle(),20));
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
                        tablero[i][j].setEditable(false);
                    }
                }
                tablero[i][j].setEditable(false);
                p_Center.add(tablero[i][j]);
            }

        }

        /////////////////////////////////////
        /////        South Part        /////
        ///////////////////////////////////

        JPanel p_South = new JPanel();
        p_South.setLayout(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);


        show = new JButton();
        if(kak==0) show.setText("Show your Kakuro state");
        else show.setText("Show solution");
        p_South.add(show,BorderLayout.EAST);

        exit = new JButton("Exit");
        p_South.add(exit,BorderLayout.WEST);



        //LISTENERS
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaRepo(0,0);
                setVisible(false);
                ControladorPresentacio.deleteGame(ControladorPresentacio.getReferenceGame());
            }
        });



        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kak==0)ControladorPresentacio.VistaSolution(given_sol,sol,1);
                else ControladorPresentacio.VistaSolution(given_sol,sol,0);
                setVisible(false);
            }
        });

        try {
            InputStream imgStream = VistaSolution.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(700,700);
        setVisible(true);

    }
}
