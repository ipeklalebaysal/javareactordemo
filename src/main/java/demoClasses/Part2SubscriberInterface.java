package demoClasses;

import reactor.core.publisher.Mono;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Part2SubscriberInterface {

	public static void main(String args[]) {
		Mono<Integer> mono = Mono.just(1);

		//Without request in onSubscribe
		mono.subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {

			}

			@Override
			public void onNext(Integer integer) {
				System.out.println("no request is called");
				System.out.println(integer);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		});

		//With request in onSubscribe
		mono.subscribe(new Subscriber<Integer>() {
			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(Integer integer) {
				System.out.println("request is called");
				System.out.println(integer);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {

			}
		});

	}
}
