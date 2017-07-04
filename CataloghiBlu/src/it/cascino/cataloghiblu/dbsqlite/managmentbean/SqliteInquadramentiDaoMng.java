package it.cascino.cataloghiblu.dbsqlite.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteInquadramentiDao;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteInquadramenti;
import it.cascino.cataloghiblu.utils.Resources;

public class SqliteInquadramentiDaoMng implements SqliteInquadramentiDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmSqlite();
	private EntityTransaction utx = res.getUtxSqlite();	
	
	Logger log = Logger.getLogger(SqliteInquadramentiDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<SqliteInquadramenti> getAll(){
		List<SqliteInquadramenti> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteInquadramenti.findAll");
				o = (List<SqliteInquadramenti>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public Integer salva(SqliteInquadramenti a){
		try{
			try{
				utx.begin();
//				a.setId(null);
//				log.info("salva: " + a.toString());
				em.persist(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return a.get_id();
	}
	
	public void aggiorna(SqliteInquadramenti a){
		try{
			try{
				utx.begin();
				log.info("aggiorna: " + a.toString());
				em.merge(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public void elimina(SqliteInquadramenti aElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				SqliteInquadramenti a = em.find(SqliteInquadramenti.class, aElimina.get_id());
				log.info("elimina: " + a.toString());
				em.remove(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public SqliteInquadramenti getDaId(Integer id){
		SqliteInquadramenti o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteInquadramenti.findById");
				query.setParameter("_id", id);
				o = (SqliteInquadramenti)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return o;
	}
	
	public SqliteInquadramenti getDaCodinquad(String codinquad){
		SqliteInquadramenti o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteInquadramenti.findByCodinquad");
				query.setParameter("codinquad", codinquad);
				o = (SqliteInquadramenti)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return o;
	}

	public void close(){
		res.close();
		log.info("chiuso");
	}
}
