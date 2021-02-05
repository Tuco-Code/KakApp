package presentacio;

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


public class VistaRepo extends JFrame {
    JButton crear,cerca,kkrBt,exit;
    int dif,propi;
    JCheckBox dificulty,owner;
    ButtonGroup difBt,ownBt;
    JRadioButtonMenuItem easy,medium,hard,auto,own,og;
    JPanel selected,p;
    JPanel[][] graella;





    public VistaRepo(int d, int prop){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        /////////////////////////////////////
        /////         West Part        /////
        ///////////////////////////////////

        dif=d; propi=prop;

        JPanel p_West = new JPanel();
        p_West.setLayout(new BoxLayout(p_West,BoxLayout.Y_AXIS));

        cp.add(p_West,BorderLayout.WEST);

        // PART DIFICULTAT

        dificulty = new JCheckBox("Dificulty",false);
        p_West.add(dificulty);

        difBt = new ButtonGroup();


        easy = new JRadioButtonMenuItem("Easy");
        difBt.add(easy);
        p_West.add(easy);

        medium = new JRadioButtonMenuItem("Medium");
        difBt.add(medium);
        p_West.add(medium);

        hard = new JRadioButtonMenuItem("Hard");
        difBt.add(hard);
        p_West.add(hard);



        ActionListener radioBt = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();

                if(source==easy){
                    dif=1;
                }
                else if(source==medium){
                    dif=2;
                }
                else{
                    dif=3;
                }
            }
        };


        easy.addActionListener(radioBt);
        medium.addActionListener(radioBt);
        hard.addActionListener(radioBt);

        easy.setEnabled(false);
        medium.setEnabled(false);
        hard.setEnabled(false);

        if(dif!=0){
            dificulty.setSelected(true);
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
            if(dif==1) easy.setSelected(true);
            else if(dif==2) medium.setSelected(true);
            else hard.setSelected(true);
        }

        dificulty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dificulty.isSelected()){
                    easy.setEnabled(true);
                    medium.setEnabled(true);
                    hard.setEnabled(true);
                    if(easy.isSelected()) dif =1;
                    else if(medium.isSelected()) dif =2;
                    else if(hard.isSelected()) dif=3;
                }
                else{
                    dif=0;
                    easy.setEnabled(false);
                    medium.setEnabled(false);
                    hard.setEnabled(false);
                }
            }
        });

        //PART PROPIETARI


        owner = new JCheckBox("Kakuro source");
        p_West.add(owner);


        ownBt = new ButtonGroup();

        auto = new JRadioButtonMenuItem("Generated automatically");
        ownBt.add(auto);
        p_West.add(auto);

        own = new JRadioButtonMenuItem("Proposed by User");
        ownBt.add(own);
        p_West.add(own);

        og = new JRadioButtonMenuItem("Original kakuros");
        ownBt.add(og);
        p_West.add(og);

        ActionListener radioBt2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
                if(source==auto){
                    propi=1;
                }
                else if(source==own){
                    propi=2;
                }
                else{
                    propi=3;
                }
            }
        };

        auto.addActionListener(radioBt2);
        own.addActionListener(radioBt2);
        og.addActionListener(radioBt2);

        auto.setEnabled(false);
        own.setEnabled(false);
        og.setEnabled(false);

        if(propi!=0){
            owner.setSelected(true);
            auto.setEnabled(true);
            own.setEnabled(true);
            og.setEnabled(true);
            if(propi==1) auto.setSelected(true);
            else if(propi==2) own.setSelected(true);
            else og.setSelected(true);
        }

        owner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(owner.isSelected()){
                    auto.setEnabled(true);
                    own.setEnabled(true);
                    og.setEnabled(true);

                    if(auto.isSelected()) propi =1;
                    else if(own.isSelected()) propi =2;
                    else propi=3;
                }
                else{
                    propi=0;
                    auto.setEnabled(false);
                    own.setEnabled(false);
                    og.setEnabled(false);
                }
            }
        });

        //BUTO DE CERCA

        cerca = new JButton("Search");
        p_West.add(cerca);




        /////////////////////////////////////
        /////      Central Part        /////
        ///////////////////////////////////

        ControladorPresentacio.applyFilter(dif,propi);
        int cols = ControladorPresentacio.getSizeKkr();
        if(cols%2==0) cols /= 2;
        else cols = cols/2 +1;
        JPanel p_Center = new JPanel();
        p_Center.setLayout(new BorderLayout());
        cp.add(p_Center,BorderLayout.CENTER);

        if(cols !=0) {
            p = new JPanel();
            p.setLayout(new GridLayout(2, cols, 50, 20));

            JScrollPane barra = new JScrollPane(p);


            //accedir controlador per pillar el numero de kkr cols = tot/2;
            p_Center.add(barra, BorderLayout.CENTER);


            graella = new JPanel[2][cols];


            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < cols; ++j) {
                    graella[i][j] = new JPanel();
                    p.add(graella[i][j]);

                }
            }

            int cont = 0;
            for (int i = 0; i < cols; ++i) {
                for (int j = 0; j < 2; ++j) {
                    if (cont != ControladorPresentacio.getSizeKkr()) {
                        graella[j][i].add(new JLabel("Kakuro_number " + cont + ""));
                        ++cont;
                    }
                }

            }

            kkrBt = new JButton("Select");
            p_Center.add(kkrBt, BorderLayout.SOUTH);


            if (cols != 0) {
                graella[0][0].getComponent(0).setForeground(Color.BLUE);
            }
            selected = graella[0][0];

            MouseListener kkrSel = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel source = (JPanel) e.getSource();
                    selected.getComponent(0).setForeground(Color.BLACK);
                    selected = source;
                    selected.getComponent(0).setForeground(Color.BLUE);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            };

            for (int i = 0; i < cols; ++i) {
                for (int j = 0; j < 2; ++j) {
                    graella[j][i].addMouseListener(kkrSel);
                }

            }

            kkrBt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel label = (JLabel) selected.getComponent(0);
                    String kak = label.getText();
                    String[] lines = kak.split(" ");
                    int i = Integer.parseInt(lines[1]);
                    String Kak = ControladorPresentacio.selectKkr(i);
                    ControladorPresentacio.VistaSelected(Kak);
                    setVisible(false);

                }
            });

        }
        else{
            p_Center.add(new JLabel("There are no Kakuros to play"));

        }
        cerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaRepo(dif,propi);
                setVisible(false);
            }
        });


        /////////////////////////////////////
        /////        South Part        /////
        ///////////////////////////////////

        JPanel p_south =new JPanel();
        p_south.setLayout(new BorderLayout());
        cp.add(p_south,BorderLayout.SOUTH);


        crear = new JButton("Create");
        p_south.add(crear,BorderLayout.EAST);

        exit = new JButton("Exit");
        p_south.add(exit,BorderLayout.WEST);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
                setVisible(false);
            }
        });

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaNouKakuro();
                setVisible(false);
            }
        });

        try {
            InputStream imgStream = VistaRepo.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(700,400);
        setVisible(true);

    }



}
