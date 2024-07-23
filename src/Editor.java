import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.io.*;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Editor extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Editor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmGrabar = new JMenuItem("Grabar...");
		mntmGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grabar();
			}
		});
		mnOpciones.add(mntmGrabar);
		
		JMenuItem mntmLeer = new JMenuItem("Leer...");
		mntmLeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leer();
			}
		});
		mnOpciones.add(mntmLeer);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnOpciones.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	public void grabar() {
		FileDialog fd= new FileDialog(this,"Grabar",FileDialog.SAVE);
		fd.setVisible(true);
		if(fd.getFile()==null) {
			setTitle("No dispuso nombre de ese archivo");
		}else {
			try {
				FileWriter fw=new FileWriter(fd.getDirectory()+fd.getFile());
				fw.write(textArea.getText());
				fw.close();
			}catch(IOException e) {
				setTitle(e.toString());
			}
		}
	}
	
	private void leer() {
		FileDialog fd= new FileDialog(this,"Grabar",FileDialog.LOAD);
		fd.setVisible(true);
		if (fd.getFile()==null) {
			setTitle("No selecciono archivo.");
		}else {
			textArea.setText("");
		try {
				FileReader fr=new FileReader(fd.getDirectory()+fd.getFile());
				BufferedReader br= new BufferedReader(fr);
				String linea=br.readLine();
				while (linea!=null) {
					textArea.append(linea);
					textArea.append("\n");
					linea=br.readLine();
				}
				br.close();
				fr.close();
			}catch(IOException e) {
				setTitle(e.toString());
			}
		}
	}
	
}
