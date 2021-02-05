package presentacio;

import dominio.Kakuro;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class VistaNouKakuro extends JFrame {
    JSpinner tam,blanques,nums;
    ButtonGroup dif;
    JRadioButtonMenuItem easy,hard,medium;
    JButton crear,exit;
    JCheckBox advance;
    JLabel l,la,ld;


    public VistaNouKakuro(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        JPanel p_Center = new JPanel(new GridLayout(2,2));
        cp.add(p_Center,BorderLayout.CENTER);

        //PART DE DALT
        JPanel p_size_resolved = new JPanel();
        p_size_resolved.setLayout(new FlowLayout());
        p_Center.add(p_size_resolved);

        p_size_resolved.add(new JLabel("Size:"));
        SpinnerNumberModel model = new SpinnerNumberModel(8,2,12,1);
        tam = new JSpinner(model);
        p_size_resolved.add(tam);


        //cp.add(new JLabel("    "));
        JPanel p = new JPanel(new GridLayout(2,1));
        p_Center.add(p);

        advance = new JCheckBox("Advanced Options");
        advance.setSelected(false);
        p.add(advance);

        JPanel p_cells = new JPanel();
        p.add(p_cells);

        la = new JLabel("White Cells percentage:");
        p_cells.add(la);
        la.setEnabled(false);


        SpinnerNumberModel model3 = new SpinnerNumberModel(40,25,55,5);
        blanques = new JSpinner(model3);
        //blanques.setPreferredSize(new Dimension(5,3));
        //blanques.setMaximumSize(new Dimension(100,10));
        p_cells.add(blanques);
        blanques.setEnabled(false);



        //PART CENTRAL

        JPanel p_Cent = new JPanel();
        p_Center.add(p_Cent);

        ld = new JLabel("Difficulty:");
        p_Cent.add(ld);

        JPanel botons = new JPanel();
        botons.setLayout(new BoxLayout(botons,1));
        p_Cent.add(botons);


        dif = new ButtonGroup();

        easy = new JRadioButtonMenuItem("Easy");
        dif.add(easy);
        botons.add(easy);

        medium = new JRadioButtonMenuItem("Medium");
        dif.add(medium);
        botons.add(medium);
        medium.setSelected(true);

        hard = new JRadioButtonMenuItem("Hard");
        dif.add(hard);
        botons.add(hard);

        //cp.add(new JLabel("    "));

        JPanel p_solved = new JPanel();
        l = new JLabel("Resolved Cells:");
        p_solved.add(l);
        l.setEnabled(false);
        int max = ControladorPresentacio.max_resoltes(8,40);
        SpinnerNumberModel model2 = new SpinnerNumberModel(0,0,max,1);
        nums = new JSpinner(model2);
        nums.setEnabled(false);
        p_solved.add(nums);

        p_Center.add(p_solved);



        //PART SUD
        JPanel p_South = new JPanel(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);

        JPanel pLabel =  new JPanel(new FlowLayout());
        p_South.add(pLabel,BorderLayout.NORTH);
        pLabel.add(new JLabel("Generate a large Kakuro (size +10) might take a few minutes"));

        JPanel esq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p_South.add(esq,BorderLayout.WEST);

        JPanel dre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p_South.add(dre,BorderLayout.EAST);


        exit = new JButton("Exit");
        esq.add(exit);

        crear = new JButton("Generate");
        dre.add(crear);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=ControladorPresentacio.getUser();
                ControladorPresentacio.VistaOpcions(username);
                setVisible(false);
            }
        });



        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sz = (Integer) tam.getValue();
                int resolved = (Integer) nums.getValue();
                int white = (Integer) blanques.getValue();

                String Kakuro = ControladorPresentacio.generarKakuro(sz, white,resolved);
                int errorr = ControladorPresentacio.crearGame(Kakuro);
                if(errorr ==0)ControladorPresentacio.VistaKakuro(Kakuro,0);
		else ControladorPresentacio.VistaExiste("You have already started a game with this Kakuro. Do you want to continue it?",Kakuro);
                setVisible(false);
            }
        });


        tam.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if ((Integer) tam.getValue() <= 3) {
                    easy.setSelected(true);
                    easy.setEnabled(false);
                    medium.setEnabled(false);
                    hard.setEnabled(false);

                } else {
                    int sz = (Integer) tam.getValue();
                    int resolved = (Integer) nums.getValue();
                    int white = (Integer) blanques.getValue();
                    int dif = ControladorPresentacio.dificultat(sz, white, resolved);
                    if (dif == 1) {
                        easy.setSelected(true);
                    }
                    else if (dif == 2) {
                        medium.setSelected(true);
                    }
                    else hard.setSelected(true);

		    if(!advance.isSelected()){
                    	easy.setEnabled(true);
                    	medium.setEnabled(true);
                    	hard.setEnabled(true);
			}

                }
            }
        });

        advance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if(source.isSelected()){
                    blanques.setEnabled(true);
                    nums.setEnabled(true);
                    la.setEnabled(true);
                    l.setEnabled(true);

                    ld.setEnabled(false);
                    easy.setEnabled(false);
                    medium.setEnabled(false);
                    hard.setEnabled(false);


                }
                else{
                    blanques.setEnabled(false);
                    nums.setEnabled(false);
                    la.setEnabled(false);
                    l.setEnabled(false);

                    ld.setEnabled(true);
                    easy.setEnabled(true);
                    medium.setEnabled(true);
                    hard.setEnabled(true);


                }
            }
        });

        ActionListener difAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
                if(source==easy){
                    blanques.setValue(30);
                }
                else if(source==medium){
                    blanques.setValue(40);
                }
                else{
                    blanques.setValue(50);
                }

            }
        };
        ChangeListener bl_re = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sz = (Integer) tam.getValue();
                int resolved = (Integer) nums.getValue();
                int white = (Integer) blanques.getValue();
                int dif = ControladorPresentacio.dificultat(sz, white, resolved);
                if (dif == 1) {
                    easy.setSelected(true);
                } else if (dif == 2) {
                    medium.setSelected(true);
                } else hard.setSelected(true);
            }
        };

        ChangeListener res_max = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sz = (Integer) tam.getValue();
                int white = (Integer) blanques.getValue();
                int mac = ControladorPresentacio.max_resoltes(sz,white);
                int res = (Integer) nums.getValue();
                p_solved.remove(1);

                if(res>mac) res=mac;
                SpinnerNumberModel model3 = new SpinnerNumberModel(res,0,mac,1);
                nums = new JSpinner(model3);
                if(advance.isSelected()) nums.setEnabled(true);
                else nums.setEnabled(false);
                nums.addChangeListener(bl_re);
                p_solved.add(nums);
                setVisible(false);
                setVisible(true);
            }
        };

        easy.addActionListener(difAction);
        medium.addActionListener(difAction);
        hard.addActionListener(difAction);

        tam.addChangeListener(res_max);
        blanques.addChangeListener(res_max);



        blanques.addChangeListener(bl_re);
        nums.addChangeListener(bl_re);

        try {
            InputStream imgStream = VistaNouKakuro.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(500,350);
        setVisible(true);
    }


}
