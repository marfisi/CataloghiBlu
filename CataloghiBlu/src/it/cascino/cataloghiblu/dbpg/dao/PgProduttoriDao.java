package it.cascino.cataloghiblu.dbpg.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbpg.model.PgProduttori;

public interface PgProduttoriDao{
	List<PgProduttori> getAll();
	
	PgProduttori getProduttoreDaIdProduttore(Integer idProduttore);
	
	void close();
}
