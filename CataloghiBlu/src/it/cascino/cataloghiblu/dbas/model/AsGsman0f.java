package it.cascino.cataloghiblu.dbas.model;

import java.io.Serializable;
import javax.persistence.*;
import it.cascino.cataloghiblu.dbas.model.pkey.AsGsman0fPKey;
import it.cascino.cataloghiblu.dbas.model.pkey.AsListi0fPKey;

/**
* The persistent class for the cas_dat/gsman0f database table.
* 
*/
@Entity(name="Gsman0f")
@NamedQueries({
	@NamedQuery(name = "AsGsman0f.findAll", query = "SELECT a FROM Gsman0f a WHERE a.id.ggrup != 0 order by a.id.ggrup, a.id.gsotg asc"),
	@NamedQuery(name = "AsGsman0f.findByGgrupAndGsotg", query = "SELECT a FROM Gsman0f a WHERE a.id.ggrup = :ggrup and a.id.gsotg = :gsotg")
})
public class AsGsman0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	@EmbeddedId
	private AsGsman0fPKey id;
	private String gdesc;
	
	public AsGsman0f(){
	}

	public AsGsman0f(AsGsman0fPKey id, String gdesc){
		super();
		this.id = id;
		this.gdesc = gdesc;
	}

	public AsGsman0fPKey getId(){
		return id;
	}

	public void setId(AsGsman0fPKey id){
		this.id = id;
	}

	public String getGdesc(){
		return gdesc;
	}

	public void setGdesc(String gdesc){
		this.gdesc = gdesc;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gdesc == null) ? 0 : gdesc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsGsman0f) {
			if(this.id == ((AsGsman0f)obj).id) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsGsman0f [id=" + id + ", gdesc=" + gdesc + "]";
	}
}