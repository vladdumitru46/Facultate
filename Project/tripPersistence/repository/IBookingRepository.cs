using Proiect.repository;
using tripModel;

namespace tripPersistence
{
    public interface IBookingRepository : IGenericRepository<int, Booking<int>>
    {
        
    }
}