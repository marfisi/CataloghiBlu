package it.cascino.cataloghiblu.dbas.model;

import java.io.Serializable;
import javax.persistence.*;

/**
* The persistent class for the cas_dat/anmag0f database table.
* 
*/
@Entity(name="Anmag0f")
@NamedQueries({
	@NamedQuery(name = "AsAnmag0f.findAll", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.atama != 'S' order by a.mcoda asc"),
	@NamedQuery(name = "AsAnmag0f.findByMcoda", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.atama != 'S' and a.mcoda = :mcoda "),
	@NamedQuery(name = "AsAnmag0f.findAllIngrosso", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.atama != 'S' and ((a.mdepi = 1) or (a.mdepi = 3))")
})
public class AsAnmag0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	private String atama;
	private String mcoda;
	private String mdesc;
	private String mumis;
	private Float mpeu1;
	private Float msc11;
	private Float mconf;
	private Integer mgrup;
	private Integer msotg;
	private String madiv;
	private String mdepi;
	private String magru;
	private String masot;
	private String mafam;
	private String mastf;
	
	public AsAnmag0f(){
	}
	
	public AsAnmag0f(String atama, String mcoda, String mdesc, String mumis, Float mpeu1, Float msc11, Float mconf, Integer mgrup, Integer msotg, String madiv, String mdepi, String magru, String masot, String mafam, String mastf){
		super();
		this.atama = atama;
		this.mcoda = mcoda;
		this.mdesc = mdesc;
		this.mumis = mumis;
		this.mpeu1 = mpeu1;
		this.msc11 = msc11;
		this.mconf = mconf;
		this.mgrup = mgrup;
		this.msotg = msotg;
		this.madiv = madiv;
		this.mdepi = mdepi;
		this.magru = magru;
		this.masot = masot;
		this.mafam = mafam;
		this.mastf = mastf;
	}	

	public String getAtama(){
		return atama;
	}

	public void setAtama(String atama){
		this.atama = atama;
	}

	@Id
	public String getMcoda(){
		return mcoda;
	}

	public void setMcoda(String mcoda){
		this.mcoda = mcoda;
	}
	
	public String getMdesc(){
		return mdesc;
	}

	public void setMdesc(String mdesc){
		this.mdesc = mdesc;
	}

	public String getMumis(){
		return mumis;
	}

	public void setMumis(String mumis){
		this.mumis = mumis;
	}

	public Float getMpeu1(){
		return mpeu1;
	}

	public void setMpeu1(Float mpeu1){
		this.mpeu1 = mpeu1;
	}
	
	public Float getMsc11(){
		return msc11;
	}

	public void setMsc11(Float msc11){
		this.msc11 = msc11;
	}

	public Float getMconf(){
		return mconf;
	}

	public void setMconf(Float mconf){
		this.mconf = mconf;
	}

	public Integer getMgrup(){
		return mgrup;
	}

	public void setMgrup(Integer mgrup){
		this.mgrup = mgrup;
	}

	public Integer getMsotg(){
		return msotg;
	}

	public void setMsotg(Integer msotg){
		this.msotg = msotg;
	}

	public String getMadiv(){
		return madiv;
	}

	public void setMadiv(String madiv){
		this.madiv = madiv;
	}

	public String getMdepi(){
		return mdepi;
	}

	public void setMdepi(String mdepi){
		this.mdepi = mdepi;
	}

	public String getMagru(){
		return magru;
	}

	public void setMagru(String magru){
		this.magru = magru;
	}

	public String getMasot(){
		return masot;
	}

	public void setMasot(String masot){
		this.masot = masot;
	}

	public String getMafam(){
		return mafam;
	}

	public void setMafam(String mafam){
		this.mafam = mafam;
	}

	public String getMastf(){
		return mastf;
	}

	public void setMastf(String mastf){
		this.mastf = mastf;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atama == null) ? 0 : atama.hashCode());
		result = prime * result + ((mafam == null) ? 0 : mafam.hashCode());
		result = prime * result + ((magru == null) ? 0 : magru.hashCode());
		result = prime * result + ((masot == null) ? 0 : masot.hashCode());
		result = prime * result + ((mastf == null) ? 0 : mastf.hashCode());
		result = prime * result + ((mcoda == null) ? 0 : mcoda.hashCode());
		result = prime * result + ((mconf == null) ? 0 : mconf.hashCode());
		result = prime * result + ((mgrup == null) ? 0 : mgrup.hashCode());
		result = prime * result + ((msotg == null) ? 0 : msotg.hashCode());
		result = prime * result + ((madiv == null) ? 0 : madiv.hashCode());
		result = prime * result + ((mdepi == null) ? 0 : mdepi.hashCode());
		result = prime * result + ((mdesc == null) ? 0 : mdesc.hashCode());
		result = prime * result + ((mpeu1 == null) ? 0 : mpeu1.hashCode());
		result = prime * result + ((msc11 == null) ? 0 : msc11.hashCode());
		result = prime * result + ((mumis == null) ? 0 : mumis.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAnmag0f){
			if(this.mcoda == ((AsAnmag0f)obj).mcoda){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsAnmag0f [atama=" + atama + ", mcoda=" + mcoda + ", mdesc=" + mdesc + ", mumis=" + mumis + ", mpeu1=" + mpeu1 + ", msc11=" + msc11 + ", mconf=" + mconf + ", mgrup=" + mgrup + ", msotg=" + msotg + ", madiv=" + madiv + ", mdepi=" + mdepi + ", magru=" + magru + ", masot=" + masot + ", mafam=" + mafam + ", mastf=" + mastf + "]";
	}
}