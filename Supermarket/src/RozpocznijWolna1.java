import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.concurrent.ThreadLocalRandom;

public class RozpocznijWolna1 extends BasicSimEvent<Smo, Klient> {
    private final RNGenerator generator;

    public RozpocznijWolna1(Smo parent) throws SimControlException
    {
        super(parent);
        generator = new RNGenerator();
    }

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        if (smoParent.liczbaZgl_wolna1() > 0)
        {
            // Zablokuj gniazdo
            smoParent.setWolne_wolna_1(false);
            // Pobierz klienta
            Klient2 zgl = smoParent.usun_wolna_1();
            // Wygeneruj czas obsługi
            double czasObslugi;
            do {
                int rand = ThreadLocalRandom.current().nextInt(1, 10);
                if (rand==4){
                    czasObslugi = generator.normal(11, 1);
                    System.out.println("Kasa Wolna #1  AWARIA TERMINALA ");
                }else{
                    czasObslugi = generator.normal(8, 1);
                }

            } while (czasObslugi<=0.0);

            // Zapamiętaj dane monitorowane
            smoParent.MVczasy_obslugi_wolna_1.setValue(czasObslugi);
            smoParent.MVczasy_oczekiwania_wolna_1.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+":    Kasa Wolna #1  Początek obsługi zgl. nr: " + zgl.getId());
            // Zaplanuj koniec obsługi
            new ZakonczWolna1(smoParent, czasObslugi, zgl);
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
