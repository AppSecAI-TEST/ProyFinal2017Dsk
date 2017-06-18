package gpd.presentacion.formulario;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;

import gpd.dominio.producto.Deposito;
import gpd.dominio.producto.TipoProd;
import gpd.dominio.producto.Utilidad;
import gpd.dominio.transaccion.EstadoTran;
import gpd.dominio.transaccion.Transaccion;
import gpd.dominio.usuario.UsuarioDsk;
import gpd.presentacion.controlador.CtrlFrmProducto;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrmProducto.class);

	private static FrmProducto instance; 
	private CtrlFrmProducto ctrlProd;
	private JPanel contentPane;
	private JTextField txtProId;
	private JTextField txtProCod;
	private JTextField txtProNom;
	private JTextField txtProDesc;
	private JFormattedTextField ftxtProStockMin;
	private JFormattedTextField ftxtProPrecio;
	private JComboBox<TipoProd> cbxTipoProd;
	private JDesktopPane desktopPane;
	private JButton btnTpAgregar;
	private JButton btnProAgr;
	private JButton btnProMod;
	private JButton btnProEli;
	private JButton btnProBuscar;
	private JButton btnProLimpiar;
	private JTable jtProd;
	private JComboBox<Deposito> cbxLoteDep;
	private JComboBox<Utilidad> cbxLoteUtil;
	private JScrollPane scrollPaneLote;
	private JButton btnUtilAgregar;
	private JTable jtLote;
	private JTable jtLoteDep;
	private JComboBox<EstadoTran> cbxFiltroLote;
	private JDateChooser dchLoteVenc;
	private JComboBox<Transaccion> cbxLoteCompras;
	private JDateChooser dchLoteIni;
	private JDateChooser dchLoteFin;


	public static FrmProducto getFrmProducto(UsuarioDsk usr) {
		if(instance == null) {
			logger.info("Se genera nueva instancia de FrmProducto > usuario logueado: " + usr.getNomUsu());
			instance = new FrmProducto(usr);
		}
		return instance;
	}

	/**
	 * Create the frame.
	 */
	private FrmProducto(UsuarioDsk usr) {
		
		setLocationRelativeTo(null);
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		ctrlProd = new CtrlFrmProducto(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.control);
		desktopPane.setBounds(0, 0, 0, 0);
		desktopPane.setLayout(null);
		contentPane.add(desktopPane);
		//agrego desktopPane a controlador
		ctrlProd.setDeskPane(desktopPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 784, 565);
		contentPane.add(tabbedPane);
		
		//PROD
		
		JPanel tpProd = new JPanel();
		tabbedPane.addTab("Producto", null, tpProd, null);
		tpProd.setLayout(null);
		
		txtProId = new JTextField();
		txtProId.setEditable(false);
		txtProId.setColumns(10);
		txtProId.setBounds(72, 12, 66, 20);
		tpProd.add(txtProId);
		
		JLabel label = new JLabel("Tipo");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(23, 45, 46, 14);
		tpProd.add(label);
		
		cbxTipoProd = new JComboBox<>();
		cbxTipoProd.setBounds(72, 42, 151, 20);
		tpProd.add(cbxTipoProd);
		
		JLabel label_1 = new JLabel("Codigo");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(23, 73, 46, 14);
		tpProd.add(label_1);
		
		txtProCod = new JTextField();
		txtProCod.setColumns(10);
		txtProCod.setBounds(72, 70, 151, 20);
		tpProd.add(txtProCod);
		
		JLabel label_2 = new JLabel("Nombre");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(23, 104, 46, 14);
		tpProd.add(label_2);
		
		txtProNom = new JTextField();
		txtProNom.setColumns(10);
		txtProNom.setBounds(72, 101, 151, 20);
		tpProd.add(txtProNom);
		
		JLabel label_3 = new JLabel("Descripcion");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 132, 59, 14);
		tpProd.add(label_3);
		
		txtProDesc = new JTextField();
		txtProDesc.setColumns(10);
		txtProDesc.setBounds(72, 129, 151, 20);
		tpProd.add(txtProDesc);
		
		JLabel label_4 = new JLabel("Stock Min");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(10, 160, 59, 14);
		tpProd.add(label_4);
		
		ftxtProStockMin = new JFormattedTextField();
		ftxtProStockMin.setBounds(72, 157, 65, 20);
		tpProd.add(ftxtProStockMin);
		
		ftxtProPrecio = new JFormattedTextField();
		ftxtProPrecio.setBounds(72, 185, 65, 20);
		
		JLabel label_5 = new JLabel("Precio");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(23, 188, 46, 14);
		tpProd.add(label_5);
		tpProd.add(ftxtProPrecio);
		btnProAgr = new JButton("Agregar");
		btnProAgr.setBounds(282, 11, 89, 23);
		tpProd.add(btnProAgr);
		
		JSeparator sepProd1 = new JSeparator();
		sepProd1.setOrientation(SwingConstants.VERTICAL);
		sepProd1.setForeground(SystemColor.info);
		sepProd1.setBackground(SystemColor.info);
		sepProd1.setBounds(270, 11, 2, 268);
		tpProd.add(sepProd1);
		btnProMod = new JButton("Modificar");
		btnProMod.setBounds(282, 45, 89, 23);
		tpProd.add(btnProMod);
		btnProEli = new JButton("Eliminar");
		btnProEli.setBounds(282, 79, 89, 23);
		tpProd.add(btnProEli);
		btnTpAgregar = new JButton("...");
		btnTpAgregar.setBounds(228, 41, 32, 23);
		tpProd.add(btnTpAgregar);
		
		btnProBuscar = new JButton("Buscar");
		btnProBuscar.setBounds(282, 129, 89, 23);
		tpProd.add(btnProBuscar);
		
		btnProLimpiar = new JButton("Limpiar");
		btnProLimpiar.setBounds(282, 160, 89, 23);
		tpProd.add(btnProLimpiar);
		
		JScrollPane scrollPaneProd = new JScrollPane();
		scrollPaneProd.setBounds(10, 303, 759, 223);
		tpProd.add(scrollPaneProd);
		
		jtProd = new JTable();
		scrollPaneProd.setColumnHeaderView(jtProd);
		scrollPaneProd.setViewportView(jtProd);
		
		
		JSeparator sepProd2 = new JSeparator();
		sepProd2.setForeground(SystemColor.info);
		sepProd2.setBackground(SystemColor.info);
		sepProd2.setBounds(10, 290, 759, 2);
		tpProd.add(sepProd2);
		
		//LOTE
		
		JPanel tpLote = new JPanel();
		tabbedPane.addTab("Lote", null, tpLote, null);
		tpLote.setLayout(null);
		
		scrollPaneLote = new JScrollPane();
		scrollPaneLote.setBounds(10, 80, 759, 160);
		tpLote.add(scrollPaneLote);
		
		jtLote = new JTable();
		scrollPaneLote.setColumnHeaderView(jtLote);
		scrollPaneLote.setViewportView(jtLote);
		
		JLabel lblLotesDisponibles = new JLabel("Estado");
		lblLotesDisponibles.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLotesDisponibles.setBounds(16, 11, 56, 14);
		tpLote.add(lblLotesDisponibles);
		
		JPanel panelLoteDatos = new JPanel();
		panelLoteDatos.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLoteDatos.setBounds(10, 264, 322, 195);
		tpLote.add(panelLoteDatos);
		panelLoteDatos.setLayout(null);
		
		JButton btnDepAgregar = new JButton("...");
		btnDepAgregar.setBounds(227, 11, 32, 23);
		panelLoteDatos.add(btnDepAgregar);
		
		cbxLoteDep = new JComboBox<>();
		cbxLoteDep.setBounds(66, 12, 151, 20);
		panelLoteDatos.add(cbxLoteDep);
		
		
		JLabel lblDeposito = new JLabel("Deposito");
		lblDeposito.setBounds(10, 15, 46, 14);
		panelLoteDatos.add(lblDeposito);
		lblDeposito.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_6 = new JLabel("Utilidad");
		label_6.setBounds(4, 46, 52, 14);
		panelLoteDatos.add(label_6);
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		
		cbxLoteUtil = new JComboBox<>();
		cbxLoteUtil.setBounds(66, 43, 151, 20);
		panelLoteDatos.add(cbxLoteUtil);
		
		dchLoteVenc = new JDateChooser();
		dchLoteVenc.setBounds(130, 74, 87, 20);
		panelLoteDatos.add(dchLoteVenc);
		
		JButton btnLoteAct = new JButton("Actualizar");
		btnLoteAct.setBounds(128, 105, 89, 23);
		panelLoteDatos.add(btnLoteAct);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(128, 139, 89, 23);
		panelLoteDatos.add(btnLimpiar);
		
		btnUtilAgregar = new JButton("...");
		btnUtilAgregar.setBounds(227, 42, 32, 23);
		panelLoteDatos.add(btnUtilAgregar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.info);
		separator.setBackground(SystemColor.info);
		separator.setBounds(10, 251, 759, 2);
		tpLote.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(SystemColor.info);
		separator_1.setBackground(SystemColor.info);
		separator_1.setBounds(342, 264, 2, 195);
		tpLote.add(separator_1);
		
		JScrollPane scrollPaneLoteDep = new JScrollPane();
		scrollPaneLoteDep.setBounds(354, 264, 415, 195);
		tpLote.add(scrollPaneLoteDep);
		
		jtLoteDep = new JTable();
		scrollPaneLoteDep.setColumnHeaderView(jtLoteDep);
		scrollPaneLoteDep.setViewportView(jtLoteDep);
		
		cbxFiltroLote = new JComboBox<>();
		cbxFiltroLote.setBounds(82, 8, 122, 20);
		tpLote.add(cbxFiltroLote);
		
		cbxLoteCompras = new JComboBox<>();
		cbxLoteCompras.setBounds(82, 49, 381, 20);
		tpLote.add(cbxLoteCompras);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCompras.setBounds(16, 52, 56, 14);
		tpLote.add(lblCompras);
		
		dchLoteIni = new JDateChooser();
		dchLoteIni.setBounds(278, 8, 87, 20);
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_YEAR, -10);
		dchLoteIni.setCalendar(gc);
		tpLote.add(dchLoteIni);
		
		dchLoteFin = new JDateChooser();
		dchLoteFin.setBounds(376, 8, 87, 20);
		dchLoteFin.setCalendar(new GregorianCalendar());
		tpLote.add(dchLoteFin);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPeriodo.setBounds(214, 11, 56, 14);
		tpLote.add(lblPeriodo);
		
		JButton btnLoteObtener = new JButton("Obtener");
		btnLoteObtener.setBounds(473, 7, 89, 23);
		tpLote.add(btnLoteObtener);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.info);
		separator_2.setBackground(SystemColor.info);
		separator_2.setBounds(10, 36, 759, 2);
		tpLote.add(separator_2);
		
		/*****************************************************************************************************************************************************/
		/* ACCIONES CONTROLES */
		/*****************************************************************************************************************************************************/
		
		ctrlProd.cargarCbxTipoProd(cbxTipoProd);
		ctrlProd.cargarCbxDep(cbxLoteDep);
		ctrlProd.cargarCbxUtil(cbxLoteUtil);
		ctrlProd.cargarCbxFiltroLote(cbxFiltroLote);
		
		btnUtilAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlProd.abrirIFrmUtil();
			}
		});
		btnDepAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlProd.abrirIFrmDep();
			}
		});
		
		ftxtProStockMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ctrlProd.controlInputNum(e);
			}
		});
		ftxtProPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ctrlProd.controlInputNum(e);
			}
		});
		btnLoteObtener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlProd.obtenerTransac(cbxFiltroLote, dchLoteIni, dchLoteFin);
			}
		});
		btnProAgr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlProd.agregarProducto(cbxTipoProd, txtProCod, txtProNom, txtProDesc, ftxtProStockMin, ftxtProPrecio);
			}
		});
		btnProMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlProd.modificarProducto(cbxTipoProd, txtProId, txtProCod, txtProNom, txtProDesc, ftxtProStockMin, ftxtProPrecio);
			}
		});
		btnProEli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlProd.eliminarProducto(txtProId);
			}
		});
		btnTpAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlProd.abrirIFrmTp();
			}
		});
		btnProBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlProd.buscarProducto(cbxTipoProd, txtProCod, txtProNom, txtProDesc);
			}
		});
		btnProLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlProd.clearForm(getContentPane());
			}
		});
		
		btnLoteAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlProd.actualizarLote(jtLote, cbxLoteDep, cbxLoteUtil, dchLoteVenc);
			}
		});
		
		cbxLoteCompras.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ctrlProd.cargarJtLote(cbxLoteCompras);
			}
		});
	}
	

	/*****************************************************************************************************************************************************/
	/* GET Y SET */
	/*****************************************************************************************************************************************************/
	
	public CtrlFrmProducto getCtrlProd() {
		return ctrlProd;
	}
	public void setCtrlProd(CtrlFrmProducto ctrlProd) {
		this.ctrlProd = ctrlProd;
	}
	
	public JComboBox<TipoProd> getCbxTipoProd() {
		return this.cbxTipoProd;
	}
	public void setCbxTipoProd(JComboBox<TipoProd> cbxTipoProd) {
		this.cbxTipoProd = cbxTipoProd;
	}
	
	public JComboBox<Deposito> getCbxDep() {
		return cbxLoteDep;
	}
	public void setCbxDep(JComboBox<Deposito> cbxDep) {
		this.cbxLoteDep = cbxDep;
	}
	
	public JTextField getTxtProId() {
		return txtProId;
	}
	public void setTxtProId(JTextField txtProId) {
		this.txtProId = txtProId;
	}

	public JTextField getTxtProCod() {
		return txtProCod;
	}
	public void setTxtProCod(JTextField txtProCod) {
		this.txtProCod = txtProCod;
	}
	
	public JTextField getTxtProNom() {
		return txtProNom;
	}
	public void setTxtProNom(JTextField txtProNom) {
		this.txtProNom = txtProNom;
	}

	public JTextField getTxtProDesc() {
		return txtProDesc;
	}
	public void setTxtProDesc(JTextField txtProDesc) {
		this.txtProDesc = txtProDesc;
	}

	public JFormattedTextField getFtxtProStockMin() {
		return ftxtProStockMin;
	}
	public void setFtxtProStockMin(JFormattedTextField ftxtProStockMin) {
		this.ftxtProStockMin = ftxtProStockMin;
	}

	public JFormattedTextField getFtxtProPrecio() {
		return ftxtProPrecio;
	}
	public void setFtxtProPrecio(JFormattedTextField ftxtProPrecio) {
		this.ftxtProPrecio = ftxtProPrecio;
	}
	
	public JTable getJtProd() {
		return jtProd;
	}
	public void setJtProd(JTable jtProd) {
		this.jtProd = jtProd;
	}
	
	public JComboBox<Utilidad> getCbxUtil() {
		return cbxLoteUtil;
	}
	public void setCbxUtil(JComboBox<Utilidad> cbxUtil) {
		this.cbxLoteUtil = cbxUtil;
	}
	
	public JTable getJtLote() {
		return jtLote;
	}
	public void setJtLote(JTable jtLote) {
		this.jtLote = jtLote;
	}

	public JTable getJtLoteDep() {
		return jtLoteDep;
	}
	public void setJtLoteDep(JTable jtLoteDep) {
		this.jtLoteDep = jtLoteDep;
	}

	public JComboBox<EstadoTran> getCbxFiltroLote() {
		return cbxFiltroLote;
	}
	public void setCbxFiltroLote(JComboBox<EstadoTran> cbxFiltroLote) {
		this.cbxFiltroLote = cbxFiltroLote;
	}
	
	public JDateChooser getDchLoteVenc() {
		return dchLoteVenc;
	}
	public void setDchLoteVenc(JDateChooser dchLoteVenc) {
		this.dchLoteVenc = dchLoteVenc;
	}
	
	public JComboBox<Deposito> getCbxLoteDep() {
		return cbxLoteDep;
	}
	public void setCbxLoteDep(JComboBox<Deposito> cbxLoteDep) {
		this.cbxLoteDep = cbxLoteDep;
	}

	public JComboBox<Utilidad> getCbxLoteUtil() {
		return cbxLoteUtil;
	}
	public void setCbxLoteUtil(JComboBox<Utilidad> cbxLoteUtil) {
		this.cbxLoteUtil = cbxLoteUtil;
	}

	public JComboBox<Transaccion> getCbxLoteCompras() {
		return cbxLoteCompras;
	}
	public void setCbxLoteCompras(JComboBox<Transaccion> cbxLoteCompras) {
		this.cbxLoteCompras = cbxLoteCompras;
	}

	public JDateChooser getDchLoteIni() {
		return dchLoteIni;
	}
	public void setDchLoteIni(JDateChooser dchLoteIni) {
		this.dchLoteIni = dchLoteIni;
	}

	public JDateChooser getDchLoteFin() {
		return dchLoteFin;
	}
	public void setDchLoteFin(JDateChooser dchLoteFin) {
		this.dchLoteFin = dchLoteFin;
	}
}
