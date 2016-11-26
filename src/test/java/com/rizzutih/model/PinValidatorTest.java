package com.rizzutih.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PinValidatorTest {
	private PinValidator pvalidator;
	@Before
	public void setUp() {
		pvalidator = new PinValidator();
	}

	@Test
	public void testsplitPinReturnStringArray() {
		String[] split = pvalidator.splitPin("1234CON");
		assertEquals("1234", split[0]);
		assertEquals("ON", split[1]);
	}

	@Test(expected=PinFormatException.class)
	public void testValidatePinThrowinvalidFormat() throws PinFormatException {
		pvalidator.validatePin("12con");
		pvalidator.validatePin("12consdbjbs");
		pvalidator.validatePin("12215454");
		pvalidator.validatePin("1284384coon");
	}
	
	@Test(expected=PinFormatException.class)
	public void testValidatePinThrowinvalidFormatwhenNoPinEntered() throws PinFormatException {
		pvalidator.validatePin("");
		pvalidator.validatePin("    ");
	}
}
