package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class VistaDeleteUser extends JFrame {
    JButton yes, no;
    JLabel text;

    public VistaDeleteUser(String tex) {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Buttons = new JPanel(new FlowLayout());

        JPanel p_Message = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cp.add(p_Message, BorderLayout.CENTER);


        cp.add(new JLabel(" "), "North");


        text = new JLabel(tex);
        p_Message.add(text);

        cp.add(p_Buttons, BorderLayout.SOUTH);

        yes = new JButton("Yes");
        p_Buttons.add(yes);

        no = new JButton("No");
        p_Buttons.add(no);


        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.deleteProfile();
                setVisible(false);
                ControladorPresentacio.VistaPrincipal();

            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaProfile();
                setVisible(false);
            }
        });

        try {
            InputStream imgStream = VistaDeleteUser.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(600,150);
        setVisible(true);

    }

}
