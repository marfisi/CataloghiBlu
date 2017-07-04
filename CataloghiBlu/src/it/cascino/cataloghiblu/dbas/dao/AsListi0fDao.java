package it.cascino.cataloghiblu.dbas.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbas.model.AsListi0f;

public interface AsListi0fDao{
	List<AsListi0f> getAll();
	
//	void salva(AsListi0f a);
//	
//	void aggiorna(AsListi0f a);
//	
//	void elimina(AsListi0f a);

	AsListi0f getDaLscoa(String lscoa);
	
	void close();
}
