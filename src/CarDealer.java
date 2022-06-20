import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarDealer {
    private final int MILLIS_DELIVERY;
    private final int MILLIS_SELL;
    private final List<Car> cars = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public CarDealer(int MILLIS_DELIVERY, int MILLIS_SELL) {
        this.MILLIS_DELIVERY = MILLIS_DELIVERY;
        this.MILLIS_SELL = MILLIS_SELL;
    }

    public void deliverCar() {
        try {
            lock.lock();
            System.out.println("START: Car Delivery " + Thread.currentThread().getName());
            Thread.sleep(MILLIS_DELIVERY);
            cars.add(new Car());
            System.out.println("FINISH: Car Delivery " + Thread.currentThread().getName());
            condition.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void sellCar() {
        try {
            lock.lock();
            System.out.println("START: Car Sell " + Thread.currentThread().getName());
            while (cars.size() == 0) {
                System.out.println(" - WAIT: Car Sell " + Thread.currentThread().getName());
                condition.await();
            }
            Thread.sleep(MILLIS_SELL);
            cars.remove(0);
            System.out.println("FINISH: Car Sell " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
