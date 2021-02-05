package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaPwd extends JFrame {
    JPasswordField oldPwd,newPwd;
    JLabel antPwd, novPwd,error;
    JButton change,exit;

    public VistaPwd(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Center = new JPanel();
        p_Center.setLayout(new GridLayout(8,1,5,1));
        cp.add(p_Center,BorderLayout.CENTER);

        cp.add(new JLabel("               "),BorderLayout.EAST);
        cp.add(new JLabel("               "),BorderLayout.WEST);


        JPanel p_old = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p_Center.add(p_old);

        antPwd = new JLabel("Old Password:");
        p_old.add(antPwd);

        error = new JLabel("");
        error.setForeground(Color.RED);
        error.setVisible(false);
        p_old.add(error);

        oldPwd = new JPasswordField("",30);
        p_Center.add(oldPwd);

        p_Center.add(new JLabel(" "));
        novPwd = new JLabel("New Password:");
        p_Center.add(novPwd);

        newPwd = new JPasswordField("",30);
        p_Center.add(newPwd);

        JPanel p_But = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p_Center.add(p_But);

        change = new JButton("Change Password");
        p_But.add(change);


        JPanel p_South = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cp.add(p_South,BorderLayout.SOUTH);

        exit = new JButton("Exit");
        p_South.add(exit);



        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old = String.valueOf(oldPwd.getPassword());
                String newo = String.valueOf(newPwd.getPassword());

                int err = ControladorPresentacio.changePwd(old,newo);
                if(err==-2){
                    error.setVisible(true);
                    error.setText("New Password must be diferent");
                }
                else if(err==-1){
                    error.setVisible(true);
                    error.setText("Old Password incorrect");
                }
                else if(err==1){
                    error.setVisible(true);
                    error.setText("New Password incorrect, try another one");
                }
                else {
                    ControladorPresentacio.VistaProfile();
                    setVisible(false);
                }
            }
        };


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ControladorPresentacio.VistaProfile();
            }
        });

        change.addActionListener(a);
        oldPwd.addActionListener(a);
        newPwd.addActionListener(a);

        try {
            InputStream imgStream = VistaPwd.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(500,300);
        setVisible(true);

    }

}
