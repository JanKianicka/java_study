package java_study;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class GreetingClient {
	public static void main(String[] args) {
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);

			System.out.println("Server says " + in.readUTF());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// Hurah, it has worked with local host
// C:\repo\java_study\src>java java_study.GreetingServer 6066
// and connecting client to 192.168.160.172 6066
// This was the output:

//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60784
//Hello from /192.168.160.172:60784
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60785
//Hello from /192.168.160.172:60785
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60787
//Hello from /192.168.160.172:60787
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60788
//Hello from /192.168.160.172:60788
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60789
//Hello from /192.168.160.172:60789
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60790
//Hello from /192.168.160.172:60790
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60791
//Hello from /192.168.160.172:60791
//Waiting for client on port 6066...
//Just connected to /192.168.160.172:60792
//Hello from /192.168.160.172:60792
//Waiting for client on port 6066...
//Socket timed out!
//
//C:\repo\java_study\src>
