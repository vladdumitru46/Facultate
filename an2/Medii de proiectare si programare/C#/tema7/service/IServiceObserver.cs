using model.domains;

namespace service
{
    public interface IServiceObserver
    {
        void TicketsSold(Buyer buyers);
    }
}