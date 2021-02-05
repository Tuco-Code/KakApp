package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaCongrats extends JFrame {
    JButton exit,cancelar;
    JLabel text;

    public VistaCongrats(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Buttons = new JPanel(new BorderLayout());

        JPanel p_Message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cp.add(p_Message,BorderLayout.CENTER);


        cp.add(new JLabel(" "),"North");



        text = new JLabel("Congrats you have finished succesfully the Kakuro!!");
        p_Message.add(text);

        cp.add(p_Buttons,BorderLayout.SOUTH);


        exit = new JButton("Exit");
        p_Buttons.add(exit,BorderLayout.WEST);


        cancelar = new JButton("Go to Options Menu");
        p_Buttons.add(cancelar,BorderLayout.EAST);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
                setVisible(false);
            }
        });
        try {
            InputStream imgStream = VistaCongrats.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(600,150);
        setVisible(true);

    }


}
