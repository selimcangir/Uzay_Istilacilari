package com.selimcangir.uzayistilacilari;

import java.util.LinkedList;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.primitive.Rectangle;

import android.graphics.Color;

public class Dusman {
	public Rectangle dusmanSekli;
	public int can;
	// Her düþman için max. can
	protected final int MAX_CAN = 2;
	public Dusman() {
		dusmanSekli = new Rectangle(0, 0, 30, 30, TemelActivity.getSharedInstance().getVertexBufferObjectManager());
		dusmanSekli.setColor(Color.YELLOW);
		baslat();
	}
	public void baslat() {
		can = MAX_CAN;
		dusmanSekli.registerEntityModifier(new LoopEntityModifier(new RotationModifier(5, 0, 360)));
	}
	public void temizle(){
		dusmanSekli.clearEntityModifiers();
		dusmanSekli.clearUpdateHandlers();
	}
	// Baþarýlý vuruþlarýn tutulduðu metod, düþmanýn ölü olup olmadýðýna bakýyor. Öldüyse false gönderiyor.
	public boolean vuruldu() {
		synchronized(this){
			can--;
			if(can<=0)
				return false;
			else
				return true;
		}
	}	
}
