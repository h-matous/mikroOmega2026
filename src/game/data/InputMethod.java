package game.data;

/**
 * The InputMethod Enum represents a InputMethod that will be used during the gameplay
 */
public enum InputMethod {
    KEYBOARD("Keyboard Input"),
    MOUSE("Mouse Input");

    private final String name;

    /**
     * Constructor sets its name
     * @param name input method name as a String
     */
    InputMethod(String name) {
        this.name = name;
    }

    /**
     * Used for getting the name of the InputMethod
     * @return returns the input method name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Used for getting all the names of the InputMethod values
     * @return returns the names as a String array
     */
    public static String[] getValueNames() {
        String[] names = new String[values().length];

        for (int i = 0; i < names.length; i++) {
            names[i] = values()[i].getName();
        }

        return names;
    }
}