package domainevent.command.validateuser;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.ValidateUserQualifier;
import business.user.UserDTO;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventHandler;
import msa.commons.commands.createreservation.CreateReservationCommand;
import msa.commons.commands.createreservation.model.CustomerInfo;
import msa.commons.commands.hotelbooking.CreateHotelBookingCommand;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.CreateReservation;
import msa.commons.event.eventoperation.user.UserValidate;

@Stateless
@ValidateUserQualifier
@Local(EventHandler.class)
public class ValidateUserEvent extends BaseHandler {

    @Override
    public void handleCommand(String json) {
        EventData eventData = this.gson.fromJson(json, EventData.class);
        if (UserValidate.CREATE_RESERVATION_AIRLINE.equals(eventData.getOperation())) 
            this.handleCreateReservationAirline(json);

        if (UserValidate.CREATE_RESERVATION_HOTEL.equals(eventData.getOperation()))
            this.handleCreateReservationHotel(json);
    }

    private void handleCreateReservationAirline(String json) {
        EventData event = EventData.fromJson(json, CreateReservationCommand.class);
        CreateReservationCommand command = (CreateReservationCommand) event.getData();
        UserDTO user = this.userService.getUserById(command.getIdUser());   
        if (user == null) event.setOperation(CreateReservation.CREATE_RESERVATION_ONLY_AIRLINE_ROLLBACK); 
            else  event.setOperation(CreateReservation.CREATE_RESERVATION_ONLY_AIRLINE_BEGIN);
        
        if (user != null) {
            CustomerInfo customerInfo = command.getCustomerInfo();
            customerInfo.setEmail(user.getEmail());
            customerInfo.setName(user.getName());
            customerInfo.setPhone(user.getPhone());
        }
        
        this.jmsEventDispatcher.publish(EventId.CREATE_RESERVATION_TRAVEL, event);
    }

    private void handleCreateReservationHotel(String json) {
        EventData event = EventData.fromJson(json, CreateHotelBookingCommand.class);
        CreateHotelBookingCommand command = (CreateHotelBookingCommand) event.getData();
        UserDTO user = this.userService.getUserById(command.getTravelUserId());
        if (user == null) event.setOperation(CreateReservation.CREATE_RESERVATION_ONLY_HOTEL_ROLLBACK); 
            else event.setOperation(CreateReservation.CREATE_RESERVATION_ONLY_HOTEL_BEGIN);
        
        if (user != null) {
            CustomerInfo customerInfo = command.getCustomerInfo();
            customerInfo.setEmail(user.getEmail());
            customerInfo.setName(user.getName());
            customerInfo.setPhone(user.getPhone());
        }
        
        this.jmsEventDispatcher.publish(EventId.CREATE_RESERVATION_TRAVEL, event);
    }

   

}
