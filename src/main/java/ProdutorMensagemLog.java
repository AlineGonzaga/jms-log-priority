import javax.jms.*;
import javax.naming.InitialContext;

public class ProdutorMensagemLog {

    public static void main(String[] args) throws Exception {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("LOG");


        MessageProducer producer = session.createProducer(fila);

        Message message = session.createTextMessage("INFO | is starting...");
        producer.send(message, DeliveryMode.NON_PERSISTENT, 3, 100000);

        session.close();
        connection.close();
        context.close();
    }
}