

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            System.out.println("Server started. Waiting for requests...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        ///////// Handles the TXT.files
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Receive file from router
            int fileSize = in.readInt();
            byte[] fileBytes = new byte[fileSize];
            in.readFully(fileBytes);

            // Process file (e.g., save to disk)
            saveFile(fileBytes, "Received_Run_Instructions_file.txt");

            // Send response to router
            out.writeUTF("File received successfully!");



            ///////////////// Read sound file
            byte[] soundData = readSoundFile("cheer_and_applause.wav");

            // Send sound file size and data to router
            out.writeInt(soundData.length);
            out.write(soundData);
            System.out.println("Server sent sound file to router.");


        } catch (IOException e) {
            System.err.println("Error in server thread: " + e.getMessage());
        }
    }
    //// Saves Txt.file to a new file
    private void saveFile(byte[] data, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data);
        }
    }

    ////////////////// Reads Sound files
    private byte[] readSoundFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] soundData = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(soundData);
        }
        return soundData;
    }
}
