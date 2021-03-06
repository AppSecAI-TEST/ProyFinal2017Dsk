package gpd.persistencia.persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpd.db.constantes.CnstQryTipoDoc;
import gpd.db.generic.GenSqlExecType;
import gpd.db.generic.GenSqlSelectType;
import gpd.dominio.persona.TipoDoc;
import gpd.exceptions.ConectorException;
import gpd.exceptions.PersistenciaException;
import gpd.interfaces.persona.IPersTipoDoc;
import gpd.persistencia.conector.Conector;

public class PersistenciaTipoDoc extends Conector implements IPersTipoDoc, CnstQryTipoDoc {

	private static final Logger logger = Logger.getLogger(PersistenciaTipoDoc.class);
	private ResultSet rs;
	
	@Override
	public TipoDoc obtenerTipoDocPorId(Integer id) throws PersistenciaException {
		TipoDoc tipoDoc = null;
		try {
			GenSqlSelectType genSel = new GenSqlSelectType(QRY_SELECT_TIPODOC_XID);
			genSel.setParam(id);
			rs = (ResultSet) runGeneric(genSel);
			if(rs.next()) {
				tipoDoc = new TipoDoc();
				tipoDoc.setIdTipoDoc(rs.getInt("id_tipo_doc"));
				tipoDoc.setNombre(rs.getString("nombre"));
			}
		} catch (ConectorException | SQLException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion al obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return tipoDoc;
	}
	
	@Override
	public Integer guardarTipoDoc(TipoDoc tipoDoc) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_INSERT_TIPODOC);
		genExec.setParam(tipoDoc.getNombre());
		try {
			resultado = (Integer) runGeneric(genExec);
		} catch (ConectorException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion al guardarTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer modificarTipoDoc(TipoDoc tipoDoc) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_UPDATE_TIPODOC);
		genExec.setParam(tipoDoc.getNombre());
		genExec.setParam(tipoDoc.getIdTipoDoc());
		try {
			resultado = (Integer) runGeneric(genExec);
		} catch (ConectorException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion al modificarTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public Integer eliminarTipoDoc(TipoDoc tipoDoc) throws PersistenciaException {
		Integer resultado = null;
		GenSqlExecType genExec = new GenSqlExecType(QRY_DELETE_TIPODOC);
		genExec.setParam(tipoDoc.getIdTipoDoc());
		try {
			resultado = (Integer) runGeneric(genExec);
		} catch (ConectorException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion al eliminarTipoProd: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		}
		return resultado;
	}

	@Override
	public List<TipoDoc> obtenerListaTipoDoc() throws PersistenciaException {
		List<TipoDoc> listaTipoDoc = new ArrayList<>();
		try {
			GenSqlSelectType genType = new GenSqlSelectType(QRY_SELECT_TIPODOC);
			rs = (ResultSet) runGeneric(genType);
			while(rs.next()) {
				TipoDoc tipoDoc = new TipoDoc();
				tipoDoc.setIdTipoDoc(rs.getInt("id_tipo_doc"));
				tipoDoc.setNombre(rs.getString("nombre"));
				listaTipoDoc.add(tipoDoc);
			}
		} catch (ConectorException | SQLException e) {
			Conector.rollbackConn();
			logger.fatal("Excepcion al obtenerListaTipoDoc: " + e.getMessage(), e);
			throw new PersistenciaException(e);
		} finally {
			closeRs(rs);
		}
		return listaTipoDoc;
	}
	

}
