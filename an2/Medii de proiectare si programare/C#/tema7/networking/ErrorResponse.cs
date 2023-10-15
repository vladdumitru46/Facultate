using System;

namespace networking
{
    [Serializable]
    public class ErrorResponse : Response
    {
        public ErrorResponse(string message)
        {
            Message = message;
        }

        public string Message { get; set; }
    }
}