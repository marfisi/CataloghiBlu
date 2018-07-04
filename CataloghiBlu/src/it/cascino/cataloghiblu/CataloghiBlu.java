package it.cascino.cataloghiblu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbas.dao.AsAnmag0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsGsman0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsListi0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsNativeQueryDao;
import it.cascino.cataloghiblu.dbas.managmentbean.AsAnmag0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsGsman0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsListi0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsNativeQueryDaoMng;
import it.cascino.cataloghiblu.dbas.model.AsAnmag0f;
import it.cascino.cataloghiblu.dbas.model.AsGsman0f;
import it.cascino.cataloghiblu.dbas.model.AsListi0f;
import it.cascino.cataloghiblu.dbpg.dao.PgArticoliDao;
import it.cascino.cataloghiblu.dbpg.dao.PgProduttoriDao;
import it.cascino.cataloghiblu.dbpg.managmenntbean.PgArticoliDaoMng;
import it.cascino.cataloghiblu.dbpg.managmenntbean.PgProduttoriDaoMng;
import it.cascino.cataloghiblu.dbpg.model.PgArticoli;
import it.cascino.cataloghiblu.dbpg.model.PgProduttori;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteArticoliDao;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteAssociatiDao;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteInfogenericheDao;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteInquadramentiDao;
import it.cascino.cataloghiblu.dbsqlite.dao.SqliteMarcheDao;
import it.cascino.cataloghiblu.dbsqlite.managmentbean.SqliteArticoliDaoMng;
import it.cascino.cataloghiblu.dbsqlite.managmentbean.SqliteAssociatiDaoMng;
import it.cascino.cataloghiblu.dbsqlite.managmentbean.SqliteInfogenericheDaoMng;
import it.cascino.cataloghiblu.dbsqlite.managmentbean.SqliteInquadramentiDaoMng;
import it.cascino.cataloghiblu.dbsqlite.managmentbean.SqliteMarcheDaoMng;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteArticoli;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteAssociati;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteInfogeneriche;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteInquadramenti;
import it.cascino.cataloghiblu.dbsqlite.model.SqliteMarche;

public class CataloghiBlu{
	
	private Logger log = Logger.getLogger(CataloghiBlu.class);
	
	StringBuilder stringBuilder = new StringBuilder();
	
	private String urlPrefissoFoto = "";	// "http://78.5.87.180:8181/Cascino-web/faces/resources/gfx/foto/";
	
	// AS400
	private AsAnmag0fDao asAnmag0fDao = new AsAnmag0fDaoMng();
	private List<AsAnmag0f> asAnmag0fLs;
	private AsListi0fDao asListi0fDao = new AsListi0fDaoMng();
	private AsGsman0fDao asGsman0fDao = new AsGsman0fDaoMng();
	private AsNativeQueryDao asNativeQueryDao = new AsNativeQueryDaoMng();
	
	// SqLite
	private SqliteArticoliDao sqliteArticoliDao = new SqliteArticoliDaoMng();
	private List<SqliteArticoli> sqliteArticoliLs;
	private SqliteAssociatiDao sqliteAssociatiDao = new SqliteAssociatiDaoMng();
	private SqliteMarcheDao sqliteMarcheDao = new SqliteMarcheDaoMng();
	private SqliteInquadramentiDao sqliteInquadramentiDao = new SqliteInquadramentiDaoMng();
	private SqliteInfogenericheDao sqliteInfogenericheDao = new SqliteInfogenericheDaoMng();
	
	// PostgreSQL
	private static PgArticoliDao pgArticoliDao = new PgArticoliDaoMng();
	private static PgProduttoriDao pgProduttoriDao = new PgProduttoriDaoMng();
	
	
	public CataloghiBlu(String args[]){
		log.info("[" + "CataloghiBlu");
		
		BigDecimal bdModelVal = null;
		BigDecimal bdDisplayVal = null;
		
		for(int numArg = 0; numArg < args.length; numArg++){
			if(args[numArg].compareTo("-soloModificati") == 0){
//				numArg++;
//			}else if(args[numArg].compareTo("-fI") == 0){
//				numArg++;
//				basePathInput = args[numArg];
			}else{ // se c'e' almeno un parametro e non e' tra quelli previsti stampo il messaggio d'aiuto
			}
		}
		
		AsAnmag0f asAnmag0f = new AsAnmag0f();
		asAnmag0fLs = asAnmag0fDao.getArticoliIngrosso();
		// asAnmag0fLs = asAnmag0fLs.subList(0, 500);	// TODO TMP ----------------------------------------------------------------------------------------------------------
		
		Iterator<AsAnmag0f> iter_asAnmag0fLs = asAnmag0fLs.iterator();
		iter_asAnmag0fLs = asAnmag0fLs.iterator();
		while(iter_asAnmag0fLs.hasNext()){
			asAnmag0f = iter_asAnmag0fLs.next();
			// log.info(asAnmag0f.toString());
			
			if(StringUtils.startsWith(asAnmag0f.getMcoda(), "/")){
				continue;
			}
			
			SqliteArticoli sqliteArticoli = new SqliteArticoli();
			
			String codiceArticolo = StringUtils.trim(asAnmag0f.getMcoda());
			
			sqliteArticoli.setCodart(codiceArticolo);
			sqliteArticoli.setDesc(StringUtils.trim(asAnmag0f.getMdesc()));
			sqliteArticoli.setUm(StringUtils.trim(asAnmag0f.getMumis()));
			// determino il prezzo
			BigDecimal prezzo = determinaPrezzo(asAnmag0f);
			sqliteArticoli.setPrezzo(prezzo);
			bdModelVal = new BigDecimal(asAnmag0f.getMconf());
			bdDisplayVal = bdModelVal.setScale(0,  BigDecimal.ROUND_HALF_UP);
			if(bdDisplayVal.intValue() < 1){
				bdDisplayVal = new BigDecimal(1);
			}
			sqliteArticoli.setQty_per_confez(bdDisplayVal);
			
			// in futuro si dovra' gestire l'albero da Almer0f e non Gsman0f
			// prima controllo che il primo livello sia inserito in db
			String codinquad = String.valueOf(asAnmag0f.getMgrup());
			//sqliteArticoli.setCodinquad(codinquad);
			SqliteInquadramenti sqliteInquadramenti = sqliteInquadramentiDao.getDaCodinquad(codinquad);
			if(sqliteInquadramenti == null){
				sqliteInquadramenti = new SqliteInquadramenti();
				sqliteInquadramenti.setCodinquad(codinquad);
				sqliteInquadramenti.setCodcat1(String.valueOf(asAnmag0f.getMgrup()));
				AsGsman0f asGsman0f = asGsman0fDao.getDaGgrupAndGsotg(asAnmag0f.getMgrup(), 0);
				sqliteInquadramenti.setNomecat1(asGsman0f.getGdesc());
				
				// setto valori vuoti per gli altri campi non significativi
				sqliteInquadramenti.setCodcat2(" ");
				sqliteInquadramenti.setCodcat3(" ");
				sqliteInquadramenti.setCodcat4(" ");
				sqliteInquadramenti.setCodcat5(" ");
				sqliteInquadramenti.setNomecat2(" ");
				sqliteInquadramenti.setNomecat3(" ");
				sqliteInquadramenti.setNomecat4(" ");
				sqliteInquadramenti.setNomecat5(" ");
				
				sqliteInquadramentiDao.salva(sqliteInquadramenti);
			}
			
			codinquad = String.valueOf(asAnmag0f.getMgrup()) + "-" + String.valueOf(asAnmag0f.getMsotg());
			sqliteArticoli.setCodinquad(codinquad);
			sqliteInquadramenti = sqliteInquadramentiDao.getDaCodinquad(codinquad);
			if(sqliteInquadramenti == null){
				sqliteInquadramenti = new SqliteInquadramenti();
				sqliteInquadramenti.setCodinquad(codinquad);
				sqliteInquadramenti.setCodcat1(String.valueOf(asAnmag0f.getMgrup()));
				sqliteInquadramenti.setCodcat2(String.valueOf(asAnmag0f.getMsotg()));
				AsGsman0f asGsman0f = asGsman0fDao.getDaGgrupAndGsotg(asAnmag0f.getMgrup(), 0);
				sqliteInquadramenti.setNomecat1(asGsman0f.getGdesc());
				asGsman0f = asGsman0fDao.getDaGgrupAndGsotg(asAnmag0f.getMgrup(), asAnmag0f.getMsotg());
				sqliteInquadramenti.setNomecat2(asGsman0f.getGdesc());
				
				// setto valori vuoti per gli altri campi non significativi
				sqliteInquadramenti.setCodcat3(" ");
				sqliteInquadramenti.setCodcat4(" ");
				sqliteInquadramenti.setCodcat5(" ");
				sqliteInquadramenti.setNomecat3(" ");
				sqliteInquadramenti.setNomecat4(" ");
				sqliteInquadramenti.setNomecat5(" ");
				
				sqliteInquadramentiDao.salva(sqliteInquadramenti);
			}
			
			sqliteArticoli.setArtfoto1(" ");
			sqliteArticoli.setArtfoto2(" ");
			sqliteArticoli.setCodmarca(" ");
			sqliteArticoli.setDesc2(" ");
			sqliteArticoli.setDesc3(" ");
			sqliteArticoli.setDesc4(" ");
			sqliteArticoli.setDesc5(" ");

			PgArticoli pgArticoli = pgArticoliDao.getArticoloDaCodice(codiceArticolo);
			if(pgArticoli != null){
				String foto =  pgArticoliDao.getFotoNameArticoloDaId(pgArticoliDao.getFotoArticoloDaCodice(codiceArticolo));
				
				if(foto != null){
					sqliteArticoli.setArtfoto1(urlPrefissoFoto + foto);
				}
				
				PgProduttori pgProduttori = pgProduttoriDao.getProduttoreDaIdProduttore(pgArticoli.getProduttore());
				if((pgProduttori != null) && (pgProduttori.getId() != 1)){
					String codmarca = String.valueOf(pgProduttori.getId());
					
					sqliteArticoli.setCodmarca(StringUtils.defaultString(codmarca, " "));
					
					SqliteMarche sqliteMarche = sqliteMarcheDao.getDaCodmarca(codmarca);
					if(sqliteMarche == null){
						sqliteMarche = new SqliteMarche();
						sqliteMarche.setCodmarca(codmarca);
						sqliteMarche.setMarca(pgProduttori.getNome());
						foto =  pgArticoliDao.getFotoNameArticoloDaId(pgProduttori.getIdFoto());
						sqliteMarche.setMarcafoto(" ");
						if(foto != null){
							sqliteMarche.setMarcafoto(urlPrefissoFoto + foto);
						}
						sqliteMarcheDao.salva(sqliteMarche);
					}
				}
				sqliteArticoli.setDesc2(StringUtils.defaultString(pgArticoli.getNome(), " "));
				sqliteArticoli.setDesc3(StringUtils.defaultString(pgArticoli.getModello(), " "));
				sqliteArticoli.setDesc4(StringUtils.defaultString(pgArticoli.getDescrizione(), " "));				
			}
			
			Integer idArticoloElaborato = sqliteArticoliDao.salva(sqliteArticoli);
		}
		
		// ora popolo la tabella delle fratellanze
		sqliteArticoliLs = sqliteArticoliDao.getAll();
		SqliteArticoli sqliteArticoliArray[] = sqliteArticoliLs.toArray(new SqliteArticoli[sqliteArticoliLs.size()]);
		//SqliteArticoli sqliteArticoliArrayAppoggio[] = sqliteArticoliArray.clone();
		
		SqliteAssociati sqliteAssociati[] = new SqliteAssociati[sqliteArticoliLs.size()];
		
		for(int i = 0; i < sqliteArticoliLs.size(); i++){
			sqliteAssociati[i] = new SqliteAssociati();
			sqliteAssociati[i].setCodartfiglio(sqliteArticoliArray[i].getCodart());
		}
				
		for(int i = 0; i < sqliteArticoliLs.size(); i++){
			//SqliteArticoli sqliteArticoliAppoggio = sqliteArticoliArray[i];
			
			if(sqliteAssociati[i].getCodartcapo() != null){
				continue;
			}
			
			String fratelloMaggiore = sqliteAssociati[i].getCodartfiglio();
			
			sqliteAssociati[i].setCodartcapo(fratelloMaggiore);
			
			// identifico la foto per articolo in analisi
			Integer idFoto = pgArticoliDao.getFotoArticoloDaCodice(fratelloMaggiore);
			
			// se non ha foto disponibile, continuo con l'articolo successivo
			if(idFoto == -1){
				// log.warn("non ha foto disponibile");
				continue;
			}
			// quindi ha una foto
			
			// cerco se ha fratelli (compreso se stesso) che condividono la stessa foto
			List<PgArticoli> fratelliLs = pgArticoliDao.getArticoloFratelliLsDaCodiceFoto(idFoto);
			PgArticoli fratelliAry[] = fratelliLs.toArray(new PgArticoli[fratelliLs.size()]);
			// log.info("fratelli: " + fratelliAry.length);
			
			if(fratelliAry.length == 1){ // e' solo lui
				continue;
			}
			
			for(int j = 0; j < fratelliAry.length; j++){
				PgArticoli art = fratelliAry[j];
				log.info("articolo: " + art.getCodice() + " (id: " + art.getId() + ")");
				
				// controllo che effettivamente l'articolo abbia la foto come ordine minore e non come secondo o altro
				boolean ePrimaFoto = pgArticoliDao.checkArticoloHaComePrimaFotoIdFoto(art, idFoto);
				
				if(!(ePrimaFoto)){
					log.info("non coincide con la foto di ordine minore (fratello scartato)");
					continue;
				}
				
				if(StringUtils.equals(fratelloMaggiore, art.getCodice())){
					log.info("continuo perche' e' lui stesso");
					continue; // con il fratello successivo
				}else{
					log.info(art.getCodice() + "e' un fratello minore del fratello maggiore " + fratelloMaggiore);
					
					for(int y = i; y < sqliteAssociati.length; y++){
						if(StringUtils.equals(sqliteAssociati[y].getCodartfiglio(), art.getCodice())){
							sqliteAssociati[y].setCodartcapo(fratelloMaggiore);
						}
					}
				}
			}
		}
		
		for(int i = 0; i < sqliteArticoliLs.size(); i++){
			sqliteAssociati[i].setCodasso(sqliteAssociati[i].getCodartcapo() + "-" + sqliteAssociati[i].getCodartfiglio());
			sqliteAssociatiDao.salva(sqliteAssociati[i]);
		}
				
		SqliteInfogeneriche sqliteInfogeneriche = new SqliteInfogeneriche();
		sqliteInfogeneriche = sqliteInfogenericheDao.getDaNomeInfo("data_creazione");
		DateTimeFormatter formatter = null;
		String strTtimestampAs400 =  asNativeQueryDao.getDaSysdummy1_TimestampAs400().toString();
		// e' in formato "yyyy-MM-dd HH:mm:ss.SSSSSS"
		strTtimestampAs400 = StringUtils.substringBefore(strTtimestampAs400, ".");
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	
		LocalDateTime timestampAs400 = LocalDateTime.parse(strTtimestampAs400, formatter);
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");	
		sqliteInfogeneriche.setValore(timestampAs400.format(formatter));
		sqliteInfogenericheDao.aggiorna(sqliteInfogeneriche);
		
		asAnmag0fDao.close();
		asListi0fDao.close();
		asGsman0fDao.close();
		asNativeQueryDao.close();
		
		sqliteArticoliDao.close();
		sqliteAssociatiDao.close();
		sqliteInquadramentiDao.close();
		sqliteMarcheDao.close();
		sqliteInfogenericheDao.close();	// in questo close c'è "pragma wal_checkpoint" per svuotare il wal file
		
		pgArticoliDao.close();
		pgProduttoriDao.close();
		
		log.info("]" + "CataloghiBlu");
	}

	private BigDecimal determinaPrezzo(AsAnmag0f asAnmag0f){
		Float prezzo = 0.0f;
		
		AsListi0f asListi0f = asListi0fDao.getDaLscoa(asAnmag0f.getMcoda());
		if(asListi0f != null){
			if(asListi0f.getLsprn() != 0){
				prezzo = asListi0f.getLsprn();
			}else if((asListi0f.getLssc1() != 0) || (asListi0f.getLssc2() != 0)){
				prezzo = asAnmag0f.getMpeu1() - (asAnmag0f.getMpeu1() * asListi0f.getLssc1() / 100); 
				prezzo = prezzo - (prezzo * asListi0f.getLssc2() / 100); 
			}else{
				prezzo = asAnmag0f.getMpeu1() - (asAnmag0f.getMpeu1() * asAnmag0f.getMsc11() / 100); 
			}
		}else{
			prezzo = asAnmag0f.getMpeu1() - (asAnmag0f.getMpeu1() * asAnmag0f.getMsc11() / 100); 
		}
		
		prezzo = prezzo * 2;
		
		// arrotondo
		BigDecimal bdModelVal = null;
		BigDecimal bdDisplayVal = null;
		
		bdModelVal = new BigDecimal(prezzo);
		
		if(prezzo.compareTo(1.0f) < 0){	// sotto € 1
			bdDisplayVal = bdModelVal.setScale(3,  BigDecimal.ROUND_HALF_UP);
		}else if(prezzo.compareTo(5.0f) < 0){	// sotto € 5
			bdDisplayVal = bdModelVal.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}else if(prezzo.compareTo(10.0f) < 0){	// sotto € 10
			BigDecimal result =  new BigDecimal(Math.ceil(prezzo * 20) / 20);
			bdDisplayVal = result.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}else if(prezzo.compareTo(50.0f) < 0){	// sotto € 50
			bdDisplayVal = bdModelVal.setScale(1,  BigDecimal.ROUND_HALF_UP);
		}else if(prezzo.compareTo(5.0f) < 0){	// sotto € 100
			BigDecimal result =  new BigDecimal(Math.ceil(prezzo * 2) / 2);
			bdDisplayVal = result.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}else{	// sopra € 100
			bdDisplayVal = bdModelVal.setScale(0,  BigDecimal.ROUND_HALF_UP);
		}
		
		return bdDisplayVal;
	}
}