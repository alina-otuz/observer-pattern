import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(String message);
}

// Subject Class
class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String news;
    
    // Subscribe an observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    // Unsubscribe an observer
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    // Notify all observers
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}

// Concrete Observer 1
class EmailSubscriber implements Observer {
    private String name;
    
    public EmailSubscriber(String name) {
        
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println(name + " received email: " + message);
    }
}

// Concrete Observer 2
class SMSSubscriber implements Observer {
    private String phoneNumber;
    
    public SMSSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void update(String message) {
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}

// Main Demo
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create the Subject
        NewsAgency agency = new NewsAgency();
        
        // Create Observers
        Observer emailSub1 = new EmailSubscriber("John");
        Observer emailSub2 = new EmailSubscriber("Sarah");
        Observer smsSub = new SMSSubscriber("555-1234");
        
        // Subscribe observers
        agency.addObserver(emailSub1);
        agency.addObserver(emailSub2);
        agency.addObserver(smsSub);
        
        // Publish news - all observers get notified
        System.out.println("--- Breaking News ---");
        agency.setNews("Java 21 Released!");
        
        System.out.println("\n--- Another Update ---");
        agency.setNews("Observer Pattern is awesome!");
        
        // Unsubscribe one observer
        agency.removeObserver(emailSub2);
        
        System.out.println("\n--- After Sarah unsubscribed ---");
        agency.setNews("Sarah won't see this");
    }
}