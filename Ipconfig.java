package Ipconfig;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static java.lang.System.out;

public class Ipconfig extends JFrame {

    private final JPanel contentPane;
    private final JTextPane textPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ipconfig frame = new Ipconfig();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Ipconfig() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 100, 753, 492);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 45, 548, 395);
        contentPane.add(scrollPane);

        textPane = new JTextPane();
        textPane.setFont(new Font("Consolas", Font.BOLD, 12));
        textPane.setForeground(new Color(255, 255, 255));
        textPane.setBackground(new Color(0, 0, 0));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setBold(keyWord, true);
        

        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = textPane.getInputMap(condition);
        ActionMap aMap = textPane.getActionMap();
        String enter = "enter";
        String strCut;

        ArrayList<String> strList = new ArrayList<>();
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        
        scrollPane.setViewportView(textPane);

        JButton btnAll = new JButton("Ipconfig/all");
        btnAll.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnAll.setBounds(568, 12, 159, 23);
        contentPane.add(btnAll);
        btnAll.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/ALL ------------------------------------------------------\n\n");
            try {
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
                for (NetworkInterface netint : Collections.list(nets))
                    allTxt(textPane, netint);
            } catch (SocketException socketException) {
                socketException.printStackTrace();
            }
            textPane.setText(textPane.getText() + "\n\n\n");
        });

        JButton btnHelp = new JButton("Ipconfig/?");
        btnHelp.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnHelp.setBounds(568, 43, 159, 23);
        btnHelp.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/? --------------------------------------------------------\n\n");
            extracted("ipconfig", "/?");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnHelp);

        JButton btnRelease = new JButton("Ipconfig/release");
        btnRelease.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnRelease.setBounds(568, 281, 159, 23);
        btnRelease.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/RELEASE -------------------------------------------------\n\n");
            extracted("ipconfig", "/release");
//        	try {
//				Process result = Runtime.getRuntime().exec("Ipconfig /release");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        	textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnRelease);

        JButton btnRelease6 = new JButton("Ipconfig/release6");
        btnRelease6.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRelease6.setBounds(568, 315, 159, 23);
        btnRelease6.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/RELEASE 6 -----------------------------------------------\n\n");
            extracted("ipconfig", "/release6");
//        	try {
//				Process result = Runtime.getRuntime().exec("Ipconfig /release6");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnRelease6);

        JButton btnRenew = new JButton("Ipconfig/renew");
        btnRenew.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRenew.setBounds(568, 350, 159, 23);
        btnRenew.addActionListener((e) -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/RENEW ---------------------------------------------------\n\n");
            extracted("ipconfig", "/renew");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnRenew);

        JButton btnFlushdns = new JButton("Ipconfig/flushdns");
        btnFlushdns.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnFlushdns.setBounds(568, 179, 159, 23);
        btnFlushdns.addActionListener((e) -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/FLUSHDNS ------------------------------------------------\n\n");
            extracted("ipconfig", "/flushdns");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnFlushdns);

        JButton btnRegisterdns = new JButton("Ipconfig/registerdns");
        btnRegisterdns.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRegisterdns.setBounds(568, 213, 159, 23);
        btnRegisterdns.addActionListener((e) -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/REGISTERDNS ---------------------------------------------\n\n");
            extracted("ipconfig", "/registerdns");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnRegisterdns);

        JButton btnDisplaydns = new JButton("Ipconfig/displaydns");
        btnDisplaydns.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnDisplaydns.setBounds(568, 247, 159, 23);
        btnDisplaydns.addActionListener((e) -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/DISPLAYDNS ----------------------------------------------\n\n");
        	//List<String> nameServers = ResolverConfiguration.open().nameservers();
        	//nameServers.forEach((s) -> textPane.setText(textPane.getText() + s + "\n"));
        	extracted("ipconfig", "/displaydns");
        	textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnDisplaydns);

        JButton btnShowclassid = new JButton("Ipconfig/showclassid");
        btnShowclassid.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnShowclassid.setBounds(568, 145, 159, 23);
        btnShowclassid.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/SHOWCLASSID ---------------------------------------------\n\n");
            extracted("ipconfig", "/showclassid");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnShowclassid);

        JButton btnSetclassid = new JButton("Ipconfig/setclassid");
        btnSetclassid.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnSetclassid.setBounds(568, 111, 159, 23);
        btnSetclassid.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/SETCLASSID ----------------------------------------------\n\n");
            extracted("ipconfig", "/setclassid");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnSetclassid);

        JButton btnAllcom = new JButton("Ipconfig/allcompartments");
        btnAllcom.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnAllcom.setBounds(568, 77, 159, 23);
        btnAllcom.addActionListener(e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/ALLCOMPARTMENTS -----------------------------------------\n\n");
            extracted("ipconfig", "/allcompartments");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        contentPane.add(btnAllcom);

        JLabel lblNewLabel = new JLabel("Command Promt Java");
        lblNewLabel.setForeground(new Color(255, 0, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setBounds(185, 10, 239, 24);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Delete");
        btnNewButton.setBounds(568, 415, 159, 25);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textPane.setText("");
        	}
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        JButton btnRenew6 = new JButton("Ipconfig/renew6");
        btnRenew6.addActionListener( e -> {
        	textPane.setText(textPane.getText() + "----- IPCONFIG/RENEW 6 ---------------------------------------------\n\n");
            extracted("ipconfig", "/renew6");
            textPane.setText(textPane.getText() + "\n\n\n");
        });
        btnRenew6.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRenew6.setBounds(568, 384, 159, 23);
        contentPane.add(btnRenew6);
    }

    private void extracted(String c1, String c2) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{c1, c2});
            int code = process.waitFor();
            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            while ((line = input.readLine()) != null) {
                textPane.setText(textPane.getText() + line);
            }
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }

    private void allTxt(JTextPane textPane, NetworkInterface netint) throws SocketException {
        textPane.setText(textPane.getText() + "Display name: " + netint.getDisplayName());
        textPane.setText(textPane.getText() + "\nName        : " + netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            textPane.setText(textPane.getText() + "\nInetAddress : " + inetAddress);
        }
        textPane.setText(textPane.getText() + "\n\n");
        
    }
}