package main.java.Batalla.naval.TPFinal;

public interface Subject {

	public void registerObserver(Observer observer);

	public void detachObserver(Observer observer);

	public void notifyObservers();

}