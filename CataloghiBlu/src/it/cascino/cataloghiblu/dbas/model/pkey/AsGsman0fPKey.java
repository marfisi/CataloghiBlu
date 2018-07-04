package it.cascino.cataloghiblu.dbas.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class AsGsman0fPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer ggrup;
	private Integer gsotg;

	public AsGsman0fPKey(){
	}

	public AsGsman0fPKey(Integer ggrup, Integer gsotg){
		super();
		this.ggrup = ggrup;
		this.gsotg = gsotg;
	}

	public Integer getGgrup(){
		return ggrup;
	}

	public void setGgrup(Integer ggrup){
		this.ggrup = ggrup;
	}

	public Integer getGsotg(){
		return gsotg;
	}

	public void setGsotg(Integer gsotg){
		this.gsotg = gsotg;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ggrup == null) ? 0 : ggrup.hashCode());
		result = prime * result + ((gsotg == null) ? 0 : gsotg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsGsman0fPKey){
			if((this.ggrup == ((AsGsman0fPKey)obj).ggrup)&&(this.gsotg == ((AsGsman0fPKey)obj).gsotg)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsGsman0fPKey [ggrup=" + ggrup + ", gsotg=" + gsotg + "]";
	}
}
