package demoClasses;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Part9ColdHotStream {
	public static void main(String[] args) {
		//Until now, we only dealed with cold streams-fixed length, static streams
		//Hot streams: Streams that constantly needs to be reacted/Infinite streams

		ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
			while(true) {
				fluxSink.next(System.currentTimeMillis());
			}
		}).sample(Duration.ofSeconds(2)).publish();

		//This sample method above is called throttling

		publish.subscribe(System.out::println); //Wont do anything

		//publish.connect(); //Need this to run

	}
}
