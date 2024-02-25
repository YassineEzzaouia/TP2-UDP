package EX2;
import java.io.*;
import java.net.*;

public class serveur {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(987s6);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                voiture voiture = (voiture) ois.readObject();

                voiture.setCarburant(100);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(voiture);
                byte[] sendData = baos.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                socket.send(sendPacket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
