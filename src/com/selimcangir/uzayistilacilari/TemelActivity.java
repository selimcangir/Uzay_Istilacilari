package com.selimcangir.uzayistilacilari;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

public class TemelActivity extends SimpleBaseGameActivity {

	static final int KAMERA_GENISLIGI = 800;
	static final int KAMERA_YUKSEKLIGI = 480;

	public Font fontum;
	public Camera kameram;

	// Anlýk sahnemizine referans
	public Scene suAnkiSahnem;
	public static Music muzik;
	public static TemelActivity instance;

	@Override
	public EngineOptions onCreateEngineOptions() {
		instance = this;
		kameram = new Camera(0, 0, KAMERA_GENISLIGI, KAMERA_YUKSEKLIGI);

		EngineOptions motorAyarlarim = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(
						KAMERA_GENISLIGI, KAMERA_YUKSEKLIGI), kameram);
		return motorAyarlarim;
	}

	@Override
	protected void onCreateResources() throws IOException {
		
		fontum = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 48);
		fontum.load();
	}

	@Override
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		suAnkiSahnem = new AcilisEkrani();
		return suAnkiSahnem;
	}

	public static TemelActivity getSharedInstance() {
		return instance;
	}

	// Sahnemizi deðiþtirdiðimiz metod
	public void setCurrentScene(Scene scene) {
		suAnkiSahnem = scene;
		getEngine().setScene(suAnkiSahnem);
	}
	
	@Override
	public void onBackPressed(){
		if (suAnkiSahnem instanceof OyunEkrani){
			((OyunEkrani) suAnkiSahnem).bosalt();
		}		
		suAnkiSahnem = null;
		SensorDinleyicisi.instance = null;
		super.onBackPressed();
	}
	
}// sýnýf sonu
