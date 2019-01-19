package input;

import events.EventsCenter;
import events.KeyPressedEvent;
import events.KeyReleasedEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
detects which keys are pressed, and post events to event class
 */
public class GameKeyListener implements KeyListener {
   private static GameKeyListener singleton;

   private GameKeyListener() {
       //
   }

   private static GameKeyListener getSingleton() {
       if (singleton == null) {
            singleton = new GameKeyListener();
            EventsCenter.getSingleton().registerHandler(singleton);
       }
       return singleton;
   }

    @Override
    public void keyTyped(KeyEvent e) {
        //TODO: Does this need to do anything?
    }

    @Override
    public void keyPressed(KeyEvent e) {
        EventsCenter.getSingleton().post(new KeyPressedEvent(e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        EventsCenter.getSingleton().post(new KeyReleasedEvent(e));
    }
}
