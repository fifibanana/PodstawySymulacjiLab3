import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters;

import java.util.Random;

public class ZakonczObsluge extends BasicSimEvent<Smo, Klient>
{
    public ZakonczObsluge(Smo parent, double delay, Klient zgl) throws SimControlException
    {
        super(parent, delay, zgl);
    }

    @Override
    protected void onTermination() throws SimControlException {}

    @Override
    protected void stateChange() throws SimControlException {
        Smo smoParent = getSimObj();
        Random rand = new Random();
        int random= rand.nextInt(2);
        System.out.println(simTimeFormatted()+": SMO-Koniec obsługi zgl. nr: " + eventParams.getId());
        smoParent.setWolne(true);


        Klient2 zgl = new Klient2(simTime());
        //System.out.println("Random:"+random+"ilosc prod w koszyku"+zgl.koszyk_produkty);
        if (zgl.koszyk_produkty <= 5 ){
            smoParent.dodaj_szybka(zgl);
        }else{
            if (random==0){
                smoParent.dodaj_wolna1(zgl);
            }
            if (random==1){
                smoParent.dodaj_wolna2(zgl);
            }
        }


        if (smoParent.liczbaZgl_szybka() == 1 && smoParent.isWolne_szybkie()){
            new RozpocznijObslugeSzybka(smoParent);
        }

        if (smoParent.liczbaZgl_wolna1() == 1 && smoParent.isWolne_wolna_1()){
            new RozpocznijWolna1(smoParent);
        }

        if (smoParent.liczbaZgl_wolna2() == 1 && smoParent.isWolne_wolna_2()){
            new RozpocznijWolna2(smoParent);
        }

        // Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl() > 0)
        {
            new RozpocznijObsluge(smoParent);
        }
    }

    @Override
    public Klient getEventParams() {
        return null;
    }
}
