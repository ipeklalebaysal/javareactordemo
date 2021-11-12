package demoClasses;

import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Part7Flux {
	public static void main(String[] args) {
		//stream that can emit 0..n elements
		ArrayList<Integer> arr = new ArrayList();

		Flux<Integer> flux = Flux.just(1, 2, 3, 4);
		flux.map(item -> arr.add(item))
		    .subscribe();

		arr.forEach(System.out::println);
	}
}
