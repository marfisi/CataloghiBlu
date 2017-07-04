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
* The persistent class for the articoli database table.
* 
*/
@Entity(name="articoli")
@NamedQueries({
	@NamedQuery(name = "SqliteArticoli.findAll", query = "SELECT a FROM articoli a"),
	@NamedQuery(name = "SqliteArticoli.findById", query = "SELECT a FROM articoli a WHERE a._id = :id"),
	@NamedQuery(name = "SqliteArticoli.findByCodart", query = "SELECT a FROM articoli a WHERE a.codart = :codart")
})
public class SqliteArticoli implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer _id;
	private String codart;
	private String desc;
	private String um;
	private BigDecimal prezzo;
	private BigDecimal qty_per_confez;
	private String codinquad;
	private String artfoto1;
	private String artfoto2;
	private String codmarca;
	private String desc2;
	private String desc3;
	private String desc4;
	private String desc5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer get_id(){
		return _id;
	}

	public void set_id(Integer _id){
		this._id = _id;
	}

	public String getCodart(){
		return codart;
	}

	public void setCodart(String codart){
		this.codart = codart;
	}

	public String getDesc(){
		return desc;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getUm(){
		return um;
	}

	public void setUm(String um){
		this.um = um;
	}

	public BigDecimal getPrezzo(){
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo){
		this.prezzo = prezzo;
	}

	public BigDecimal getQty_per_confez(){
		return qty_per_confez;
	}

	public void setQty_per_confez(BigDecimal qty_per_confez){
		this.qty_per_confez = qty_per_confez;
	}

	public String getCodinquad(){
		return codinquad;
	}

	public void setCodinquad(String codinquad){
		this.codinquad = codinquad;
	}

	public String getArtfoto1(){
		return artfoto1;
	}

	public void setArtfoto1(String artfoto1){
		this.artfoto1 = artfoto1;
	}

	public String getArtfoto2(){
		return artfoto2;
	}

	public void setArtfoto2(String artfoto2){
		this.artfoto2 = artfoto2;
	}

	public String getCodmarca(){
		return codmarca;
	}

	public void setCodmarca(String codmarca){
		this.codmarca = codmarca;
	}

	public String getDesc2(){
		return desc2;
	}

	public void setDesc2(String desc2){
		this.desc2 = desc2;
	}

	public String getDesc3(){
		return desc3;
	}

	public void setDesc3(String desc3){
		this.desc3 = desc3;
	}

	public String getDesc4(){
		return desc4;
	}

	public void setDesc4(String desc4){
		this.desc4 = desc4;
	}

	public String getDesc5(){
		return desc5;
	}

	public void setDesc5(String desc5){
		this.desc5 = desc5;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((artfoto1 == null) ? 0 : artfoto1.hashCode());
		result = prime * result + ((artfoto2 == null) ? 0 : artfoto2.hashCode());
		result = prime * result + ((codart == null) ? 0 : codart.hashCode());
		result = prime * result + ((codinquad == null) ? 0 : codinquad.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((desc2 == null) ? 0 : desc2.hashCode());
		result = prime * result + ((desc3 == null) ? 0 : desc3.hashCode());
		result = prime * result + ((desc4 == null) ? 0 : desc4.hashCode());
		result = prime * result + ((desc5 == null) ? 0 : desc5.hashCode());
		result = prime * result + ((codmarca == null) ? 0 : codmarca.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		result = prime * result + ((qty_per_confez == null) ? 0 : qty_per_confez.hashCode());
		result = prime * result + ((um == null) ? 0 : um.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof SqliteArticoli){
			if(this._id == ((SqliteArticoli)obj)._id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "SqliteArticoli [_id=" + _id + ", codart=" + codart + ", desc=" + desc + ", um=" + um + ", prezzo=" + prezzo + ", qty_per_confez=" + qty_per_confez + ", codinquad=" + codinquad + ", artfoto1=" + artfoto1 + ", artfoto2=" + artfoto2 + ", codmarca=" + codmarca + ", desc2=" + desc2 + ", desc3=" + desc3 + ", desc4=" + desc4 + ", desc5=" + desc5 + "]";
	}
}
