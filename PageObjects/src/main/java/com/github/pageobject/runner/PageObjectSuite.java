package com.github.pageobject.runner;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite.SuiteClasses;

import com.github.pageobject.AbstractFactory;
import com.github.pageobject.DefaultFactory;
import com.github.pageobject.RepositoryAwareFactory;
import com.github.pageobject.impl.browser.BrowserLocker;
import com.github.pageobject.impl.browser.TestSuiteAwareBrowser;
public class PageObjectSuite extends Runner implements BrowserLocker{
	private Class<?> clazz;
	private Class<?>[] classes;
	RepositoryAwareFactory defaultFactory;
	private Description description;
	private List<Runner> db;

	public PageObjectSuite(Class<?> clazz){
		Validator.checkValidPageObjectSuite(clazz);
		this.clazz = clazz;
		SuiteClasses suite = this.clazz.getAnnotation(SuiteClasses.class);
		classes = suite.value();
		db = new ArrayList<Runner>();
		defaultFactory = new DefaultFactory();
	}
	
	public PageObjectSuite(Class<?> clazz,RepositoryAwareFactory defaultFactory){
		this(clazz);
		this.defaultFactory=defaultFactory;
	}

	@Override
	public Description getDescription() {
		if(description!=null)
			return description;
		description = Description.createSuiteDescription(clazz);
		for(Class<?> c : classes){
			Runner runner = createRunner(c);
			description.addChild(runner.getDescription());
			this.db.add(runner);
		}
		return description;
	}

	private Runner createRunner(Class<?> c) {
		RunWith runWith = c.getAnnotation(RunWith.class);
		if(runWith.value().equals(this.clazz)){
			return new PageObjectSuite(c,defaultFactory);
		}
		return new PageObjectRunner(c, defaultFactory);
	}

	@Override
	public void run(RunNotifier notifier) {
		TestSuiteAwareBrowser browser = TestSuiteAwareBrowser.getInstance();
		browser.setBrowserLocker(this);
		notifier.fireTestStarted(description);
		for( Runner runner : db){
			try{
				runner.run(notifier);
			}catch(RuntimeException ex){
				continue;
			}
		}
		notifier.fireTestFinished(description);
		browser.close(this);
	}

}
