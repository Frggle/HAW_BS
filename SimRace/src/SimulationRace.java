import java.util.ArrayList;
import java.util.Collections;

public class SimulationRace {
	public static void main(String[] args) {
		ArrayList<Car_Thread> racers = new ArrayList<Car_Thread>();

		int _numberOfCar = 3;
		int _numberOfRound = 5;

		for (int i = 0; i < _numberOfCar; i++) {
			// Thread erzeugen
			racers.add(new Car_Thread(i + 1, _numberOfRound));
		}

		// Run Accident-Thread
		Accident accident = new Accident(_numberOfRound, Thread.currentThread());
		accident.start();

		// Threads starten
		for (int i = 0; i < _numberOfCar; i++) {
			racers.get(i).start(); // führt die run() im Car_Thread aus
		}
		try {
			// Auf Threads warten
			for (int i = 0; i < _numberOfCar; i++) {
				racers.get(i).join();
			}
			// Alle Autos im Ziel, Unfall-Thread wird unterbrochen
			accident.interrupt();
			
			// Ergebnisausgabe
			Collections.sort(racers);			
			int place = 1;
			System.err.println("    **** Rennergebnisse ****");
			for(Car_Thread car : racers)
			{
				System.err.println(place + ". Platz Wagen " + car.getCarNumber() 
						+ " -- Zeit: " + car.getUsedTime() + "ms");
				place++;
			}

		} catch (InterruptedException e) {
			// Unfallbehandlung
			for(Car_Thread car : racers)
			{
				car.interrupt();
			}
			System.err.println("<<<<<<<<<<<<<< Rennen unterbrochen - Unfall! >>>>>>>>>>>>>>");
		}
	}
}
