import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczWolna1 extends BasicSimEvent<Smo, Klient2> {
    public ZakonczWolna1(Smo parent, double delay, Klient2 zgl) throws SimControlException
    {
        super(parent, delay, zgl);
    }

    @Override
    protected void onTermination() throws SimControlException {}

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+":      Kasa Wolna #1 Koniec obsługi zgl. nr: " + eventParams.getId());
        smoParent.setWolne_wolna_1(true);



        // Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl_wolna1() > 0)
        {
            new RozpocznijWolna1(smoParent);
        }
    }

    @Override
    public Klient2 getEventParams() {
        return null;
    }
}

