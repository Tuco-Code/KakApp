package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaWrong extends JFrame {
    JButton exit;
    JLabel text;

    public VistaWrong(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Buttons = new JPanel(new BorderLayout());

        JPanel p_Message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cp.add(p_Message,BorderLayout.CENTER);


        cp.add(new JLabel(" "),"North");

        text = new JLabel("Wrong Solution");
        p_Message.add(text);

        cp.add(p_Buttons,BorderLayout.SOUTH);
        

        exit = new JButton("Go to Kakuro");
        p_Buttons.add(exit,BorderLayout.WEST);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kkr = ControladorPresentacio.getBoardGame();
                int time = ControladorPresentacio.getGameTime();
                ControladorPresentacio.VistaKakuro(kkr,time);
                setVisible(false);

            }
        });

        try {
            InputStream imgStream = VistaWrong.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(600,150);
        setVisible(true);

    }



}
