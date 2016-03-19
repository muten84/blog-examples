package it.luigibifulco.blog.example.spring.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("2ndDecorator")
public class AnotherServiceDecorator implements IExampleService {

	@Autowired
	private IExampleService delegate;

	@Override
	public String startExample() {
		String decorated = delegate.startExample();
		return decorated + " , with another too";
	}

}
