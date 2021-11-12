package demoClasses;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class ChangeClassName {

	public static void main(String[] args) {

		//PART 1
		System.out.println("PART 1");

		Mono<Integer> mono = Mono.just(1); //Creates a mono with the integer value 1 as element

		System.out.println("Mono without any subscribers: " + mono);

		mono.subscribe(); //Data will be emitted but no consumer doesnt do anything.

		mono.subscribe(i -> System.out.println("Mono after subscriber " + i)); //Data will be emitted and consumer will print data.

		//PART 2 - Subscriber interface no request data wont be emitted
		System.out.println("PART 2");

		mono.subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {

			}

			@Override
			public void onNext(Integer integer) {
				System.out.println(integer);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		});

		//PART 2 - onSubscribe has the request, data will be emitted
		System.out.println("PART 3");

		mono.subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE); //MAX_VALUE represents the max number of requests to be sent
			}

			@Override
			public void onNext(Integer integer) {
				System.out.println(integer);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		});


		//PART 3 - Overriding subscriber with lambda - no error.
		System.out.println("PART 4");

		mono.subscribe(
				item -> System.out.println("item received " + item), //onNext (consumer), what will be done with emitted data
				err -> System.out.println("Hey I am error " + err), //onError (consumer), Will run in case of an error
				() -> System.out.println("Completed.") //onComplete(runnable) - Should be runnable
		);

		//PART 4 - Error case
		System.out.println("PART 5");

		mono = Mono.just(1).map(i -> i/0); //Error case
		mono.subscribe(
				item -> System.out.println("item received " + item), //onNext (consumer), what will be done with emitted data
				err -> System.out.println("Hey I am error " + err), //onError (consumer), Will run in case of an error
				() -> System.out.println("Completed.") //onComplete(runnable) - Should be runnable
		);

		//PART 5 - Logging
		System.out.println("PART 6");

		mono.just(1)
		    .log()
		    .subscribe(System.out::println);

		//PART 6 - Do not use just if there is no ready data
		System.out.println("PART 7");

		mono.just(getName()); //When this mono is created it will call the method but this is unrequired if there is no subscribers

		//Part 7 - Supplier methods, wont call method if there is no subscriber
		System.out.println("PART 8");

		mono.just(Mono.fromSupplier(() -> getName()));

		//Above is equivalent to below:
		Supplier<Integer> supplier = () -> getName().length();
		mono = Mono.fromSupplier(supplier);

		//When subscribed
		mono.subscribe(System.out::println);



		//PART 8 - Synchronous/Asynchronous
		System.out.println("PART 9");


		//PART 7 - Flux
		//PART 8 - Backpressure
		//PART 9 - Cold Stream, Hot Stream
		//PART 10 - Throttling











	}

	private static String getName() {
		System.out.println("Generating name...");
		return "Charmander";
	}

	private static Mono<String> getNameMono() {
		System.out.println("Entered getNameMono method...");
		return Mono.fromSupplier(() -> {
			System.out.println("Generating name for getNameMono...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("could not sleep");;
			}
			return "Pikachu";
		});
	}
}

