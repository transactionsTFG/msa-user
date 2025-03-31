package payment;




import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import services.LogService;
import weblogic.wsee.wstx.wsat.Transactional;



@WebService(name = "Payment", serviceName = "PaymentService", targetNamespace = "http://payment/")
public class PaymentService {

    @EJB
    private LogService logService;
	
    @WebMethod(operationName = "process")
    @Transactional
    public boolean processPayment(String accountId, double amount) {        
        return this.logService.log(accountId, amount);
    }
}
