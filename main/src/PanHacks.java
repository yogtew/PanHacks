import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Game;

public class PanHacks extends Application {

        private Game game;
        private Logger logger;

        @Override
        public void init() throws Exception {
            super.init();
            logger = Logger.getLogger(PanHacks.class.getName());
        }
        @Override
        public void start(Stage primaryStage) throws Exception {
        game = new Game(logger);
        game.init();
        game.setVisible(true);
        game.start();
    }
}
