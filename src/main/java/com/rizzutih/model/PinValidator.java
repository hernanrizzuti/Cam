package com.rizzutih.model;

import java.sql.SQLException;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.rizzutih.model.service.HashPassword;
import com.rizzutih.model.storage.PinDAO;

public class PinValidator {

	public PinValidator() {
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
	}

	Logger log = Logger.getLogger(PinValidator.class);

	@Resource(name="pinDao")
	private PinDAO pinDao;

	@Resource(name="pin")
	private Pin pin;

	@Resource(name="hashPassword")
	private HashPassword hashPassword;

	public String[] splitPin(String fullPin) {
		fullPin = fullPin.toUpperCase();
		return fullPin.split("C");
	}

	public void validatePin(String fullPin) throws PinFormatException {
		if(fullPin.isEmpty()){
			throw new PinFormatException("No Pin enterred");	
		}
		if(!fullPin.matches("^[0-9]{4,7}(con|coff)$")){
			throw new PinFormatException("Malformed Pin number");
		}
	}

	public void verifyPin(String pinIn) throws InvalidPinException, SQLException{
		String hashedPin = hashPassword.encrypt(pinIn);
		pin = pinDao.getPin(hashedPin);
		if(pin==null){
			log.info("Invalid Pin number");
			throw new InvalidPinException("Invalid Pin number");
		}else{
			log.info("Pin for "+ pin.getUser() +" retrieved OK.");
		}
	}

}
