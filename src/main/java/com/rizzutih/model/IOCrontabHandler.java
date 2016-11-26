package com.rizzutih.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class IOCrontabHandler {

	Logger log = Logger.getLogger(IOCrontabHandler.class);

	public void camSwitcher(String onOff) {
		String command = "sh /home/pi/camHandler.sh " + onOff;
		log.info("command executed: "+ command);
		String output = executeCommand(command);
		log.info(output);
	}

	private String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		return output.toString();
	}
}