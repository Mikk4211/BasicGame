package mikkel.sorensen;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

//@Author Mikk4211 https://github.com/Mikk4211
//EASJ Datamatiker 2. semester, Basic Game Project

public class BasicGame extends GameApplication{
    @Override                   // Dette sætter størrelsen på vinduet, spillet kører i.
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(15*70);
        gameSettings.setHeight(10*70);
    }

    private Entity player;              // Player variabel

    @Override
    protected void initInput() {                    // Gør at man kan gå til venstre
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Right") {  // Gør at man kan gå til højre
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Jump") {   // Gør at man kan hoppe
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("BasicGame.json");   // Mappet fra Tiled

        player = getGameWorld().spawn("player", 50, 50); // Player spawn

        getGameScene().getViewport().setBounds(-1500, 0, 1500, getHeight());          // Definerer grænser for banen. Du kan ikke se under banen.
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2); // "Kamera" der låser til karakteren

        getGameWorld().spawn("enemy", 470, 50);
        getGameWorld().spawn("enemy1", 670, 50);

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.COIN) {
            @Override       // Collision handler, der gør at der er collision mellem player og objekt
            protected void onCollisionBegin(Entity player, Entity coin){
                coin.removeFromWorld();
            }
        });
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR) {

                @Override       // Collisionhandler, der gør at der er collision mellem player og objekt
                protected void onCollisionBegin(Entity player, Entity door){
                    getGameWorld().setLevelFromMap("BasicGame2.json");
                    getDisplay().showMessageBox("Level Complete!", () -> {
                        System.out.println("Dialog Closed!");   // Når playeren går ind i døren, får du dialog om at du har klaret banen
                    });
                }
            });
    }
    public static void main(String[] args) {
    launch(args);
    }   // Launcher spillet
}
