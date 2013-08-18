package com.selimcangir.uzayistilacilari;

import org.andengine.engine.handler.IUpdateHandler;

public class OyunDongusuUpdateHandler implements IUpdateHandler {

	@Override
	public void onUpdate(float pSecondsElapsed) {
		((OyunEkrani) TemelActivity.getSharedInstance().suAnkiSahnem)
				.gemiyiHareketEttir();

		((OyunEkrani) TemelActivity.getSharedInstance().suAnkiSahnem)
				.temizleyici();
	}

	@Override
	public void reset() {
	}
}
