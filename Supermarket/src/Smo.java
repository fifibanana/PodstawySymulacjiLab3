import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;



public class Smo extends BasicSimObj
{
    private final LinkedList <Klient> kolejka;
    private final LinkedList <Klient2> kolejka_szybka;
    private final LinkedList <Klient2> kolejka_wolna1;
    private final LinkedList <Klient2> kolejka_wolna2;

    private boolean wolne = true;
    private boolean wolne_szybkie = true;
    private boolean wolne_wolna_1 = true;
    private boolean wolne_wolna_2 = true;

    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;
    public MonitoredVar MVutraconeZgl;

    public MonitoredVar MVczasy_obslugi_szybkie;
    public MonitoredVar MVczasy_oczekiwania_szybkie;
    public MonitoredVar MVdlKolejki_szybkie;
    public MonitoredVar MVutraconeZgl_szybkie;

    public MonitoredVar MVczasy_obslugi_wolna_1;
    public MonitoredVar MVczasy_oczekiwania_wolna_1;
    public MonitoredVar MVdlKolejki_wolna_1;
    public MonitoredVar MVutraconeZgl_wolna_1;

    public MonitoredVar MVczasy_obslugi_wolna_2;
    public MonitoredVar MVczasy_oczekiwania_wolna_2;
    public MonitoredVar MVdlKolejki_wolna_2;
    public MonitoredVar MVutraconeZgl_wolna_2;


    public Smo() throws SimControlException
    {
        //Utworzenie kolejek dla gniazd
        //kolejka - kolejka do bramki wejsciowej
        //pozostale kolejki - kolejki do kas
        kolejka = new LinkedList<>();
        kolejka_szybka = new LinkedList<>();
        kolejka_wolna1 = new LinkedList<>();
        kolejka_wolna2 = new LinkedList<>();

        // Deklaracja zmiennych
        MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVutraconeZgl = new MonitoredVar();
        // Deklaracaja zmiennych dla kolejki do kasy szybkiej
        MVczasy_obslugi_szybkie = new MonitoredVar();
        MVczasy_oczekiwania_szybkie = new MonitoredVar();
        MVdlKolejki_szybkie = new MonitoredVar();
        MVutraconeZgl_szybkie = new MonitoredVar();
        // Deklaracaja zmiennych dla kolejki do kasy "wolnej nr1"
        MVczasy_obslugi_wolna_1 = new MonitoredVar();
        MVczasy_oczekiwania_wolna_1 = new MonitoredVar();
        MVdlKolejki_wolna_1 = new MonitoredVar();
        MVutraconeZgl_wolna_1 = new MonitoredVar();
        // Deklaracaja zmiennych dla kolejki do kasy "wolnej nr2"
        MVczasy_obslugi_wolna_2 = new MonitoredVar();
        MVczasy_oczekiwania_wolna_2 = new MonitoredVar();
        MVdlKolejki_wolna_2 = new MonitoredVar();
        MVutraconeZgl_wolna_2 = new MonitoredVar();



    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj(Klient zgl)
    {
        kolejka.add(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return kolejka.size();
    }

    public int dodaj_szybka(Klient2 zgl)
    {
        kolejka_szybka.add(zgl);
        MVdlKolejki_szybkie.setValue(kolejka_szybka.size());
        return kolejka_szybka.size();
    }

    public int dodaj_wolna1(Klient2 zgl)
    {
        kolejka_wolna1.add(zgl);
        MVdlKolejki_wolna_1.setValue(kolejka_wolna1.size());
        return kolejka_wolna1.size();
    }

    public int dodaj_wolna2(Klient2 zgl)
    {
        kolejka_wolna2.add(zgl);
        MVdlKolejki_wolna_2.setValue(kolejka_wolna2.size());
        return kolejka_wolna2.size();
    }


    // Pobranie zgłoszenia z kolejki
    public Klient usun()
    {
        Klient zgl = (Klient) kolejka.removeFirst();
        MVdlKolejki.setValue(kolejka.size());
        return zgl;
    }

    public Klient2 usun_2()
    {
        Klient2 zgl = (Klient2) kolejka_szybka.removeFirst();
        MVdlKolejki_szybkie.setValue(kolejka_szybka.size());
        return zgl;
    }

    public Klient2 usun_wolna_1()
    {
        Klient2 zgl = (Klient2) kolejka_wolna1.removeFirst();
        MVdlKolejki_wolna_1.setValue(kolejka_wolna1.size());
        return zgl;
    }

    public Klient2 usun_wolna_2()
    {
        Klient2 zgl = (Klient2) kolejka_wolna2.removeFirst();
        MVdlKolejki_wolna_2.setValue(kolejka_wolna2.size());
        return zgl;
    }



    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Klient zgl)
    {
        Boolean b= kolejka.remove(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return b;
    }

    public int liczbaZgl()
    {
        return kolejka.size();
    }

    public int liczbaZgl_szybka()
    {
        return kolejka_szybka.size();
    }

    public int liczbaZgl_wolna1()
    {
        return kolejka_wolna1.size();
    }

    public int liczbaZgl_wolna2()
    {
        return kolejka_wolna2.size();
    }

    public boolean isWolne() {
        return wolne;
    }

    public boolean isWolne_szybkie() {
        return wolne_szybkie;
    }

    public boolean isWolne_wolna_1() {
        return wolne_wolna_1;
    }

    public boolean isWolne_wolna_2() {
        return wolne_wolna_2;
    }


    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    public void setWolne_szybkie(boolean wolne) {
        this.wolne_szybkie = wolne;
    }

    public void setWolne_wolna_1(boolean wolne) {
        this.wolne_wolna_1 = wolne;
    }

    public void setWolne_wolna_2(boolean wolne) {
        this.wolne_wolna_2 = wolne;
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub
        return false;
    }


}
