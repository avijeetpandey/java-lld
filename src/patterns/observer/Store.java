package patterns.observer;

import patterns.observer.Observable.StocksObservable;
import patterns.observer.Observable.iPhoneObservableImpl;
import patterns.observer.Observer.EmailAlertObserverImpl;
import patterns.observer.Observer.MobileAlertObserverImpl;
import patterns.observer.Observer.NotificationAlertObserver;

/**
 * We want to implement notify now functionality in our application similar to amazon, so that when an item comes into stock again we get notified
 */
public class Store {
    public static void main(String[] args) {
        StocksObservable iphoneObservable = new iPhoneObservableImpl();

        NotificationAlertObserver observerOne = new EmailAlertObserverImpl("test@g.cc", iphoneObservable);
        NotificationAlertObserver observerTwo = new MobileAlertObserverImpl(915, iphoneObservable);

        iphoneObservable.add(observerOne);
        iphoneObservable.add(observerTwo);

        iphoneObservable.setStockCount(10);
    }
}
