package presentacio;

import dominio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaWorkshop extends JFrame{
    JButton generate,accept,exit;
    JSpinner tam;

    JTextField[][] tablero;
    Boolean[][] whites;
    JPanel p_Center;

    private Pair trobar_pos(JTextField source){
        for(int i=0; i<tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j]==source) return new Pair(i,j);
            }
        }
        return new Pair(-1,-1);
    }


    public VistaWorkshop(int t){

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_North = new JPanel();
        p_North.setLayout(new BoxLayout(p_North,1));
        cp.add(p_North,BorderLayout.NORTH);

        JPanel p_nn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p_North.add(p_nn);
        p_nn.add(new JLabel("Info: Choose size and click genereate, this will generate a new kakuro with this size. To change the celltype double right click and choose"));

        JPanel p_n = new JPanel(new FlowLayout());
        p_North.add(p_n);

        p_n.add(new JLabel("Size:"));

        SpinnerNumberModel model = new SpinnerNumberModel(t,2,20,1);
        tam = new JSpinner(model);
        p_n.add(tam);

        JPanel p_ = new JPanel(new FlowLayout());
        p_North.add(p_);

        generate = new JButton("Genearate");
        p_.add(generate);


        /////////////////////////////////////
        /////      Central Part        /////
        ///////////////////////////////////



        p_Center = new JPanel();
        p_Center.setLayout(new GridLayout(t,t,1,1));
        cp.add(p_Center,BorderLayout.CENTER);

        tablero = new JTextField[t][t];
        whites = new Boolean[t][t];

        for(int i=0; i<t; i++){
            for(int j =0; j<t; j++){
                tablero[i][j] = new JTextField("");
                whites[i][j] = true;
                Font f = tablero[i][j].getFont();
                tablero[i][j].setFont(new Font(f.getName(),f.getStyle(),20));
                if(i==0 || j==0) {
                    tablero[i][j].setBackground(Color.BLACK);
                    tablero[i][j].setForeground(Color.WHITE);
                    whites[i][j] = false;
                }
                p_Center.add(tablero[i][j]);
            }

        }


        JPanel p_South = new JPanel();
        p_South.setLayout(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);

        accept = new JButton("Accept");
        p_South.add(accept,BorderLayout.EAST);


        exit = new JButton("Exit");
        p_South.add(exit,BorderLayout.WEST);


        //LISTENERES
        MouseListener right = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField source = (JTextField) e.getSource();
                if(e.getButton()==MouseEvent.BUTTON3){
                 JPopupMenu pop = new JPopupMenu("Color");
                 JMenuItem black = new JMenuItem("Black");
                 JMenuItem white = new JMenuItem("White");

                 black.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         source.setForeground(Color.WHITE);
                         source.setBackground(Color.BLACK);
                         Pair p = trobar_pos(source);
                         whites[p.first()][p.second()] = false;
                         source.setText("");
                     }
                 });
                 white.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         source.setForeground(Color.black);
                         source.setBackground(Color.WHITE);
                         Pair p = trobar_pos(source);
                         whites[p.first()][p.second()] = true;
                         source.setText("");
                     }
                 });


                 pop.add(black); pop.add(white);

                 pop.show(source,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        };


        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaWorkshop((Integer) tam.getValue());
                setVisible(false);

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaProposarKakuro();
                setVisible(false);
            }
        });



        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = getCodi();
                int err = ControladorPresentacio.addKakuro(s);
                String text;
                if(err==-1){
                    text = new String("Kakuro already exists");

                }
                else if(err==-2){
                    text = new String("Wrong Kakuro format");
                }
                else if(err==-3){
                    text = new String("The Kakuro has 0 or more than one solutions");
                }
                else{
                    text = new String("Kakuro succesfully added");
                }
                setVisible(false);
                ControladorPresentacio.VistaWrongKakuro(text,s);
            }
        });

        for(int i=0; i<t; i++){
            for(int j =0; j<t; j++) {
                tablero[i][j].addMouseListener(right);
            }
        }

        try {
            InputStream imgStream = VistaWorkshop.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(1000,800);
        setVisible(true);
    }



    public String getCodi(){
        String s = new String(tablero.length +","+tablero.length+"\n");
        for (int i =0; i<tablero.length; ++i){
            for(int j=0; j<tablero.length; ++j){
                String aux = tablero[i][j].getText();
                if(!aux.equals(null) && !aux.equals("")) s+= aux;
                else{
                    if(whites[i][j]) s+="?";
                    else s+= "*";
                }
                if(j<tablero.length-1) s+=",";
                else if(i<tablero.length-1)s+="\n";
            }
        }
    return s;
    }







    public VistaWorkshop(String ka){

        String[] kakuro_splited = ka.split("\n");
        String[] primera_linia = kakuro_splited[0].split(",");
        int t= Integer.parseInt(primera_linia[0]);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_North = new JPanel();
        p_North.setLayout(new BoxLayout(p_North,1));
        cp.add(p_North,BorderLayout.NORTH);

        JPanel p_nn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p_North.add(p_nn);
        p_nn.add(new JLabel("Info: Choose size and click genereate, this will generate a new kakuro with this size. To change the celltype double right click and choose"));

        JPanel p_n = new JPanel(new FlowLayout());
        p_North.add(p_n);

        p_n.add(new JLabel("Size:"));

        SpinnerNumberModel model = new SpinnerNumberModel(t,2,20,1);
        tam = new JSpinner(model);
        p_n.add(tam);

        JPanel p_ = new JPanel(new FlowLayout());
        p_North.add(p_);

        generate = new JButton("Generate");
        p_.add(generate);


        /////////////////////////////////////
        /////      Central Part        /////
        ///////////////////////////////////





        p_Center = new JPanel();
        p_Center.setLayout(new GridLayout(t,t,1,1));
        cp.add(p_Center,BorderLayout.CENTER);



        whites = new Boolean[t][t];
        tablero = new JTextField[t][t];


        for(int i = 1; i<=t; ++i){
            primera_linia = kakuro_splited[i].split(",");
            for(int j =0; j<t; ++j){
                tablero[i-1][j] = new JTextField("");
                Font f = tablero[i-1][j].getFont();
                tablero[i-1][j].setFont(new Font(f.getName(),f.getStyle(),20));

                if(primera_linia[j].equals("*")){
                    tablero[i-1][j].setBackground(Color.BLACK);
                    tablero[i-1][j].setForeground(Color.WHITE);
                    whites[i-1][j] = false;
                }
                else if(primera_linia[j].equals("?")){
                    whites[i-1][j] = true;

                }
                else if(primera_linia[j].charAt(0) == 'C' || primera_linia[j].charAt(0) == 'F'){
                    tablero[i-1][j].setBackground(Color.BLACK);
                    tablero[i-1][j].setForeground(Color.WHITE);
                    whites[i-1][j] = false;
                    tablero[i-1][j].setText(primera_linia[j]);
                }
                else{
                    tablero[i-1][j].setText(primera_linia[j]);
		    whites[i-1][j] = true;
                }
                p_Center.add(tablero[i-1][j]);
            }

        }



        JPanel p_South = new JPanel();
        p_South.setLayout(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);

        accept = new JButton("Accept");
        p_South.add(accept,BorderLayout.EAST);


        exit = new JButton("Exit");
        p_South.add(exit,BorderLayout.WEST);


        //LISTENERES
        MouseListener right = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField source = (JTextField) e.getSource();
                if(e.getButton()==MouseEvent.BUTTON3){
                    JPopupMenu pop = new JPopupMenu("Color");
                    JMenuItem black = new JMenuItem("Black");
                    JMenuItem white = new JMenuItem("White");

                    black.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            source.setForeground(Color.WHITE);
                            source.setBackground(Color.BLACK);
                            Pair p = trobar_pos(source);
                            whites[p.first()][p.second()] = false;
                            source.setText("");
                        }
                    });
                    white.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            source.setForeground(Color.black);
                            source.setBackground(Color.WHITE);
                            Pair p = trobar_pos(source);
                            whites[p.first()][p.second()] = true;
                            source.setText("");
                        }
                    });


                    pop.add(black); pop.add(white);

                    pop.show(source,e.getX(),e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        };


        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaWorkshop((Integer) tam.getValue());
                setVisible(false);

            }
        });


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaProposarKakuro();
                setVisible(false);
            }
        });


        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = getCodi();
                int err = ControladorPresentacio.addKakuro(s);
                String text;
                if(err==-1){
                    text = new String("Kakuro already exists");

                }
                else if(err==-2){
                    text = new String("Wrong Kakuro format");
                }
                else if(err==-3){
                    text = new String("The Kakuro has 0 or more than one solutions");
                }
                else{
                    text = new String("Kakuro succesfully added");
                }
                setVisible(false);
                ControladorPresentacio.VistaWrongKakuro(text,s);
            }
        });


        for(int i=0; i<t; i++){
            for(int j =0; j<t; j++) {
                tablero[i][j].addMouseListener(right);


            }
        }

        try {
            InputStream imgStream = VistaWorkshop.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(1200,1200);
        setVisible(true);
    }


}
