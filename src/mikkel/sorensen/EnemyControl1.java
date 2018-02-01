package mikkel.sorensen;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class EnemyControl1 extends Control{

    private PhysicsComponent physics;

    private LocalTimer jumpTimer;

    @Override
    public void onAdded(Entity entity){
        jumpTimer = FXGL.newLocalTimer();
        jumpTimer.capture();
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

