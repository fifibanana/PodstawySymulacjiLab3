import dissimlab.simcore.BasicSimObj;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class OtoczenieMarketu extends BasicSimObj {

    public MonitoredVar MVczasyMiedzyZgl;
    public Smo smo;


    public OtoczenieMarketu(Smo smo, double srednia, double odchylenie) throws SimControlException {
        // Powołanie instancji pierwszego zdarzenia
        new PrzybycieKlienta(this, 0.0, srednia, odchylenie);
        // Deklaracja zmiennych monitorowanych
        MVczasyMiedzyZgl = new MonitoredVar();
        // referencja na SMO
        this.smo = smo;
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
