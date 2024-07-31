package src;

public class PrioritizedNode implements Comparable<PrioritizedNode> {
    private final Coordinates coordinates;
    private final int priority;

    public PrioritizedNode(Coordinates coordinates, int priority) {
        this.coordinates = coordinates;
        this.priority = priority;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PrioritizedNode node = (PrioritizedNode) obj;
        return this.getCoordinates().equals(node.getCoordinates());
    }

    @Override
    public int hashCode() {
        int result = 29;
        result = 31 * result + getCoordinates().getX();
        result = 31 * result + getCoordinates().getY();
        return result;
    }

    @Override
    public int compareTo(PrioritizedNode o) {
        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
