package demoClasses;

import reactor.core.publisher.Flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Part8BackPressure {
	public static void main(String[] args) {
		//Backpressure is when a downstream can tell an upstream to send it fewer data in order to prevent it from being overwhelmed.

		Flux.just(1, 2, 3, 4)
		    .log()
		    .subscribe(new Subscriber<Integer>() {
		    	Subscription s;

			    @Override
			    public void onSubscribe(Subscription s) {
			    	this.s = s;
				    s.request(1);
			    }

			    @Override
			    public void onNext(Integer integer) {
				    System.out.println(integer);
				    try {
					    Thread.sleep(2000);
				    } catch (InterruptedException e) {
					    e.printStackTrace();
				    }
				    s.request(1);

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
