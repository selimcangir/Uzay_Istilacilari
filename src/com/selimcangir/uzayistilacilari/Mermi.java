package com.selimcangir.uzayistilacilari;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.adt.color.Color;

public class Mermi {
	public Rectangle mermiSekli;
	public Mermi(){
		mermiSekli = new Rectangle(0, 0, 10, 10, TemelActivity.getSharedInstance().getVertexBufferObjectManager());
		
		mermiSekli.setColor(Color.RED);		
	}
}
