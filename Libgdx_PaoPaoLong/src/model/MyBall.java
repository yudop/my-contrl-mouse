package model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyBall extends Ball{
	
	public MyBall(int positionX, int positionY, int type) {
		super(positionX, positionY, type);
	}
	


	public int radX;
	public int radY;

	

	public void draw(SpriteBatch batch) {
		positionX = positionX + radX;
		positionY = positionY + radY;
		batch.draw(region, positionX - radius, positionY - radius, 2 * radius,
				2 * radius);
	}
	
	public void update(float delta){
		
	}

}
