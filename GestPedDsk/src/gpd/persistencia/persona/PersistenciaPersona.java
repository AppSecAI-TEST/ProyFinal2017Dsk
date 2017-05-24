package gpd.persistencia.persona;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import gpd.db.constantes.CnstQryPersona;
import gpd.db.generic.GenSqlExecType;
import gpd.dominio.persona.PersonaFisica;
import gpd.dominio.persona.PersonaJuridica;
import gpd.dominio.persona.TipoPersona;
import gpd.dominio.util.Origen;
import gpd.dominio.util.Sinc;
import gpd.exceptions.ConectorException;
import gpd.interfaces.persona.IPersPersona;
import gpd.persistencia.conector.Conector;
import gpd.types.Fecha;

public class PersistenciaPersona extends Conector implements IPersPersona  {
	
	private static final Logger logger = Logger.getLogger(PersistenciaPersona.class);
	private Integer resultado;
	
	@Override
	public PersonaFisica obtenerPersFisicaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonaJuridica obtenerPersJuridicaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer guardarPersFisica(PersonaFisica persFisica) {
		try {
			
			Long documento = persFisica.getDocumento();
			
			guardarPersona(documento, persFisica.getDireccion(), persFisica.getPuerta(), persFisica.getSolar(), persFisica.getManzana(),
					persFisica.getKm(), persFisica.getComplemento(), persFisica.getTelefono(), persFisica.getCelular(), persFisica.getEmail(),
					persFisica.getFechaReg(), persFisica.getTipoPers(), persFisica.getLocalidad().getIdLocalidad());
			
			GenSqlExecType genExec = new GenSqlExecType(CnstQryPersona.QUERY_INSERT_CLIENTE);
			genExec.getExecuteDatosCond().put(1, documento);
			genExec.getExecuteDatosCond().put(2, persFisica.getTipoDoc().getIdTipoDoc());
			genExec.getExecuteDatosCond().put(3, persFisica.getApellido1());
			genExec.getExecuteDatosCond().put(4, persFisica.getApellido2());
			genExec.getExecuteDatosCond().put(5, persFisica.getNombre1());
			genExec.getExecuteDatosCond().put(6, persFisica.getNombre2());
			genExec.getExecuteDatosCond().put(7, persFisica.getFechaNac());
			genExec.getExecuteDatosCond().put(8, persFisica.getSexo().getAsChar());
			genExec.getExecuteDatosCond().put(9, Origen.D.getAsChar());
			genExec.getExecuteDatosCond().put(10, Sinc.N.getAsChar());
			genExec.getExecuteDatosCond().put(11, persFisica.getUltAct());
			resultado = (Integer) runGeneric(genExec);
			
		} catch (ConectorException e) {
			Conector.rollbackConn();
			logger.log(Level.FATAL, "Excepcion al guardarPersFisica: " + e.getMessage(), e);
		}
		return resultado;
	}

	@Override
	public Integer guardarPersJuridica(PersonaJuridica personaJuridica, char tipoPj) {
		try {
			guardarPersona(personaJuridica.getRut(), personaJuridica.getDireccion(), personaJuridica.getPuerta(), personaJuridica.getSolar(), personaJuridica.getManzana(),
					personaJuridica.getKm(), personaJuridica.getComplemento(), personaJuridica.getTelefono(), personaJuridica.getCelular(), personaJuridica.getEmail(),
					personaJuridica.getFechaReg(), personaJuridica.getTipoPers(), personaJuridica.getLocalidad().getIdLocalidad());
			
			GenSqlExecType genExec = new GenSqlExecType(CnstQryPersona.QUERY_INSERT_PROVEEDOR);
			genExec.getExecuteDatosCond().put(1, personaJuridica.getRut());
			genExec.getExecuteDatosCond().put(2, personaJuridica.getNombre());
			genExec.getExecuteDatosCond().put(3, personaJuridica.getRazonSocial());
			genExec.getExecuteDatosCond().put(4, personaJuridica.getBps());
			genExec.getExecuteDatosCond().put(5, personaJuridica.getBse());
			genExec.getExecuteDatosCond().put(6, tipoPj);
			resultado = (Integer) runGeneric(genExec);
			
		} catch (ConectorException e) {
			Conector.rollbackConn();
			logger.log(Level.FATAL, "Excepcion al guardarPersJuridica: " + e.getMessage(), e);
		}
		return resultado;
	}
	
	private Integer guardarPersona(Long idPersona, String direccion, String puerta, String solar, String manzana,
			Float km, String complemento, String telefono, String celular, String email, Fecha fechaReg, TipoPersona tipoPers, 
			Integer idLoc) throws ConectorException {
		try {
			GenSqlExecType genExec = new GenSqlExecType(CnstQryPersona.QUERY_INSERT_PERSONA);
			genExec.getExecuteDatosCond().put(1, idPersona);
			genExec.getExecuteDatosCond().put(2, direccion);
			genExec.getExecuteDatosCond().put(3, puerta);
			genExec.getExecuteDatosCond().put(4, solar);
			genExec.getExecuteDatosCond().put(5, manzana);
			genExec.getExecuteDatosCond().put(6, km);
			genExec.getExecuteDatosCond().put(7, complemento);
			genExec.getExecuteDatosCond().put(8, telefono);
			genExec.getExecuteDatosCond().put(9, celular);
			genExec.getExecuteDatosCond().put(10, email);
			genExec.getExecuteDatosCond().put(11, fechaReg);
			genExec.getExecuteDatosCond().put(12, tipoPers.getAsChar());
			genExec.getExecuteDatosCond().put(13, idLoc);
			resultado = (Integer) runGeneric(genExec);
		} catch (ConectorException e) {
			Conector.rollbackConn();
			throw e;
		}
		return resultado;
	}


}
