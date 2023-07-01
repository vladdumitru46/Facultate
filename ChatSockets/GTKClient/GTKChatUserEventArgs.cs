using System;
namespace GTKClient
{
    public enum ChatUserEvent
    {
        FriendLoggedIn, FriendLoggedOut, NewMessage
    } ;
    public class ChatUserEventArgs : EventArgs
    {
        private readonly ChatUserEvent userEvent;
        private readonly Object data;

        public ChatUserEventArgs(ChatUserEvent userEvent, object data)
        {
            this.userEvent = userEvent;
            this.data = data;
        }

        public ChatUserEvent UserEventType
        {
            get { return userEvent; }
        }

        public object Data
        {
            get { return data; }
        }
    }
}