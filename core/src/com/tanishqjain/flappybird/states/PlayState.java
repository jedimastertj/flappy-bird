package com.tanishqjain.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.tanishqjain.flappybird.FlappyBird;
import com.tanishqjain.flappybird.sprites.Bird;
import com.tanishqjain.flappybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET = -80;

    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;
    private Texture ground;
    private Vector3 ground1pos, ground2pos;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.bird = new Bird(50, 150);
        this.camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        this.background = new Texture("background.png");
        this.tubes = new Array<Tube>();
        for (int i = 1; i <= PlayState.TUBE_COUNT; i++) {
            this.tubes.add(new Tube(i * (PlayState.TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        this.ground = new Texture("ground.png");
        this.ground1pos = new Vector3(this.camera.position.x - (this.camera.viewportWidth/2), PlayState.GROUND_OFFSET, 0);
        this.ground2pos = new Vector3(this.ground1pos.x + this.ground.getWidth(), PlayState.GROUND_OFFSET, 0);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            this.bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.bird.update(dt);
        this.updateGround();
        for (Tube tube: this.tubes) {
            if (tube.collides(this.bird.getBounds())) {
                this.gsm.set(new PlayState(this.gsm));
                break;
            }
            if (this.camera.position.x - (this.camera.viewportWidth/2) > tube.getTopTubePosition().x + Tube.TUBE_WIDTH) {
                tube.reposition(tube.getTopTubePosition().x + PlayState.TUBE_COUNT * (PlayState.TUBE_SPACING + Tube.TUBE_WIDTH));
            }
        }
        if (this.bird.getPosition().y <= this.ground.getHeight() + PlayState.GROUND_OFFSET) {
            this.gsm.set(new PlayState(this.gsm));
        }
        this.camera.position.x = this.bird.getPosition().x + 80;
        this.camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(this.camera.combined);
        sb.begin();
        sb.draw(this.background, this.camera.position.x - this.camera.viewportWidth / 2, this.camera.position.y - this.camera.viewportHeight / 2);
        sb.draw(this.bird.getTexture(), this.bird.getPosition().x, this.bird.getPosition().y);
        for (Tube tube: this.tubes) {
            sb.draw(tube.getTopTubeTexture(), tube.getTopTubePosition().x, tube.getTopTubePosition().y);
            sb.draw(tube.getBottomTubeTexture(), tube.getBottomTubePosition().x, tube.getBottomTubePosition().y);
        }
        sb.draw(this.ground, ground1pos.x, ground1pos.y);
        sb.draw(this.ground, ground2pos.x, ground2pos.y);
        sb.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.bird.dispose();
        for (Tube tube: this.tubes) {
            tube.dispose();
        }
        this.ground.dispose();
//        System.out.println("Play state disposed");
    }

    private void updateGround() {
        if (this.camera.position.x - (this.camera.viewportWidth/2) > this.ground1pos.x + this.ground.getWidth()) {
            this.ground1pos.add(this.ground.getWidth()*2, 0, 0);
        }
        if (this.camera.position.x - (this.camera.viewportWidth/2) > this.ground2pos.x + this.ground.getWidth()) {
            this.ground2pos.add(this.ground.getWidth()*2, 0, 0);
        }
    }
}
