using System.Data;
using Npgsql;

namespace persistence.repositoryes
{
    internal class DbcUtils
    {
        public static IDbConnection GetConnection()
        {
            return new NpgsqlConnection(
                @"Server=localhost;Port=5432;User Id=postgres;Password=postgres;Database=musicFestival;");
        }
    }
}