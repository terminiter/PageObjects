package com.github.pageobject.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.PageObjectBuilderFactory;

public class SerialPageObjectBuilder {
	private List<AssertivePageObject> all;
	private PageObjectBuilderFactory factory;
	private PageObjectBuilder current;
	public SerialPageObjectBuilder(PageObjectBuilderFactory factory){
		this.all = new ArrayList<AssertivePageObject>();
		this.factory = factory;
	}
	public PageObjectBuilder newPage(String name){
		if(current!=null)
			all.add(current.get());
		current = factory.createPageObjectBuilder();
		current.setName(name);
		return current;
	}

	public List<AssertivePageObject> getAll() {
		if(current!=null){
			all.add(current.get());
			current=null;
		}
		return all;
	}

	
}