import java.math.BigDecimal;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Diagram.DiagramType;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;


public class AppSMO {
    public static void main(String[] args) {
        try {
            SimManager simManager = SimManager.initInstance();
            // Powołanie Systemu Masowej Obslugi
            Smo smo = new Smo();
            // Utworzenie otoczenia sklepu
            new OtoczenieMarketu(smo, 5.0, 1.5);

            // Dwa sposoby zaplanowanego końca symulacji
            simManager.setEndSimTime(120);
            // lub
            //SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);

            // Uruchomienie symulacji za pośrednictwem metody "start"
            simManager.startSimulation();

            // Wyniki
            double wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVczasy_oczekiwania)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia czasu oczekiwania na obsługę:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVczasy_oczekiwania)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia czasu oczekiwania na obsługę:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .standardDeviation(smo.MVczasy_oczekiwania)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Odchylenie standardowe dla czasu obsługi:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("Wartość maksymalna czasu oczekiwania na obsługę: "
                    + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVdlKolejki)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia długości kolejki:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVdlKolejki)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia długości kolejki:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .max(smo.MVdlKolejki)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość maksymalna długości kolejki:       "
                            + wynik);

            Diagram d1 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania");
            d1.add(smo.MVczasy_obslugi, java.awt.Color.GREEN);
            d1.show();

            Diagram d2 = new Diagram(DiagramType.HISTOGRAM,
                    "Dlugość kolejki - HISTOGRAM");
            d2.add(smo.MVdlKolejki, java.awt.Color.BLUE);
            d2.show();

            Diagram d3 = new Diagram(DiagramType.HISTOGRAM,
                    "Czasy oczekiwania na obsługę");
            d3.add(smo.MVczasy_oczekiwania, java.awt.Color.BLUE);
            d3.show();

            Diagram d4 = new Diagram(DiagramType.TIME,
                    "Długość kolejki w czasie");
            d4.add(smo.MVdlKolejki, java.awt.Color.RED);
            d4.show();
            //--------------------------------------------------------------------

            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVczasy_oczekiwania_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia czasu oczekiwania na obsługę w szybkiej kasie:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVczasy_oczekiwania_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia czasu oczekiwania na obsługę w szybkiej kasie:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .standardDeviation(smo.MVczasy_oczekiwania_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Odchylenie standardowe dla czasu obsługi w szybkiej kasie:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania_szybkie))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w szybkiej kasie: "
                    + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVdlKolejki_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia długości kolejki w szybkiej kasie:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVdlKolejki_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia długości kolejki w szybkiej kasie:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .max(smo.MVdlKolejki_szybkie)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość maksymalna długości kolejki w szybkiej kasie:       "
                            + wynik);

            Diagram d5 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania szybka kasa");
            d5.add(smo.MVczasy_obslugi_szybkie, java.awt.Color.GREEN);
            d5.show();

            Diagram d6 = new Diagram(DiagramType.HISTOGRAM,
                    "Dlugość kolejki szybka kasa - HISTOGRAM");
            d6.add(smo.MVdlKolejki_szybkie, java.awt.Color.BLUE);
            d6.show();

            Diagram d7 = new Diagram(DiagramType.HISTOGRAM,
                    "Czasy oczekiwania na obsługę szybka kasa");
            d7.add(smo.MVczasy_oczekiwania_szybkie, java.awt.Color.BLUE);
            d7.show();

            Diagram d8 = new Diagram(DiagramType.TIME,
                    "Długość kolejki w czasie szybka kasa");
            d8.add(smo.MVdlKolejki_szybkie, java.awt.Color.RED);
            d8.show();
            //--------------------------------------------------------------------
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVczasy_oczekiwania_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia czasu oczekiwania na obsługę w wolnej kasie 1:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVczasy_oczekiwania_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia czasu oczekiwania na obsługę w wolnej kasie 1:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .standardDeviation(smo.MVczasy_oczekiwania_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Odchylenie standardowe dla czasu obsługi w wolnej kasie 1:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania_wolna_1))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w wolnej kasie 1: "
                    + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVdlKolejki_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia długości kolejki w wolnej kasie 1:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVdlKolejki_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia długości kolejki w wolnej kasie 1:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .max(smo.MVdlKolejki_wolna_1)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość maksymalna długości kolejki w wolnej kasie 1:       "
                            + wynik);

            Diagram d9 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania wolna kasa 1");
            d9.add(smo.MVczasy_obslugi_wolna_1, java.awt.Color.GREEN);
            d9.show();

            Diagram d10 = new Diagram(DiagramType.HISTOGRAM,
                    "Dlugość kolejki wolna kasa 1 - HISTOGRAM");
            d10.add(smo.MVdlKolejki_wolna_1, java.awt.Color.BLUE);
            d10.show();

            Diagram d11 = new Diagram(DiagramType.HISTOGRAM,
                    "Czasy oczekiwania na obsługę wolna kasa 1");
            d11.add(smo.MVczasy_oczekiwania_wolna_1, java.awt.Color.BLUE);
            d11.show();

            Diagram d12 = new Diagram(DiagramType.TIME,
                    "Długość kolejki w czasie wolna kasa 1");
            d12.add(smo.MVdlKolejki_wolna_1, java.awt.Color.RED);
            d12.show();
            //--------------------------------------------------------------------
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVczasy_oczekiwania_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia czasu oczekiwania na obsługę w wolnej kasie 2:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVczasy_oczekiwania_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia czasu oczekiwania na obsługę w wolnej kasie 2:   "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .standardDeviation(smo.MVczasy_oczekiwania_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Odchylenie standardowe dla czasu obsługi w wolnej kasie 2:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania_wolna_2))
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w wolnej kasie 2: "
                    + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .arithmeticMean(smo.MVdlKolejki_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość średnia długości kolejki w wolnej kasie 2:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .weightedMean(smo.MVdlKolejki_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Ważona wartość średnia długości kolejki w wolnej kasie 2:       "
                            + wynik);
            wynik = BigDecimal.valueOf(Statistics
                    .max(smo.MVdlKolejki_wolna_2)).setScale(2,
                    BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out
                    .println("Wartość maksymalna długości kolejki w wolnej kasie 2:       "
                            + wynik);

            Diagram d13 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania wolna kasa 2");
            d13.add(smo.MVczasy_obslugi_wolna_2, java.awt.Color.GREEN);
            d13.show();

            Diagram d14 = new Diagram(DiagramType.HISTOGRAM,
                    "Dlugość kolejki wolna kasa 2 - HISTOGRAM");
            d14.add(smo.MVdlKolejki_wolna_2, java.awt.Color.BLUE);
            d14.show();

            Diagram d15 = new Diagram(DiagramType.HISTOGRAM,
                    "Czasy oczekiwania na obsługę wolna kasa 2");
            d15.add(smo.MVczasy_oczekiwania_wolna_2, java.awt.Color.BLUE);
            d15.show();

            Diagram d16 = new Diagram(DiagramType.TIME,
                    "Długość kolejki w czasie wolna kasa 2");
            d16.add(smo.MVdlKolejki_wolna_2, java.awt.Color.RED);
            d16.show();



        } catch (SimControlException e) {
            e.printStackTrace();
        }

    }
}
