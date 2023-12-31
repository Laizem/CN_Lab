import java.net.Socket;
import java.io.*;
import java.util.*;

public class Client{
	public static void main(String []args){
		String serverAddress="localhost";
		int serverPort=44440;
		String fileName="hello.txt";
		
		try{
			Socket socket=new Socket(serverAddress, serverPort);
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			out.println(fileName);
			String response;
			while((response=in.readLine()) != null){
				if(response.equals("File not found")){
					System.out.println("File not found on server");
					break;
				}
				System.out.println(response);
			}
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
