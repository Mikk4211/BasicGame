package mikkel.sorensen;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import java.util.Map;

//@Author Mikk4211 https://github.com/Mikk4211
//EASJ Datamatiker 2. semester, Basic Game Project

public class BasicGame extends GameApplication{
    /* Dette sætter størrelsen på vinduet, spillet kører i. */
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(15*70);
        gameSettings.setHeight(10*70);
    }

    private Entity player;              // Player variabel
    @Override
    protected void initInput() {                    /* Gør at man kan gå til venstre */
        getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).left();
            }
        }, KeyCode.A);

        /* Gør at man kan gå til højre */
        getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).right();
            }
        }, KeyCode.D);

        /* Gør at man kan hoppe */
        getInput().addAction(new UserAction("Jump") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).jump();
            }
        }, KeyCode.W);
    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("BasicGame.json");   // Mappet fra Tiled

        player = getGameWorld().spawn("player", 10, 10); // Player spawn

        getGameScene().getViewport().setBounds(-1500, 0, 1500, getHeight());          // Definerer grænser for banen. Du kan ikke se under banen.
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2); // "Kamera" der låser til karakteren

        getGameWorld().spawn("enemy", 470, 50);
        getGameWorld().spawn("enemy1", 670, 50);

    }

    /* Collision handler, der gør at der er collision mellem player og objekt */
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin){
                coin.removeFromWorld();
                System.out.println("You have gathered a coin!");                    // Printer i konsol at der er samlet en coin
                getGameState().increment("Coins Gathered",+1);    //adder en coin til UI tæller.
            }
        });

                /* Collisionhandler, der gør at der er collision mellem player og objekt */

            getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR) {
                Entity spawn = new Entity();                        // Laver et spawn entity
                @Override
                protected void onCollisionBegin(Entity player, Entity door){
                    getDisplay().showMessageBox("Level Complete!", () -> {          // Giver dig en messagebox, der siger at du har klaret levellet.
                        getGameWorld().setLevelFromMap("BasicGame2.json");          // Sætter nyt map
                        player.getControl(PhysicsControl.class).reposition(spawn.getPosition());    //Definerer hvor man spawner efter at have gået igennem en dør
                    });
                }
            });

            getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR1) {
                Entity spawn = new Entity();
                @Override
                protected void onCollisionBegin(Entity player, Entity door1){
                    getDisplay().showMessageBox("Level Complete!", () ->{
                        getGameWorld().setLevelFromMap("BasicGame3.json");
                        player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                    });
                }
            });
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(15);//UI på 10X
        textPixels.setTranslateY(15);//UI på 10Y

        getGameScene().addUINode(textPixels); //tilføjer UI til scenen/spillet.
        textPixels.setText("Coins Gathered: ");
        textPixels.textProperty().bind(getGameState().intProperty("Coins Gathered").asString()); //
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("text","Coins Gathered ");
        vars.put("Coins Gathered", 0);
    }
    public static void main(String[] args) {
    launch(args);
    }   // Launcher spillet
}
