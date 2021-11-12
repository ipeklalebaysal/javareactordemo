package demoClasses;

import reactor.core.publisher.Mono;

public class Part3OverridingSubscriber {

	public static void main(String[] args) {
		Mono<String> mono = Mono.just("I am an item");

		mono.subscribe(
				item -> System.out.println("item received " + item), //onNext (consumer), what will be done with emitted data
				err -> System.out.println("Hey I am error " + err), //onError (consumer), Will run in case of an error
				() -> System.out.println("Completed.") //onComplete(runnable) - Should be runnable
		);

		System.out.println("\n");

		//With an error
		mono.map(item -> item.length() / 0)
		    .subscribe(
				    item -> System.out.println("item received " + item),
				    err -> System.out.println("Hey I am error " + err),
				    () -> System.out.println("Completed.")
		    );
	}
}
