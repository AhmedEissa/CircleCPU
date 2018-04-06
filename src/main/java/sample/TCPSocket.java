package sample;

import protocol.TextProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPSocket{

    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;

    public TCPSocket() throws IOException {
        establishConnection();
    }

    private void establishConnection() throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void sendDataToServer(TextProtocol textProt) throws IOException {
        outStream.writeObject(textProt);
    }

    public TextProtocol receiveDataFromServer() throws IOException, ClassNotFoundException {
        TextProtocol textProt = (TextProtocol) inStream.readObject();
        return textProt;
    }




}
