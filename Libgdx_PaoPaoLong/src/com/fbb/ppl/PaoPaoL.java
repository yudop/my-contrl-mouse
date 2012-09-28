package com.fbb.ppl;

import screen.PaoPaoScreen;
import utils.Assets;

import com.badlogic.gdx.Game;

public class PaoPaoL extends Game {

	public void create() {
//		Setting.load();
		Assets.load();
		setScreen(new PaoPaoScreen(this));

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.getScreen().dispose();
		super.dispose();
	}
	
	

}
