# Tuntikirjanpito

## 29.10.2014, aikaa käytetty noin 1h

Aihemäärittelyn kirjoittaminen, ohjelman perusrungon suunnittelu

## 3.11.2014, aikaa käytetty noin 2h

Määrittelyvaiheen luokkakaavion piirtäminen ja luokkien luominen projektiin. Ruutu-luokan ja sen testien luonti. Nyt pohdinnassa pelilaudan luominen ja miten se onnistuu järkevästi, tekisinkö taulukon int[][] vai ArrayListin, jossa ruudut itse tietävät koordinaattinsa. Saatan päätyä jälkimmäiseen, sillä se kuulostaa yksinkertaisemmalta ja käytän itse mieluummin ArrayListejä kuin taulukoita.

## 6.11.2014, aikaa käytetty noin 2h

Pelilaudan luominen valmis, toteutin miinojen arpomisen luomalla pelilaudan kokoisen kokonaislukulistan, jonka sekoitin Collections.shuffle-metodilla. Näin saan satunnaisia lukuja ilman toistoa. Tein myös melko kattavat testit Ruutu- ja Pelilauta-luokille. Molempiin luokkiin tulee lisää tavaraa, mutta tähänastiset metodit on melko hyvin testattu. Yksi testi ei toimi enkä ole ihan varma miksi, mutta vika on varmaankin testissä itsessään, voisin tutkia sitä vaikka maanantain pajassa. Jotkut testit ovat ehkä hieman hassuja, kuten konstruktoriTekeePelilaudanOikein(), mutta hion niitä tulevaisuudessa. Testaa kuitenkin jokaista metodia, joten alkuasetelmat ovat ihan hyvät. Pit-testauksessa oli jotain ongelmaa, en siis välttämättä saa sitä tämän viikon deadlineen :( Yritän selvittää ongelmaa ircin välityksellä, se kun näytti olevan melko erikoislaatuinen ongelma.

## 7.11.2014, aikaa käytetty noin 30min

Pit-raportin generointi onnistui, pom.xml-tiedostosta piti muuttaa pari riviä. Päätin ohjaajan avustuksella kuitenkin tehdä pelilaudan 2-ulotteisella Arraylla, jonka teen ensi viikolla. Harmillisesti tuli tehtyä vähän turhaa työtä ArrayListin kanssa, mutta ainakin logiikka on minulla jo tiedossa, joten muutettavaa on vain itse taulukot ja niiden alustaminen.

## 11.11.2014, aikaa käytetty noin 1,5h

Pelilaudan luominen meni täysin uusiksi. Luovuin ArrayListin käytöstä ja toteutin laudan kaksiulotteisena taulukkona. Konstruktori on hieman sotkuinen, mutta ehkä voin senkin koodia hieman hioa. Kaikki toiminnot ovat kuitenkin melko suoraviivaisia. Ruutu-luokka muuttui nyt turhaksi, sillä uudessa toteutuksessa ruudut ovat vain Pelilauta-taulukon arvoja, -1 = miina, 0 = ei vierekkäisiä miinoja, 1 = yksi vierekkäinen miina jne. Ensi kerralla (todennäköisesti huomenna) toteutan ruutujen arvojen asettamisen edellä mainitun mukaisesti. Luovuin asetaMiinat()-metodissa satunnaislukulistan käytöstä, sillä se aiheutti ArrayIndexOutOfBounds-ongelmia. Nyt toteutettu ratkaisu on mielestäni paljon siistimpi ja kompaktimpi. Pelilauta alkaa kohta olla valmis ja voinkin alkaa jo miettiä itse pelin toteutusta ja graafista ulkoasua.

## 13.11.2014, aikaa käytetty noin 2,5h

Arvojen asettaminen ruuduille osoittautuikin astetta hankalammaksi tehtäväksi. Ongelmana oli reunoissa olevien ruutujen arvojen asettaminen, sillä jos miina on pelilaudan reunassa, niin on varottava ArrayIndexOutOBoundsExceptionia, jonka onnistuin pitkän taistelun jälkeen kiertämään try-catchilla. Kokeilin useita eri vaihtoehtoja ja sain pelilaudan alustuksen toimimaan oikein. Mietin, että olisiko ollut fiksua tehdä asetaMiinat()-metodissa luoduista miinoista lista, jotta ne löytäisi helpommin, kun nyt miinat pitää etsiä joka kerta uudestaan pelilaudalta. Etsiminen ei toisaalta vie kovin kauaa, sillä lauta on maksimissaan 24x24-kokoinen. Suuremmissa tapauksissa olisi varmasti fiksumpaa tehdä lista miinojen koordinaateista, mutta tähän tarkoitukseen miinojen etsiminen on riittävä. Pelilaudan ruutujen arvoille pitää vielä kehittää mahdollisimman kattava testi, sillä nykyinen testi käsittelee vain äärimmäisiä arvoja, eli kulmia. Sen tekeminen onnistuu varmaan melko helposti muutamalla silmukalla.

## 14.11.2014, aikaa käytetty noin 4h

Huomasin pienen ongelman alustaLauta()-metodissa. Jos miinan sisältävän ruudun vieressä oli miinan sisältävä ruutu, niin tällöin miinan sisältävän ruudun arvoa kasvatettiin yhdellä, mikä ei tietenkään ole suotavaa. Sain ongelman korjattua ja tein tapaukselle testin. Heitin myös try-catchin menemään ja tein oman metodin, joka tarkistaa ovatko annetut koordinaatit laudalla. Lisäsin checkstylen projektiini ja tutkin jo hieman grafiikan toteuttamista. Siitä kuitenkin lisää varmasti ensi viikolla. Luokkakaaviota muokkasin myös hieman, se ei tosin ole kauhean mielenkiintoisen näköinen. Sekvenssikaaviot varmasti kertovat ohjelman toiminnasta paljon enemmän.

## 21.11.2014, aikaa käytetty noin 2,5h

Graafisen käyttöliittymän koodaaminen aloitettu. Grafiikan koodaaminen oli minulle aluksi hieman hidasta, sillä ainoat guit olen tehnyt ohjelmoinnin jatkokurssilla kaksi vuotta sitten. Myös ohjelman lopullinen runko alkaa muototua: lisäsin luokat MiinanlakaisijaUI ja Sovellus, joista jälkimmäinen on pääohjelma. Tein myös testit Peli-luokalle ja rivikattavuus onkin jo 100%, joskin testien laatua voi vielä hieman hioa.

## 28.11.2014, aikaa käytetty noin 3h

Graafinen käyttöliittymä edistyy. Pelilauta alkaa olla valmis, paitsi jostain syystä 8x8-laudalle ilmestyy 20 miinaa 10 sijasta, bugin syytä tutkitaan. Myös pieniä visible-ongelmia pelilaudassa. Logiikka vielä vähän kesken ja vaikeusasteen valitseminen puuttuu.

## 4.12.2014, aikaa käytetty noin 4h

Peli alkaa olla valmis. Lisätty vaikeusasteen valinta ja pelin häviäminen miinaa klikattaessa. Lisätty myös mahdollisuus myös aloittaa peli uudelleen. Vielä puuttuvat pelin voittaminen ja nolla-arvoista ruutua painettaessa viereisten ruutujen rekursiivinen paljastaminen, kunnes päädytään ei-nolla-arvoiseen ruutuun.

## 5.12.2014, aikaa käytetty noin 5h

Ruutujen rekursiivinen paljastaminen toimii jo lähes oikein. Ajanpuutteen vuoksi en ehtinyt saada peliä täysin valmiiksi tällä viikolla, mutta se on jo hyvässä demokunnossa. MiinanlakaisijaUI-luokka on nyt tupaten täynnä logiikkaa, ensi viikolla vuorossa vielä sen siistiminen ja logiikan erottaminen omaksi luokakseen.

## 11.12.2014, aikaa käytetty noin 3h

Ruutujen paljastaminen toimii ja pelin on lähes valmis. Lisäsin ratkaisun ajanoton, jonka alkuajankohta on vielä asetettava alkamaan ensimmäisen ruudun klikkauksesta. On myös vielä toteutettava ominaisuus, että ensimmäinen klikattu ruutu ei voi olla miina. Jos ehdin, niin parannan valikoita ja lisään toiminnon, jolla voidaan aloittaa uusi peli F2-näppäimestä. Testejä hieman lisätty. Kaikkea Pelilauta-luokan metodeja en oikein osannut testata, sillä ne menevät jo vähän grafiikan puolelle.

## 12.12.2014, aikaa käytetty noin 2h

Ohjelma on täysin toimintakunnossa lukuunottamatta graafista bugia, joka uskoakseni kuitenkin johtuu vain laitoksen koneiden Javan puutteellisuudesta. Javadoc kokonaisuudessaan luotu ja muu dokumentointi viimeistelty.
