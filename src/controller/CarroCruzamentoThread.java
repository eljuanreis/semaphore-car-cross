package controller;

import java.util.concurrent.Semaphore;

public class CarroCruzamentoThread extends Thread {

	private final int MCruzamento = 1000;
	private final int CarroMS = 100;

	private String lado;
	private Semaphore semaforo;

	public CarroCruzamentoThread(String lado, Semaphore semaforo) {
		this.lado = lado;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {

		try {
			andar(0);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Início seção crítica
		try {
			this.semaforo.acquire();
			cruzar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.semaforo.release();
		}
		// Fim seção crítica
	}

	private void andar(int carroMover) throws InterruptedException {
		carroMover += (int) (Math.random() * 400 - 0) + 0; // Isso vai me dar de 0 a 400 metros
		System.out.println("#" + (int) getId() + " está andando (KM:" + carroMover + ")");

		// o carro anda 100m/s => 1s => 100 metros
		// Xs => X * 100
		// 2s => 2 * 100 => ele faz 200 metros em 2s

		int sleepTime = carroMover / this.CarroMS;

		// Transforma em milisegundos
		sleep(sleepTime * 1000);

		if (carroMover < this.MCruzamento) {
			andar(carroMover);
		}
	}

	private void cruzar() {
		System.out.println("#" + (int) getId() + " acabou de cruzar o lado " + this.lado + " do cruzamento");
	}
}
