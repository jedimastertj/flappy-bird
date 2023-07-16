package com.tanishqjain.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -10;
    private static final int HORIZONTAL_VELOCITY = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;

    public Bird(int x, int y) {
        this.position = new Vector3(x, y, 0);
        this.velocity = new Vector3(Bird.HORIZONTAL_VELOCITY, 0, 0);
        this.texture = new Texture("bird.png");
        this.bounds = new Rectangle(this.position.x, this.position.y, this.texture.getWidth(), this.texture.getHeight());
    }

    public void update(float dt) {
//        System.out.println(this.position.x);
        Vector3 displacement = new Vector3(this.velocity);
        displacement.scl(dt);
        this.position.add(displacement);
        if (this.position.y > 0) {
            this.velocity.add(0, Bird.GRAVITY, 0);
        } else {
            this.position.y = 0;
        }
        this.bounds.setPosition(this.position.x, this.position.y);
    }

    public void dispose() {
        this.texture.dispose();
    }

    public void jump() {
        this.velocity.y = 200;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
