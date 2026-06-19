package game.renderable.entity;

import game.data.BananaParticleData;
import game.data.GameData;


import java.awt.*;

public class BananaParticle extends Banana {

    private final BananaParticleData data;

    /**
     * Constructor sets the default values
     * @param gameData data of the Game
     * @param player affected Player for getting the spawning position
     */
    public BananaParticle(GameData gameData, Player player) {
        super(gameData);

        this.data = gameData.getConstants().getBananaParticleData();

        this.scale = gameData.getScale() / data.getScaleDivisor();

        this.pos.setBoth((int) (player.pos.getX() + player.collider.getPos().getX() * player.scale + player.collider.getSize().getX() * player.scale / 2.0 - (this.collider.getPos().getX() * this.scale + this.collider.getSize().getX() * this.scale / 2.0)), (int) (player.pos.getY() + player.collider.getPos().getY() * player.scale - this.collider.getPos().getY() * this.scale));
        this.vel.setX(gameData.getRnd().nextIntIncl(data.getInitMinHorizontalSpeed(), data.getInitMaxHorizontalSpeed()));
        this.vel.setY(-1 * gameData.getRnd().nextIntIncl(data.getInitMinUpSpeed(), data.getInitMaxUpSpeed()));


        this.disableCollider();
    }

    /**
     * Used for updating the BananaParticle Entity at a fixed time step,
     * the BananaParticle Entity is rotated and falls to the ground
     * @param gameData data of the Game
     */
    @Override
    public void update(GameData gameData) {
        vel.addY(data.getDownAcceleration());
        vel.setY(Math.min(vel.getY(), data.getMaxFallingSpeed()));
        pos.add(vel);

        //Even if these Particles are not collectable, their rotation also should be disabled with this setting
        if (!gameData.isCollectableRotationDisabled()) {
            rotation = rotation + rotationSpeed;
        }
    }

    /**
     * Used for drawing the BananaParticle Entity
     * @param gfx Graphics2D context
     * @param gameData data of the Game
     */
    @Override
    public void draw(Graphics2D gfx, GameData gameData) {
        super.draw(gfx, gameData);
    }
}
