package com.github.hvasoares.pageobjects.impl.field;

import java.util.HashMap;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.ClickableContainer;

public class ClickableContainerImpl implements ClickableContainer {

	private HashMap<String, Clickable> dataBase;

	public ClickableContainerImpl(){
		this.dataBase = new HashMap<String,Clickable>();
	}
	
	@Override
	public void click(String buttonAlias) {
		Clickable click = dataBase.get(buttonAlias);
		if(click==null)
			throw new RuntimeException("there's no clickable aliased " + buttonAlias);
		try{
			click.click();
		}catch(Throwable ex){
			throw new RuntimeException("Couldn't click " + buttonAlias,ex);
		}
	}
	
	@Override
	public void doubleClick(String buttonAlias){
		Clickable click = dataBase.get(buttonAlias);
		if(click==null)
			throw new RuntimeException("there's no clickable aliased " + buttonAlias);
		try{
			click.doubleClick();
		}catch(Throwable ex){
			throw new RuntimeException("Couldn't double click " + buttonAlias,ex);
		}
	}

	@Override
	public void add(Clickable click) {
		dataBase.put(click.getAlias(),click);
	}

}
