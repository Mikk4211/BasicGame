/* @Author Mikk4211 https://github.com/Mikk4211 */
/* EASJ Datamatiker 2. semester, Basic Game Project */
package mikkel.sorensen;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class EnemyControl1 extends Control{
    private boolean isMoving() {            // For readability, laver jeg her en boolean, så det ikke bliver lige så rodet.
        return FXGLMath.abs(physics.getVelocityX()) > 0;
    }
    private PhysicsComponent physics;

    private LocalTimer jumpTimer;

    private AnimationChannel animIdle, animWalk;
    private AnimatedTexture texture;
    public EnemyControl1() {         // Definerer bevægelserne for spillerens karakter
        animIdle = new AnimationChannel("john.png", 4, 33, 42, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel("john.png", 4, 33, 42, Duration.seconds(1), 1, 1);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded(Entity entity){
        jumpTimer = FXGL.newLocalTimer();
        jumpTimer.capture();
        entity.setView(texture);
    }
    @Override
    public void onUpdate(Entity entity, double v) {
        if (jumpTimer.elapsed(Duration.seconds(4))) {
            jump();
            jumpTimer.capture();
        }
    }
    public void jump(){                // Definerer hop
        physics.setVelocityY(-400);
    }
}

