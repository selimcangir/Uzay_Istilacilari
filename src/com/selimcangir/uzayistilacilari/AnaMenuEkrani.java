package com.selimcangir.uzayistilacilari;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.util.adt.color.Color;

import android.util.Log;


public class AnaMenuEkrani extends MenuScene implements IOnMenuItemClickListener {
	TemelActivity activity;
	final int MENU_BASLA = 0;	
	
	public AnaMenuEkrani(){
		super(TemelActivity.getSharedInstance().kameram);
		activity = TemelActivity.getSharedInstance();
		setBackground(new Background(Color.WHITE));
		IMenuItem baslaButonu = new TextMenuItem(MENU_BASLA, activity.fontum, activity.getString(R.string.menu_basla), activity.getVertexBufferObjectManager());
		baslaButonu.setPosition(activity.kameram.getWidth() / 2, activity.kameram.getHeight() / 2);
		addMenuItem(baslaButonu);
		 
		setOnMenuItemClickListener(this);
		
	}
	
	
	
	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		switch (arg1.getID()) {
		case MENU_BASLA:
			activity.setCurrentScene(new OyunEkrani());
			return true;
		default:
			break;
		}
		return false;
	}
	}
