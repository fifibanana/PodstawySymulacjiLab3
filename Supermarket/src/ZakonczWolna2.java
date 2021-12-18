import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczWolna2 extends BasicSimEvent<Smo, Klient2> {
    public ZakonczWolna2(Smo parent, double delay, Klient2 zgl) throws SimControlException
    {
        super(parent, delay, zgl);
    }

    @Override
    protected void onTermination() throws SimControlException {}

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+":      Kasa Wolna #2 Koniec obsługi zgl. nr: " + eventParams.getId());
        smoParent.setWolne_wolna_2(true);



        // Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl_wolna2() > 0)
        {
            new RozpocznijWolna2(smoParent);
        }
    }

    @Override
    public Klient2 getEventParams() {
        return null;
    }
}

