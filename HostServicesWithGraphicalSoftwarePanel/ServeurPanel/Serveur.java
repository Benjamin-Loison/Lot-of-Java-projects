import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Serveur {
	final static int port = 9631;
	final static int taille = 1024;
	static byte buffer[] = new byte[taille];

	@SuppressWarnings({ "resource" })
	public static void main(String argv[]) throws Exception {
		DatagramSocket socket = new DatagramSocket(port);
		String donnees = "";
		int taille = 0;
		String ligne = "";

		System.out.println("Lancement du serveur !");
		while (true) {
			System.out.println("Prêt à capter !");
			DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
			DatagramPacket envoi = null;
			socket.receive(paquet);

			System.out.println("\n" + paquet.getAddress());
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);

			if (donnees.contains("serveur1")) {
				System.out.println(donnees + "...");
				File monFichier = new File(donnees + ".txt");
				if (monFichier.exists()) {
					Scanner sc = new Scanner(new File(donnees + ".txt"));
					while (sc.hasNextLine()) {
						ligne = sc.nextLine();
						donnees = ligne;
					}
					envoi = new DatagramPacket(donnees.getBytes(), donnees.length(), paquet.getAddress(),
							paquet.getPort());
					socket.send(envoi);
					System.out.println("Envoi !");
				}
			}
		}
	}

}
