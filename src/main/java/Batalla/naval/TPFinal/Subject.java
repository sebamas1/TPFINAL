package main.java.Batalla.naval.TPFinal;

import main.java.Batalla.naval.TPFinal.UI.Observer;

public interface Subject {
  
  public void registerObserver(Observer observer);
  
  public void detachObserver(Observer observer);
  
  public void notifyObservers();

}