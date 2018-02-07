// @Author Mikk4211
// https://github.com/Mikk4211
package mikkel.sorensen;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


@SetEntityFactory
public class BasicGameFactory implements EntityFactory{



    @Spawns("enemy")     // Laver en enemy
    public Entity enemy(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(BasicGameType.ENEMY)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(30,30,Color.RED))
                .with(physics)
                .with(new EnemyControl())
                .build();
    }
    @Spawns("enemy1")     // Laver en enemy
    public Entity enemy1(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(BasicGameType.ENEMY)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(40,40,Color.RED))
                .with(physics)
                .with(new EnemyControl1())
                .build();
    }

    @Spawns("platform")     //Generere hitbox for platform
    public Entity newPlatform(SpawnData data){
        return Entities.builder()
                .type(BasicGameType.PLATFORM)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }@Spawns("door")     // Dør der går til et nyt level
    public Entity newDoor(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.DOOR)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("door1")     // Dør 1
    public Entity newDoor1(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.DOOR1)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("door2")     // Dør 2
    public Entity newDoor2(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.DOOR2)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("door3")     // Dør 3
    public Entity newDoor3(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.DOOR3)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("door4")     // Dør 4
    public Entity newDoor4(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.DOOR4)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }


    @Spawns("player")     //Generere spillerens karakter
    public Entity newPlayer(SpawnData data) {

        Entity entity = new Entity();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        entity.addComponent(physics);

        return Entities.builder()
                .from(data)
                .type(BasicGameType.PLAYER)
                .bbox(new HitBox(BoundingShape.box(32,42)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new PlayerControl())
                .build();
    }
    @Spawns("coin")     // Laver et coin objekt
    public Entity newCoin(SpawnData data) {
        return Entities.builder()
                .type(BasicGameType.COIN)
                .from(data)
                .viewFromNodeWithBBox(new Circle(data.<Integer>get("width") / 2, Color.GOLD)) // Laver en coin, der har ½ width, og er gul
                .with(new CollidableComponent(true))
                .build();

    }
    @Spawns("spawn")
    public Entity newSpawn(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(BasicGameType.SPAWN)
                .build();
    }


}
