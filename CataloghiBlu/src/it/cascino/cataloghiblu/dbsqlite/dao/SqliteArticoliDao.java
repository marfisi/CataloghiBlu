package it.cascino.cataloghiblu.dbsqlite.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteArticoli;

public interface SqliteArticoliDao{
	List<SqliteArticoli> getAll();
	
	Integer salva(SqliteArticoli a);
	
	void aggiorna(SqliteArticoli a);
	
	void elimina(SqliteArticoli a);

	SqliteArticoli getDaId(Integer id);
	
	SqliteArticoli getDaCodice(String codiceArticolo);
	
	void close();
}
