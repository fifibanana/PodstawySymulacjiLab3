import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczObslugeSzybka extends BasicSimEvent<Smo, Klient2>
{
    public ZakonczObslugeSzybka(Smo parent, double delay, Klient2 zgl) throws SimControlException
    {
        super(parent, delay, zgl);
    }

    @Override
    protected void onTermination() throws SimControlException {}

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+":      Kasa Szybka #1 Koniec obsługi zgl. nr: " + eventParams.getId());
        smoParent.setWolne_szybkie(true);



        // Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl_szybka() > 0)
        {
            new RozpocznijObslugeSzybka(smoParent);
        }
    }

    @Override
    public Klient2 getEventParams() {
        return null;
    }
}