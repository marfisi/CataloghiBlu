package it.cascino.cataloghiblu.dbpg.managmenntbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbpg.dao.PgProduttoriDao;
import it.cascino.cataloghiblu.dbpg.model.PgProduttori;
import it.cascino.cataloghiblu.utils.Resources;

public class PgProduttoriDaoMng implements PgProduttoriDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmPg();
	private EntityTransaction utx = res.getUtxPg();	
	
	Logger log = Logger.getLogger(PgProduttoriDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<PgProduttori> getAll(){
		List<PgProduttori> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgProduttori.findAll");
				o = (List<PgProduttori>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public PgProduttori getProduttoreDaIdProduttore(Integer idProduttore){
		PgProduttori o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgProduttori.findById");
				query.setParameter("id", idProduttore);
				o = (PgProduttori)query.getSingleResult();
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
