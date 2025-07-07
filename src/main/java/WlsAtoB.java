import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class WlsAtoB {

    /* 1) Nos conectamos ÚNICAMENTE al dominio A (Agency) */
    private static final String URL_A      = "t3://localhost:9001";
    private static final String USER_A     = "weblogic";
    private static final String PASSWORD_A = "password1$";

    /* 2) Alias locales que expone el Foreign Server */
    private static final String CF_LOCAL   = "jmsremote/connectionFactoryAirline";              // Foreign CF
    private static final String QUEUE_LOCAL = "jmsremote/orchestratorAirline"; // Foreign Dest
    //private static final String CF_LOCAL   = "jms/connectionFactory";              // Foreign CF
    //private static final String QUEUE_LOCAL = "jms/agencyOrchestatorQueue"; // Foreign Dest
    public static void main(String[] args) throws Exception {

        /* InitialContext contra el Server A */
        Hashtable<String,String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, URL_A);
        env.put(Context.SECURITY_PRINCIPAL, USER_A);
        env.put(Context.SECURITY_CREDENTIALS, PASSWORD_A);

        try {
            Context ic = new InitialContext(env);
            /* Lookup de los OBJETOS LOCALES (son wrappers del Foreign Server) */
            ConnectionFactory cf = (ConnectionFactory) ic.lookup(CF_LOCAL);
            Queue             q  = (Queue)          ic.lookup(QUEUE_LOCAL);

            /* A partir de aquí WebLogic usa internamente la URL/credenciales del dominio B */
            try (JMSContext ctx = cf.createContext()) {
                String body = "ping " + System.currentTimeMillis();
                ctx.createProducer().send(q, body);
                System.out.println("Enviado al dominio B: " + body);

                /* Recibirlo (opcional, sólo para probar end-to-end) */
                String r = ctx.createConsumer(q).receiveBody(String.class, 3000);
                System.out.println("Recibido desde dominio B: " + r);
            } 
            catch (Exception e) {
                System.out.println("Error al enviar el mensaje al dominio B: " + e.getMessage());
                e.printStackTrace();    
            }
        } catch (Exception e) {
            System.out.println("Error al conectarse al dominio A: " + e.getMessage());
            e.printStackTrace();    
        }
    }
}