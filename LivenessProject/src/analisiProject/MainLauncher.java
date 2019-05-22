package analisiProject;

import java.io.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.*;


public class MainLauncher {
	private JFrame frame;
	private JFrame frame2;
	public String testo;
	public String risultato;
	public int iterazioni;
	public Analisi analisi = new Analisi();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLauncher window = new MainLauncher();
					window.frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainLauncher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(18, 51, 190, 169);
		JScrollPane scrollAreaIns = new JScrollPane(textArea);
		scrollAreaIns.setBounds(18, 51, 190, 169);
		frame.getContentPane().add(scrollAreaIns);
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(233, 51, 190, 21);
		frame.getContentPane().add(textArea_1);
		
		JButton StartAnalysis = new JButton("Start analyze");
		StartAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testo = textArea.getText();
				iterazioni = Integer.parseInt(textArea_1.getText());
				analisi.setIterations(iterazioni);
				
				FileWriter fw;
				try {
					fw = new FileWriter("/Users/Giacomo/Documents/java-workspace/FrameworkSpecification/corpoJava.txt");
					fw.write(testo);
					fw.flush();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					risultato = analisi.main(null);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
								
				JTextPane textpane = new JTextPane();
				textpane.setBackground(Color.WHITE);
				textpane.setText(risultato);
				textpane.setFont(new Font("Arial", 0, 20));
				textpane.setBounds(0,0, 1000,1000);
				textpane.setEditable(false);
				
				JScrollPane scroll = new JScrollPane(textpane);
				scroll.setBounds(0, 0, 455, 300);
		        
		        JFrame frame2 = new JFrame();
		        frame2.setBounds(100, 150, 450, 300);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				
				frame2.getContentPane().add(scroll);
				
			    frame2.setLocationRelativeTo ( null );
				
			    frame2.setVisible ( true );
				
				frame.dispose();
			}
		});
		
		StartAnalysis.setBounds(263, 122, 117, 29);
		frame.getContentPane().add(StartAnalysis);
		
		JTextPane txtpnWriteBelowYour = new JTextPane();
		txtpnWriteBelowYour.setBackground(Color.WHITE);
		txtpnWriteBelowYour.setText("WRITE BELOW YOUR CODE");
		txtpnWriteBelowYour.setBounds(28, 20, 170, 16);
		txtpnWriteBelowYour.setEditable(false);
		frame.getContentPane().add(txtpnWriteBelowYour);
		
		
		JTextPane txtpnInsertNumberOf = new JTextPane();
		txtpnInsertNumberOf.setText("NUMBER OF ITERATION");
		txtpnInsertNumberOf.setEditable(false);
		txtpnInsertNumberOf.setBackground(Color.WHITE);
		txtpnInsertNumberOf.setBounds(249, 23, 147, 16);
		frame.getContentPane().add(txtpnInsertNumberOf);
		
		
		
		
	}
}
