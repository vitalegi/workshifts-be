package it.vitalegi.workshifts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkshiftsApplication {

	public static void main(String[] args) {
		System.out.println("Link library");
		try {
			System.loadLibrary("lib/jniortools");
			System.out.println("Linked");
		} catch (UnsatisfiedLinkError e) {
			System.err.println(e);
		}

		SpringApplication.run(WorkshiftsApplication.class, args);
	}

}
