
## Programowanie Obiektowe (Java)

Projekt działający na Java SE Development Kit 13.0.2 lub nowszej wersji!
<hr>
<p align="center">
<h1>“Light no more”</h1>
</p>
<hr>

**1.	Opis projektu**

Interaktywna gra przenosząca nas do świata spowitego mrokiem – naszym zadaniem jest stworzenie zgranego składu bohaterów który umóżliwi nam przetrwanie i unieszkodliwienie zagrożenia jakim są wrogowie przebywający w pobliskim lochu pośród gór. Po uruchomieniu gry i wyboru opcji **play** zostaniemy przeniesieni do **miasta** z odrobiną złota i trzema podstawowymi postaciami. Znajdziemy w nim lokacje dzięki którym będzie nam łatwiej przetrwać w brutalnym świecie. Są to:
- **Tawerna** – pozwalająca na dołączenie do naszej siedziby innych postaci za opłatą
- **Znachor** – umożliwiający ulecznie postaci zranionych podczas eksploracji lochów za opłatą
- **Kupiec** – u niego będziemy mogli zaopatrzeć się w mikstury oraz sprzedać znalezione skarby
- **Siedziba** – to w niej dokonamy wyboru aktualnego składu i ulepszymy postacie

Po wkroczenia do **lochu** zostaniemy przeniesieni już do właściwej rozgrywki. Poruszając się po nim napotkamy kolejno to pokoje pełne skarbów, wrogów i upragnione dla naszych bohaterów wyjście. Skarby pozwolą zapopatrzeć się nam w złoto, eliksiry i przedmioty które będziemy mogli sprzedać w sklepie. Napotkanie wrogów wiąże się jednak z **walką** – ma ona charakter turowy, możemy w niej stosować podstawowe ataki, specjalne umiejętności i posiadane mikstury. W tym świecie złoto ma dla nas szczególną wartość – zapewnia rozwój i przetrwanie naszych postaci. Nie dajmy jednakże się zwieść bogactwu – mrok potrafi uderzyć z potężną siłą, a nasze postacie mogą już na zawsze zostać pogrzebane w czeluściach lochu. 
W świat ten wprowadzi nas na początku tutorial przedstawiający kolejne to lokacje, przedmioty oraz odpowiednie mechaniki gry pozwalające nawet początkującemu graczowi stawić pierwsze kroki w spektrum **“Light no more”**.


**2.	Interfejs graficzny aplikacji**

Interfejs graficzny jest zmienny – dla naszego projektu będziemy w stanie wyróżnić kilka interfejsów w tym każdy zależny od **aktualnej sceny**, okno gry pozostaje natomiast niezmienne – zmieniamy jedynie jego zawartość. Przykładowe sceny wraz z interfejsami:

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/704387250351243425/798524414580621322/unknown.png" width=400 /></br>
Postacie znajdujące się w poszczególnych stanach (zielony brak lub lekkie otrzymane obrażenia <90%,100%> maxHP, pomarańczowy – ranny <50%, 90%) maxHP, czerowny – stan krytyczny <0,50%) maxHP) znajdują się w wyżej przedstawionych sekcjach (kwadraty). Wybieramy postacie które chcemy uleczyć za przedstawiony pod nimi koszt w złocie.
</p>

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/704387250351243425/798524445517676544/unknown.png" width=400 /></br>
Możliwość kupna użytkowych przedmiotów oraz sprzedaży niepotrzebnych dla nas przedmiotów.
</p>

<p align="center">
  <img src="https://cdn.discordapp.com/attachments/704387250351243425/798524811101339658/unknown.png" width=400 /></br>
Możliwość wyboru przez nas miejsca do któremy chcemy się dostać.
</p>

**3.	Scenariusze użycia**
**Scenariusz: Rozgrywka w “Light no more”**
1.	Uruchomienie gry i wybór interesującego nas miejsca w menu

	Play – uruchomienie gry **->** Load game – wczytanie stanu gry **->** Help – informacje o grze **->** Quit – wyjście z gry

2.	Przejście przez samouczek
3.	(opcjonalnie) Wybór poznanych wcześniej lokacji do zaopatrzenia postaci
4.	Przejście do lochu poprzez kliknięcie “Enter dark” – właściwa rozgrywka
5.	Poruszanie się po lochu
6.	(opcjonalnie) Zebranie skarbów 
7.	(opcjonalnie) Walka z przeciwnikami
8.	Dotarcie do wyjścia i opuszczenie lochu
9.	(opcjonalnie) Ponowne zaopatrzenie się w mieście i dalsza eksploracja lochów
10.	Grę możemy zamknąć klikając na krzyżyk w prawym górnym rogu, wybór “Quit” w menu lub w będąc w mieście klikajac przycisk “Esc” i wybór opcji “Quit”.

**4.	Wymagania dodatkowe**

-	Zabezpieczenia wyjątków obsługi gry (lokacji, złota) 
-	Stworzenie mechaniki reakcji przeciwników na podstawowe sytuacje podczas walki

**5.	Terminarz realizacji projektu**

I. Specyfikacja (3 zajęcia)

II. Prototype - User Interface (5 zajęcia) - gotowy interfejs użytkownika

III. Release Candidate (10 zajęcia) - implementacja minimum połowy funkcjonalności

IV. Final (15 zajęcia) - ukończony projekt spełniający założone wymagania

**6.	Tabela zadań**

	Funkcjonalność

```
1.	GUI

2.	Program znajduje się repozytorium GIT

3.	Używanie Trello* podczas tworzenia projektu 

4.	Używanie GIT podczas tworzenia projektu

5.	Stworzenie grafik w klimacie pixelart

6.	Gra wielojęzyczna

7.	Zmienna (dla niektórych scen), zapętlona muzyka w tle oraz możliwość jej włączenia i wyłączenia

8. Wykonanie:

- Postaci
- Wrogów
- Ataków
- Umiejętności
- Przedmiotów

9. Wykonanie:

- Sklepu (opcja kupna lub sprzedawania przedmiotów)
- Znachora (leczenie postaci z sekcji aktualnego składu)
- Tawerny (Możliwość kupna kolejnych postaci)
- Siedziby (ulepszanie umiejętności/postaci, zmiana squadu oraz przegląd aktualnego stanu)
- Lochu (przechodzimy do eksploracji w poszukiwaniu przedmiotów, złota itp)

10. Techniczne:

- Wybór umiejętności (lub przedmiotu użytkowego) dla danej postaci
- Zaimplementowanie mechaniki damage/heal (per turn)
- Stworzenie mechaniki reakcji przeciwników na podstawowe sytuacje podczas walki
- Implementacja wykluczenia postaci jeśli HP = 0 oraz zabezpieczenia przed overheal  (HP > HPMAX)

11.	Zabezpieczenia wyjątków obsługi gry (lokacji, złota) 

12.	Stworzenie pokazowej mapy ukazującej aspekty lochu (horyzontalne poruszanie się po lochu, interakcja z napotkanymi eventami)

13.	Tutorial wprowadzający

14.	Zapis i odczyt stanu gry (złoto, przedmioty, postacie)

15.	Instalacja za pomocą instalatora w systemie Windows

```
