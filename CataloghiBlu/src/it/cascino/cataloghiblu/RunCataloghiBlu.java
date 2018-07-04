package it.cascino.cataloghiblu;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class RunCataloghiBlu{
	private static Logger log;
	
	public static void main(String[] args){
		PropertyConfigurator.configure("logdir/log.properties");
		log = Logger.getLogger(RunCataloghiBlu.class);
		log.info("START");
		
		@SuppressWarnings("unused")
		CataloghiBlu cataloghiBlu = new CataloghiBlu(args);
		
		log.info("STOP");
		System.exit(0);
	}
}
