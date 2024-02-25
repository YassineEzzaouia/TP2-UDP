package EX2;
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");

            voiture voiture = new voiture("SUV", "Toyota");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voiture);
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 9876);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            voiture modifiedVoiture = (voiture) ois.readObject();

            System.out.println("Carburant après modification: " + modifiedVoiture.getCarburant());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
