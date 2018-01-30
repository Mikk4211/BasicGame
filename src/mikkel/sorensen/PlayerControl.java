// @Author Mikk4211
// https://github.com/Mikk4211

package mikkel.sorensen;
import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerControl extends Control{

    private PhysicsComponent physics;

    @Override
    public void onUpdate(Entity entity, double tpf) {

    }
    public void left() {                // Definerer venstre
        physics.setVelocityX(-150);
    }
    public void right() {               // Definerer højre
        physics.setVelocityX(150);
    }
    public void jump() {                // Definerer hop
        physics.setVelocityY(-400);
    }

}
