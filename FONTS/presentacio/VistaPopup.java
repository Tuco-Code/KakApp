package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaPopup extends JFrame {
    JButton save,exit,cancelar;
    JLabel text;
    int exit_value=0;

    public VistaPopup(String tex,int t){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Buttons = new JPanel(new FlowLayout());

        JPanel p_Message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cp.add(p_Message,BorderLayout.CENTER);


        cp.add(new JLabel(" "),"North");



        text = new JLabel(tex);
        p_Message.add(text);

        cp.add(p_Buttons,BorderLayout.SOUTH);

        save = new JButton("Yes");
        p_Buttons.add(save);

        exit = new JButton("No");
        p_Buttons.add(exit);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.changeGameTime(t);
                ControladorPresentacio.saveCurrentGame();
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
                setVisible(false);

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
                setVisible(false);
            }
        });


        try {
            InputStream imgStream = VistaPopup.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(600,150);
        setVisible(true);

    }


    public int getExit_value(){ return  exit_value;}


}
