package payment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PaymentLog implements Serializable {
	
	private static final long serialVersionUID = -7448435127317767118L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountId;
    private double amount;
    private String status; // INIT, SUCCESS, FAILED

    public PaymentLog() { }

    public PaymentLog(String accountId, double amount, String status) {
        this.accountId = accountId;
        this.amount = amount;
        this.status = status;
    }

}
