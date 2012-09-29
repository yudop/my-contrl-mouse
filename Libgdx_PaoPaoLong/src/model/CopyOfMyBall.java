package model;

import utils.Assets;
import junit.framework.Assert;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CopyOfMyBall {
	public int radius;
	public int positionX;
	public int positionY;
	public int type;
	public TextureRegion region;
	public int radX;
	public int radY;

	public CopyOfMyBall(int radius, int touchX, int touchY, int type) {
		super();
		this.radius = radius;
		this.positionX = 240;
		this.positionY = radius;
		radX = touchX - 240;
		radY = touchY - positionY;
		if (Math.abs(radX) >= Math.abs(radY)) {
			radX = radX / radY;
			radY = 1;
		} else {
			radY = Math.abs(radY / radX);
			radX = radX > 0 ? 1 : -1;
		}
		Log.d("fbb", "fbb x:" + positionX + " y:" + positionY);
		this.type = type;
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
		positionX = positionX + radX;
		positionY = positionY + radY;
		batch.draw(region, positionX - radius, positionY - radius, 2 * radius,
				2 * radius);
	}

}
