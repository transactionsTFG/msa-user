
package soapclient.bank;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soapclient.bank package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Debit_QNAME = new QName("http://bank/", "debit");
    private final static QName _DebitResponse_QNAME = new QName("http://bank/", "debitResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soapclient.bank
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Debit }
     * 
     */
    public Debit createDebit() {
        return new Debit();
    }

    /**
     * Create an instance of {@link DebitResponse }
     * 
     */
    public DebitResponse createDebitResponse() {
        return new DebitResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Debit }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Debit }{@code >}
     */
    @XmlElementDecl(namespace = "http://bank/", name = "debit")
    public JAXBElement<Debit> createDebit(Debit value) {
        return new JAXBElement<Debit>(_Debit_QNAME, Debit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DebitResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DebitResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://bank/", name = "debitResponse")
    public JAXBElement<DebitResponse> createDebitResponse(DebitResponse value) {
        return new JAXBElement<DebitResponse>(_DebitResponse_QNAME, DebitResponse.class, null, value);
    }

}
