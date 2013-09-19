package com.github.pageobject.impl.el;

import org.apache.commons.jexl2.MapContext;

import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.field.FieldFactoryImpl;

public class ElFactory {

	private static ElContextImpl elContext;

	public static FieldFactory createFieldFactory(
			FieldFactoryImpl innerFieldFactory) {
		return new ElFieldFactory(createElContext(), innerFieldFactory);
	}

	private static ElContextImpl createElContext() {
		if(elContext!=null)
			return elContext;
		elContext = new ElContextImpl(
				new MapContext(),
				new JexlExpressionFactoryImpl()
		);
		return elContext;
	}

	public static ProxyStatePageObjectAdapter createElContextStatePageObject() {
		return createElContext();
	}

}