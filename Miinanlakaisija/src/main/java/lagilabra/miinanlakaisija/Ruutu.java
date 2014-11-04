
package lagilabra.miinanlakaisija;

public class Ruutu {
    
    private boolean ruudussaMiina;
    private int x;
    private int y;
    
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
        this.ruudussaMiina = false;
    }
    
    public void setMiina() {
        this.ruudussaMiina = true;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public boolean ruudussaMiina() {
        return this.ruudussaMiina;
    }
    
}
