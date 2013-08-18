package com.selimcangir.uzayistilacilari;

import java.util.Timer;
import java.util.TimerTask;

public class AtisSinirla {
	private boolean gecerli;
	private Timer zamanlayici;
	private long gecikme = 500;
	private static AtisSinirla instance = null;
	
	public static AtisSinirla getSharedInstance(){
		if(instance == null){
			instance = new AtisSinirla();
		}
		return instance;
	}
	private AtisSinirla(){
		zamanlayici = new Timer();
		gecerli = true;
	}
	public boolean gecerlilikKontrolu(){
		if(gecerli){
			gecerli = false;
			zamanlayici.schedule(new Gorev(), gecikme);
			return true;
		}
		return false;
	}
	class Gorev extends TimerTask{
		@Override
		public void run() {
			gecerli = true;			
		}
	}
}
