package com.selimcangir.uzayistilacilari;

import java.util.Iterator;
import java.util.LinkedList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.modifier.ease.EaseSineOut;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

public class OyunEkrani extends Scene implements IOnSceneTouchListener{
	public Gemi gemi;
	Camera kameram;
	public float ivmeOlcerXHizi;
	SensorManager sensorManager;
	public LinkedList<Mermi> mermiListesi;
	public int mermiSayisi;
	int kacanAtisSayisi;

	public OyunEkrani() {
		mermiListesi = new LinkedList<Mermi>();
		
		setBackground(new Background(0.098f, 0.62f, 0.87f));
		// 12 düsman yaratalým
		attachChild(new DusmanKatmani(12));
		kameram = TemelActivity.getSharedInstance().kameram;
		gemi = Gemi.getSharedInstance();
		attachChild(gemi.gemiSekli);
		
		TemelActivity.getSharedInstance().setCurrentScene(this);
		sensorManager = (SensorManager) TemelActivity.getSharedInstance()
				.getSystemService(BaseGameActivity.SENSOR_SERVICE);
		SensorDinleyicisi.getSharedInstance();
		sensorManager.registerListener(SensorDinleyicisi.getSharedInstance(),
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
				
		setOnSceneTouchListener(this);
		
		degerleriSifirla();
		
		
	}

	public void gemiyiHareketEttir() {
		gemi.gemiyiHareketEttir(ivmeOlcerXHizi);
	}
	
	public void temizleyici() {
	    synchronized (this) {
	    	// Tüm düþmanlar ölmüþ ise
	    	if (DusmanKatmani.bosMu()) {
	    	    setChildScene(new SonucEkrani(kameram));
	    	    clearUpdateHandlers();
	    	}
	    	
	    	Iterator dIt = DusmanKatmani.getIterator();
	    	while (dIt.hasNext()) {
	    		Dusman d = (Dusman) dIt.next();
	        Iterator mIt = mermiListesi.iterator();
	        while (mIt.hasNext()) {
	            Mermi m = (Mermi) mIt.next();
	            if (m.mermiSekli.getY() >= kameram.getHeight()) {
	                MermiHavuzu.sharedMermiHavuzu().recyclePoolItem(m);
	                mIt.remove();
	                kacanAtisSayisi++;
	                continue;
	            }
	            if (m.mermiSekli.collidesWith(d.dusmanSekli)){
	            	if (!d.vuruldu() ) {
	            		DusmanHavuzu.sharedEnemyPool().recyclePoolItem(d);
	            		dIt.remove();
	            	}
	            MermiHavuzu.sharedMermiHavuzu().recyclePoolItem(m);
	            mIt.remove();
	            break;	
	            }
	            
	        }
	     }
	   }
	}
	
	// Deðerleri sýfýrlayan ve oyunu baþtan baþlatan metod
	public void degerleriSifirla() {
	    kacanAtisSayisi = 0;
	    mermiSayisi = 0;
	    gemi.sifirla();
	    DusmanKatmani.temizleVeBastanBaslat();
	    clearChildScene();
	    registerUpdateHandler(new OyunDongusuUpdateHandler());
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene,
			final TouchEvent pSceneTouchEvent) {
		if (!AtisSinirla.getSharedInstance().gecerlilikKontrolu())
		    return false;
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			gemi.atesEt();
			break;
		}
		return true;
	}
	
	public void bosalt() {
		clearUpdateHandlers();
		for (Mermi m : mermiListesi){
			MermiHavuzu.sharedMermiHavuzu().recyclePoolItem(m);
		}
		mermiListesi.clear();
		detachChildren();
		Gemi.instance = null;
		DusmanHavuzu.instance = null;
		MermiHavuzu.instance = null;		
	}

}// sýnýf sonu
	