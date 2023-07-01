package chat.network.objectprotocol;

import chat.network.dto.UserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Mar 18, 2009
 * Time: 4:24:37 PM
 */
public class LogoutRequest implements Request {
    private UserDTO user;

    public LogoutRequest(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
