using System.Collections.Generic;
using tripModel;

public interface IServices
{
    void Login(Person<int> person, IObserver client);

    List<TripDTO<int>> FindAllTrips();

    void BookTrip(TripDTO<int> tripDto);
    
    void Logout(Person<int> person, IObserver obs);
}