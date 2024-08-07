package g55994.stib.model.dto;

import java.util.List;
import g55994.stib.model.dto.Dto;

/**
 *
 * @author g55994
 */
public class StationDto extends Dto<Integer>{
    
    private String name;

    public StationDto(Integer key, String name) {
        super(key);
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
