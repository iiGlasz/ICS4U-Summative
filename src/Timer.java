/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ljphi
 */
public class Timer {
    public class Stopwatch {
    private long startTime;
    private boolean running;

    public void start() {
        startTime = System.nanoTime();
        running = true;
    }

    public long stop() {
        if (!running) {
            return 0;
        }
        running = false;
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long getElapsedTime() {
        if (!running) {
            return 0;
        }
        return System.nanoTime() - startTime;
    }


    public void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        try {
            Thread.sleep(5000); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long elapsedTime = stopwatch.stop();
        double elapsedSeconds = (double) elapsedTime / 1_000_000_000.0;
        System.out.println("Elapsed time: " + elapsedSeconds + " seconds");
    }
}
}
