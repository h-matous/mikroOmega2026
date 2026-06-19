package game.utilities.input;

import game.utilities.Vector2i;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Used for getting the mouse inputs
 */
public class MouseHandler implements MouseListener, MouseMotionListener {
    private volatile boolean hasMouseLeftClicked, hasMouseRightClicked, hasMouseWheelClicked;
    private volatile int mouseClickCount;

    private final Vector2i mousePosition;

    /**
     * Default constructor
     */
    public MouseHandler() {
        this.mousePosition = new Vector2i();
    }

    /**
     * Used for resetting Mouse values
     */
    public void reset() {
        this.hasMouseLeftClicked = false;
        this.hasMouseRightClicked = false;
        this.hasMouseWheelClicked = false;

        this.mouseClickCount = 0;

        //this.mousePosition.setBoth(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();

        mouseClickCount = e.getClickCount();

        switch (button) {
            case MouseEvent.BUTTON1:
                hasMouseLeftClicked = true;
                break;
            case MouseEvent.BUTTON3:
                hasMouseRightClicked = true;
                break;
            case MouseEvent.BUTTON2:
                hasMouseWheelClicked = true;
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.setBoth(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setBoth(e.getX(), e.getY());
    }

    /**
     * Used for checking if a mouse left click happened, after a mouse left click it will hold the boolean value &bdquo;true&ldquo; until it is called and the true value is returned
     * @return returns a true boolean value if a mouse left click happened
     */
    public boolean hasMouseLeftClicked() {
        boolean returnValue = hasMouseLeftClicked;
        hasMouseLeftClicked = false;

        return returnValue;
    }

    /**
     * Used for checking if a mouse right click happened, after a mouse right click it will hold the boolean value &bdquo;true&ldquo; until it is called and the true value is returned
     * @return returns a true boolean value if a mouse right click happened
     */
    public boolean hasMouseRightClicked() {
        boolean returnValue = hasMouseRightClicked;
        hasMouseRightClicked = false;

        return returnValue;
    }

    /**
     * Used for checking if a mouse wheel click happened, after a mouse wheel click it will hold the boolean value &bdquo;true&ldquo; until it is called and the true value is returned
     * @return returns a true boolean value if a mouse wheel click happened
     */
    public boolean hasMouseWheelClicked() {
        boolean returnValue = hasMouseWheelClicked;
        hasMouseWheelClicked = false;

        return returnValue;
    }

    /**
     * Used for getting the amount of mouse clicks in a row, works for every mouse button
     * @return returns an int representing the click count
     */
    public int getMouseClickCount() {
        return mouseClickCount;
    }

    /**
     * Used for getting the current mouse position
     * @return returns an integer vector representing the position of the mouse
     */
    public Vector2i getMousePosition() {
        return mousePosition;
    }
}