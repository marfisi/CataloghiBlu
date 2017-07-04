package it.cascino.cataloghiblu.dbsqlite.managmentbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteMarcheDao;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteMarche;
import it.cascino.cataloghiblu.utils.Resources;

public class SqliteMarcheDaoMng implements SqliteMarcheDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmSqlite();
	private EntityTransaction utx = res.getUtxSqlite();	
	
	Logger log = Logger.getLogger(SqliteMarcheDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<SqliteMarche> getAll(){
		List<SqliteMarche> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteMarche.findAll");
				o = (List<SqliteMarche>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public Integer salva(SqliteMarche a){
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
	
	public void aggiorna(SqliteMarche a){
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
	
	public void elimina(SqliteMarche aElimina){
		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
		try{
			try{
				utx.begin();
				SqliteMarche a = em.find(SqliteMarche.class, aElimina.get_id());
				log.info("elimina: " + a.toString());
				em.remove(a);
			}finally{
				utx.commit();
			}
		}catch(Exception e){
			log.fatal(e.toString());
		}
	}
	
	public SqliteMarche getDaId(Integer id){
		SqliteMarche o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteMarche.findById");
				query.setParameter("_id", id);
				o = (SqliteMarche)query.getSingleResult();
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
	
	public SqliteMarche getDaCodmarca(String codmarca){
		SqliteMarche o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("SqliteMarche.findByCodmarca");
				query.setParameter("codmarca", codmarca);
				o = (SqliteMarche)query.getSingleResult();
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
