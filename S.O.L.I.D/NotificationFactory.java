package creational_design_pattern;


interface Notifications {
    void send();
}

class EmailNotification implements Notifications {
    @Override
    public void send() {
        System.out.println("Sending Email Notification");
    }
}

class SMSNotification implements Notifications {
    @Override
    public void send() {
        System.out.println("Sending SMS Notification");
    }
}

class WhatsappNotification implements Notifications {
    @Override
    public void send() {
        System.out.println("Sending WhatsApp Notification");
    }
}


class factory { 
    public static Notifications createNotification(String type) {
        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else if (type.equalsIgnoreCase("WHATSAPP")) {
            return new WhatsappNotification();
        }
        throw new IllegalArgumentException("Unknown notification type: " + type);
    }
    
}

public class NotificationFactory {
    
    public static void main(String[] args) {
        Notifications notification1 = factory.createNotification("EMAIL");
        notification1.send(); // Output: Sending Email Notification

        Notifications notification2 = factory.createNotification("SMS");
        notification2.send(); // Output: Sending SMS Notification

        Notifications notification3 = factory.createNotification("WHATSAPP");
        notification3.send(); // Output: Sending WhatsApp Notification
    } 
}