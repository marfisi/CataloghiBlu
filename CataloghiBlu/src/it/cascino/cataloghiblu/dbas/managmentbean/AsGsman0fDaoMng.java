package it.cascino.cataloghiblu.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbas.dao.AsGsman0fDao;
import it.cascino.cataloghiblu.dbas.model.AsGsman0f;
import it.cascino.cataloghiblu.utils.Resources;

public class AsGsman0fDaoMng implements AsGsman0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsGsman0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsGsman0f> getAll(){
		List<AsGsman0f> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsGsman0f.findAll");
				o = (List<AsGsman0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}

	public AsGsman0f getDaGgrupAndGsotg(Integer ggrup, Integer gsotg){
		AsGsman0f o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsGsman0f.findByGgrupAndGsotg");
				query.setParameter("ggrup", ggrup);
				query.setParameter("gsotg", gsotg);
				o = (AsGsman0f)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public void close(){
		res.close();
		log.info("chiuso");
	}
}
