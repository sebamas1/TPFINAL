package main.java.model;

import main.java.view.Observer;

public interface Subject {
  
  public void registerObserver(Observer observer);
  
  public void detachObserver(Observer observer);
  
  public void notifyObservers();

}