package g55994.stib.model;

/**
 *
 * @author Iuliana
 */
public enum ErrorType {
    SEARCH_NO_INPUT("Select the source and the destination."), 
    ADD_MISSING_NAME("Enter a name for the favorite."), 
    ADD_MISSING_STATIONS("Select the source and the destination."),
    FAVORITE_NAME_EXISTING("The name already exists in your favorites."),
    FAVORITE_PATH_EXISTING("The path already exists in your favorites."),
    NO_ERROR("Done."),
    DELETE_MISSING_INPUT("The name of the favorite is not valid"),
    FAVORITE_STATIONS_NOT_EXIST("There is favorite with this stations to update");
 
    private String message;
 
    ErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
    
    
}
