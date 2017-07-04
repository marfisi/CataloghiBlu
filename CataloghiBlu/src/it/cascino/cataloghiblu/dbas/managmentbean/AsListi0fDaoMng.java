package it.cascino.cataloghiblu.dbas.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbas.dao.AsListi0fDao;
import it.cascino.cataloghiblu.dbas.model.AsListi0f;
import it.cascino.cataloghiblu.utils.Resources;

public class AsListi0fDaoMng implements AsListi0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsListi0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsListi0f> getAll(){
		List<AsListi0f> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsListi0f.findAll");
				o = (List<AsListi0f>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
//	public void salva(AsListi0f a){
//		try{
//			try{
//				utx.begin();
//				// precodice.setId(null);
//				log.info("salva: " + a.toString());
//				em.persist(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void aggiorna(AsListi0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				em.merge(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void elimina(AsListi0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsListi0f a = em.find(AsListi0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	public AsListi0f getDaLscoa(String lscoa){
		AsListi0f o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsListi0f.findByLscoa");
				query.setParameter("lscoa", lscoa);
				o = (AsListi0f)query.getSingleResult();
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
