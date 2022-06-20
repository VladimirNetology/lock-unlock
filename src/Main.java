import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final int NUMBER_OF_SALES = 4;
    public static final int NUMBER_OF_DELIVERIES = 2;

    public static void main(String[] args)  {
        final CarDealer carDealer = new CarDealer(5000, 2000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < NUMBER_OF_SALES; i++) {
            executorService.execute(carDealer::sellCar);
        }
        for (int i = 0; i < NUMBER_OF_DELIVERIES; i++) {
            executorService.execute(carDealer::deliverCar);
        }
    }
}

