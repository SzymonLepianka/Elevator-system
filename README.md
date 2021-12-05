# Elevator-system

1. Możliwe stany windy:
- MOVING_UP
- MOVING_DOWN
- NOT_MOVING 
2. Winda może się poruszać z danego piętra do innego.
3. Gdy winda zatrzymuje się na danym piętrze zmienia stan na NOT_MOVING, na jeden krok symulacji.
4. Maksymalna liczba wind: 16. Liczba pięter: 10.

Działanie programu:
1. Pierwsze pojawia się okno konfiguracji, w którym użytkownik ustala liczbę wind.
2. Po uruchomieniu symulacji pojawiają się 3 okna:
   - Główne okno z parametrami wind oraz przyciskiem do zwiększania kroku symulacji.
   - Okno służące do wysyłania żądań do systemu wind.
   - Okno loggera - aby lepiej móc przeanalizować co się obecnie dzieje. Dane zapisywane są do pliku.

Forma programu: aplikacja desktopowa.

Program napisany w języku Java.

Opis działania algorytmu:

