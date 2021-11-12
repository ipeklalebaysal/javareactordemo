package demoClasses;

import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public class Part0StreamsReactors {
	public static void main(String[] args) {
		Mono<String> mono = Mono.just("I am Reactor");

		Stream<String> stream = Stream.of("I am Stream");

		mono.subscribe(System.out::println);
		mono.subscribe(System.out::println);

		stream.forEach(System.out::println);
		stream.forEach(System.out::println);
	}
}
