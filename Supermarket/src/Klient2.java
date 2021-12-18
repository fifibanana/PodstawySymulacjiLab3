import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.util.concurrent.ThreadLocalRandom;

public class Klient2 extends BasicSimObj {
    public static int licznikKlientow = 0;
    private final double czasPrzybycia;
    private final int id;
    public static int koszyk_produkty = 0;

    public Klient2(double czasPrzybycia) {
        this.czasPrzybycia = czasPrzybycia;
        this.koszyk_produkty = ThreadLocalRandom.current().nextInt(1, 15);
        this.id = licznikKlientow++;
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