package com.fbb;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class YaoChatServer extends Thread {

	private ServerSocket server = null;
	private static final int PORT = 5000;
	private BufferedWriter writer;
	private BufferedReader reader;
	private Dimension dim;
	private int width;
	private int height;
	private Thread thread;
	private ProcressRunnable run;

	private YaoChatServer() throws IOException {
		server = new ServerSocket(PORT, 100);
		System.out.println("Server starting..");
			
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)dim.getWidth();
		height =  (int)dim.getHeight();
	}

	public void run() {
		try {
			while (true) {
				Socket client = server.accept();
				System.out.println("client connected..");
				if (client != null) {
					if(run!=null){
						run.stop();
					}
					run = new ProcressRunnable(client);
					thread = new Thread(run);
					thread.start();
				}
//				CloseSocket(client);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private void CloseSocket(Socket socket) throws IOException {
		reader.close();
		writer.close();
		socket.close();
		System.out.println("client closed..");
	}

	private void SendMsg(Socket socket, String Msg) throws IOException {
		writer = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		writer.write(Msg + "\n");
		writer.flush();

	}

	public static void main(final String args[]) throws IOException {
		YaoChatServer yaochatserver = new YaoChatServer();
		if (yaochatserver != null) {
			yaochatserver.start();
		}
	}

}