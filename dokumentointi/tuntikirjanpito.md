Tuntikirjanpito

29.10.2014, aikaa käytetty noin 1h

Aihemäärittelyn kirjoittaminen, ohjelman perusrungon suunnittelu

3.11.2014, aikaa käytetty noin 2h

Määrittelyvaiheen luokkakaavion piirtäminen ja luokkien luominen projektiin. Ruutu-luokan ja sen testien luonti. Nyt pohdinnassa pelilaudan luominen ja miten se onnistuu järkevästi, tekisinkö taulukon int[][] vai ArrayListin, jossa ruudut itse tietävät koordinaattinsa. Saatan päätyä jälkimmäiseen, sillä se kuulostaa yksinkertaisemmalta ja käytän itse mieluummin ArrayListejä kuin taulukoita.

6.11.2014, aikaa käytetty noin 2h

Pelilaudan luominen valmis, toteutin miinojen arpomisen luomalla pelilaudan kokoisen kokonaislukulistan, jonka sekoitin Collections.shuffle-metodilla. Näin saan satunnaisia lukuja ilman toistoa. Tein myös melko kattavat testit Ruutu- ja Pelilauta-luokille. Molempiin luokkiin tulee lisää tavaraa, mutta tähänastiset metodit on melko hyvin testattu. Yksi testi ei toimi enkä ole ihan varma miksi, mutta vika on varmaankin testissä itsessään, voisin tutkia sitä vaikka maanantain pajassa. Jotkut testit ovat ehkä hieman hassuja, kuten konstruktoriTekeePelilaudanOikein(), mutta hion niitä tulevaisuudessa. Testaa kuitenkin jokaista metodia, joten alkuasetelmat ovat ihan hyvät. Pit-testauksessa oli jotain ongelmaa, en siis välttämättä saa sitä tämän viikon deadlineen :( Yritän selvittää ongelmaa ircin välityksellä, se kun näytti olevan melko erikoislaatuinen ongelma.

7.11.2014, aikaa käytetty noin 30min

Pit-raportin generointi onnistui, pom.xml-tiedostosta piti muuttaa pari riviä. Päätin ohjaajan avustuksella kuitenkin tehdä pelilaudan 2-ulotteisella Arraylla, jonka teen ensi viikolla. Harmillisesti tuli tehtyä vähän turhaa työtä ArrayListin kanssa, mutta ainakin logiikka on minulla jo tiedossa, joten muutettavaa on vain itse taulukot ja niiden alustaminen.

11.11.2014, aikaa käytetty noin 1,5h

Pelilaudan luominen meni täysin uusiksi. Luovuin ArrayListin käytöstä ja toteutin laudan kaksiulotteisena taulukkona. Konstruktori on hieman sotkuinen, mutta ehkä voin senkin koodia hieman hioa. Kaikki toiminnot ovat kuitenkin melko suoraviivaisia. Ruutu-luokka muuttui nyt turhaksi, sillä uudessa toteutuksessa ruudut ovat vain Pelilauta-taulukon arvoja, -1 = miina, 0 = ei vierekkäisiä miinoja, 1 = yksi vierekkäinen miina jne. Ensi kerralla (todennäköisesti huomenna) toteutan ruutujen arvojen asettamisen edellä mainitun mukaisesti. Luovuin asetaMiinat()-metodissa satunnaislukulistan käytöstä, sillä se aiheutti ArrayIndexOutOfBounds-ongelmia. Nyt toteutettu ratkaisu on mielestäni paljon siistimpi ja kompaktimpi. Pelilauta alkaa kohta olla valmis ja voinkin alkaa jo miettiä itse pelin toteutusta ja graafista ulkoasua.
