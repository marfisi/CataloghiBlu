package it.cascino.cataloghiblu.dbpg.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbpg.model.PgArticoli;

public interface PgArticoliDao{
	List<PgArticoli> getAll();
	
	PgArticoli getArticoloDaCodice(String codiceArticolo);
	
	Integer getFotoArticoloDaCodice(String codiceArticolo);
	
	List<PgArticoli> getArticoloFratelliLsDaCodiceFoto(Integer idFoto);
	
	String getFotoNameArticoloDaId(Integer idFoto);
	
	boolean checkArticoloHaComePrimaFotoIdFoto(PgArticoli art, Integer idFoto);
	
	void close();
}
