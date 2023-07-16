package com.tanishqjain.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    public static final int TUBE_HEIGHT = 320;
    public static final int TUBE_WIDTH = 52;
    private static final int TUBE_GAP = 80;
    private static final int LOWEST_LOWER_TUBE = 60;
    private static final int HIGHEST_LOWER_TUBE = 260;
    private static final int FLUCTUATION = 200;

    private Texture topTubeTexture, bottomTubeTexture;
    private Vector3 topTubePosition, bottomTubePosition;
    private Rectangle boundsTop, boundsBottom;
    private Random r;

    public Tube(float x) {
        this.r = new Random();
        this.topTubeTexture = new Texture("toptube.png");
        this.bottomTubeTexture = new Texture("bottomtube.png");
        this.topTubePosition = new Vector3(x, this.r.nextInt(Tube.FLUCTUATION) + Tube.LOWEST_LOWER_TUBE + Tube.TUBE_GAP, 0);
        this.bottomTubePosition = new Vector3(x, this.topTubePosition.y - Tube.TUBE_GAP - Tube.TUBE_HEIGHT, 0);
        this.boundsTop = new Rectangle(this.topTubePosition.x, this.topTubePosition.y, Tube.TUBE_WIDTH, Tube.TUBE_HEIGHT);
        this.boundsBottom = new Rectangle(this.bottomTubePosition.x, this.bottomTubePosition.y, Tube.TUBE_WIDTH, Tube.TUBE_HEIGHT);
    }

    public void reposition(float x) {
        this.topTubePosition.set(x, this.r.nextInt(Tube.FLUCTUATION) + Tube.LOWEST_LOWER_TUBE + Tube.TUBE_GAP, 0);
        this.bottomTubePosition.set(x, this.topTubePosition.y - Tube.TUBE_GAP - Tube.TUBE_HEIGHT, 0);
        this.boundsTop.setPosition(this.topTubePosition.x, this.topTubePosition.y);
        this.boundsBottom.setPosition(this.bottomTubePosition.x, this.bottomTubePosition.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(this.boundsTop) || player.overlaps(this.boundsBottom);
    }

    public void dispose() {
        this.getTopTubeTexture().dispose();
        this.getBottomTubeTexture().dispose();
    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public Vector3 getTopTubePosition() {
        return topTubePosition;
    }

    public Vector3 getBottomTubePosition() {
        return bottomTubePosition;
    }
}

