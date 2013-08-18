package com.selimcangir.uzayistilacilari;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorDinleyicisi implements SensorEventListener {
	static SensorDinleyicisi instance;
	OyunEkrani ekran;
	
	public static SensorDinleyicisi getSharedInstance(){
		if (instance == null)
			instance = new SensorDinleyicisi();
		return instance;
	}
	
	public SensorDinleyicisi(){
		instance = this;
		ekran = (OyunEkrani) TemelActivity.getSharedInstance().suAnkiSahnem;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this){
			switch(event.sensor.getType()){
			case Sensor.TYPE_ACCELEROMETER:
				ekran.ivmeOlcerXHizi = event.values[1];
				break;
			default:
				break;			
			}
		}
		
	}
}
