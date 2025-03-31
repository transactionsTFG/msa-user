package services;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;
import payment.PaymentLog;
import soapclient.bank.BankService;
import weblogic.wsee.wstx.wsat.Transactional;

@Stateless
public class LogService {
	@PersistenceContext
    private EntityManager em;
    
    @Transactional(value = Transactional.TransactionFlowType.MANDATORY, 
            version = Transactional.Version.WSAT12)
    @WebServiceRef(wsdlLocation = "https://localhost:7002/serverA/BankService?wsdl")
    private BankService bankService;
    
    public boolean log(String accountId, double amount) {
    	this.bankService.getBankPort().debit(accountId, amount);
    	PaymentLog p = new PaymentLog();
    	p.setAccountId("1");
        p.setAmount(11111);
        p.setStatus("PRUEBA Stateless");
        this.em.persist(p);
        return true;
    }
}
