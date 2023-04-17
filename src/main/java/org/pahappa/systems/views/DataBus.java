package org.pahappa.systems.views;

import javax.faces.context.FacesContext;
import java.util.HashMap;

/**
 * This class is used to store data in the session scope. It is used to pass data between pages.
 * It is an implementation of the singleton pattern, and it is a subclass of the HashMap class.
 * <p>
 * To use it, simply call the getInstance() method to get an instance of the class, and then use the put() and get() methods
 * You add data to the session scope by calling the put() method, and you retrieve data from the session scope by calling the get() method.
 * Simply pass the key and value to the put() method, and pass the key to the get() method .
 * <p>
 *
 * @author katusiimeconrad
 * @version 1.0
 * @see HashMap
 * @see FacesContext
 * @since 1.0
 */
public class DataBus extends HashMap<String, Object> {
    /*
     * The instance of the class. This is the only instance of the class that will be created.
     */
    private static DataBus instance = null;

    /*
     * The constructor is private, so that it cannot be called from outside the class.
     */
    private DataBus() {
    }

    /**
     * This method returns an instance of the class. If an instance of the class has not been created, it creates one.
     *
     * @return An instance of the class.
     */
    public static DataBus getInstance() {
        if (instance == null) {
            instance = new DataBus();
        }
        return instance;
    }

    /**
     * This method returns the session key. (The session key is the session id)
     *
     * @return The session key.
     */
    private String getSessionKey() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
    }

    /**
     * Associates the specified value with the specified key in this map.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    @Override
    public Object put(String key, Object value) {
        return super.put(String.format("%s-%s", getSessionKey(), key), value);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    @Override
    public Object get(Object key) {
        String formattedKey = String.format("%s-%s", getSessionKey(), key.toString());
        Object data = super.get(formattedKey);

        super.remove(formattedKey);

        return data;
    }
}
