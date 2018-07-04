package it.cascino.cataloghiblu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import it.cascino.cataloghiblu.dbas.dao.AsAlmer0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsAnmag0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsAnmar0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsListi0fDao;
import it.cascino.cataloghiblu.dbas.dao.AsNativeQueryDao;
import it.cascino.cataloghiblu.dbas.dao.AsTabe20fDao;
import it.cascino.cataloghiblu.dbas.managmentbean.AsAlmer0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsAnmag0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsAnmar0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsListi0fDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsNativeQueryDaoMng;
import it.cascino.cataloghiblu.dbas.managmentbean.AsTabe20fDaoMng;
import it.cascino.cataloghiblu.dbas.model.AsAlmer0f;
import it.cascino.cataloghiblu.dbas.model.AsAnmag0f;
import it.cascino.cataloghiblu.dbas.model.AsAnmar0f;
import it.cascino.cataloghiblu.dbas.model.AsListi0f;
import it.cascino.cataloghiblu.dbas.model.AsTabe20f;
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
	private AsNativeQueryDao asNativeQueryDao = new AsNativeQueryDaoMng();
	private AsAnmar0fDao asAnmar0fDao = new AsAnmar0fDaoMng();
	private List<AsAnmar0f> asAnmar0fLs;
	private AsTabe20fDao asTabe20fDao = new AsTabe20fDaoMng();
	private List<AsTabe20f> asTabe20fLs;
	private AsAlmer0fDao asAlmer0fDao = new AsAlmer0fDaoMng();
	private List<AsAlmer0f> asAlmer0fLs;
	
	// SqLite
	private SqliteArticoliDao sqliteArticoliDao = new SqliteArticoliDaoMng();
	private SqliteAssociatiDao sqliteAssociatiDao = new SqliteAssociatiDaoMng();
	private SqliteMarcheDao sqliteMarcheDao = new SqliteMarcheDaoMng();
	private SqliteInquadramentiDao sqliteInquadramentiDao = new SqliteInquadramentiDaoMng();
	private SqliteInfogenericheDao sqliteInfogenericheDao = new SqliteInfogenericheDaoMng();
	
	// PostgreSQL
//	private static PgArticoliDao pgArticoliDao = new PgArticoliDaoMng();
//	private static PgProduttoriDao pgProduttoriDao = new PgProduttoriDaoMng();
	
	
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
		
		// popolo marche
		AsTabe20f asTabe20f = new AsTabe20f();
		asTabe20fLs = asTabe20fDao.getAll();
//		asTabe20fLs = asTabe20fLs.subList(0, 100);
		Iterator<AsTabe20f> iter_asTabe20fLs = asTabe20fLs.iterator();
		iter_asTabe20fLs = asTabe20fLs.iterator();
		while(iter_asTabe20fLs.hasNext()){
			asTabe20f = iter_asTabe20fLs.next();
			
			SqliteMarche sqliteMarche = new SqliteMarche();
			sqliteMarche.setCodmarca(asTabe20f.getTbele());
			sqliteMarche.setMarca(asTabe20f.getTbdes());
			sqliteMarche.setMarcafoto(urlPrefissoFoto + StringUtils.trim(asTabe20f.getTbcom()));
			sqliteMarcheDao.salva(sqliteMarche);
		}
				
		// popolo inquadramenti con almer con amset in (C, D)
		AsAlmer0f asAlmer0f = new AsAlmer0f();
		asAlmer0fLs = asAlmer0fDao.getAll();
//		asAlmer0fLs = asAlmer0fLs.subList(0, 100);
		Iterator<AsAlmer0f> iter_asAlmer0fLs = asAlmer0fLs.iterator();
		iter_asAlmer0fLs = asAlmer0fLs.iterator();
		while(iter_asAlmer0fLs.hasNext()){
			asAlmer0f = iter_asAlmer0fLs.next();
			
			String codinquad = StringUtils.join(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), asAlmer0f.getId().getAmsot(), asAlmer0f.getId().getAmfam(), asAlmer0f.getId().getAmstf(), asAlmer0f.getId().getAmst1());
			SqliteInquadramenti sqliteInquadramenti = new SqliteInquadramenti();
			sqliteInquadramenti.setCodinquad(codinquad);
			sqliteInquadramenti.setCodcat1(asAlmer0f.getId().getAmset() + asAlmer0f.getId().getAmgru());
			sqliteInquadramenti.setCodcat2(asAlmer0f.getId().getAmsot());
			sqliteInquadramenti.setCodcat3(asAlmer0f.getId().getAmfam());
			sqliteInquadramenti.setCodcat4(asAlmer0f.getId().getAmstf());
			sqliteInquadramenti.setCodcat5(asAlmer0f.getId().getAmst1());
			sqliteInquadramenti.setNomecat1("");
			if(!(StringUtils.isBlank(asAlmer0f.getId().getAmgru()))){
				sqliteInquadramenti.setNomecat1((asAlmer0fDao.getDaId(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), "", "", "", "")).getAmdes());
			}
			sqliteInquadramenti.setNomecat2("");
			if(!(StringUtils.isBlank(asAlmer0f.getId().getAmsot()))){
				sqliteInquadramenti.setNomecat2((asAlmer0fDao.getDaId(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), asAlmer0f.getId().getAmsot(), "", "", "")).getAmdes());
			}
			sqliteInquadramenti.setNomecat3("");
			if(!(StringUtils.isBlank(asAlmer0f.getId().getAmfam()))){
				sqliteInquadramenti.setNomecat3((asAlmer0fDao.getDaId(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), asAlmer0f.getId().getAmsot(), asAlmer0f.getId().getAmfam(), "", "")).getAmdes());
			}
			sqliteInquadramenti.setNomecat4("");
			if(!(StringUtils.isBlank(asAlmer0f.getId().getAmstf()))){
				sqliteInquadramenti.setNomecat4((asAlmer0fDao.getDaId(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), asAlmer0f.getId().getAmsot(), asAlmer0f.getId().getAmfam(), asAlmer0f.getId().getAmstf(), "")).getAmdes());
			}
			sqliteInquadramenti.setNomecat5("");
			if(!(StringUtils.isBlank(asAlmer0f.getId().getAmst1()))){
				sqliteInquadramenti.setNomecat5((asAlmer0fDao.getDaId(asAlmer0f.getId().getAmset(), asAlmer0f.getId().getAmgru(), asAlmer0f.getId().getAmsot(), asAlmer0f.getId().getAmfam(), asAlmer0f.getId().getAmstf(), asAlmer0f.getId().getAmst1())).getAmdes());
			}
			
			sqliteInquadramentiDao.salva(sqliteInquadramenti);
		}

		AsAnmag0f asAnmag0f = new AsAnmag0f();
		asAnmag0fLs = asAnmag0fDao.getArticoliIngrosso();
//		asAnmag0fLs = asAnmag0fLs.subList(0, 100);
		
		Iterator<AsAnmag0f> iter_asAnmag0fLs = asAnmag0fLs.iterator();
		iter_asAnmag0fLs = asAnmag0fLs.iterator();
		while(iter_asAnmag0fLs.hasNext()){
			asAnmag0f = iter_asAnmag0fLs.next();
			log.info("Articolo: " + asAnmag0f.getMcoda());
			
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
			bdDisplayVal = bdModelVal.setScale(2,  BigDecimal.ROUND_HALF_UP);
			if(bdDisplayVal.intValue() < 1){
				bdDisplayVal = new BigDecimal(1);
			}
			sqliteArticoli.setQty_per_confez(bdDisplayVal);
			
			String codinquad = StringUtils.join(asAnmag0f.getMadiv(), asAnmag0f.getMagru(), asAnmag0f.getMasot(), asAnmag0f.getMafam(), asAnmag0f.getMastf(), asAnmag0f.getMast1());
			
			sqliteArticoli.setCodinquad(codinquad);
			
			sqliteArticoli.setCodmarca(asAnmag0f.getMarch());
			
			sqliteArticoli.setArtfoto1(" ");
			sqliteArticoli.setArtfoto2(" ");
			sqliteArticoli.setDesc2(" ");
			sqliteArticoli.setDesc3(" ");
			sqliteArticoli.setDesc4(" ");
			sqliteArticoli.setDesc5(" ");
			AsAnmar0f asAnmar0f = asAnmar0fDao.getGruppoDaMcomp(asAnmag0f.getMcomp());
			if(asAnmar0f != null){
				log.info("raggruppamento: " + asAnmar0f.getMcomp() + " - " +  asAnmar0f.getMdes1());
								
				if(!(StringUtils.isBlank(asAnmar0f.getMfoto()))){
					String fotoArray[] = StringUtils.split(asAnmar0f.getMfoto(), ",");
					
					if((fotoArray.length > 0) && (!(StringUtils.isBlank(fotoArray[0])))){
						sqliteArticoli.setArtfoto1(urlPrefissoFoto + StringUtils.trim(fotoArray[0]));
						log.info("foto: " + fotoArray[0]);
					}
					if((fotoArray.length > 1) && (!(StringUtils.isBlank(fotoArray[1])))){
						sqliteArticoli.setArtfoto2(urlPrefissoFoto + StringUtils.trim(fotoArray[1]));
					}
				}else{
					log.warn(asAnmar0f.getMcomp() + " non ha nessuna foto definita");				
				}
				
				if(!(StringUtils.isBlank(asAnmar0f.getMdes1()))){
					sqliteArticoli.setDesc4(asAnmar0f.getMdes1());
				}
				if(!(StringUtils.isBlank(asAnmar0f.getMdes2()))){
					sqliteArticoli.setDesc5(asAnmar0f.getMdes2() + asAnmar0f.getMdes3());
				}
			}
			@SuppressWarnings("unused")
			Integer idArticoloElaborato = sqliteArticoliDao.salva(sqliteArticoli);
		}
		
		// ora popolo la tabella delle fratellanze/associazioni
		asAnmar0fLs = asAnmar0fDao.getAll();
//		asAnmar0fLs = asAnmar0fLs.subList(0, 100);
		Iterator<AsAnmar0f> iter_asAnmar = asAnmar0fLs.iterator();
		while(iter_asAnmar.hasNext()){
			AsAnmar0f asAnmar0f = iter_asAnmar.next();
			log.info("raggruppamento: " + asAnmar0f.getMcomp() + " - " +  asAnmar0f.getMdes1());
			
			asAnmag0fLs = asAnmag0fDao.getArticoliDaMcompIngrosso(asAnmar0f.getMcomp());
			
			if(asAnmag0fLs.isEmpty()){
				log.warn("raggruppamento " + asAnmar0f.getMcomp() + " non ha articoli all'ingrosso");
				continue;
			}
			
			log.info("gruppo di " + asAnmag0fLs.size());
			
			Iterator<AsAnmag0f> iter_asAnmag = asAnmag0fLs.iterator();
			Boolean soloPrimo = true;
			String fratelloMaggiore = null;
			while(iter_asAnmag.hasNext()){
				asAnmag0f = iter_asAnmag.next();
			
				if(soloPrimo){
					log.info("e' un fratello maggiore");
					fratelloMaggiore = StringUtils.trim(asAnmag0f.getMcoda());
					soloPrimo = false;
				}
				
				SqliteAssociati sqliteAssociati = new SqliteAssociati();
				
				sqliteAssociati.setCodartcapo(fratelloMaggiore);
				sqliteAssociati.setCodartfiglio(asAnmag0f.getMcoda());
				sqliteAssociati.setCodasso(StringUtils.join(asAnmag0f.getMcomp(), "-", fratelloMaggiore, "-", StringUtils.trim(asAnmag0f.getMcoda())));
				sqliteAssociatiDao.salva(sqliteAssociati);				
			}
		}
		
		// aggiungo, nelle associazioni, pure tutti gli articoli all'ingrosso senza mcomp, quindi per ogni articolo inserisco un singolo entry "00000-ABCD123-ABCD123" 
		asAnmag0fLs = asAnmag0fDao.getArticoliDaMcompIngrosso("");
		
		if(asAnmag0fLs.isEmpty()){
			log.warn("raggruppamento " + "00000" + " non ha articoli all'ingrosso");
		}
		
		log.info("articoli all'ingrosso senza mcomp: " + asAnmag0fLs.size());
		
		Iterator<AsAnmag0f> iter_asAnmag = asAnmag0fLs.iterator();
		while(iter_asAnmag.hasNext()){
			asAnmag0f = iter_asAnmag.next();
		
			String art = StringUtils.trim(asAnmag0f.getMcoda());
						
			SqliteAssociati sqliteAssociati = new SqliteAssociati();
			
			sqliteAssociati.setCodartcapo(art);
			sqliteAssociati.setCodartfiglio(art);
			sqliteAssociati.setCodasso(StringUtils.join("00000", "-", art, "-", art));
			sqliteAssociatiDao.salva(sqliteAssociati);				
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
		asNativeQueryDao.close();
		asAlmer0fDao.close();
		asTabe20fDao.close();
		asAnmar0fDao.close();
		
		sqliteArticoliDao.close();
		sqliteAssociatiDao.close();
		sqliteInquadramentiDao.close();
		sqliteMarcheDao.close();
		sqliteInfogenericheDao.close();	// in questo close c'è "pragma wal_checkpoint" per svuotare il wal file
		
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