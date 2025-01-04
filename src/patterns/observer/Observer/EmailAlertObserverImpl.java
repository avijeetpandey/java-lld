package patterns.observer.Observer;


import patterns.observer.Observable.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver {
    String emailId;
    StocksObservable observable;

    public EmailAlertObserverImpl(String emailId, StocksObservable observable) {
        this.emailId = emailId;
        this.observable = observable;
    }

    @Override
    public void update() {
        sendEmail(emailId,"products are back in stock");
    }

    private void sendEmail(String email, String message) {
        System.out.println("email sent to "+ email);
    }
}
