package gpd.interfaces.transaccion;

import java.util.List;

import gpd.dominio.transaccion.TranLinea;
import gpd.dominio.transaccion.Transaccion;
import gpd.exceptions.PersistenciaException;

public interface IPersTranLinea {

	public TranLinea obtenerTranLineaPorId(Integer nroTransac, Integer idProducto) throws PersistenciaException;
	public List<TranLinea> obtenerListaTranLinea(Transaccion transac) throws PersistenciaException;
	public Integer guardarListaTranLinea(List<TranLinea> tranLinea) throws PersistenciaException;
	public Integer eliminarTranLinea(Integer nroTransac) throws PersistenciaException;
	
}
