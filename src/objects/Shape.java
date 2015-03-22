package objects;

import java.util.Enumeration;

/**
 * Created by Max on 22.03.2015.
 */
public class Shape {
    private final String name;
    private final int[][] shape;

    /**
     * @param name  name of shape
     * @param shape shape data
     */
    public Shape(String name, int[][] shape) {
        this.name = name;
        this.shape = shape;
    }

    /**
     * @return name of shape
     */
    public String getName() {
        return name;
    }

    /**
     * Get shape data.
     * Hide the shape implementation. Returns a anonymous Enumerator object.
     *
     * @return enumerated shape data
     */
    public Enumeration getCells() {
        return new Enumeration() {
            private int index = 0;

            public boolean hasMoreElements() {
                return index < shape.length;
            }

            public Object nextElement() {
                return shape[index++];
            }
        };
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return name + " (" + shape.length + " cell" + (shape.length == 1 ? "" : "s") + ")";
    }
}
