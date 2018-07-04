package it.cascino.cataloghiblu.dbas.dao;

import java.util.List;
import it.cascino.cataloghiblu.dbas.model.AsTabe20f;

public interface AsTabe20fDao{
	List<AsTabe20f> getAll();

	AsTabe20f getMarchio(String tbele);

	void close();
}
