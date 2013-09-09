package com.github.pageobject.impl.field.select;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.Select;

public class FixedChoiceTest {

	private FixedChoice instance;
	@Rule
	private JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock
	private Browser browser;
	private Select select;

	@Test
	public void shouldOnlyGetTheFixedOptionsValues() {
		instance = new FixedChoice(
				select = ctx.mock(Select.class),
				"option1"
		);
		browser = ctx.mock(Browser.class);
		
		ctx.checking(new Expectations(){{
			oneOf(select).setBrowser(browser);
			oneOf(select).fill("option1");
			oneOf(select).getAlias();
			will(returnValue("alias"));
		}});
		
		instance.setBrowser(browser);		
		instance.fill("option1");
		assertEquals("alias",instance.getAlias());
		ctx.assertIsSatisfied();
	}
	
}

