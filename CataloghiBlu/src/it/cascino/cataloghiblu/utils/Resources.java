package it.cascino.cataloghiblu.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Resources{
	private EntityManagerFactory emfAs = null;
	private EntityManager emAs = null;
	private EntityTransaction utxAs = null;
	
	private EntityManagerFactory emfSqlite = null;
	private EntityManager emSqlite = null;
	private EntityTransaction utxSqlite = null;
	
	private EntityManagerFactory emfPg = null;
	private EntityManager emPg = null;
	private EntityTransaction utxPg = null;
	
	public Resources(){
		super();
		if(emfAs == null) {
			initAs();
		}
		if(emfSqlite == null) {
			initSqlite();
		}
		if(emfPg == null) {
			initPg();
		}
	}
	
	private void initAs(){
		emfAs = Persistence.createEntityManagerFactory("DB2AS400");
		emAs = emfAs.createEntityManager();
		utxAs = emAs.getTransaction();
	}
	
	private void initSqlite(){
		emfSqlite = Persistence.createEntityManagerFactory("Sqlite");
		emSqlite = emfSqlite.createEntityManager();
		utxSqlite = emSqlite.getTransaction();
	}
	
	private void initPg(){
		emfPg = Persistence.createEntityManagerFactory("Postgresql");
		emPg = emfPg.createEntityManager();
		utxPg = emPg.getTransaction();
	}
	
	public void close(){
		if(emfAs != null) {
			closeAs();
		}
		if(emfSqlite != null) {
			closeSqlite();
		}
		if(emfPg != null) {
			closePg();
		}
	}

	private void closeAs(){
		emAs.close();
		emfAs.close();
	}
	
	private void closeSqlite(){
		emSqlite.close();
		emfSqlite.close();
	}
	
	private void closePg(){
		emPg.close();
		emfPg.close();
	}
	
	public EntityManagerFactory getEmfAs(){
		return emfAs;
	}
	
	public void setEmfAs(EntityManagerFactory emfAs){
		this.emfAs = emfAs;
	}
	
	public EntityManager getEmAs(){
		return emAs;
	}
	
	public void setEmAs(EntityManager emAs){
		this.emAs = emAs;
	}
	
	public EntityTransaction getUtxAs(){
		return utxAs;
	}
	
	public void setUtxAs(EntityTransaction utxAs){
		this.utxAs = utxAs;
	}
	
	public EntityManagerFactory getEmfSqlite(){
		return emfSqlite;
	}
	
	public void setEmfSqlite(EntityManagerFactory emfSqlite){
		this.emfSqlite = emfSqlite;
	}
	
	public EntityManager getEmSqlite(){
		return emSqlite;
	}
	
	public void setEmSqlite(EntityManager emSqlite){
		this.emSqlite = emSqlite;
	}
	
	public EntityTransaction getUtxSqlite(){
		return utxSqlite;
	}
	
	public void setUtxSqlite(EntityTransaction utxSqlite){
		this.utxSqlite = utxSqlite;
	}
	
	public EntityManagerFactory getEmfPg(){
		return emfPg;
	}
	
	public void setEmfPg(EntityManagerFactory emfPg){
		this.emfPg = emfPg;
	}
	
	public EntityManager getEmPg(){
		return emPg;
	}
	
	public void setEmPg(EntityManager emPg){
		this.emPg = emPg;
	}
	
	public EntityTransaction getUtxPg(){
		return utxPg;
	}
	
	public void setUtxPg(EntityTransaction utxPg){
		this.utxPg = utxPg;
	}
}
