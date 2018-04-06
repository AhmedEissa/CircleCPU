package server;

import protocol.TextProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class DBServer {


    public static void main(String[] aregs) throws IOException {

        DBServer dbServer = new DBServer();
        dbServer.start();

    }

    private void start() throws IOException {
        System.out.println("Server Ready!");
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    connectionEstablished(connectionSocket);
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            });
            thread.start();
        }
    }

    private void connectionEstablished(Socket connectionSocket) throws IOException, ClassNotFoundException, SQLException {
        DBManager dbManager = new DBManager();
        boolean closeConnection = true;
        ObjectInputStream inStream = new ObjectInputStream(connectionSocket.getInputStream());
        ObjectOutputStream outStream = new ObjectOutputStream(connectionSocket.getOutputStream());

        while (closeConnection) {

            TextProtocol clientMessage = (TextProtocol) inStream.readObject();
            switch (clientMessage.getHeader()) {
                case "saveCPUUtil":
                    insertValue(dbManager,clientMessage.getData());
                    outStream.writeObject(readLastValue(dbManager));
                    break;

                case "closeConnection":
                    connectionSocket.close();
                    closeConnection = false;
                    break;
            }
        }
    }

    private void insertValue(DBManager dbManager, int y) {
        try {
            dbManager.insertCPUUtil(y);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TextProtocol readLastValue(DBManager dbManager) throws SQLException {
        int x = dbManager.receiveLastRecordInserted();
        return new TextProtocol("lastCPUUtil",x);
    }

}
