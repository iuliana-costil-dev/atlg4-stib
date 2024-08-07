package g55994.stib.model.dto;

import java.util.AbstractMap;
import java.util.Map;

/**
 *
 * @author Iuliana
 */
public class StopDto extends Dto<Map.Entry<Integer,Integer>>{
    
    private int id_order;
    
    public StopDto(Integer id_line, Integer id_station, int id_order) {
        super(new AbstractMap.SimpleEntry<Integer, Integer>(id_line, id_station));
        this.id_order = id_order;
    }

    public int getId_order() {
        return id_order;
    }

    
}