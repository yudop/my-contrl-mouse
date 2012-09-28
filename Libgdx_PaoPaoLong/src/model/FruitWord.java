package model;

import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screen.LlkScreen;
import utils.Assets;

public class FruitWord {
	
	public int[][] ff = {
			{0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,2,2,3,3,4,4,5,5,0},
			{0,6,6,7,7,8,8,9,9,10,10,0},
			{0,1,1,2,2,3,3,4,4,5,5,0},
			{0,6,6,7,7,8,8,9,9,10,10,0},
			{0,1,1,2,2,3,3,4,4,5,5,0},
			{0,6,6,7,7,8,8,9,9,10,10,0},
			{0,1,1,2,2,3,3,4,4,5,5,0},
			{0,6,6,7,7,8,8,9,9,10,10,0},
			{0,1,1,2,2,3,3,4,4,5,5,0},
			{0,6,6,7,7,8,8,9,9,10,10,0},
			{0,0,0,0,0,0,0,0,0,0,0,0}};
	
	LlkScreen screen;
	int perW;
	int perH;
	SpriteBatch batch;
	
	
	public FruitWord(LlkScreen screen) {
		super();
		this.screen = screen;
		perW = screen.screenWidth/12;
		perH = perW;
		batch = screen.batch;
		initFruits();
	}

	public void draw(float delta){
		for(int i = 0;i<12;i++){
			for(int j=1;j<12;j++){
				switch (ff[i][j]) {
				case 0:
					break;
				case 1:
					batch.draw(Assets.ma, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 2:
					batch.draw(Assets.mb, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 3:
					batch.draw(Assets.mc, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 4:
					batch.draw(Assets.md, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 5:
					batch.draw(Assets.me, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 6:
					batch.draw(Assets.mf, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 7:
					batch.draw(Assets.mg, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 8:
					batch.draw(Assets.mh, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 9:
					batch.draw(Assets.mi, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				case 10:
					batch.draw(Assets.mj, perW*j, screen.screenHeight-perH*(i+1), perW, perH);
					break;
				default:
					break;
				}
			}
		}
		
	}
	
	public void update(){
		
	}
	
	public void onTouch(int x, int y){
//		Assets.playSounds(Assets.clickSound);
		Log.d("llk", "x:"+x+" y:"+y);
		int raw = 0;
		int col = 0;
		Log.d("llk", "screenHeight:"+screen.screenHeight+" perH:"+perH);
		if(x>0&&x<=screen.screenWidth&&y<perH*12&&y>0){
			raw = y/perH;
			col = x/perW;
			Log.d("llk", "raw:"+raw+" col:"+col);
		}
		
	}
	public void initFruits(){
		int buffer = 0;
		int raw = 0;
		int col = 0;
		Random random = new Random();
		for(int i = 1;i<=10;i++){
			for(int j=1;j<=10;j++){
				buffer = ff[i][j];
				raw = 1+random.nextInt(9);
				col = 1+random.nextInt(9);
				ff[i][j]= ff[raw][col];
				ff[raw][col] = buffer;
			}
		}
		
	}

}
