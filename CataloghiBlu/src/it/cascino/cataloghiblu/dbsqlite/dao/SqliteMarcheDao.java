package it.cascino.cataloghiblu.dbsqlite.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteMarche;

public interface SqliteMarcheDao{
	List<SqliteMarche> getAll();
	
	Integer salva(SqliteMarche a);
	
	void aggiorna(SqliteMarche a);
	
	void elimina(SqliteMarche a);

	SqliteMarche getDaId(Integer id);
	
	SqliteMarche getDaCodmarca(String codmarca);
	
	void close();
}
