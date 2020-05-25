package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
<<<<<<< HEAD
import com.badlogic.gdx.math.Vector2;

public class Projectile extends GameObject implements Poolable {
    private TextureRegion texture;
    private Vector2 velocity;
    private float speed;
    private float angle;
    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public Projectile(GameController gc) {
        super(gc);
        this.velocity = new Vector2();
        this.speed = 320.0f;
    }

    public void setup(Vector2 startPosition, float angle, TextureRegion texture) {
        this.texture = texture;
        this.position.set(startPosition);
        this.angle = angle;
        this.velocity.set(speed * MathUtils.cosDeg(angle), speed * MathUtils.sinDeg(angle));
        this.active = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 8, position.y - 8);
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720) {
            deactivate();
        }
=======
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion textureRegion;

    private float speedProjectile = 200f;

    public Projectile(TextureAtlas atlas) {
        this.textureRegion = new TextureRegion(atlas.findRegion("bullet"));
    }

    public void setup(Vector2 startPosition, float angle) {
        velocity = new Vector2(speedProjectile * MathUtils.cosDeg(angle), speedProjectile * MathUtils.sinDeg(angle));
        position = new Vector2(startPosition.x + (23 * MathUtils.cosDeg(angle)), startPosition.y - 3 + (23 * MathUtils.sinDeg(angle)));
    }


    public void update(float dt) {
        position.mulAdd(velocity, dt);
    }

    public boolean checkBounds(Vector2 position){
        return (position.x < 0 || position.y < 0 || position.x > 1280 || position.y > 720);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Vector2 getPosition() {
        return position;
>>>>>>> master
    }
}
