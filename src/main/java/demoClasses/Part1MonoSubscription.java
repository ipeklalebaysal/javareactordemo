package demoClasses;

import reactor.core.publisher.Mono;

public class Part1MonoSubscription {

	public static void main(String[] args) {

		//stream of 0..1 element
		Mono<Integer> mono = Mono.just(1); //Creates a mono with the integer value 1 as element

		System.out.println("Mono without any subscribers: " + mono);

		mono.subscribe(); //Data will be emitted but no consumer do anything.

		mono.subscribe(i -> System.out.println("Mono after subscriber " + i)); //Data will be emitted and consumer will print data.

	}
}
