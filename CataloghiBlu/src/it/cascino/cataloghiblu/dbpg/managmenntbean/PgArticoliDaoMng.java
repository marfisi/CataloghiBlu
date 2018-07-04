package it.cascino.cataloghiblu.dbpg.managmenntbean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbpg.dao.PgArticoliDao;
import it.cascino.cataloghiblu.dbpg.model.PgArticoli;
import it.cascino.cataloghiblu.utils.Resources;

public class PgArticoliDaoMng implements PgArticoliDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmPg();
	private EntityTransaction utx = res.getUtxPg();
	
	Logger log = Logger.getLogger(PgArticoliDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<PgArticoli> getAll(){
		List<PgArticoli> o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgArticoli.findAll");
				o = (List<PgArticoli>)query.getResultList();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public PgArticoli getArticoloDaCodice(String codiceArticolo){
		PgArticoli o = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("PgArticoli.findByCodiceArticolo");
				query.setParameter("codiceArticolo", codiceArticolo);
				o = (PgArticoli)query.getSingleResult();
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
	
	public Integer getFotoArticoloDaCodice(String codiceArticolo){
		Integer o = null;
		try{
			try{
				utx.begin();
				String sql = "select foto " +
				"from ( " +
				"select row_number() OVER () AS rownum, selord.foto  " +
				"from (select foto " +
				"from articoli_foto af join articoli a on af.articolo = a.id  " +
				"where upper(codice) = :codice " +
				"order by ordinamento, af.updtime desc) as selord) as fotord " +
				"where fotord.rownum = 1";
				Query query = em.createNativeQuery(sql);
				query.setParameter("codice", codiceArticolo);
				o = (Integer)query.getSingleResult();
			}catch(NoResultException e){
				o = -1;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return o;
	}
	
	@SuppressWarnings("unchecked")
	public List<PgArticoli> getArticoloFratelliLsDaCodiceFoto(Integer idFoto){
		List<PgArticoli> o = null;
		try{
			try{
				utx.begin();
				String sql = "select a.* " +
				"from articoli a inner join articoli_foto af on a.id = af.articolo " +
				"where af.foto = :idFoto";
				Query query = em.createNativeQuery(sql, PgArticoli.class);
				query.setParameter("idFoto", idFoto);
				o = (List<PgArticoli>)query.getResultList();
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
	
	public boolean checkArticoloHaComePrimaFotoIdFoto(PgArticoli art, Integer idFoto){
		Integer o = null;
		try{
			try{
				utx.begin();
				String sql = "select fotord.foto " +
				"from ( " +
				"select row_number() OVER () AS rownum, * " +
				"from (select foto " +
				"from articoli_foto " +
				"where articolo = :idArticolo " +
				"order by ordinamento, updtime desc) as fotordin) as fotord " +
				"where fotord.rownum = 1";
				Query query = em.createNativeQuery(sql);
				query.setParameter("idArticolo", art.getId());
				o = (Integer)query.getSingleResult();
			}catch(NoResultException e){
				o = -1;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
			utx.commit();
		}
		return o.equals(idFoto);


	}
	
	public String getFotoNameArticoloDaId(Integer idFoto){
		String o = null;
		try{
			try{
				utx.begin();
				String sql = "select originale " +
				"from foto " +
				"where id = :idFoto";
				Query query = em.createNativeQuery(sql);
				query.setParameter("idFoto", idFoto);
				o = (String)query.getSingleResult();
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
