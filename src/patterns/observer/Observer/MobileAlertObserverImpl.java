package patterns.observer.Observer;

import patterns.observer.Observable.StocksObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver {
    int mobileNumber;
    StocksObservable observable;

    public MobileAlertObserverImpl(int mobileNumber, StocksObservable observable) {
        this.observable = observable;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void update() {
        sendMessageToMobile(mobileNumber, "Products are back in stock");
    }

    private void sendMessageToMobile(int mobileNumber, String message) {
        System.out.println(message+ mobileNumber);
    }
}
