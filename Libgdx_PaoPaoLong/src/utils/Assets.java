package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureRegion ma;
	public static TextureRegion mb;
	public static TextureRegion mc;
	public static TextureRegion md;
	public static TextureRegion me;
	public static TextureRegion mf;
	public static TextureRegion mg;
	public static TextureRegion mh;
	public static TextureRegion mi;
	public static TextureRegion mj;
	
	public static TextureRegion mm;
	
	public static Texture ms;
	
	public static void load(){
		ms = new Texture(Gdx.files.internal("ms_default.png"));
		
		ma = new TextureRegion(ms, 371, 70, 62, 62);
		mb = new TextureRegion(ms, 371+62, 70, 62, 62);
		mc = new TextureRegion(ms, 371+62*2, 70, 62, 62);
		md = new TextureRegion(ms, 371+62*3, 70, 62, 62);
		me = new TextureRegion(ms, 371+62*4, 70, 62, 62);
		mf = new TextureRegion(ms, 371+62*5, 70, 62, 62);
		
		mm = new TextureRegion(ms, 0, 0, 100, 100);
	}
	
	public static void playSounds(Sound sound){
	}

}
