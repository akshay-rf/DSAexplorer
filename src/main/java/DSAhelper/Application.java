package DSAhelper;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.FlatLaf;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

class Application extends JFrame{

    public Application(){
        init();
    }

    private void init(){
        setTitle("DSA Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,700));
        setLocationRelativeTo(null);
        setContentPane(new Home(this));
    }

    public static void main(String[] args){
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("ronnie.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new Application().setVisible(true));
    }
}

