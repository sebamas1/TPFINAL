package model;

import view.Observer;

public interface Subject {
  
  public void registerObserver(Observer observer);
  
  public void detachObserver(Observer observer);
  
  public void notifyObservers(Evento e);

}