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
* The persistent class for the marche database table.
* 
*/
@Entity(name="marche")
@NamedQueries({
	@NamedQuery(name = "SqliteMarche.findAll", query = "SELECT a FROM marche a"),
	@NamedQuery(name = "SqliteMarche.findById", query = "SELECT a FROM marche a WHERE a._id = :id"),
	@NamedQuery(name = "SqliteMarche.findByCodmarca", query = "SELECT a FROM marche a WHERE a.codmarca = :codmarca")
})
public class SqliteMarche implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer _id;
	private String codmarca;
	private String marca;
	private String marcafoto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer get_id(){
		return _id;
	}

	public void set_id(Integer _id){
		this._id = _id;
	}

	public String getCodmarca(){
		return codmarca;
	}

	public void setCodmarca(String codmarca){
		this.codmarca = codmarca;
	}

	public String getMarca(){
		return marca;
	}

	public void setMarca(String marca){
		this.marca = marca;
	}

	public String getMarcafoto(){
		return marcafoto;
	}

	public void setMarcafoto(String marcafoto){
		this.marcafoto = marcafoto;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((codmarca == null) ? 0 : codmarca.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((marcafoto == null) ? 0 : marcafoto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof SqliteMarche){
			if(this._id == ((SqliteMarche)obj)._id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "SqliteMarche [_id=" + _id + ", codmarca=" + codmarca + ", marca=" + marca + ", marcafoto=" + marcafoto + "]";
	}

}
