# Arytmetyka komputerowa

## 📘 Opis projektu

Projekt stanowi rozwiązanie zadań laboratoryjnych z tematu **Arytmetyka
komputerowa**, realizowanych w ramach kursu **Metody obliczeniowe i symulacje**.
Celem ćwiczenia jest praktyczne zbadanie ograniczeń reprezentacji liczb
zmiennoprzecinkowych w komputerach oraz wpływu błędów numerycznych na stabilność
obliczeń.

---

## ⚠️ Źródło zadań

Zadania, do których odnoszą się rozwiązania w tym repozytorium, pochodzą z
materiałów dydaktycznych których autorem jest dr. Włodzimierz Funika
udostępnionych w ramach kursu _Metody obliczeniowe i symulacja_
[WSZiB](https://www.wszib.edu.pl/).

Oryginalna treść zadań dostępna jest pod adresem:
[https://artemis.wszib.edu.pl/~funika/mois/lab1/](https://artemis.wszib.edu.pl/~funika/mois/lab1/)

Autor niniejszego repozytorium **nie jest autorem zadań**, a jedynie autorem
**rozwiązań i opracowania kodu źródłowego**.

---

## 🧮 Zakres zadań

1.  [ ] **Ciąg iteracyjny – wersja 1**

    Obliczane są kolejne wyrazy ciągu zdefiniowanego wzorem:

    ![equation](<https://latex.codecogs.com/png.latex?x_{n%2B1}=x_n%2B3.0\cdot{x_n\cdot(1-x_n)}>)

    dla początkowej wartości:
    ![equation](https://latex.codecogs.com/png.latex?x_0=0.01). Zadanie należy
    wykonać dla różnych typów danych (`float`, `double`) i porównać dokładność
    wyników. Celem jest zaobserwowanie rozbieżności wynikających z ograniczonej
    precyzji reprezentacji zmiennoprzecinkowej.

---

2. [ ] **Ciąg iteracyjny – wersja równoważna**

   Algebraicznie równoważna wersja poprzedniego ciągu, zdefiniowana wzorem:
   ![equation](https://latex.codecogs.com/png.latex?x_{n%2B1}=4.0\cdot{x_n-3.0\cdot{x_n\cdot{x_n}}})

- [ ] porownac z wynikami z wczesniejszego zadania

  Pomimo równoważności matematycznej, wyniki obliczeń mogą się różnić z powodu
  błędów zaokrągleń i propagacji błędów numerycznych.

---

3. [ ] **Wyznaczyć "maszynowe epsilon"**

   Znalezienie najmniejszej liczby
   ![equation](https://latex.codecogs.com/png.latex?\varepsilon), dla której:

   ![equation](https://latex.codecogs.com/png.latex?1%2B\varepsilon>1)

   Zadanie polega na wyznaczeniu wartości epsilon dla różnych typów (`float`,
   `double`). Wynik pokazuje granicę precyzji obliczeń w danej reprezentacji
   liczb zmiennoprzecinkowych.

---

- [ ] **4. Sporządzenie sprawozdania** Na podstawie wykonanych zadań należy
      opracować sprawozdanie zawierające pełną analizę wyników i wniosków.

  ### Wymagania dotyczące sprawozdania:

  **Nagłówek:**

  - [ ] numer laboratorium
  - [ ] temat
  - [ ] autor
  - [ ] data

  **Zawartość:**

  - [ ] Treść zadania
  - [ ] Moje podejście do rozwiązania problemu — jak najgłębsza argumentacja
        przyjętego podejścia
  - [ ] Ważniejsze fragmenty kodu
  - [ ] Wyniki (liczby, wykresy, tabele)
  - [ ] Wnioski (związki przyczynowo-skutkowe)
  - [ ] Bibliografia
