using System;
using System.Security.Permissions;

public class TripException:Exception
{
    public TripException() { }

    public TripException(string message):base(message) { }

    public TripException(string message, Exception innerException):base(message,innerException){}
}