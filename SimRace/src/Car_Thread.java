import java.util.Random;

public class Car_Thread extends Thread implements Comparable<Car_Thread>{
	final int _minRoundTime = 0; // in Milisec.
	final int _maxRoundTime = 99; // in Milisec.

	int _carNumber; // Autonummer
	int _elapsedTime; // gefahrende Gesamtzeit
	int _numOfRound;
	int _countRound;

	long _startTime;
	long _usedTime;

	public Car_Thread(int numCar, int numRound) {
		_carNumber = numCar;
		_numOfRound = numRound;
		_elapsedTime = 0;
		_usedTime = 0;
	}

	/**
	 * wird automatisch von start() aufgerufen
	 */
	public void run() {

		_startTime = System.nanoTime();

		for (int i = 0; i < _numOfRound && !this.isInterrupted(); i++) {
			try {
				int roundTime = randomRoundTime();
				_elapsedTime += roundTime;
				Thread.sleep(roundTime);
			} catch (InterruptedException e) {
				this.interrupt();
			}
			_usedTime = (System.nanoTime() - _startTime) / 1000000;
		}
	}

	private int randomRoundTime() {
		Random random = new Random();
		return random.nextInt(_maxRoundTime - _minRoundTime + 1)
				+ _minRoundTime;
	}

	public long getUsedTime() {
		return _usedTime;
	}

	public int getCarNumber() {
		return _carNumber;
	}

	public int getTime() {
		return _elapsedTime;
	}

	@Override
	public int compareTo(Car_Thread o) {
		return Long.compare(_usedTime, o._usedTime);
	}
}
