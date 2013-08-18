package com.selimcangir.uzayistilacilari;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.primitive.Rectangle;

public class Gemi {
	public Rectangle gemiSekli;
	public static Gemi instance;
	Camera kameram;
	boolean hareketli = true;

	public static Gemi getSharedInstance() {
		if (instance == null)
			instance = new Gemi();
		return instance;
	}

	private Gemi() {
		gemiSekli = new Rectangle(0, 0, 70, 30, TemelActivity
				.getSharedInstance().getVertexBufferObjectManager());

		kameram = TemelActivity.getSharedInstance().kameram;
		gemiSekli.setPosition(kameram.getWidth() / 2, gemiSekli.getHeight());
	}

	public void gemiyiHareketEttir(float ivmeOlcerXHizi) {
		// Geminin hareket özlliði aktifmi diye bakýyoruz.
		if (!hareketli)
			return;
		// Sensörden veri geldiði takdirde uzay gemisinin yeri deðiþiyor
		if (ivmeOlcerXHizi != 0) {
			// Gemini ekranda ilerleyebileceði limtler belirleniyor
			int solLimit = (int) (0 + gemiSekli.getWidth() / 2);
			int sagLimit = (int) (kameram.getWidth() - (int) gemiSekli
					.getWidth() / 2);
			float yeniX;

			// Limitler dahilinde yeni koordinatlarý hesaplýyoruz
			if (gemiSekli.getX() >= solLimit)
				yeniX = gemiSekli.getX() + ivmeOlcerXHizi;
			else
				yeniX = solLimit;
			if (yeniX <= sagLimit)
				yeniX = gemiSekli.getX() + ivmeOlcerXHizi;
			else
				yeniX = sagLimit;

			// Koordinatlarýn sýnýrlar içinde olduðu tekrar kontrol ediliyor
			if (yeniX < solLimit)
				yeniX = solLimit;
			else if (yeniX > sagLimit)
				yeniX = sagLimit;

			// Yeni koordinatlar gemiye uygulanýyor.
			gemiSekli.setPosition(yeniX, gemiSekli.getY());
		}

	}

	public void atesEt() {
		if (!hareketli)
			return;
		OyunEkrani ekran = (OyunEkrani) TemelActivity.getSharedInstance().suAnkiSahnem;

		Mermi m = (Mermi) MermiHavuzu.sharedMermiHavuzu().obtainPoolItem();
		m.mermiSekli.setPosition(gemiSekli.getX(), gemiSekli.getY());
		MoveYModifier yukariGit = new MoveYModifier(1.5f, m.mermiSekli.getY(),
				kameram.getHeight() + m.mermiSekli.getHeight());

		m.mermiSekli.setVisible(true);
		m.mermiSekli.detachSelf();
		ekran.attachChild(m.mermiSekli);
		ekran.mermiListesi.add(m);
		m.mermiSekli.registerEntityModifier(yukariGit);
		ekran.mermiSayisi++;
	}
	
	public void sifirla(){
		hareketli = false;
		Camera kameram = TemelActivity.getSharedInstance().kameram;
		 MoveXModifier ortala = new MoveXModifier(0.2f, gemiSekli.getX(),
			        kameram.getWidth()/2) {
			        @Override
			        protected void onModifierFinished(IEntity pItem) {
			            super.onModifierFinished(pItem);
			            hareketli = true;
			        }
			    };
		gemiSekli.registerEntityModifier(ortala);
	}

}
