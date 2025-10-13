package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

public class Main {
  public static void main(String[] args) {
    int maxIter = 100;

    String zad1Result = "results_1.csv";
    String zad2Result = "results_2.csv";

    Zadanie1 zad1 = new Zadanie1(maxIter);
    Zadanie2 zad2 = new Zadanie2(maxIter);

    zad1.runExperiment(zad1Result);
    zad2.runExperiment(zad2Result);
  }
}
