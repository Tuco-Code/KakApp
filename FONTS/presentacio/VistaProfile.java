package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class VistaProfile extends JFrame {
    JLabel User,games,wins;
    JButton exit,ChangePwd,Delete;
    JLabel error;


    public VistaProfile(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        try {
            BufferedImage wPic = ImageIO.read(this.getClass().getResource("usuari.png"));
            JLabel wIcon = new JLabel(new ImageIcon(wPic));
            cp.add(wIcon,BorderLayout.WEST);
        }catch (IOException ex){};

        JPanel p = new JPanel(new GridLayout(3,2,30,5));
        cp.add(p,BorderLayout.CENTER);


        p.add(new JLabel("Username:"));

        User = new JLabel(ControladorPresentacio.getUser());
        p.add(User);


        int num = ControladorPresentacio.getNumOfGames();
        int win = ControladorPresentacio.getNumOfWins();

        p.add(new JLabel("Kakuros played:"));

        games = new JLabel(num+"");
        p.add(games);



        p.add(new JLabel("Kakuros finished:"));

        wins = new JLabel(win+"");
        p.add(wins);




        JPanel p_South = new JPanel(new BorderLayout());
        cp.add(p_South,BorderLayout.SOUTH);

        exit = new JButton("Exit");
        p_South.add(exit,BorderLayout.WEST);

        JPanel pe = new JPanel(new FlowLayout());

        p_South.add(pe,BorderLayout.EAST);

        ChangePwd = new JButton("Change Password");
        pe.add(ChangePwd);

        Delete = new JButton("Delete Profile");
        pe.add(Delete);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
                setVisible(false);
            }
        });

        ChangePwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaPwd();
                setVisible(false);

            }
        });

        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaDeleteUser("Are you sure you want to delete this Profile?");
                setVisible(false);

            }
        });

        try {
            InputStream imgStream = VistaProfile.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(450,250);
        setVisible(true);

    }


}
