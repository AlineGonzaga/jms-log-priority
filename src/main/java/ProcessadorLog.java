
import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ProcessadorLog {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("LOG");
        MessageConsumer consumer = session.createConsumer(fila );

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                     TextMessage textMessage = (TextMessage)message;
                    // message.acknowledge();

                     System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
    }
}