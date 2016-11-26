package com.rizzutih.controller;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rizzutih.model.IOCrontabHandler;
import com.rizzutih.model.InvalidPinException;
import com.rizzutih.model.PinFormatException;
import com.rizzutih.model.PinValidator;

@Controller
@Scope("session")
public class HomeController {
	Logger log = Logger.getLogger(HomeController.class);
	
	@Resource(name="pinValidator")
	private PinValidator pinValidator;
	
	@Resource(name="ioCrontabHandler")
	private IOCrontabHandler ioCrontabHandler;
	
	
	@RequestMapping(value="/enableDisable.html", method=RequestMethod.POST)
	public String enableDisableCamera(HttpServletRequest req){
		String fullpin = req.getParameter("pin");
		try {
			pinValidator.validatePin(fullpin);
			String[] pinArray = pinValidator.splitPin(fullpin);
			pinValidator.verifyPin(pinArray[0]);
			ioCrontabHandler.camSwitcher(pinArray[1]);
			req.getSession().setAttribute("camSession", pinArray[1]);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			log.info("Camera is "+ pinArray[1] +" at " +timestamp);
		} catch (PinFormatException | InvalidPinException | SQLException e) {
			if(e instanceof SQLException){
				req.setAttribute("errorMessage", "Database server is not available");	
			}else{
				req.setAttribute("errorMessage", e.getMessage());				
			}
			log.info(e.getMessage());
		}
		return "index";
	} 

}
