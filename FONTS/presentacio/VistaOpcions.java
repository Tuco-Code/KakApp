package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaOpcions extends JFrame {
    private JButton Jugar,Perfils,Repo,Prop,enrere;


    public VistaOpcions() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());


        JPanel p_Center = new JPanel(new GridLayout(2,2,20,20));
        cp.add(p_Center,BorderLayout.CENTER);

        Jugar = new JButton("Automatic Kakuro Generation");
        Insets i = new Insets(0,20,0,0);
        p_Center.add(Jugar);



        Repo = new JButton("Access Kakuro Repository");
        p_Center.add(Repo);

        Prop = new JButton("Manually Add Kakuro");
        p_Center.add(Prop);

        Perfils = new JButton("Profile Settings");
        p_Center.add(Perfils);

        JPanel p_South = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cp.add(p_South,BorderLayout.SOUTH);

        enrere = new JButton("Exit");
        p_South.add(enrere);

        Jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaNouKakuro();
                setVisible(false);
            }
        });

        Prop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaProposarKakuro();
                setVisible(false);
            }
        });

        Perfils.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaProfile();
                setVisible(false);
            }
        });

        Repo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaRepo(0,0);
                setVisible(false);
            }
        });

        enrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaPrincipal();
                setVisible(false);
            }
        });

        try {
            InputStream imgStream = VistaOpcions.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(650,350);
        setVisible(true);

    }


}
