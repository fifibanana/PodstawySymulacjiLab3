import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.concurrent.ThreadLocalRandom;

public class RozpocznijObslugeSzybka extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public RozpocznijObslugeSzybka(Smo parent) throws SimControlException
    {
        super(parent);
        generator = new RNGenerator();
    }

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        if (smoParent.liczbaZgl_szybka() > 0)
        {
            // Zablokuj gniazdo
            smoParent.setWolne_szybkie(false);
            // Pobierz klienta
            Klient2 zgl = smoParent.usun_2();
            // Wygeneruj czas obsługi
            double czasObslugi;
            do {

                int rand = ThreadLocalRandom.current().nextInt(1, 10);
                if (rand==4){
                    czasObslugi = generator.normal(7, 0.7);
                    System.out.println("Kasa Szybka #1  AWARIA TERMINALA ");
                }else{
                    czasObslugi = generator.normal(4, 0.7);


                }
            } while (czasObslugi<=0.0);

            // Zapamiętaj dane monitorowane
            smoParent.MVczasy_obslugi_szybkie.setValue(czasObslugi);
            smoParent.MVczasy_oczekiwania_szybkie.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+":    Kasa Szybka #1  Początek obsługi zgl. nr: " + zgl.getId());
            // Zaplanuj koniec obsługi
            new ZakonczObslugeSzybka(smoParent, czasObslugi, zgl);
        }

    }

    @Override
    protected void onTermination() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    public Klient getEventParams() {
        // TODO Auto-generated method stub
        return null;
    }
}
