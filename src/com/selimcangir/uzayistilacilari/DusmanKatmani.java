package com.selimcangir.uzayistilacilari;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

public class DusmanKatmani extends Entity {
	private LinkedList<Dusman> dusmanlar;
	public static DusmanKatmani instance;
	public int dusmanSayisi;

	public static DusmanKatmani getSharedInstance() {
		return instance;
	}

	// Tüm Düsmanlar öldü mü?
	public static boolean bosMu() {
		if (instance.dusmanlar.size() == 0)
			return true;
		return false;
	}

	public static Iterator getIterator() {
		return instance.dusmanlar.iterator();
	}

	public DusmanKatmani(int x) {
		dusmanlar = new LinkedList();
		instance = this;
		dusmanSayisi = x;
	}

	private void bastanBaslat() {
		dusmanlar.clear();
		clearEntityModifiers();
		clearUpdateHandlers();

		for (int i = 0; i < dusmanSayisi; i++) {
			Dusman d = DusmanHavuzu.sharedEnemyPool().obtainPoolItem();
			float sonXKonumu = (i % 6) * 4 * d.dusmanSekli.getWidth();
			float sonYKonumu = ((int) (i / 6)) * d.dusmanSekli.getHeight() * 3;

			Random r = new Random();
			d.dusmanSekli.setPosition(
					r.nextInt(2) == 0 ? -(d.dusmanSekli.getWidth()) * 3
							: TemelActivity.KAMERA_GENISLIGI
									+ (d.dusmanSekli.getWidth()) * 3,
					(r.nextInt(5) + 1) * d.dusmanSekli.getHeight());
			d.dusmanSekli.setVisible(true);

			attachChild(d.dusmanSekli);
			d.dusmanSekli.registerEntityModifier(new MoveXModifier(2,
					d.dusmanSekli.getX(), sonXKonumu));
			d.dusmanSekli.registerEntityModifier(new MoveYModifier(2,
					d.dusmanSekli.getY(), sonYKonumu));

			dusmanlar.add(d);
		}
		setVisible(true);
		setPosition(50, 300);

		MoveXModifier sagaGit = new MoveXModifier(2, 50, 150);
		MoveXModifier solaGit = new MoveXModifier(2, 150, 50);
		MoveYModifier yukariGit = new MoveYModifier(1, 300, 350);
		MoveYModifier asagiGit = new MoveYModifier(1, 350, 300);

		registerEntityModifier(new LoopEntityModifier(
				new SequenceEntityModifier(sagaGit, yukariGit, solaGit, asagiGit)));
	}

	// purgeAndRestart
	public static void temizleVeBastanBaslat() {
		instance.dusmanTemizle();
		instance.bastanBaslat();
	}
	@Override
	public void onDetached() {
		dusmanTemizle();
		clearUpdateHandlers();
		super.onDetached();
	}

	// purge
	public void dusmanTemizle() {
		detachChildren();
		for (Dusman d : dusmanlar) {
			DusmanHavuzu.sharedEnemyPool().recyclePoolItem(d);
		}
		dusmanlar.clear();
	}

}
