package DSAhelper;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

class Home extends JPanel {
    public Application mainClass;
    public Home(Application mainClass){
        this.mainClass = mainClass;
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        JButton sort = new JButton("Sorting Algorithms");
        sort.addActionListener(new event());
        JButton search = new JButton("Searching Algorithms");
        search.addActionListener(new eventSearch());
        JButton graph = new JButton("Graph Algorithms");
        graph.addActionListener(new eventGraph());
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "background:lighten(@background, 3%)");

        title = new JLabel("DSA Explorer");
        title.setHorizontalAlignment(JLabel.CENTER);
        description = new JLabel("By Akshay RF");
        description.setHorizontalAlignment(JLabel.CENTER);
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground: darken(@foreground, 30%)");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        panel.add(title);
        panel.add(description);
        panel.add(sort, "gapy 10");
        panel.add(search, "gapy 10");
        panel.add(graph, "gapy 10");

        panel.add(createCreditLable(), "gapy 10");


        add(panel);

    }

    private Component createCreditLable(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");

        JButton redir = new JButton("<html>This project is licensed under the <a href=\"#\">MIT License</a>.</html>");
        redir.putClientProperty(FlatClientProperties.STYLE, "" +
                "border: 3,3,3,3");
        redir.setContentAreaFilled(false);
        redir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redir.addActionListener(e -> {
            String s = "https://github.com/akshay-rf/DSAexplorer/blob/master/LICENSE";
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(URI.create(s));
            }catch(IOException e1) {
                System.out.println(e1);
            }
        });

        panel.add(redir);
        return panel;
    }

    private JLabel title;
    private JLabel description;

    class event implements ActionListener {
        public void actionPerformed(ActionEvent e){
            mainClass.getContentPane().removeAll();
            mainClass.getContentPane().add(new SortWin(mainClass));
            mainClass.getContentPane().revalidate();
            mainClass.getContentPane().repaint();

            System.out.println("yes");
        }
    }

    class eventSearch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            mainClass.getContentPane().removeAll();
            mainClass.getContentPane().add(new SearchWin(mainClass));
            mainClass.getContentPane().revalidate();
            mainClass.getContentPane().repaint();

            System.out.println("yes");
        }
    }

    class eventGraph implements ActionListener{
        public void actionPerformed(ActionEvent e){
            mainClass.getContentPane().removeAll();
            mainClass.getContentPane().add(new GraphWin(mainClass));
            mainClass.getContentPane().revalidate();
            mainClass.getContentPane().repaint();

            System.out.println("yes");
        }
    }
}