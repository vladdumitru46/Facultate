using System.Collections.Generic;
using tripModel;

namespace Proiect.repository
{
    public interface ITripRepository : IGenericRepository<int, Trip<int>>
    {
        List<Trip<int>> FindAllTripsByLandMark(int idLandMark);
        int UpdateNumberOfSeatsAvailable(int numberOfSeats, int idTrip);
    }
}