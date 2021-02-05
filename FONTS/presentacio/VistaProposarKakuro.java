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


public class VistaProposarKakuro extends JFrame{
    JFileChooser choser;
    JTextField ruta;
    JButton search,accept,accept_code,Exit,workshop;
    JTextArea codi;
    JLabel error;


    public VistaProposarKakuro(){
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel p_Center = new JPanel();
        p_Center.setLayout(new BoxLayout(p_Center,1));
        cp.add(p_Center,BorderLayout.CENTER);

        error = new JLabel("");
        error.setVisible(true);
        error.setForeground(Color.RED);
        p_Center.add(error);

        JPanel fitxer = new JPanel();
        fitxer.setLayout(new FlowLayout());
        p_Center.add(fitxer);

        fitxer.add(new JLabel("Route:"));
        ruta = new JTextField("",20);
        fitxer.add(ruta);

        search = new JButton("Search");
        fitxer.add(search);

        accept = new JButton("Accept");
        fitxer.add(accept);

        JPanel text = new JPanel();
        text.setLayout(new FlowLayout());
        p_Center.add(text);

        text.add(new JLabel("Codi Kakuro:"));
        codi = new JTextArea(10,30);
        text.add(codi);

        accept_code = new JButton("Accept");
        text.add(accept_code);


        JPanel work_pan = new JPanel();
        work_pan.setLayout(new FlowLayout());
        p_Center.add(work_pan);

        workshop = new JButton("Go to Kakuro Workshop");
        work_pan.add(workshop);


        JPanel p_South = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cp.add(p_South,BorderLayout.SOUTH);

        Exit = new JButton("Exit");
        p_South.add(Exit);



        //LISTENERS
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JFrame frame = new JFrame();
                choser = new JFileChooser();
                int i = choser.showOpenDialog(cp);
                if(i== JFileChooser.APPROVE_OPTION){
                    File f = choser.getSelectedFile();
                    String path = f.getPath();
                    ruta.setText(path);
                }
            }
        });

        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = ruta.getText();
                int err = ControladorPresentacio.loadFromFile(rut);
                if(err==-1){
                    error.setText("Kakuro already exists");
                    error.setForeground(Color.RED);
                }
                else if(err==-2){
                    error.setText("Wrong Kakuro format");
                    error.setForeground(Color.RED);
                }
                else if(err==-3){
                    error.setText("The Kakuro has 0 or more than one solutions");
                    error.setForeground(Color.RED);
                }
                else if(err==-4){
                    error.setText("Error at load the file");
                    error.setForeground(Color.RED);
                }
                else{
                    error.setText("Kakuro succesfully added");
                    error.setForeground(new Color(0, 141, 6,255));

                }
            }
        });

        accept_code.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int err = ControladorPresentacio.addKakuro(codi.getText());
                if(err==-1){
                    error.setText("Kakuro already exists");
                    error.setForeground(Color.RED);
                }
                else if(err==-2){
                    error.setText("Wrong Kakuro format");
                    error.setForeground(Color.RED);
                }
                else if(err==-3){
                    error.setText("The Kakuro has 0 or more than one solutions");
                    error.setForeground(Color.RED);
                }
                else{
                    error.setText("Kakuro succesfully added");
                    error.setForeground(new Color(0, 141, 6,255));

                }
            }
        });

        workshop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorPresentacio.VistaWorkshop(8);
                setVisible(false);
            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ControladorPresentacio.VistaOpcions(ControladorPresentacio.getUser());
            }
        });
        try {
            InputStream imgStream = VistaProposarKakuro.class.getResourceAsStream("kakapp.png");
            BufferedImage myImg = ImageIO.read(imgStream);

            setIconImage(myImg);
        }catch (IOException ex){};


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kakuro");
        setSize(500,500);
        setVisible(true);


    }
}
