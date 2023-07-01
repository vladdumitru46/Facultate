package com.example.objectProtocol.protocols;

import com.example.Game;
import com.example.IService;
import com.example.IServiceObserver;
import com.example.Player;
import com.example.objectProtocol.interfaces.Request;
import com.example.objectProtocol.interfaces.Response;
import com.example.objectProtocol.requestClasses.*;
import com.example.objectProtocol.responseClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ObjectWorker implements Runnable, IServiceObserver {

    private final IService server;
    private final Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private volatile boolean connected;

    public ObjectWorker(IService server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = inputStream.readObject();
                Object response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse((Response) response);
                }
            } catch (IOException | ClassNotFoundException e) {
                connected = false;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            inputStream.close();
            outputStream.close();
            connection.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendResponse(Response response) throws IOException {
        synchronized (outputStream) {
            outputStream.writeObject(response);
            outputStream.flush();
        }
    }

    private Object handleRequest(Request request) {
        Response response = null;
        if (request instanceof LogInRequest logInRequest) {
            Player player = logInRequest.getPlayer();
            try {
                server.logIn(player.getUserName(), this);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof AddPositionRequest addPositionRequest) {
            String positoon = addPositionRequest.getPosition();
            try {
                server.addPosition(positoon);
                return new OkResponse();
            } catch (Exception e) {
                connected = false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GetLoggedInPlayers getLoggedInPlayers) {
            Player player = getLoggedInPlayers.getPlayer();
            try {
                List<Player> list = server.getLoggedInPlayers(player);
                return new GetLoggedInPlayersResponse(list);
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GetPositionsRequest getPositionsRequest) {
            Player player = getPositionsRequest.getPlayer();
            try {
                List<String> list = server.last2Positions(player);
                return new GetPositionsResponse(list);
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof GuessPositionRequest guessPositionRequest) {
            String position = guessPositionRequest.getPosition();
            Game game = guessPositionRequest.getGame();
            try {
                server.verifyPosition(position, game);
                return new OkResponse();
            } catch (Exception e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void nottifyDecision(String s, Game game, String guess) throws Exception {
        try {
            sendResponse(new GuessPositionResponse(s, game, guess));
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
