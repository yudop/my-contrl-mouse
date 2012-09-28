package model;

import utils.Assets;
import junit.framework.Assert;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ball {
	int radius;
	int positionX;
	int positionY;
	int type;
	TextureRegion region;

	public Ball(int radius, int positionX, int positionY, int type) {
		super();
		this.radius = radius;
		this.positionX = positionX;
		this.positionY = positionY;
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
		batch.draw(region, positionX, positionY, radius, radius);
	}

}
