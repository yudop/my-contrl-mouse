package screen;

import model.FruitWord;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class LlkScreen implements Screen {
//	int screenWidth = 320;
//	int screenHeight = 480;
	public int screenWidth = Gdx.graphics.getWidth();
	public int screenHeight = Gdx.graphics.getHeight();
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	
	Game game;
//	OrthographicCamera camera;
	public SpriteBatch batch;
	Vector3 touchPoint;
	
//	Rectangle pauseBounds;
//	Rectangle resumeBounds;
//	Rectangle quitBounds;
	
//	World world;
	
	int lastScore;
	String scoreString;
	
	
	FruitWord fw;
	public LlkScreen(Game game) {
		super();
		this.game = game;
//		state = GAME_READY;
		state = GAME_RUNNING;
//		camera = new OrthographicCamera(screenWidth, screenHeight);
//		camera.position.set(screenWidth/2, screenHeight/2, 0);
		batch = new SpriteBatch();
		touchPoint = new Vector3();
		fw = new FruitWord(this);
//		pauseBounds = new Rectangle(320 - 64, 480 - 64, 64, 64);
//		resumeBounds = new Rectangle(160 - 96, 240, 192, 36);
//		quitBounds = new Rectangle(160 - 96, 240 - 36, 192, 36);
		
	}
	public void render(float delta) {
		draw(delta);
		update(delta);

	}
	public void update(float delta){
		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(delta);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updatePaused();
			break;

		default:
			break;
		}
		
	}

	public void draw(float delta){
		batch.begin();
		fw.draw(delta);
		batch.end();
		
	}

	private void updateRunning(float delta) {
		if(Gdx.input.justTouched()){
			fw.onTouch(Gdx.input.getX(), Gdx.input.getY());
//			Log.d("llk", "justTouch");
		}
//		world.update(delta, Gdx.input.getAccelerometerX());
		
		
		
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
