// @Author Mikk4211
// https://github.com/Mikk4211

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

public class PlayerControl extends Control {

    private boolean isMoving() {            // For readability, laver jeg her en boolean, så det ikke bliver lige så rodet.
        return FXGLMath.abs(physics.getVelocityX()) > 0;
    }

    private PhysicsComponent physics;
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk, animJump;
    private LocalTimer jumpTimer;

    public PlayerControl() {         // Definerer bevægelserne for spillerens karakter
        animIdle = new AnimationChannel("player.png", 4, 32, 42, Duration.seconds(1), 1, 1);
        animWalk = new AnimationChannel("player.png", 4, 32, 42, Duration.seconds(1), 0, 3);
        texture = new AnimatedTexture(animIdle);

        jumpTimer = FXGL.newLocalTimer();
    }

    @Override
    public void onAdded(Entity entity) {
        entity.setView(texture);

    }

    @Override
    public void onUpdate(Entity entity, double tpf) {       // Denne metode definerer hvilken animation, karakteren skal have
        texture.setAnimationChannel(isMoving() ? animWalk : animIdle);
    }


    public void left() {                // Definerer venstre
        physics.setVelocityX(-125);
        getEntity().setScaleX(-1);      // Gør at karakteren kigger til venstre
    }

    public void right() {               // Definerer højre
        physics.setVelocityX(125);
        getEntity().setScaleX(1);       // Gør at karakteren kigger til højre.
    }

    public void jump() {                // Definerer hop
                physics.setVelocityY(-300);

        }
    }



