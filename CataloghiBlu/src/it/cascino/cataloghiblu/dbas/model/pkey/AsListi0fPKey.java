package it.cascino.cataloghiblu.dbas.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AsListi0fPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String lscoa;
	private String lscat;

	public AsListi0fPKey(){
	}

	public AsListi0fPKey(String lscoa, String lscat){
		super();
		this.lscoa = lscoa;
		this.lscat = lscat;
	}

	public String getLscoa(){
		return lscoa;
	}

	public void setLscoa(String lscoa){
		this.lscoa = lscoa;
	}

	public String getLscat(){
		return lscat;
	}

	public void setLscat(String lscat){
		this.lscat = lscat;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lscat == null) ? 0 : lscat.hashCode());
		result = prime * result + ((lscoa == null) ? 0 : lscoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsListi0fPKey){
			if((this.lscoa == ((AsListi0fPKey)obj).lscoa)&&(this.lscat == ((AsListi0fPKey)obj).lscat)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsListi0fPKey [lscoa=" + lscoa + ", lscat=" + lscat + "]";
	}	
}
