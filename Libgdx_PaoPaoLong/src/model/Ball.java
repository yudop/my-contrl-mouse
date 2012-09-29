package model;

import utils.Assets;
import utils.PPData;
import junit.framework.Assert;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ball {
	public int radius;
	public float positionX;// Ô²ÐÄÎ»ÖÃ
	public float positionY;
	public int type;
	public TextureRegion region;

	public boolean isStatic;
	public float radX;
	public float radY;
	public static int flySpeed = 8;

	public Ball(int positionX, int positionY, int type) {
		super();
		this.radius = PPData.getInstance().ballRadius;
		this.positionX = positionX;
		this.positionY = positionY;
		Log.d("fbb", "fbb x:" + positionX + " y:" + positionY);
		this.type = type;
		isStatic = true;
		initTexture(type);
	}

	public void setFlyingBall(int touchX, int touchY) {
		isStatic = false;
		float absX = (float)touchX - positionX;
		float absY = (float)touchY - positionY;
		if (absX == 0 || absX == 0) {
			if (absX == 0){
				radY = flySpeed;
				radX = 0;
			}
			if (absY == 0){
				radX = flySpeed;
				radY = 0;
			}
		} else {
			double per = flySpeed/Math.sqrt(absX*absX+absY*absY);
			radX = (float) (absX *per);
			radY = (float) (absY*per);
		}
		Log.d("fbb", "fbb touchX:"+touchX+" touchY:"+touchY);
		Log.d("fbb", "fbb absX:"+absX+" absY:"+absY);
		Log.d("fbb", "fbb radX:"+radX+" radY:"+radY);
	}

	public void setReadyBall() {
		positionX = PPData.getInstance().screenWidth / 2;
		positionY = radius;
	}

	public void setStatic() {
		isStatic = true;
	}

	public boolean isDead() {
		return false;
	}

	private void initTexture(int type) {
		switch (type) {
		case 0:
			region = Assets.ma;
			break;
		case 1:
			region = Assets.mb;
			break;
		case 2:
			region = Assets.mc;
			break;
		case 3:
			region = Assets.md;
			break;
		case 4:
			region = Assets.me;
			break;
		case 5:
			region = Assets.mf;
			break;

		default:
			break;
		}
	}

	public void draw(SpriteBatch batch) {
		batch.draw(region, positionX - radius, positionY - radius, 2 * radius,
				2 * radius);
	}

	public void update(float delta) {
		if (isStatic) {

		} else {
			positionX = positionX + radX;
			positionY = positionY + radY;
			
			if(positionX-radius<=0||positionX+radius>=PPData.getInstance().screenWidth){
				radX = -radX;
			}
			if(positionY+radius>=PPData.getInstance().screenHeight){
				isStatic = true;
			}
		}
		
	}

}
