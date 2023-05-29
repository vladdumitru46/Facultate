using Microsoft.Data.SqlClient;
using System;
using System.Threading;

class Program
{
    public static string connectionString = @"Server=DESKTOP-SO4QA4B\SQLEXPRESS;
        Database=Club_de_fotbal;Integrated Security=true;
        TrustServerCertificate=true;";
    static SqlDataAdapter adapter = new SqlDataAdapter();
    static readonly object Lock1 = new object();
    static readonly object Lock2 = new object();
    static int maxRetries = 3;
    static int retries = 0;

    static void Main()
    {
        Thread thread1 = new Thread(ExecuteQuery1);
        Thread thread2 = new Thread(ExecuteQuery2);

        thread1.Start();
        thread2.Start();

        thread1.Join();
        thread2.Join();

        Console.WriteLine("Programul s-a încheiat.");
        Console.ReadLine();
    }
    static void ExecuteQuery1()
    {
        bool success = false;
        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            do
            {
                Console.WriteLine("retrie in 1:" + retries);
                try
                {
                    lock (Lock1)
                    {
                        conn.Open();
                        adapter.UpdateCommand = new SqlCommand("update echipament set culoare_echimanet='portocaliu' where id_echipament=6", conn);
                        adapter.UpdateCommand.ExecuteNonQuery();
                        Thread.Sleep(2000);

                        lock (Lock2)
                        {
                            adapter.UpdateCommand = new SqlCommand("update sponsori set baga_bani=3000 where nume_sponsor='dfiuagsd'", conn);
                            adapter.UpdateCommand.ExecuteNonQuery();
                            Console.WriteLine("Execuția procedurii stocate / interogarea 1 s-a terminat.");
                            success = true;
                        }
                    }
                }
                catch (ThreadAbortException)
                {
                    retries++;
                    if (retries >= maxRetries)
                    {
                        Console.WriteLine("S-a efectuat numarul maxim de reincercari");
                        return;
                    }
                }
            } while (!success);
        }
    }


    static void ExecuteQuery2()
    {

        bool success = false;
        using (SqlConnection conn = new SqlConnection(connectionString))
        {
            do
            {
                Console.WriteLine("retrie in 2:" + retries);
                try
                {
                    lock (Lock1)
                    {
                        conn.Open();
                        adapter.UpdateCommand = new SqlCommand("update echipament set culoare_echimanet='albastru' where id_echipament=6", conn);
                        adapter.UpdateCommand.ExecuteNonQuery();
                        Thread.Sleep(2000);

                        lock (Lock2)
                        {
                            adapter.UpdateCommand = new SqlCommand("update sponsori set baga_bani=3900 where nume_sponsor='dfiuagsd'", conn);
                            adapter.UpdateCommand.ExecuteNonQuery();
                            Console.WriteLine("Execuția procedurii stocate / interogarea 2 s-a terminat.");
                            success = true;
                        }
                    }
                }
                catch (ThreadAbortException)
                {
                    retries++;
                    if (retries >= maxRetries)
                    {
                        Console.WriteLine("S-a efectuat numarul maxim de reincercari");
                        return;
                    }
                }
            } while (!success);
        }

    }
}