package g55994.stib.utils;

/**
 * A class can implement the {@code Observer} interface when it wants to be
 * informed of changes in observable objects.
 *
 * @author jlc
 */
public interface Observer {

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's {@code notifyObservers}
     * method to have all the object's observers notified of the change.
     *
     * @param observable the observable object.
     */
    void update(Observable observable);
}
