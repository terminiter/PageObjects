package com.github.hvasoares.pageobjects.impl.field.select;

import com.github.hvasoares.pageobjects.MutabilityCustomFieldFactory;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.CustomField;
import com.github.hvasoares.pageobjects.impl.field.Select;

public abstract class SelectField implements Select{
	public static Select createFixedSelect(String alias,String rootXpath,String ... possibleOptions){
		return new FixedChoice(
				createFluidSelect(alias, rootXpath), 
				possibleOptions
		);
	}
	public static Select createFluidSelect(String alias,String rootXpath){
		return new FluidChoice(alias,rootXpath);
	}
	public static MutabilityCustomFieldFactory createMutableFactory() {
		return new MutabilityCustomFieldFactory() {
			@Override
			public void setBrowser(Browser value) {
				
			}
			
			@Override
			public CustomField create(String alias, String xpath) {
				return createFluidSelect(alias, xpath);
			}
		};
	}
}
