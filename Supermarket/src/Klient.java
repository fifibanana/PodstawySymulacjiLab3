import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import java.util.concurrent.ThreadLocalRandom;

public class Klient extends BasicSimObj{
    public static int licznikKlientow = 0;
    private final double czasPrzybycia;
    private final int id;

    /* zmienna do ilosci koszyka do zrobienia */

    public Klient(double czasPrzybycia) {
        this.czasPrzybycia = czasPrzybycia;
        this.id = licznikKlientow++;
        /* zmienna do ilosci koszyka do zrobienia */
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        return false;
    }

    public int getId() {
        return id;
    }

    public double getCzasPrzybycia() {
        return czasPrzybycia;
    }


}
