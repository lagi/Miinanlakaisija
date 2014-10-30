**Aihe:** Miinanlakaisija. Toteutetaan Miinaharava-peli, jossa etsitään miinoja
pelilaudan antamien vihjeiden perusteella. Miinojen paikat arvotaan laudalle
satunnaisesti. Lauta koostuu ruuduista, joiden määrää voi muuttaa asetuksista.
Avatuissa ruuduissa näkyy lukuja sen mukaan, kuinka monta miinaa ruudun
vieressä on. Peli ottaa aikaa kuinka nopeasti pelaaja sen läpäisee ja
kirjoittaa tulokseen Highscore-listalle, jos pelaajan aika oli nopeampi kuin
aikaisemmat ajat. Jos pelaaja klikkaa ruutua, jossa on miina, niin peli loppuu
ja pelaaja voi halutessaan aloittaa uudelleen. Tällöin pelilaudalle arvotaan
uudet miinat ja peli alkaa alusta. 

**Käyttäjä:** Pelaaja

**Toiminnot:**

* Ruudun klikkaus
  - Ruutu avautuu
  - Jos ruudussa on miina, peli loppuu ja paljastaa kaikki ruudut näkyviin
    * Pelaaja voi aloittaa pelin alusta

* Pelin ratkaiseminen nopeasti
  - Peli onnittelee pelaajaa nopeasta ajasta ja kysyy pelaajan nimeä. Tämän jälkeen peli                 kirjoittaa pelaajan nimen ja ajan highscore-listaan ja näyttää sen pelaajalle.

* Valikon käyttäminen
  - Vaikeustason valitseminen
    * Helppo, keskivaikea ja vaikea, muuttaa pelilaudan kokoa ja miinojen määrää
  - Näytä highscore
    * Avaa highscore-listan
  - Aloita peli alusta
    * Luo uuden pelilaudan
