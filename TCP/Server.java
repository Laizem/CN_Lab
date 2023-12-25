import java.io.*;
import java.net.ServerSocket;
import java.util.*;
import java.net.Socket;

public class Server {
	public static void main (String args []) {
		try {
			ServerSocket serverSocket =  new ServerSocket (44440);
			System.out.println("Server is listening on port 44440");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client connected"+socket.getInetAddress());
				Thread ClientHandler = new Thread (new ClientHandler (socket));
				ClientHandler.start();
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler (Socket socket){
		this.socket = socket;
	}
	@Override
	public void run(){
		try{
			BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter (socket.getOutputStream(), true);
			String filename = in.readLine();
			System.out.println("Client requested file "+filename);
			File file= new File(filename);
			if (file.exists()) {
				BufferedReader fileReader = new BufferedReader (new FileReader (file));
				String line;
				while ((line = fileReader.readLine()) != null) {
					out.println(line);
				}
				fileReader.close();
			}
			else {
				out.println("File not found");
			}
			socket.close();
			System.out.println ("Connection Closed");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
