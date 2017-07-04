package it.cascino.cataloghiblu.dbsqlite.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteAssociati;

public interface SqliteAssociatiDao{
	List<SqliteAssociati> getAll();
	
	Integer salva(SqliteAssociati a);
	
	void aggiorna(SqliteAssociati a);
	
	void elimina(SqliteAssociati a);

	List<SqliteAssociati> getDaCodartcapo(String codartcapo);
	
	SqliteAssociati getDaCodartfiglio(String codartfiglio);
	
	void close();
}
