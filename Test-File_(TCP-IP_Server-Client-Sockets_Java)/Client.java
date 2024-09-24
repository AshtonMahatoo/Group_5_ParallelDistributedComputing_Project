import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            //////////////////// Sends txt.file to router
            byte[] fileData = readFile("Run_Instructions.txt");
            out.writeInt(fileData.length);
            out.write(fileData);
            System.out.println("Client sent file to router.");

            // Receive response from router Txt.file
            String response = in.readUTF();
            System.out.println("Client received response from server via router: " + response);



            //////////////// Receive sound file from Router
            int fileSize = in.readInt();
            byte[] soundData = new byte[fileSize];
            in.readFully(soundData);

            // Save sound file received from server sound file
            saveSoundFile(soundData, "received_cheer_and_applause_sound.wav");
            System.out.println("Client received sound file from server via router.");



        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
    ////////////////////// SAVE TXT.FILE
    private static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileData = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileData);
        }
        return fileData;
    }

    /////////////////// SAVE SOUND FILE
    private static void saveSoundFile(byte[] data, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data);
        }
    }
}

