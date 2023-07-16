package com.tanishqjain.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tanishqjain.flappybird.states.GameStateManager;
import com.tanishqjain.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.gsm = new GameStateManager();
		this.gsm.push(new MenuState(this.gsm));
	}

	// game loop
	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
		this.gsm.update(Gdx.graphics.getDeltaTime());
		this.gsm.render(this.batch);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
