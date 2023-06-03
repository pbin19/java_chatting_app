//these are the imports required
//AWT (Abstract Window Toolkit) package provides a basic set of tools and functionality that Swing builds upon to create more advanced and flexible GUI components.
//it is windowing, graphics, and user interface toolkit used for creating graphical user interfaces (GUIs) for Java applications.
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class Client implements ActionListener{
    JTextField text;
    static JPanel textarea;
    static Box vertical = Box.createVerticalBox();
    static JFrame f=new JFrame();
    static DataOutputStream output;
    Client() {
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("whatsapp.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        f.setContentPane(backgroundLabel);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(null);
        contentPane.setBounds(0,0,450,720);
        backgroundLabel.add(contentPane);

        JPanel jpanel = new JPanel();
        jpanel.setBackground(new Color(7, 94, 84));
        jpanel.setBounds(0,0,450,70);
        jpanel.setLayout(null);
        contentPane.add(jpanel);

        ImageIcon main_arrow=new ImageIcon(ClassLoader.getSystemResource("arrow.png"));
        Image sized_arrow = main_arrow.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon copy_arrow=new ImageIcon(sized_arrow);
        JLabel arrow= new JLabel(copy_arrow);
        arrow.setBounds(5,20,25,25);
        jpanel.add(arrow);

        arrow.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                f.setVisible(false);
            }
        });

        ImageIcon main_pic=new ImageIcon(ClassLoader.getSystemResource("woman.png"));
        Image sized_pic = main_pic.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
        ImageIcon copy_pic=new ImageIcon(sized_pic);
        JLabel pic= new JLabel(copy_pic);
        pic.setBounds(40,10,40,40);
        jpanel.add(pic);

        
        ImageIcon main_vedio=new ImageIcon(ClassLoader.getSystemResource("video.png"));
        Image sized_vedio = main_vedio.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon copy_vedio=new ImageIcon(sized_vedio);
        JLabel vedio= new JLabel(copy_vedio);
        vedio.setBounds(310,25,25,25);
        jpanel.add(vedio);
        
        ImageIcon main_call=new ImageIcon(ClassLoader.getSystemResource("call.png"));
        Image sized_call = main_call.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon copy_call=new ImageIcon(sized_call);
        JLabel call= new JLabel(copy_call);
        call.setBounds(360,25,25,25);
        jpanel.add(call);
        
        ImageIcon main_dots=new ImageIcon(ClassLoader.getSystemResource("dots.png"));
        Image sized_dots = main_dots.getImage().getScaledInstance(10, 22,Image.SCALE_DEFAULT);
        ImageIcon copy_dots=new ImageIcon(sized_dots);
        JLabel dots= new JLabel(copy_dots);
        dots.setBounds(410,23,10,25);
        jpanel.add(dots);
        
        JLabel name_label= new JLabel("Shizuka");
        name_label.setBounds(100,10,150,30);
        name_label.setForeground(Color.white);
        name_label.setFont(new Font("sans-serif", Font.BOLD, 18));
        jpanel.add(name_label);

        JLabel status_label= new JLabel("Online");
        status_label.setBounds(100,30,150,30);
        status_label.setForeground(Color.white);
        status_label.setFont(new Font("sans-serif", Font.BOLD, 14));
        jpanel.add(status_label);


        textarea = new JPanel();
        textarea.setBounds(80, 75, 370, 570);
        textarea.setOpaque(false);
        textarea.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(textarea);
        scrollPane.setBounds(10, 75, 430, 570);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane);
        

        text = new JTextField();
        text.setBounds(60, 655, 330, 40);
        text.setFont(new Font("sans-serif", Font.BOLD, 16));
        text.setBorder(null);
        contentPane.add(text);

        ImageIcon smiley_icon = new ImageIcon("smiley.png");
        Image sized_smiley = smiley_icon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
        ImageIcon copy_smiley=new ImageIcon(sized_smiley);
        // JButton smiley=new JButton(copy_smiley);
        RoundedButton smiley=new RoundedButton(copy_smiley);
        smiley.setBounds(10,655,40,40);
        // send.setBackground(Color.BLACK);
        f.add(smiley);

        ImageIcon sent_icon = new ImageIcon("sent.png");
        Image sized_sent = sent_icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon copy_sent = new ImageIcon(sized_sent);
        RoundedButton send = new RoundedButton(copy_sent);
        send.addActionListener(this);
        send.setBounds(400, 655, 40, 40);
        contentPane.add(send);

        
        f.setSize(450, 720);
        f.setLocation(1000, 75);
        f.setTitle("Whatsapp");
        f.setUndecorated(true);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String name = text.getText();
            JPanel name_panel = formatLabel(name);
            JPanel right = new JPanel(new BorderLayout());
            right.setOpaque(false);
            right.add(name_panel, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            textarea.add(vertical, BorderLayout.PAGE_START);
            output.writeUTF(name);
            text.setText("");
            f.repaint();
            f.validate();
        } catch (IOException e) {

        }
    }
    
    public static JPanel formatLabel(String name) {
        JLabel name_label = new JLabel("<html><p style=\"width: 150px\">" + name + "</p></html>");
        name_label.setFont(new Font("sans-serif", Font.BOLD, 16));
        name_label.setOpaque(false);
        name_label.setForeground(Color.white);
        name_label.setBackground(new Color(0, 255, 0, 128)); // set green transparent background
        JPanel name_panel = new JPanel();
        name_panel.setBackground(new Color(7, 94, 84)); // set transparent background
        name_panel.setOpaque(true);
        name_panel.setLayout(new BoxLayout(name_panel, BoxLayout.Y_AXIS));
        name_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // name_panel.setBorder(new RoundedBorder(20));
        name_panel.add(name_label);
    
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("HH:MM");
        JLabel time = new JLabel();
        time.setText(date.format(calendar.getTime()));
        time.setFont(new Font("sans-serif", Font.PLAIN, 8));
        time.setForeground(Color.white);
        time.setAlignmentX(Component.LEFT_ALIGNMENT);
        name_panel.add(Box.createVerticalStrut(5)); // add some spacing between the name and the time label
        name_panel.add(time);
    
        return name_panel;
    }
    
    public static void main(String[] args) {
        new Client();
        try {
            Socket s= new Socket("127.0.0.1",1234);
            DataInputStream input = new DataInputStream(s.getInputStream());
            output= new DataOutputStream(s.getOutputStream());
            while(true){
                String message = input.readUTF();
                JPanel panel = formatLabel(message);
                panel.setOpaque(false); // set panel background color as transparent
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.PAGE_START);
                left.setOpaque(false); // set left panel background color as transparent
                JPanel messagePanel = new JPanel(new BorderLayout());
                messagePanel.add(left, BorderLayout.LINE_START);
                messagePanel.setOpaque(false); // set message panel background color as transparent
                vertical.add(messagePanel);
                vertical.add(Box.createVerticalStrut(15));
                vertical.setOpaque(false); // set vertical box background color as transparent
                textarea.add(vertical,BorderLayout.PAGE_START);
                textarea.setOpaque(false); // set textarea background color as transparent
                f.validate();
            }
        } catch (Exception e) {}
    }
}
