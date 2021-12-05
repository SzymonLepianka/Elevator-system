# Elevator-system

1. Możliwe stany windy:

- MOVING_UP
- MOVING_DOWN
- NOT_MOVING

2. Winda może się poruszać z danego piętra do innego.
3. Gdy winda zatrzymuje się na danym piętrze zmienia stan na NOT_MOVING, na jeden krok symulacji.
4. Maksymalna liczba wind: 16. Liczba pięter: 10.
5. Nie przyjmowane są request'y, w których startowe piętro równa się docelowe piętro.
6. Nakładamy, że wzywając windę, podaje się od razu piętro docelowe.

Działanie programu:

1. Po uruchomieniu aplikacji, pierwsze pojawia się okno konfiguracji, w którym użytkownik ustala liczbę wind i algorytm.
2. Po uruchomieniu symulacji pojawiają się 3 okna:
    - Główne okno z parametrami wind oraz przyciskiem do zwiększania kroku symulacji.
    - Okno służące do wysyłania żądań do systemu wind.
    - Okno loggera - aby lepiej móc przeanalizować co się obecnie dzieje. Dane zapisywane są do pliku.

Forma programu: aplikacja desktopowa.

Program napisany w języku Java.

Dostępne algorytmy:

1. First Come First Served
    - Najprostszy algorytm. Do danego żądania wybierana jest najbliższa nieruszająca się i wolna winda. W przypadku, gdy
      nie ma żadnej wolnej windy, dane żądanie czeka w kolejce, aż któraś z wind się zwolni.
2. Owm Primitive Cost Calculation
    - Algorytm opiera się na obliczeniu kosztu (liczby ruchów) każdej windy dla danego żądania i wybraniu windy z
      najmniejszym kosztem. Obliczenie kosztu jest o tyle prymitywne, że algorytm nie uwzględnia potencjalnych
      uproszczeń, tj. jeśli winda jest na piętrze 4 i jedzie na piętro 7, a przyszło żądanie z piętra 5 na piętro 8, to
      koszt jest liczony bez uwzględnienia możliwości wzięcia pasażera "po drodze".

Winda przyjmując request, niezawsze nowe docelowe piętra umieści na końcu swojej kolejki, wg której się porusza. W
przypadku, w którym winda znajdująca się na piętrze 2 i jedzie w górę na piętro 8, a dostanie żądanie z piętra 4 na
piętro 6, po drodze przyjmie i wykona dane żądanie, tj. zatrzyma się kolejno na piętrze 4, 6, 8.

Możliwe usprawnienia programu:

- Poprawienie wyglądu GUI.
- Usprawnienie algorytmu liczenia kosztu - uwzględniania sytuacji, w których możliwe jest wzięcie dodatkowego pasażera
  po drodze.
- Uwzględnianie liczby pasażerów (maksymalny załadunek).
