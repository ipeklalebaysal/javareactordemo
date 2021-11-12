package demoClasses;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Part6Synchronisation {

	public static void main(String[] args) {

		System.out.println("Without synchronisation");
		getPika();
		getPika()
				.subscribe(System.out::println);
		getPika();


		System.out.println("\n");
		System.out.println("With synchronisation");
		getPika();
		getPika()
				.subscribeOn(Schedulers.boundedElastic())
				.subscribe(System.out::println);
		getPika();

//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	private static Mono<String> getPika() {
		System.out.println("entered getPika method");
		return Mono.fromSupplier(() -> {
			System.out.println("Generating pika..");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Generated pika..");
			return "Pikachu";
		}).map(String::toUpperCase);
	}
}
