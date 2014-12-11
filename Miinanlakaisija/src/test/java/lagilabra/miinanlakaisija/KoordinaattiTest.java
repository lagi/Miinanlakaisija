package lagilabra.miinanlakaisija;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkojo
 */
public class KoordinaattiTest {

    private Koordinaatti koordinaatti;

    @Before
    public void setUp() {
        koordinaatti = new Koordinaatti(1, 2);
    }
    
    @Test
    public void getRiviToimiiOikein() {
        assertEquals(1, koordinaatti.getRivi(koordinaatti.toString()));
    }
    
    @Test
    public void getSarakeToimiiOikein() {
        assertEquals(2, koordinaatti.getSarake(koordinaatti.toString()));
    }

}
