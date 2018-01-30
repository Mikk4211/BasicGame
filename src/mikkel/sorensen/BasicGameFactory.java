package mikkel.sorensen;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


@SetEntityFactory
public class BasicGameFactory implements EntityFactory{

    @Spawns("platform")     //Generere hitbox for platform
    public Entity newPlatform(SpawnData data){
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }
    @Spawns("player")     //Generere spillerens karakter
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(30, 30, Color.BLUE))
                .with(physics)
                .with(new PlayerControl())
                .build();
    }
    @Spawns("coin")
    public Entity newCoin(SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromNodeWithBBox(new Circle(data.<Integer>get("width") / 2, Color.GOLD)) //Generer bounding box, med view
                .build();

    }
}
