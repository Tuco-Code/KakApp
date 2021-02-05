package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import dominio.*;

public class VistaKakuro extends JFrame {
    //ATR
    JButton pista, validar, resoldre,exit;
    JCheckBox[] Numeros;
    JTextField[][] tablero;

    private Pair trobar_pos(JTextField source){
        for(int i=0; i<tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
             if(tablero[i][j]==source) return new Pair(i,j);
            }
        }
        return new Pair(-1,-1);
    }




    public VistaKakuro(String kakuro,int t){

        class JTextFieldLimit extends PlainDocument{
            private int limit;
            JTextFieldLimit(int limit) {
                super();
                this.limit = limit;
            }
            JTextFieldLimit(int limit, boolean upper) {
                super();
                this.limit = limit;
            }
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= limit) {
                    super.insertString(offset, str, attr);
                }
            }

        }

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        /////////////////////////////////////
        /////        North Part        /////
        ///////////////////////////////////

        JPanel p_North = new JPanel();
        p_North.setLayout(new BoxLayout(p_North,1));
        cp.add(p_North,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        p_North.add(panel);
        panel.add(new JLabel("Info: A number will not be considered until you press enter on the cell"));

        DigitalWatch d = new DigitalWatch(t);
        p_North.add(d);

        JPanel num = new JPanel();
        num.setLayout(new FlowLayout());
        p_North.add(num);

        Numeros = new JCheckBox[9];
        for(int i =0; i<Numeros.length; i++){
            Numeros[i] = new JCheckBox("Mark "+(i+1));
            num.add(Numeros[i]);
        }




        /////////////////////////////////////
        /////      Central Part        /////
        ///////////////////////////////////
        Kakuro k = new Kakuro(kakuro);
        KakuroCell[][] board = k.getBoard();
        int tam = board[0].length;

        String board_act = ControladorPresentacio.getReferenceGame();


        JPanel p_Center = new JPanel();
        p_Center.setLayout(new GridLayout(tam,tam,1,1));
        cp.add(p_Center,BorderLayout.CENTER);
        //p_Center.setBackground(Color.RED);

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
                    tablero[i][j].setEditable(false);
                }
                else{
                    int n = ((WhiteKakuroCell) board[i][j]).getDigit();
                    if(n!=0){
                        tablero[i][j].setForeground(Color.BLACK);
                        tablero[i][j].setText( n+"");
                        if(kakuro==board_act)tablero[i][j].setEditable(false);
                    }
                    else tablero[i][j].setDocument(new JTextFieldLimit(1));
                }
                p_Center.add(tablero[i][j]);
            }

        }

        /////////////////////////////////////
        /////        South Part        /////
        ///////////////////////////////////

        JPanel p_South = new JPanel();
        p_South.setLayout(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);

        JPanel esq = new JPanel();
        esq.setLayout(new FlowLayout(FlowLayout.LEFT));
        p_South.add(esq,BorderLayout.WEST);

        JPanel dre = new JPanel();
        esq.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p_South.add(dre,BorderLayout.EAST);

        JPanel central = new JPanel();
        central.setLayout(new FlowLayout());
        p_South.add(central,BorderLayout.CENTER);



        pista = new JButton("Hint");
        dre.add(pista);

        resoldre = new JButton("Show Solution");
        dre.add(resoldre);

        //p_South.setLayout(new FlowLayout(FlowLayout.RIGHT));
        validar = new JButton("Validate");
        central.add(validar);

        exit = new JButton("Exit");
        esq.add(exit);





        //LISTENERS
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ControladorPresentacio.VistaPopup("Do you want to Save before Exit?",d.getTime());
                setVisible(false);
            }
        });



        ActionListener mark = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<tam; ++i){
                    for(int j=0; j<tam; ++j) {
                        String s = tablero[i][j].getText();
                        if (s.length() == 1) {
                            int n = Integer.parseInt(s);
                            if (Numeros[n - 1].isSelected()) tablero[i][j].setForeground(Color.BLUE);
                            else tablero[i][j].setForeground(Color.BLACK);

                        }
                    }
                }
            }
        };

        ActionListener teclat = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField source = (JTextField) e.getSource();
                String s = source.getText();
                if (s.length() == 1) {
                    Character c = s.charAt(0);
                    if (c < '1' || c > '9') source.setText("");
                    else if (Numeros[Integer.parseInt(s) - 1].isSelected()) source.setForeground(Color.BLUE);
                    else source.setForeground(Color.BLACK);
                }

                s = source.getText();
                Pair p = trobar_pos(source);
                int val;
                if(s.length()==0) val = 0;
                else val = Integer.parseInt(s);
                ControladorPresentacio.addValue(p.first(), p.second(), val);



            }

        };



        for(int i=0; i<tam; ++i) {
            for (int j = 0; j < tam; ++j) {
                tablero[i][j].addActionListener(teclat);

            }
        }

        for(int i=0; i<Numeros.length; i++){
            Numeros[i].addActionListener(mark);
        }


        validar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.changeGameTime(d.getTime());
                Boolean b = ControladorPresentacio.validate();
                if(b){
                    ControladorPresentacio.VistaCongrats();
                    setVisible(false);
                }
                else{
                    ControladorPresentacio.VistaWrong();
                    setVisible(false);

                }

            }
        });

        pista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Triple t = ControladorPresentacio.pista();
                if(t.first() != -1) {
                    tablero[t.first()][t.second()].setText(t.third() + "");
                    tablero[t.first()][t.second()].setEditable(false);
                    ControladorPresentacio.addValue(t.first(), t.second(), t.third());
                }
            }
        });

        resoldre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = ControladorPresentacio.getKkrSolved();
                ControladorPresentacio.VistaSolution(s,ControladorPresentacio.getBoardGame(),0);
                ControladorPresentacio.changeGameTime(d.getTime());
                setVisible(false);
            }
        });
        try {
            InputStream imgStream = VistaKakuro.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(800,800);
        setVisible(true);

    }


}
