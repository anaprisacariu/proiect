package proiect_pip_sem1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.List;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Checkbox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;


public class Masina {

	public static String last_modified=null;
	public static int accesare_meniu_bmw=0;
	public static int accesare_meniu_audi=0;
	JFrame frmTesterCheiMasina;
	private JTextField txtPagina;
	private JTextField txt_bmw;
	private JTextField txt_audi;
	static public JButton btn_pornire_motor = new JButton("Pornire motor");
	static public JButton btn_deschidere_centralizata = new JButton("Deschidere centralizata");
	static public JButton btn_deschidere_trapa = new JButton("Deschidere trapa");
	static public JButton btn_oprire_motor = new JButton("Oprire motor");
	static public JButton btn_pornire_far = new JButton("Pornire far");
	JScrollPane scrollPane1 = new JScrollPane();
	static JTextArea textArea_bmw = new JTextArea();
	JScrollPane scrollPane2 = new JScrollPane();
	static JTextArea textArea_audi = new JTextArea();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menu_toateMasinile = new JMenu("Alegere Masina");
	JMenuItem menu_masina1 = new JMenuItem("BMW");
	JMenuItem menu_masina2 = new JMenuItem("Audi");
	
	cheie_generala implementor1 = new cheie_bmw_implementor();
	BMWKey bmwKey = new BMWKey(implementor1);
	
	cheie_generala implementor2 = new cheie_audi_implementor();
	AudiKey audiKey = new AudiKey(implementor2);
	
	
	JLabel labelLeft = new JLabel();
	JLabel labelRight = new JLabel();
	

	//____________BRIDGE DESIGN PATTERN_____________
	
	//Modelul Design Brdige permite decuplarea unei abstractii de implementarea sa, astfel incat cele doua sa poata varia independent.
	//Acest lucru poate fi util atunci cand dorim sa schimbam implementarea unei abstractii fara a afecta codul client care o utilizeaza.
	
	
	//IMPLEMENTOR -> interfata ce definete metodele care trebuiesc implementate de "Implementatorii concreti"
	//->specifica detaliile "low-level" ale implementarii si este independenta de "Abstractie"
	
	interface cheie_generala
	{
		public void pornire_motor();
		public void deschidere_centralizata();
		public void deschidere_trapa();
	}


	
	//CONCRETE IMPLEMENTATOR 1
	public class cheie_bmw_implementor implements cheie_generala
	{
		

		@Override
		public void pornire_motor() {
			// TODO Auto-generated method stub
			textArea_bmw.append("\n Pornire motor");	
		}

		@Override
		public void deschidere_centralizata() {
			// TODO Auto-generated method stub
			textArea_bmw.append("\n Deschidere centralizata");
		}

		@Override
		public void deschidere_trapa() {
			// TODO Auto-generated method stub
			textArea_bmw.append("\n Deschidere trapa");
		}

	}
	//CONCRETE IMPLEMENTATOR 2
	public class cheie_audi_implementor implements cheie_generala
	{
		
		@Override
		public void pornire_motor() {
			// TODO Auto-generated method stub
			textArea_audi.append("\n Pornire motor Audi Q3");
		}

		@Override
		public void deschidere_centralizata() {
			// TODO Auto-generated method stub
			textArea_audi.append("\n Deschidere centralizata Audi Q3");
		}

		@Override
		public void deschidere_trapa() {
			// TODO Auto-generated method stub
			textArea_audi.append("\n Deschidere trapa Audi Q3");
		}
	}
	
	//ABSTRACTION ->este o interfata care defineste opeartiile de nivel inalt care vor fi implementate de catre Abstractia rafinata.
	//-> abstractia rafinata este o clasa care extinde abstractia si ofera o implementare mai rafinata a operatiilor de nivel inalt.
	//-> in abstractia rafinata se realizeaza "bridge"-ul 
	interface abstractie_cheie
	{
		void pornire();
		void deblocare();
		void trapa();
		void oprire();
		void far_pornit();
	}
	
	//REFINED ABSTRACTION 1
	public class AudiKey implements abstractie_cheie
	{

		//AICI REALIZAM "BRIDGE"-UL
		//prin crearea unei legaturi la clasa care realizeaza functia de IMPLEMENTOR
		private cheie_generala implementor;
		
		public AudiKey(cheie_generala implementor) {
			this.implementor=implementor;
		}
			
		@Override
		public void pornire() {
			// TODO Auto-generated method stub
			implementor.pornire_motor();
		}

		@Override
		public void deblocare() {
			// TODO Auto-generated method stub
			implementor.deschidere_centralizata();
		}

		@Override
		public void trapa() {
			// TODO Auto-generated method stub
			implementor.deschidere_trapa();
		}

		@Override
		public void oprire() {
			// TODO Auto-generated method stub
			textArea_audi.append("\n Oprire Motor");
		}

		@Override
		public void far_pornit() {
			// TODO Auto-generated method stub
			textArea_audi.append("\n Pornire faruri");
		}
		
	}
	
	//REFINED ABSTRACTION 2
	public class BMWKey implements abstractie_cheie
	{
		//AICI REALIZAM "BRIDGE"-UL
		//prin crearea unei legaturi la clasa care realizeaza functia de IMPLEMENTOR
		private cheie_generala implementor;
		public BMWKey(cheie_generala implementor) {
			this.implementor=implementor;
		}
		
		public void pornire() {
			// TODO Auto-generated method stub
			implementor.pornire_motor();
		}

		@Override
		public void deblocare() {
			// TODO Auto-generated method stub
			implementor.deschidere_centralizata();
		}

		@Override
		public void trapa() {
			// TODO Auto-generated method stub
			implementor.deschidere_trapa();
		}

		@Override
		public void oprire() {
			// TODO Auto-generated method stub
			textArea_bmw.append("\n Oprire Motor");
		}

		@Override
		public void far_pornit() {
			// TODO Auto-generated method stub
			textArea_bmw.append("\n Pornire faruri");
		}
	}
	

	ActionListener ascultator = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			if (source instanceof JButton && last_modified!=null) 
			{
			      JButton button = (JButton) source;
			      String text = button.getText();
			      if(last_modified=="BMW")
			      {
			    	  if(text.equals("Pornire motor"))
			    		  bmwKey.pornire();
			    	  if(text.equals("Deschidere centralizata"))
			    		  bmwKey.deblocare();
			    	  if(text.equals("Deschidere trapa"))
			    		  bmwKey.trapa();
			    	  if(text.equals("Oprire motor"))
			    		  bmwKey.oprire();
			    	  if(text.equals("Pornire far"))
			    		  bmwKey.far_pornit();
			      }
			      if(last_modified=="Audi")
			      {
			    	  if(text.equals("Pornire motor"))
			    		  audiKey.pornire();
			    	  if(text.equals("Deschidere centralizata"))
			    		  audiKey.deblocare();
			    	  if(text.equals("Deschidere trapa"))
			    		  audiKey.trapa();
			    	  if(text.equals("Oprire motor"))
			    		  audiKey.oprire();
			    	  if(text.equals("Pornire far"))
			    		  audiKey.far_pornit();
			      }
			     
			}
			if(source instanceof JMenuItem)
			{
				JMenuItem item = (JMenuItem) source;
				String text = item.getText();
				last_modified=text;
				 if(last_modified=="BMW")
			      {
			    	  textArea_bmw.append("S-a ales cheia de la BMW \n");
			      }
			      if(last_modified=="Audi")
			      {
			    	  textArea_audi.append("S-a ales cheia de la Audi \n");
			      }
			}
		}
	};
	
	public static void main(String[] args) {
		//generat de program//////////////
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Masina window = new Masina();
					window.frmTesterCheiMasina.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		///////////////////////////

	}

	/**
	 * Create the application.
	 */
	public Masina() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTesterCheiMasina = new JFrame();
		frmTesterCheiMasina.setResizable(false);
		frmTesterCheiMasina.setTitle("TESTER CHEI MASINA");
		frmTesterCheiMasina.setBounds(100, 100, 1014, 702);
		frmTesterCheiMasina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTesterCheiMasina.getContentPane().setLayout(null);
		btn_deschidere_trapa.addActionListener(ascultator);
		
		
		btn_deschidere_trapa.setBounds(390, 482, 176, 46);
		frmTesterCheiMasina.getContentPane().add(btn_deschidere_trapa);
		btn_oprire_motor.addActionListener(ascultator);
		
		
		btn_oprire_motor.setBounds(390, 527, 176, 46);
		frmTesterCheiMasina.getContentPane().add(btn_oprire_motor);
		btn_pornire_motor.addActionListener(ascultator); 
		btn_pornire_motor.setBounds(390, 394, 176, 46);
		frmTesterCheiMasina.getContentPane().add(btn_pornire_motor);
		btn_deschidere_centralizata.setBounds(390, 440, 176, 46);
		frmTesterCheiMasina.getContentPane().add(btn_deschidere_centralizata);
		btn_deschidere_centralizata.addActionListener(ascultator); 
		
		txtPagina = new JTextField();
		txtPagina.setEditable(false);
		txtPagina.setForeground(new Color(0, 128, 0));
		txtPagina.setFont(new Font("Tahoma", Font.PLAIN, 37));
		txtPagina.setText("PAGINA PRINCIPALA PENTRU VERIFICAREA CHEILOR");
		txtPagina.setBounds(34, 74, 904, 36);
		frmTesterCheiMasina.getContentPane().add(txtPagina);
		txtPagina.setColumns(10);
		
		txt_bmw = new JTextField();
		txt_bmw.setEditable(false);
		txt_bmw.setHorizontalAlignment(SwingConstants.CENTER);
		txt_bmw.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_bmw.setText("BMW");
		txt_bmw.setBounds(184, 138, 114, 31);
		frmTesterCheiMasina.getContentPane().add(txt_bmw);
		txt_bmw.setColumns(10);
		
		txt_audi = new JTextField();
		txt_audi.setEditable(false);
		txt_audi.setText("AUDI");
		txt_audi.setHorizontalAlignment(SwingConstants.CENTER);
		txt_audi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_audi.setColumns(10); 
		txt_audi.setBounds(664, 138, 114, 31);
		frmTesterCheiMasina.getContentPane().add(txt_audi);
		
		
		scrollPane1.setBounds(74, 190, 335, 177);
		frmTesterCheiMasina.getContentPane().add(scrollPane1);
		textArea_bmw.setEditable(false);
		scrollPane1.setViewportView(textArea_bmw);
		textArea_bmw.setWrapStyleWord(true);
		
		
		scrollPane2.setBounds(554, 190, 335, 177);
		frmTesterCheiMasina.getContentPane().add(scrollPane2);
		textArea_audi.setEditable(false);
		scrollPane2.setViewportView(textArea_audi);
		
		
		textArea_audi.setWrapStyleWord(true);
		btn_pornire_far.addActionListener(ascultator);
		
		
		btn_pornire_far.setBounds(390, 573, 176, 46);
		frmTesterCheiMasina.getContentPane().add(btn_pornire_far);
		
		
		
		//bara de meniu
		frmTesterCheiMasina.setJMenuBar(menuBar);
		menuBar.add(menu_toateMasinile);
		menu_toateMasinile.add(menu_masina1);
		menu_masina1.addActionListener(ascultator);
		menu_toateMasinile.add(menu_masina2);
		menu_masina2.addActionListener(ascultator);
		
		//
		JLabel labelLeft = new JLabel();
		JLabel labelRight = new JLabel();

		// Set the images for the labels
		labelLeft.setIcon(new ImageIcon("bmw.jpg"));
		labelRight.setIcon(new ImageIcon("bmw.jpg"));

		// Set the bounds of the labels
		labelLeft.setBounds(10, 10, 200, 200);
		labelRight.setBounds(210, 10, 200, 200);

		// Add the labels to the frame
		frmTesterCheiMasina.getContentPane().add(labelLeft);
		frmTesterCheiMasina.getContentPane().add(labelRight);
		
	}
}
