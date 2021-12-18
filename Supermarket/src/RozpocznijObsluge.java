import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObsluge extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public RozpocznijObsluge(Smo parent) throws SimControlException
    {
        super(parent);
        generator = new RNGenerator();
    }

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        if (smoParent.liczbaZgl() > 0)
        {
            // Zablokuj gniazdo
            smoParent.setWolne(false);
            // Pobierz klienta
            Klient zgl = smoParent.usun();
            // Wygeneruj czas obsługi
            double czasObslugi;
            do {
                //czasObslugi = generator.normal(6.0, 1.0);
                czasObslugi = generator.chisquare(1);
            } while (czasObslugi<=0.0);

            // Zapamiętaj dane monitorowane
            smoParent.MVczasy_obslugi.setValue(czasObslugi);
            smoParent.MVczasy_oczekiwania.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+": Początek obsługi zgl. nr: " + zgl.getId());
            // Zaplanuj koniec obsługi
            new ZakonczObsluge(smoParent, czasObslugi, zgl);
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
