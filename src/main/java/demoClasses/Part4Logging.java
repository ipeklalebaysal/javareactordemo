package demoClasses;

import reactor.core.publisher.Mono;

public class Part4Logging {
	public static void main(String[] args) {
		Mono<String> mono = Mono.just("I am mono");

		mono.log()
		    .subscribe(System.out::println);
	}
}
