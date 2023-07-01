using tripModel;

public interface IObserver
{
    void newBooking(TripDTO<int> tripDTO);
}