package screen;

import java.util.ArrayList;
import java.util.Random;

import model.Ball;
import model.FruitWord;
import utils.PPData;
import utils.Utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
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
	public SpriteBatch batcher;
	Vector3 touchPoint;
	FruitWord fw;
	
	int ballRadius;
	ArrayList<Ball> balls;
	Random random;
	
	Ball readyBall;
	Ball flyingBall;
	Ball readyBallRight;
	public PaoPaoScreen(Game game) {
		super();
		this.game = game;
		
		batcher = new SpriteBatch();
		touchPoint = new Vector3();
		ballRadius = screenWidth/11/2;
		PPData.getInstance().ballRadius = ballRadius;
		PPData.getInstance().screenHeight = screenHeight;
		PPData.getInstance().screenWidth = screenWidth;
		
		random = new Random();
		balls = new ArrayList<Ball>();
		for(int i = 0;i<5;i++){
			for(int j=0;j<11;j++){
				Ball ball;
				if(i%2==0){//
					ball = new Ball(ballRadius*j*2+ballRadius*2,screenHeight-(ballRadius*i*2+ballRadius), random.nextInt(5));
				}else{
					ball = new Ball(ballRadius*j*2+ballRadius,screenHeight-(ballRadius*i*2+ballRadius), random.nextInt(5));
				}
				balls.add(ball);
			}
		}
		readyBall = new Ball(screenWidth/2,ballRadius, random.nextInt(5));
		readyBallRight = new Ball(screenWidth - 3*ballRadius, ballRadius, random.nextInt(5));
//		balls.add(readyBall);
//		balls.add(readyBallRight);
	}
	
	public void render(float delta) {
		draw(delta);
		update(delta);

	}
	public void update(float delta){
		updateRunning(delta);
	}

	public void draw(float delta){
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batcher.disableBlending();
//		batcher.begin();
//		for(Ball ball:balls){
//			ball.draw(batcher);
//		}
//		batcher.end();
		batcher.enableBlending();
		batcher.begin();
		for(Ball ball:balls){
			ball.draw(batcher);
		}
		if(flyingBall!=null){
			flyingBall.draw(batcher);
		}
		readyBall.draw(batcher);
		readyBallRight.draw(batcher);
		batcher.end();
		
	}

	private void updateRunning(float delta) {
		if(Gdx.input.justTouched()){
			flyingBall = readyBall;
			readyBall = readyBallRight;
			readyBall.setReadyBall();
			readyBallRight = new Ball(screenWidth - 3*ballRadius, ballRadius, random.nextInt(5));
			if(flyingBall!=null){
				flyingBall.setFlyingBall(Gdx.input.getX(), screenHeight-Gdx.input.getY());
			}
		}
		if(flyingBall!=null){
			flyingBall.update(delta);
		}
		readyBall.update(delta);
		readyBallRight.update(delta);
		
		int size = balls.size();
		for(int i =0;i<size;i++){
			Ball ball = balls.get(i);
			ball.update(delta);
			if(flyingBall !=null && ball!=flyingBall  ){
				boolean strike = Utils.getInstance().isStrike(ball, flyingBall);
				if(strike){
					flyingBall.isStatic = true;
					balls.add(flyingBall);
					flyingBall = null;
				}
			}
		}
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
