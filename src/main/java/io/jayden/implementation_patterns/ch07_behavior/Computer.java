package io.jayden.implementation_patterns.ch07_behavior;

import java.util.Optional;

public class Computer {

    void compute1() {
        Server server = getServer();
        if (server != null) {
            Client client = server.getClient();
            if(client != null) {
                Request request = client.getRequest();
                if(request != null) {
                    processRequest(request);
                }
            }
        }
    }

    void compute2() {
        Server server = getServer();
        if (server == null) return;

        Client client = server.getClient();
        if(client == null) return;

        Request request = client.getRequest();
        if(request == null) return;

        processRequest(request);
    }

    void compute3() {
        Optional.ofNullable(getServer())
                .map(Server::getClient)
                .map(Client::getRequest)
                .ifPresent(this::processRequest);
    }

    private void processRequest(Request request) {
    }

    private Server getServer() {
        return new Server();
    }

    private class Server {
        public Client getClient() {
            return new Client();
        }
    }

    private class Client {
        public Request getRequest() {
            return new Request();
        }
    }

    private class Request {
    }
}
