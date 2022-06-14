public class Main {
    public static void main(String[] args)  {
        final CarDealer carDealer = new CarDealer();

        new Thread(carDealer::deliverCar).start();
        new Thread(carDealer::sellCar).start();
        new Thread(carDealer::sellCar).start();
        new Thread(carDealer::sellCar).start();
        new Thread(carDealer::sellCar).start();
        new Thread(carDealer::deliverCar).start();
    }
}

