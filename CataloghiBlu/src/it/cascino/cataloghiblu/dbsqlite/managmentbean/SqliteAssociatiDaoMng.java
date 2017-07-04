package it.cascino.cataloghiblu.dbsqlite.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteAssociatiDao;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteAssociati;
import it.cascino.cataloghiblu.utils.Resources;

public class SqliteAssociatiDaoMng implements SqliteAssociatiDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmSqlite();
	private EntityTransaction utx = res.getUtxSqlite();	
	
	Logger log = Logger.getLogger(SqliteAssociatiDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<SqliteAssociati> getAll(){
		List<SqliteAssociati> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteAssociati.findAll");
				o = (List<SqliteAssociati>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public Integer salva(SqliteAssociati a){
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
	
	public void aggiorna(SqliteAssociati a){
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
	
	public void elimina(SqliteAssociati aElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				SqliteAssociati a = em.find(SqliteAssociati.class, aElimina.get_id());
				log.info("elimina: " + a.toString());
				em.remove(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SqliteAssociati> getDaCodartcapo(String codartcapo){
		List<SqliteAssociati> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteAssociati.findByCodartcapo");
				query.setParameter("codartcapo", codartcapo);
				o = (List<SqliteAssociati>)query.getResultList();
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
	
	public SqliteAssociati getDaCodartfiglio(String codartfiglio){
		SqliteAssociati o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteAssociati.findByCodartfiglio");
				query.setParameter("codartfiglio", codartfiglio);
				o = (SqliteAssociati)query.getSingleResult();
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
