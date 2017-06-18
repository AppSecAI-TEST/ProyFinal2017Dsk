package gpd.presentacion.generic;

public interface CnstPresGeneric {
	
	//usuario
	public static final String USR = "Usuario";
	public final static String USR_DATOS = "Debe proporcionar nombre de usuario y password.";
	public final static String USR_NO_AUTENTICADO = "EL usuario no se ha podido autenticar.";
	
	//prod
	public static final String PROD = "Producto";
	
	//tipoProd
	public static final String TP = "Tipo Prod";
	public static final String TP_ING_OK = "Se ha ingresado el tipo producto correctamente.";
	public static final String TP_MOD_OK = "Se ha modificado el tipo producto correctamente.";
	public static final String TP_ELI_OK = "Se ha eliminado el tipo producto correctamente.";
	
	//deposito
	public static final String DEP = "Deposito";
	public static final String DEP_ING_OK = "Se ha ingresado el deposito correctamente.";
	public static final String DEP_MOD_OK = "Se ha modificado el deposito correctamente.";
	public static final String DEP_ELI_OK = "Se ha eliminado el deposito correctamente.";
	
	//persona
	public static final String PERS = "Persona";
	public static final String PERS_F_ING_OK = "Se ha ingresado la persona correctamente.";
	public static final String PERS_F_MOD_OK = "Se ha modificado la persona correctamente.";
	public static final String PERS_F_ELI_OK = "Se ha eliminado la persona correctamente.";
	public static final String PERS_J_ING_OK = "Se ha ingresado la empresa correctamente.";
	public static final String PERS_J_MOD_OK = "Se ha modificado la empresa correctamente.";
	public static final String PERS_J_ELI_OK = "Se ha eliminado la empresa correctamente.";
	public static final String PERS_J_P_ING_OK = "Se ha ingresado el proveedor correctamente.";
	public static final String PERS_J_P_MOD_OK = "Se ha modificado el proveedor correctamente.";
	public static final String PERS_J_P_ELI_OK = "Se ha eliminado el proveedor correctamente.";
	
	//compra
	public static final String MOV = "Movimiento";
	public static final String COMPRA_CONF_LIMPIAR = "Confirma que desea limpiar el formulario ?";
	public static final String COMPRA_PROD_YA_ING = "El producto ya ha sido ingresado, revise los datos.";
	public static final String COMPRA_SIN_TRANSAC = "No existe compra abierta, revise los datos.";
	public static final String COMPRA_SIN_LINEAS = "No existen items en la compra, revise los datos.";
	public static final String COMPRA_CONFIRMADA = "La compra se ha confirmado.";
	public static final String COMPRA_ANULADA = "La compra se ha anulado.";
	public static final String COMPRA_NOMODIF_PROD = "El prodcuto que intenta modificar no es parte de la compra.";
	public static final String JTABLE_SIN_ITEMS = "No hay items para mostrar...";
	public static final String JTABLE_SIN_COMPRAS = "No hay compras para mostrar...";
	
	//utilidad
	public static final String UTIL = "Utilidad";
	public static final String UTIL_ING_OK = "Se ha ingresado la utilidad correctamente.";
	public static final String UTIL_MOD_OK = "Se ha modificado la utilidad correctamente.";
	public static final String UTIL_ELI_OK = "Se ha eliminado la utilidad correctamente.";
	
	//lote
	public static final String LOTE = "Lote";
	public static final String JTABLE_SIN_LOTES = "No hay lotes para mostrar...";
	
	//controles genericos
	public static final String DATOS_OBLIG = "Los datos marcados son obligatorios.";
	public static final String JTABLE_EMPTY = "No se encontraron resultados...";
	public static final String N_A = "N/A";
	
}
