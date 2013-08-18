package com.selimcangir.uzayistilacilari;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class SonucEkrani extends CameraScene implements IOnSceneTouchListener{
	boolean bitti;
	TemelActivity activity;
	
	public SonucEkrani(Camera kameram){
		super(kameram);
	    activity = TemelActivity.getSharedInstance();
	    setBackgroundEnabled(false);
	    OyunEkrani scene = (OyunEkrani) activity.suAnkiSahnem;
	    float isabetlilik = 1 - (float) scene.kacanAtisSayisi / scene.mermiSayisi;
	    if (Float.isNaN(isabetlilik))
	        isabetlilik = 0;
	    isabetlilik *= 100;
	    Text sonuc = new Text(0, 0, activity.fontum,
	        activity.getString(R.string.isabetlilik) + ": "
	        + String.format("%.2f", isabetlilik) + "%", TemelActivity
	        .getSharedInstance().getVertexBufferObjectManager());

	    final int x = (int) (kameram.getWidth() / 2 );
	    final int y = (int) (kameram.getHeight() / 2 );

	    bitti = false;
	    sonuc.setPosition(x, kameram.getHeight());
	    MoveYModifier ortala = new MoveYModifier(5, sonuc.getY(), y) {
	        @Override
	        protected void onModifierFinished(IEntity pItem) {
	            bitti = true;
	        }
	    };
	    attachChild(sonuc);
	    sonuc.registerEntityModifier(ortala);
	    setOnSceneTouchListener(this);
		
	}
	
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (!bitti)
		    return true;
		((OyunEkrani) activity.suAnkiSahnem).degerleriSifirla();
		return false;
	}

}
