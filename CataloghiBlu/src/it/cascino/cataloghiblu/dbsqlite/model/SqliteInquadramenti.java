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
* The persistent class for the inquadramenti database table.
* 
*/
@Entity(name="inquadramenti")
@NamedQueries({
	@NamedQuery(name = "SqliteInquadramenti.findAll", query = "SELECT a FROM inquadramenti a"),
	@NamedQuery(name = "SqliteInquadramenti.findById", query = "SELECT a FROM inquadramenti a WHERE a._id = :id"),
	@NamedQuery(name = "SqliteInquadramenti.findByCodinquad", query = "SELECT a FROM inquadramenti a WHERE a.codinquad = :codinquad")
})
public class SqliteInquadramenti implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer _id;
	private String codinquad;
	private String codcat1;
	private String codcat2;
	private String codcat3;
	private String codcat4;
	private String codcat5;
	private String nomecat1;
	private String nomecat2;
	private String nomecat3;
	private String nomecat4;
	private String nomecat5;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer get_id(){
		return _id;
	}

	public void set_id(Integer _id){
		this._id = _id;
	}

	public String getCodinquad(){
		return codinquad;
	}

	public void setCodinquad(String codinquad){
		this.codinquad = codinquad;
	}

	public String getCodcat1(){
		return codcat1;
	}

	public void setCodcat1(String codcat1){
		this.codcat1 = codcat1;
	}

	public String getCodcat2(){
		return codcat2;
	}

	public void setCodcat2(String codcat2){
		this.codcat2 = codcat2;
	}

	public String getCodcat3(){
		return codcat3;
	}

	public void setCodcat3(String codcat3){
		this.codcat3 = codcat3;
	}

	public String getCodcat4(){
		return codcat4;
	}

	public void setCodcat4(String codcat4){
		this.codcat4 = codcat4;
	}

	public String getCodcat5(){
		return codcat5;
	}

	public void setCodcat5(String codcat5){
		this.codcat5 = codcat5;
	}

	public String getNomecat1(){
		return nomecat1;
	}

	public void setNomecat1(String nomecat1){
		this.nomecat1 = nomecat1;
	}

	public String getNomecat2(){
		return nomecat2;
	}

	public void setNomecat2(String nomecat2){
		this.nomecat2 = nomecat2;
	}

	public String getNomecat3(){
		return nomecat3;
	}

	public void setNomecat3(String nomecat3){
		this.nomecat3 = nomecat3;
	}

	public String getNomecat4(){
		return nomecat4;
	}

	public void setNomecat4(String nomecat4){
		this.nomecat4 = nomecat4;
	}

	public String getNomecat5(){
		return nomecat5;
	}

	public void setNomecat5(String nomecat5){
		this.nomecat5 = nomecat5;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((codcat1 == null) ? 0 : codcat1.hashCode());
		result = prime * result + ((codcat2 == null) ? 0 : codcat2.hashCode());
		result = prime * result + ((codcat3 == null) ? 0 : codcat3.hashCode());
		result = prime * result + ((codcat4 == null) ? 0 : codcat4.hashCode());
		result = prime * result + ((codcat5 == null) ? 0 : codcat5.hashCode());
		result = prime * result + ((codinquad == null) ? 0 : codinquad.hashCode());
		result = prime * result + ((nomecat1 == null) ? 0 : nomecat1.hashCode());
		result = prime * result + ((nomecat2 == null) ? 0 : nomecat2.hashCode());
		result = prime * result + ((nomecat3 == null) ? 0 : nomecat3.hashCode());
		result = prime * result + ((nomecat4 == null) ? 0 : nomecat4.hashCode());
		result = prime * result + ((nomecat5 == null) ? 0 : nomecat5.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof SqliteInquadramenti){
			if(this._id == ((SqliteInquadramenti)obj)._id){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "SqliteInquadramenti [_id=" + _id + ", codinquad=" + codinquad + ", codcat1=" + codcat1 + ", codcat2=" + codcat2 + ", codcat3=" + codcat3 + ", codcat4=" + codcat4 + ", codcat5=" + codcat5 + ", nomecat1=" + nomecat1 + ", nomecat2=" + nomecat2 + ", nomecat3=" + nomecat3 + ", nomecat4=" + nomecat4 + ", nomecat5=" + nomecat5 + "]";
	}
}
