/* @Author Mikk4211 https://github.com/Mikk4211 */
/* EASJ Datamatiker 2. semester, Basic Game Project */

package mikkel.sorensen;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsControl;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;



public class BasicGame extends GameApplication{
    /* Dette sætter størrelsen på vinduet, spillet kører i. */
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(15*70);
        gameSettings.setHeight(10*70);
        gameSettings.setTitle("Basic Game ");
    }

    private Entity player;// Player variabel
    private Entity spawn;
    @Override
    protected void initInput() {
        /* Gør at man kan gå til venstre */
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
        /* Gør at man kan stoppe karakteren på stedet */
        getInput().addAction(new UserAction("Stop") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).stop();
            }
        }, KeyCode.S);
         /* Gør at man kan respawne on-demand, hvis man f.eks. ender ude for mappet. */
        getInput().addAction(new UserAction("Respawn") {
            Entity spawn = new Entity();
            @Override
            protected void onAction() {
                player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
            }
        }, KeyCode.R);
    }



    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("BasicGame.json");   // Starter spillet på level 1.

        player = getGameWorld().spawn("player", 10, 10); // Player spawn

        getGameScene().getViewport().setBounds(-1500, 0, 1500, getHeight());          // Definerer grænser for banen. Du kan ikke se under banen.
        getGameScene().getViewport().bindToEntity(player, getWidth() / 2, getHeight() / 2); // "Kamera" der låser til karakteren
        getGameWorld().spawn("enemy", 470, 50);
        getGameWorld().spawn("enemy1", 670, 50);

    }


    /* Collision handler, der gør at der er collision mellem player og objekt */
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.ENEMY) {
            Entity spawn = new Entity();
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy){
                getDisplay().showMessageBox("You have died!", () -> {
                    player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                    System.out.println("You hit an enemy, and have died!");
                });
            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin){
                coin.removeFromWorld();
                System.out.println("You have gathered a coin!");                    // Printer i konsol at der er samlet en coin
                getGameState().increment("Coins Gathered",+1);    //adder en coin til UI tæller.
            }
        });




                /* Collisionhandler, der gør at der er collision mellem player og objekt */
            /* level 1 - 2 */
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR) {
                Entity spawn = new Entity();                        // Laver et spawn entity
                @Override
                protected void onCollisionBegin(Entity player, Entity door){
                    getDisplay().showMessageBox("Level 1 Complete!", () -> {          // Giver dig en messagebox, der siger at du har klaret levellet.
                        getGameWorld().setLevelFromMap("BasicGame2.json");          // Sætter nyt map
                        player.getControl(PhysicsControl.class).reposition(spawn.getPosition()); //Definerer hvor man spawner efter at have gået igennem en dør
                        getGameWorld().spawn("enemy", 320, 50);
                        getGameWorld().spawn("enemy", 550, 50);
                    });
                }
            });

            /* level 2 - 3 */
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR1) {
                Entity spawn = new Entity();
                @Override
                protected void onCollisionBegin(Entity player, Entity door1){
                    getDisplay().showMessageBox("Level 2 Complete!", () ->{
                        getGameWorld().setLevelFromMap("BasicGame3.json");
                        player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                        getGameWorld().spawn("enemy", 300, 50);
                        getGameWorld().spawn("enemy1", 400, 50);
                        getGameWorld().spawn("enemy", 620, 50);
                        getGameWorld().spawn("enemy1", 870, 50);
                    });
                }
            });

            /* level 3 - 4 */
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR2) {
            Entity spawn = new Entity();
            @Override
            protected void onCollisionBegin(Entity player, Entity door2){
                getDisplay().showMessageBox("Level 3 Complete!", () ->{
                    getGameWorld().setLevelFromMap("BasicGame4.json");
                    player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                    getGameWorld().spawn("enemy", 570, 50);
                    getGameWorld().spawn("enemy1", 100, 50);
                    getGameWorld().spawn("enemy", 700, 50);
                    getGameWorld().spawn("enemy1", 900, 200);

                });
            }
        });
        /* level 4 - 5 */
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR3) {
            Entity spawn = new Entity();
            @Override
            protected void onCollisionBegin(Entity player, Entity door3){
                getDisplay().showMessageBox("Level 4 Complete!", () ->{
                    getGameWorld().setLevelFromMap("BasicGame5.json");
                    player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                    getGameWorld().spawn("enemy", 600, 50);
                    getGameWorld().spawn("enemy", 100, 50);
                    getGameWorld().spawn("enemy1", 820, 50);
                    getGameWorld().spawn("enemy", 420, 400);
                    getGameWorld().spawn("enemy1", 800, 520);
                });
            }
        });

        /* level 5 - 6 */
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR4) {
            Entity spawn = new Entity();
            @Override
            protected void onCollisionBegin(Entity player, Entity door4){
                getDisplay().showMessageBox("Level 5 Complete!", () ->{
                    getGameWorld().setLevelFromMap("BasicGame6.json");
                    player.getControl(PhysicsControl.class).reposition(spawn.getPosition());
                    getGameWorld().spawn("enemy1", 700, 400);
                    getGameWorld().spawn("enemy", 920, 260);
                    getGameWorld().spawn("enemy1", 518, 409);
                    getGameWorld().spawn("enemy", 200, 470);
                    getGameWorld().spawn("enemy1", 660, 124);
                });
            }
        });

        /* Finish */
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BasicGameType.PLAYER, BasicGameType.DOOR5) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door5) {
                getDisplay().showMessageBox("You have finished Basic Game, well done!");
            }
        });
    }

    @Override
    protected void initUI() {
       int coinsGathered = 0;
        Text textPixels = new Text();
        textPixels.setTranslateX(15);   //UI på 15X
        textPixels.setTranslateY(15);   //UI på 15Y

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
