package com.github.pageobject.impl;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.Mutability;
import com.github.pageobject.PageObjectBuilder;

public abstract class PageObjectBuilderSymbolTable implements PageObjectBuilder,WaitingStartFactory  {
	private static Readability readability;
	private static Mutability mutability;

	@Override
	public final Readability readability(){
		return readability;
	}

	protected WebDriver getWebDriver(){
		return null;
	}
	
	protected final void setReadability(Readability value){
		readability = value;
	}
	
	public final Mutability mutability(){
		return mutability;
	}

	protected void setMutability(Mutability value) {
		mutability = value;
	}
}