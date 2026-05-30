package game.utilities.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Used for getting the keyboard inputs
 */
public class KeyHandler implements KeyListener {
    private volatile boolean upPressed, downPressed, leftPressed, rightPressed;

    private volatile boolean escapePressed;

    /**
     * Default constructor
     */
    public KeyHandler() {
        this.reset();
    }

    /**
     * Used for resetting Keyboard keys pressed states
     */
    public void reset() {
        this.upPressed = false;
        this.downPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;

        this.escapePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = false;
                break;
        }
    }

    /**
     * Used for checking if the W or UP key is pressed
     * @return returns a true boolean value if atleast one of the mentioned keys is held down
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Used for checking if the S or DOWN key is pressed
     * @return returns a true boolean value if atleast one of the mentioned keys is held down
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Used for checking if the A or LEFT key is pressed
     * @return returns a true boolean value if atleast one of the mentioned keys is held down
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Used for checking if the D or RIGHT key is pressed
     * @return returns a true boolean value if atleast one of the mentioned keys is held down
     */
    public boolean isRightPressed() {
        return rightPressed;
    }


    /**
     * Used for checking if a Keyboard ESC press happened, after an escape key press it will hold the boolean value &bdquo;true&ldquo; until it is called and the true value is returned
     * it consumes the value after calling
     * @return returns a true boolean value if an ESC Keyboard Key press happened
     */
    public boolean hasEscapePressed() {
        boolean result = escapePressed;
        this.escapePressed = false;
        return result;
    }
}