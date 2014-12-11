package lagilabra.miinanlakaisija;

public class Koordinaatti {

    private final int x, y;

    public Koordinaatti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Selvittää koordinaatti-parametrin x-koordinaatin.
     *
     * @param koordinaatti
     * @return Koordinaatin x-koordinaatti.
     */
    public int getRivi(String koordinaatti) {
        String out = koordinaatti.substring(0, koordinaatti.indexOf(','));
        return Integer.parseInt(out);
    }

    /**
     * Selvittää koordinaatti-parametrin y-koordinaatin.
     *
     * @param koordinaatti
     * @return Koordinaatin y-koordinaatti.
     */
    public int getSarake(String koordinaatti) {
        String out = koordinaatti.substring(koordinaatti.indexOf(',') + 1, koordinaatti.length());
        return Integer.parseInt(out);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Koordinaatti other = (Koordinaatti) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    @Override
    public String toString() {
        return getX() + "," + getY();
    }

}
