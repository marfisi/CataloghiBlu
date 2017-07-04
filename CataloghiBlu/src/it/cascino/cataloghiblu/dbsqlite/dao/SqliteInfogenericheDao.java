package it.cascino.cataloghiblu.dbsqlite.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteInfogeneriche;

public interface SqliteInfogenericheDao{
	List<SqliteInfogeneriche> getAll();
	
	Integer salva(SqliteInfogeneriche a);
	
	void aggiorna(SqliteInfogeneriche a);
	
	void elimina(SqliteInfogeneriche a);

	SqliteInfogeneriche getDaId(Integer id);
	
	SqliteInfogeneriche getDaNomeInfo(String nomeInfo);
	
	void close();
}
