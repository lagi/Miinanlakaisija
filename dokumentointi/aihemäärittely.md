**Aihe:** Miinanlakaisija. Toteutetaan Miinaharava-peli, jossa etsitään miinoja
pelilaudan antamien vihjeiden perusteella. Miinojen paikat arvotaan laudalle
satunnaisesti. Lauta koostuu ruuduista, joiden määrää voi muuttaa asetuksista. Avatuissa ruuduissa näkyy lukuja sen mukaan, kuinka monta miinaa ruudun
vieressä on. Peli ottaa aikaa kuinka nopeasti pelaaja sen läpäisee. Jos pelaaja klikkaa ruutua, jossa on miina, niin peli loppuu
ja pelaaja voi halutessaan aloittaa uudelleen. Tällöin pelilaudalle arvotaan
uudet miinat ja peli alkaa alusta. 

**Käyttäjä:** Pelaaja

**Toiminnot:**

* Ruudun klikkaus
  - Ruutu avautuu
  - Jos ruudussa on miina, peli loppuu.
    * Pelaaja voi aloittaa pelin alusta

* Pelin ratkaiseminen nopeasti
  - Peli onnittelee pelaajaa nopeasta ajasta.

* Valikon käyttäminen
  - Vaikeustason valitseminen
    * Helppo, keskivaikea ja vaikea, muuttaa pelilaudan kokoa ja miinojen määrää
  - Aloita peli alusta
    * Luo uuden pelilaudan

## Rakennekuvaus

Miinanlakaisijassa luokka Pelilauta on vastuussa pelilaudan luomisesta. Se luo kaksiulotteisen taulukon, jonka indekseihin arvotaan miinat. Tämän jälkeen luokka generoi miinojen ympärille vihjeet. Pelilauta-luokka on myös vastuussa mahdollisten tyhjien ruutujen laskemisesta ja lisäämisestä listaan niiden paljastamista varten.

Peli-luokka aloittaa pelin luomalla pelilaudan ja ottaa aikaa pelin ratkaisemisajan selvittämiseksi.

Koordinaatti-luokka selvittää painikkeen koordinaatin.

MiinanlakaisijaUI piirtää pelilaudan, ottaa vastaan vaikeustasokomennon ja avaa ruutuja pelaajan niitä klikatessa. Luokka myös ilmoittaa pelin voitosta tai häviöstä.
