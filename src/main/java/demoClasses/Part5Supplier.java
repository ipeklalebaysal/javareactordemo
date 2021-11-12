package demoClasses;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class Part5Supplier {
	public static void main(String[] args) {
		Mono<String> mono = Mono.empty();
		Mono<String> mono2 = Mono.empty();

		System.out.println("created without supplier");
		mono.just(getName()); //When this mono is created it will call the method but this is unrequired if there is no subscribers

		System.out.println("created with supplier");
		mono2.just(Mono.fromSupplier(() -> getName())); //Supplier methods, wont call method if there is no subscriber

		System.out.println("\n");

		//Above is equivalent to below:
		Supplier<String> supplier = () -> getName();
		mono2 = Mono.fromSupplier(supplier);

		//IT is only called when subscribed
		mono2.subscribe(System.out::println);
	}

	private static String getName() {
		System.out.println("I am called");
		return "Method which has been called";
	}
}
