package it.cascino.cataloghiblu.dbas.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbas.model.AsGsman0f;

public interface AsGsman0fDao{
	List<AsGsman0f> getAll();
	
	AsGsman0f getDaGgrupAndGsotg(Integer ggrup, Integer gsotg);

	void close();
}
