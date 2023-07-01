using System;

[Serializable]
public class Response
{
    // private ResponseType Type;
    // private Object Data;
    //
    // public Response(){}
    //
    //
    // public ResponseType type() { return Type; }
    // public void type(ResponseType Type) { this.Type = Type;}
    //
    //
    //
    // public Object data() { return Data; }
    // public void data(Object data) { this.Data = data;}
    //
    //
    //
    // public string toString()
    // {
    //     return "Response{"+"type='"+Type+'\''+", data='"+Data+'\''+'}';
    // }
    //
    //
    // public class Builder
    // {
    //     private static Response response = new Response();
    //
    //     public Builder type(ResponseType type)
    //     {
    //         response.Type=type;
    //         return this;
    //     }
    //
    //     public Builder data(Object data)
    //     {
    //         response.Data = data;
    //         return this;
    //     }
    //
    //     public Response build() {return response;}
    // }
    private ResponseType type;
    private Object data;

    public ResponseType Type { get => this.type; set => this.type = value; }
    public object Data { get => this.data; set => this.data = value; }

    public Response() { }


    public override String ToString()
    {
        return "Response{" +
               "type='" + type + '\'' +
               ", data='" + data + '\'' +
               '}';
    }


    public class Builder
    {
        private Response response = new Response();

        public Builder type(ResponseType type)
        {
            response.Type = type;
            return this;
        }

        public Builder data(Object data)
        {
            response.Data = data;
            return this;
        }

        public Response build()
        {
            return response;
        }
    }
}