package it.luigibifulco.blog.example.spring.decorator;

import org.springframework.stereotype.Component;

@Component("delegate")
public class ExampleService implements IExampleService {

	@Override
	public String startExample() {
		return "Example started";
	}
}
