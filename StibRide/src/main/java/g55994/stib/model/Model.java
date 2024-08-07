package g55994.stib.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import g55994.stib.model.dto.FavoriteDto;
import g55994.stib.model.dto.LineDto;
import g55994.stib.model.dto.StationDto;
import g55994.stib.model.repository.FavoriteRepository;
import g55994.stib.model.repository.LineRepository;
import g55994.stib.model.repository.StationRepository;
import g55994.stib.model.spp.Graph;
import g55994.stib.model.spp.Node;
import g55994.stib.model.spp.ShortestPath;
import g55994.stib.utils.Observable;

/**
 *
 * @author g55994
 */
public class Model extends Observable {

    private Graph graph;
    private final FavoriteRepository favoriteRepository;
    private final StationRepository stationRepository;
    private ErrorType error;

    private ObservableList<StationDto> stationsList = FXCollections.observableArrayList();
    private List<Node> path = new LinkedList<>();
    private final ObservableList<FavoriteDto> allFavorites = FXCollections.observableArrayList();

    public Model() throws RepositoryException, IOException {
        ConfigManager.getInstance().load();
        favoriteRepository = new FavoriteRepository();
        stationRepository = new StationRepository();
        createOriginalGraph();
        stationsList.addAll( stationRepository.getAll());
        favoriteRepository.getAll().forEach(dto -> allFavorites.add(dto));
        error = ErrorType.NO_ERROR;
    }

    public void createOriginalGraph() throws RepositoryException, IOException {
        graph = new Graph();
        List<StationDto> all = stationRepository.getAll();
        for (StationDto dto : all) {
            List<LineDto> lines = (new LineRepository()).selectAllForStation(dto.getKey());
            List<Integer> lineId = new ArrayList<>();
            lines.forEach(e -> lineId.add(e.getKey()));
            Node node = new Node(dto.getName(), dto.getKey(), lineId);
            graph.addNode(node);
        }
        for (Node node : graph.getNodes()) {
            var adjacents = stationRepository.getFullStation(node.getId());
            for (var next : adjacents) {
                node.addDestination(graph.getNode(next.getKey()));
            }
        }
    }

    public ObservableList<StationDto> getStationsList() {
        return stationsList;
    }

    public ObservableList<FavoriteDto> getAllFavorites() {
        return allFavorites;
    }

    public FavoriteDto getFavoriteByName(String name) {

        var favorite = allFavorites.stream().filter(f -> f.getKey().equals(name)).findAny().orElse(null);
        return favorite;
    }

    public void searchPath(StationDto source, StationDto dest) throws RepositoryException, IOException {
        if (source == null || dest == null) {
            error = ErrorType.SEARCH_NO_INPUT;
            notifyObservers();
        } else {
            error=ErrorType.NO_ERROR;
            calculatePathFromSourceToDestination(source.getKey(), dest.getKey());
            notifyObservers();
            
        }
    }

    public ErrorType getError() {
        return error;
    }

    private void calculatePathFromSourceToDestination(int idSource, int idDest) throws RepositoryException, IOException {
        path.clear();
        createOriginalGraph();
        graph = ShortestPath.calculateShortestPathFromSource(graph, graph.getNode(idSource));
        Node dest = graph.getNode(idDest);
        path.addAll(dest.getShortestPath());
        path.add(dest);
    }

    public List<Node> getPath() {
        return path;
    }

    public Set<Node> getNodes() throws RepositoryException, IOException {
        return graph.getNodes();
    }

    public void addFavorite(String name, StationDto origine, StationDto destination) throws RepositoryException {
        error=ErrorType.NO_ERROR;
        if(name==null || name.isEmpty())
            error=ErrorType.ADD_MISSING_NAME;
        if(origine==null || destination==null)
            error=ErrorType.ADD_MISSING_STATIONS;
        var favoriteByName = favoriteRepository.get(name);
        if(favoriteByName!=null)
            error=ErrorType.FAVORITE_NAME_EXISTING;
        var favoriteByStations = favoriteRepository.getByStations(origine.getKey(), destination.getKey());
        if(favoriteByStations != null)
            error=ErrorType.FAVORITE_PATH_EXISTING;
        if(error==ErrorType.NO_ERROR){
            var newFavorite = new FavoriteDto(name,origine,destination);
            favoriteRepository.add(newFavorite);
            allFavorites.clear();
            allFavorites.addAll(favoriteRepository.getAll());  
        }
        notifyObservers();
    }
    
    public void updateFavorite(String newName, StationDto origine, StationDto destination) throws RepositoryException {
        error=ErrorType.NO_ERROR;
        if(newName==null || newName.isEmpty())
            error=ErrorType.ADD_MISSING_NAME;
        
        if(origine==null || destination==null)
            error=ErrorType.ADD_MISSING_STATIONS;
        
        var favoriteByName = favoriteRepository.get(newName);
        if(favoriteByName!=null)
            error=ErrorType.FAVORITE_NAME_EXISTING;
        
        var favoriteByStations = favoriteRepository.getByStations(origine.getKey(), destination.getKey());
        if(favoriteByStations == null)
            error=ErrorType.FAVORITE_STATIONS_NOT_EXIST;
        
        if(error==ErrorType.NO_ERROR){
            var newFavorite = new FavoriteDto(newName,origine,destination);
            favoriteRepository.updateName(newFavorite);
            allFavorites.clear();
            allFavorites.addAll(favoriteRepository.getAll());
        }
        notifyObservers();
    }
    

    public void removeFavorite(String name) throws RepositoryException {
        if(name==null||name.isEmpty()){
            error=ErrorType.DELETE_MISSING_INPUT;
        }else{
            error=ErrorType.NO_ERROR;
            favoriteRepository.remove(name);
            allFavorites.clear();
            allFavorites.addAll(favoriteRepository.getAll());
        }
        notifyObservers();
    }

}
