package com.tanishqjain.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tanishqjain.flappybird.FlappyBird;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        this.background = new Texture("background.png");
        this.playButton = new Texture("playButton.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            this.gsm.set(new PlayState(this.gsm));
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(this.background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(this.playButton, ((FlappyBird.WIDTH / 2) - (this.playButton.getWidth() / 2)),
                ((FlappyBird.HEIGHT / 2) - (this.playButton.getHeight() / 2)));
        sb.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.playButton.dispose();
//        System.out.println("Menu state disposed");
    }
}
