import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;


public class PrzybycieKlienta extends BasicSimEvent<OtoczenieMarketu, Object> {
    private final RNGenerator generator;
    private final double srednia;
    private final double odchylenieStandardowe;

    public PrzybycieKlienta(OtoczenieMarketu parent, double odstep, double srednia, double odchylenieStandardowe) throws SimControlException {
        super(parent, odstep);
        generator = new RNGenerator();
        this.srednia = srednia;
        this.odchylenieStandardowe = odchylenieStandardowe;
    }

    @Override
    protected void onTermination() throws SimControlException {
    }

    @Override
    protected void stateChange() throws SimControlException {
        Klient zgl = new Klient(simTime());

        // zdarzenie jest przypisane do obiektu, ktorego stan zmienia. Podając w konstruktorze OtoczenieSklepu jako
        // "parent", tutaj możemy uzyskać dostęp to tego obiektu przy pomocy metody getSimObj().
        OtoczenieMarketu otoczenieMarketu = getSimObj();
        otoczenieMarketu.smo.dodaj(zgl);
        //otoczenieSklepu.smo.dodaj_2(zgl);


        System.out.println(simTimeFormatted() + ": Otoczenie: Dodano nowe zgl. nr: " + zgl.getId());
        System.out.println(simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - "
                + simDate(SimDateField.MILLISECOND) + ": Otoczenie: Dodano nowe zgl. nr: " + zgl.getId());

        // Aktywuj obsługę, jeżeli kolejka była pusta (gniazdo "spało")
        if (otoczenieMarketu.smo.liczbaZgl() == 1 && otoczenieMarketu.smo.isWolne()) {
            new RozpocznijObsluge(otoczenieMarketu.smo);

        }



        // Wygeneruj czas do kolejnego zgłoszenia
        double odstep;
        do {
            //odstep = generator.normal(srednia, odchylenieStandardowe);
            odstep = generator.poisson(2);
        } while (odstep <= 0.0);

        otoczenieMarketu.MVczasyMiedzyZgl.setValue(odstep);
        setRepetitionPeriod(odstep);




    }

    @Override
    public Object getEventParams() {
        return null;
    }


}
