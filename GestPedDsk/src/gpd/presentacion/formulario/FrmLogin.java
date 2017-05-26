package gpd.presentacion.formulario;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import gpd.dominio.usuario.UsuarioDsk;
import gpd.exceptions.UsuarioNoExisteException;
import gpd.presentacion.controlador.CtrlFrmLogin;
import gpd.presentacion.generic.CnstPresGeneric;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FrmLogin.class);
	private JPanel contentPane;
	private JTextField txtUsr;
	private CtrlFrmLogin ctrl;
	private JPasswordField txtPass;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FrmLogin frame = new FrmLogin();
					frame.setLocationRelativeTo(null);
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
	public FrmLogin() {
		ctrl = new CtrlFrmLogin();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsr = new JTextField();
		txtUsr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					obtenerUsuario(txtUsr.getText(), txtPass.getPassword());
				}
			}
		});
		txtUsr.setBounds(304, 94, 120, 20);
		contentPane.add(txtUsr);
		txtUsr.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obtenerUsuario(txtUsr.getText(), txtPass.getPassword());
			}
		});
		
		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					obtenerUsuario(txtUsr.getText(), txtPass.getPassword());
				}
			}
		});
		txtPass.setBounds(304, 125, 120, 20);
		contentPane.add(txtPass);
		btnLogin.setBounds(335, 166, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(248, 97, 46, 14);
		contentPane.add(lblUsuario);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(248, 128, 46, 14);
		contentPane.add(lblPassword);
	}
	
	private void obtenerUsuario(String nombre, char[] passwd) {
		UsuarioDsk usr = null;
		try {
			usr = ctrl.obtenerUsuario(nombre, String.valueOf(passwd));
		} catch (HeadlessException | UsuarioNoExisteException e) {
			logger.debug("Presentacion: " + e.getMessage());
		}
		if(usr != null) {
			FrmPrincipal frmPpl = new FrmPrincipal(usr);
			frmPpl.setLocationRelativeTo(null);
			frmPpl.setDefaultCloseOperation(FrmPrincipal.EXIT_ON_CLOSE);
			frmPpl.setVisible(true);
			setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, CnstPresGeneric.USR_NO_AUTENTICADO, CnstPresGeneric.USR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
