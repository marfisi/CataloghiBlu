package it.cascino.cataloghiblu.dbsqlite.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
* The persistent class for the associati database table.
* 
*/
@Entity(name="associati")
@NamedQueries({
	@NamedQuery(name = "SqliteAssociati.findAll", query = "SELECT a FROM associati a"),
	@NamedQuery(name = "SqliteAssociati.findByCodartcapo", query = "SELECT a FROM associati a WHERE a.codartcapo = :codartcapo"),
	@NamedQuery(name = "SqliteAssociati.findByCodartfiglio", query = "SELECT a FROM associati a WHERE a.codartfiglio = :codartfiglio")
})
public class SqliteAssociati implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer _id;
	private String codartcapo;
	private String codartfiglio;
	private String codasso;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer get_id(){
		return _id;
	}

	public void set_id(Integer _id){
		this._id = _id;
	}

	public String getCodartcapo(){
		return codartcapo;
	}

	public void setCodartcapo(String codartcapo){
		this.codartcapo = codartcapo;
	}

	public String getCodartfiglio(){
		return codartfiglio;
	}

	public void setCodartfiglio(String codartfiglio){
		this.codartfiglio = codartfiglio;
	}
	
	public String getCodasso(){
		return codasso;
	}

	public void setCodasso(String codasso){
		this.codasso = codasso;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((codartcapo == null) ? 0 : codartcapo.hashCode());
		result = prime * result + ((codartfiglio == null) ? 0 : codartfiglio.hashCode());
		result = prime * result + ((codasso == null) ? 0 : codasso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof SqliteAssociati){
			if(this._id == ((SqliteAssociati)obj)._id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "SqliteAssociati [_id=" + _id + ", codartcapo=" + codartcapo + ", codartfiglio=" + codartfiglio+ ", codasso=" + codasso + "]";
	}
}
