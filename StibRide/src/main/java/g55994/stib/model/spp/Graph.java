package g55994.stib.model.spp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import g55994.stib.model.RepositoryException;

/**
 *
 * @author g55994
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public Graph() throws RepositoryException, IOException {
    }

    public Graph(Graph other) {
        this.nodes = other.nodes;
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Node getNode(int id) {
        return nodes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
