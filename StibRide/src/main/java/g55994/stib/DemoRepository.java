package g55994.stib;

import java.io.IOException;
import java.util.List;
import g55994.stib.model.ConfigManager;
import g55994.stib.model.RepositoryException;
import g55994.stib.model.dto.LineDto;
import g55994.stib.model.repository.LineRepository;


/**
 *
 * @author Iuliana
 */
public class DemoRepository {

    /**
     * Entry points to the <code> Mentoring </code> application.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) throws RepositoryException {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

//            StopRepository repository = new StopRepository();
//            List<StopDto> dtos = repository.getAll();
//            for (StopDto dto : dtos) {
//                System.out.print("\t" + dto.getKey().getKey());
//                System.out.print("\t" + dto.getKey().getValue());
//                System.out.println("\t" + dto.getId_order());
//                System.out.println("");
//            }

//            StopRepository repository = new StopRepository();
//            StopDto dto = repository.get(new AbstractMap.SimpleEntry<Integer, Integer>(2, 8372));
//            System.out.print("\t" + dto.getKey().getKey());
//            System.out.print("\t" + dto.getKey().getValue());
//            System.out.println("\t" + dto.getId_order());
//            System.out.println("");
            

            LineRepository repository = new LineRepository();
            List<LineDto> dtos = repository.selectAllForStation(8824);
            for (LineDto dto : dtos) {
                System.out.print("\t" + dto.getKey());
                System.out.println("");
            }

//            LineRepository repository = new LineRepository();
//            LineDto dto = repository.get(2);
//            System.out.print("\t" + dto.getKey());
//            System.out.println("");
            

            
//            StationRepository repository = new StationRepository();
//            List<StationDto> dtos = repository.getAll();
//            for (StationLineDto dto : dtos) {
//                System.out.print("\t" + dto.getKey());
//                System.out.println("\t" + dto.getName());
//                System.out.println("");
//            }

//            StationRepository repository = new StationRepository();
//            StationDto dto = repository.get(8032);
//            dto = repository.get(8824);
//            System.out.print("\t" + dto.getKey());
//            System.out.println("\t" + dto.getName());
//            System.out.println("");

//            StationRepository repository1 = new StationRepository();
//            List<StationDto> dtos1 = repository1.getFullStation(8042);
//            for (StationDto dto1 : dtos1) {
//                System.out.print("\t" + dto1.getKey());
//                System.out.println("\t" + dto1.getName());
//                System.out.println("");
//            }
            
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private DemoRepository() {

    }
}
