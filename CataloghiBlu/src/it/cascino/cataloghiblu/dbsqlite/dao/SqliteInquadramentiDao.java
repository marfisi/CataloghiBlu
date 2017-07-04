package it.cascino.cataloghiblu.dbsqlite.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteInquadramenti;

public interface SqliteInquadramentiDao{
	List<SqliteInquadramenti> getAll();
	
	Integer salva(SqliteInquadramenti a);
	
	void aggiorna(SqliteInquadramenti a);
	
	void elimina(SqliteInquadramenti a);

	SqliteInquadramenti getDaId(Integer id);
	
	SqliteInquadramenti getDaCodinquad(String codinquad);
	
	void close();
}
