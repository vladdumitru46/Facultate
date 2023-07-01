using System;
using System.Collections.Generic;
using model.domains;

namespace networking
{
    [Serializable]
    public class GetAllShowsResponse : Response
    {
        private List<Show> list;

        public GetAllShowsResponse(List<Show> list)
        {
            this.list = list;
        }

        public List<Show> List
        {
            get => list;
            set => list = value;
        }
    }
}