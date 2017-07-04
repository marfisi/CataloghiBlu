package it.cascino.cataloghiblu.dbas.model;

import java.io.Serializable;
import javax.persistence.*;
import it.cascino.cataloghiblu.dbas.model.pkey.AsListi0fPKey;

/**
* The persistent class for the cas_dat/listi0f database table.
* 
*/
@Entity(name = "Listi0f")
@NamedQueries({
	@NamedQuery(name = "AsListi0f.findAll", query = "SELECT a FROM Listi0f a"),
	@NamedQuery(name = "AsListi0f.findByLscoa", query = "SELECT a FROM Listi0f a WHERE a.id.lscoa = :lscoa and a.id.lscat = 'RA'")
})
public class AsListi0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AsListi0fPKey id;
	private Float lssc1;
	private Float lssc2;
	private Float lsprn;
	
	public AsListi0f(){
	}

	public AsListi0f(AsListi0fPKey id, Float lssc1, Float lssc2, Float lsprn){
		super();
		this.id = id;
		this.lssc1 = lssc1;
		this.lssc2 = lssc2;
		this.lsprn = lsprn;
	}

	public AsListi0fPKey getId(){
		return id;
	}

	public void setId(AsListi0fPKey id){
		this.id = id;
	}

	public Float getLssc1(){
		return lssc1;
	}

	public void setLssc1(Float lssc1){
		this.lssc1 = lssc1;
	}

	public Float getLssc2(){
		return lssc2;
	}

	public void setLssc2(Float lssc2){
		this.lssc2 = lssc2;
	}

	public Float getLsprn(){
		return lsprn;
	}

	public void setLsprn(Float lsprn){
		this.lsprn = lsprn;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lsprn == null) ? 0 : lsprn.hashCode());
		result = prime * result + ((lssc1 == null) ? 0 : lssc1.hashCode());
		result = prime * result + ((lssc2 == null) ? 0 : lssc2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsListi0f) {
			if(this.id == ((AsListi0f)obj).id) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsListi0f[id=" + id + ", lssc1=" + lssc1 + ", lssc2=" + lssc2 + ", lsprn=" + lsprn + "]";
	}
}