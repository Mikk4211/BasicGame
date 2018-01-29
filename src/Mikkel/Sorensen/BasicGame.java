package Mikkel.Sorensen;
//@Author Mikk4211 https://github.com/Mikk4211
// EASJ Datamatiker 2. semester, Basic Game Project

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;

public class BasicGame extends GameApplication{
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(15*70);
        gameSettings.setHeight(10*70);

    }

    @Override
    protected void initGame() {
        getGameWorld().setLevelFromMap("BasicGame.json");
    }

    public static void main(String[] args) {
    launch(args);
    }

}
