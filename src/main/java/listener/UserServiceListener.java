package listener;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.Transactional;

import business.usecase.createuser.CreateUserUseCase;

@MessageDriven(mappedName = "jms/userServiceQueue") //Escuchamos a la cola UserServiceQueue
public class UserServiceListener implements MessageListener{
    
    private CreateUserUseCase createUserUseCase;

    @Override
    @Transactional //Ponemos el @Transactional aqui para saber que se inicia la transaccion desde aqui, ya que es inncessario en el EJB ya que se inicia automaticamente 
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String payload = ((TextMessage) message).getText();

                // Decodificar el tipo de mensaje, por ejemplo:
                // - "CreateUserCommand: {...}"
                // - "CompensateUserCreationCommand: {...}"
                if (payload.contains("CreateUserCommand")) {
                    this.createUserUseCase.handleCreateUser(payload);
                } else if (payload.contains("CompensateUserCreationCommand")) {
                    System.out.println("HOLAAAAAAAAA");
                    //handleCompensateUser(payload);
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing message: " + e.getMessage());
        }
    }
    

    @EJB
    public void setCreateUserUseCase(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }
}
