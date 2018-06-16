package prison.util;

/**
 * Provides some utilities for manipulating text.
 */
public class Text {

    private Text() {
    }

    public static String color(String original) {
        char[] b = original.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == '&'
                    && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = '\u00A7';
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

}
