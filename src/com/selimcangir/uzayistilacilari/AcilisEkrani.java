package com.selimcangir.uzayistilacilari;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;
import org.andengine.util.modifier.IModifier;

public class AcilisEkrani extends Scene {

	TemelActivity activity;

	public AcilisEkrani() {
		setBackground(new Background(Color.WHITE));
		activity = TemelActivity.getSharedInstance();
		Text baslik1 = new Text(0, 0, activity.fontum,
				activity.getString(R.string.baslik_1),
				activity.getVertexBufferObjectManager());
		Text baslik2 = new Text(0, 0, activity.fontum,
				activity.getString(R.string.baslik_2),
				activity.getVertexBufferObjectManager());

		baslik1.setPosition(-baslik1.getWidth(),
				activity.kameram.getHeight() / 2);
		baslik2.setPosition(activity.kameram.getWidth(),
				activity.kameram.getHeight() / 2 - baslik1.getHeight());

		attachChild(baslik1);
		attachChild(baslik2);

		baslik1.registerEntityModifier(new MoveXModifier(1, baslik1.getX(),
				activity.kameram.getWidth() / 2));
		baslik2.registerEntityModifier(new MoveXModifier(1, baslik2.getX(),
				activity.kameram.getWidth() / 2));

		kaynaklariYukle();

	}

	void kaynaklariYukle() {
		
		DelayModifier ikiSaniyeBekle = new DelayModifier(2,
				new IEntityModifierListener() {
					@Override
					public void onModifierStarted(IModifier arg0, IEntity arg1) {
					}
					@Override
					public void onModifierFinished(IModifier arg0, IEntity arg1) {
						activity.setCurrentScene(new AnaMenuEkrani());
					}
				});
		registerEntityModifier(ikiSaniyeBekle);
	}
}
