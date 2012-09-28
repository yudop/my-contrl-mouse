package screen;

import java.util.ArrayList;
import java.util.Random;

import model.Ball;
import model.FruitWord;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class PaoPaoScreen implements Screen {
	public int screenWidth = Gdx.graphics.getWidth();
	public int screenHeight = Gdx.graphics.getHeight();
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	int state;
	Game game;
	public SpriteBatch batch;
	Vector3 touchPoint;
	FruitWord fw;
	
	int ballRadius;
	ArrayList<Ball> balls;
	Random random;
	
	public PaoPaoScreen(Game game) {
		super();
		this.game = game;
		batch = new SpriteBatch();
		touchPoint = new Vector3();
		ballRadius = screenWidth/11;
		random = new Random();
		balls = new ArrayList<Ball>();
		for(int i = 0;i<5;i++){
			for(int j=0;j<10;j++){
				new Ball(ballRadius, ballRadius*j+ballRadius/2,screenHeight-(ballRadius*i+ballRadius/2), random.nextInt(5));
			}
		}
	}
	
	public void render(float delta) {
		draw(delta);
		update(delta);

	}
	public void update(float delta){
		updateRunning(delta);
	}

	public void draw(float delta){
		batch.begin();
//		fw.draw(delta);
		for(Ball ball:balls){
			ball.draw(batch);
		}
		batch.end();
		
	}

	private void updateRunning(float delta) {
//		if(Gdx.input.justTouched()){
//			fw.onTouch(Gdx.input.getX(), Gdx.input.getY());
//		}
		
		
		
	}

	private void updateReady() {
		if(Gdx.input.justTouched()){
			state = GAME_RUNNING;
		}
		
	}
	private void updateLevelEnd() {
	}

	private void updatePaused() {
	}
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub

	}

	public void show() {
		// TODO Auto-generated method stub

	}

}
