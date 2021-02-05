package presentacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class VistaPrincipal extends JFrame {

    private JTextField Tfuser,Tfpwd;
    private JButton reg,env;
    private JPasswordField Tfpw;
    JLabel errUser;

    public VistaPrincipal() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,1));

        panelNorth.add(p);
        p.add(new JLabel("Welcome to the KakApp"));

        JPanel pw = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.add(pw);
        try {
            BufferedImage wPic = ImageIO.read(this.getClass().getResource("kakapp.png"));
            JLabel wIcon = new JLabel(new ImageIcon(wPic));
            pw.add(wIcon);
        }catch (IOException ex){};


        cp.add(panelNorth, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter,1));

        JPanel perror = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCenter.add(perror);

        errUser = new JLabel("Wrong Username or Password\n");
        errUser.setVisible(false);
        errUser.setForeground(Color.red);
        perror.add(errUser);


        JPanel puser= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(puser);
        puser.add(new JLabel("Username:"));
        Tfuser = new JTextField("",30);
        puser.add(Tfuser);

        JPanel ppwd = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(ppwd);
        ppwd.add(new JLabel("Password:"));
        Tfpw = new JPasswordField("",30);
        ppwd.add(Tfpw);

        JPanel penv = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(penv);
        env = new JButton("Enviar");
        penv.add(env);

        JPanel plin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(plin);
        plin.add(new JLabel("-----------------------------------------------------------------------------------------------------------------------------------"));

        JPanel preg = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(preg);
        reg = new JButton("Registrarse");
        preg.add(reg);


        cp.add(panelCenter, BorderLayout.CENTER);

        cp.add(new JLabel("Made by: Albert Vilardell, Dani Santiago, Ivan López i Victor Gregori"),BorderLayout.SOUTH);

        env.addActionListener(enviar);
        Tfpw.addActionListener(enviar);
        Tfuser.addActionListener(enviar);

        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = Tfuser.getText();
                String password = String.valueOf(Tfpw.getPassword());

                int error=ControladorPresentacio.Register(username,password);
                if(error == 1){
                    errUser.setText("Too many users                                                                                           ");
                    errUser.setVisible(true);

                }
                else if(error == 2){
                    errUser.setText("Username already taken                                                                           ");
                    errUser.setVisible(true);
                }
                else{
                    ControladorPresentacio.VistaOpcions(username);
                    setVisible(false);
                }


            }
        });



        try {
            InputStream imgStream = VistaPrincipal.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};

        try {
            InputStream imgStream = VistaPrincipal.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};

        setTitle("Kakuro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,450);
        setVisible(true);
    }

    ActionListener enviar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)  {
            String username = Tfuser.getText();
            String password = String.valueOf(Tfpw.getPassword());
            int error = ControladorPresentacio.AccessUser(username,password);
            if (error != 0) {
                errUser.setText("Wrong Username or Password                                                                          \n");
                errUser.setVisible(true);
            }
            else{
                ControladorPresentacio.VistaOpcions(username);
                setVisible(false);
            }

        }
    };

}
