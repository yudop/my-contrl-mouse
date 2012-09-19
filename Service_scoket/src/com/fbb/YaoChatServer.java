package com.fbb;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class YaoChatServer extends Thread {

	private ServerSocket server = null;
	private static final int PORT = 5000;
	private BufferedWriter writer;
	private BufferedReader reader;
	private Robot robot;
	private Dimension dim;
	private int width;
	private int height;

	private YaoChatServer() throws IOException {
		server = new ServerSocket(PORT, 100);
		System.out.println("Server starting..");
		try {
			robot = new Robot();
			dim = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int)dim.getWidth();
			height =  (int)dim.getHeight();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Socket client;
		String txt;

		try {
			while (true) {
				client = server.accept();
				System.out.println("client connected..");

				if (client != null) {
					reader = new BufferedReader(new InputStreamReader(
							client.getInputStream()));
				}
				while (true) {
					// txt = ReceiveMsg(client);
					System.out.println("server get input from client socket..");
					txt = reader.readLine();
					System.out.println();

					processContext(txt);
					SendMsg(client, txt);
					break;
				}

				CloseSocket(client);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private void processContext(String txt) {
		int code = Integer.valueOf(txt);
		switch (code) {
		case 1:
			robot.mouseMove(0, 0);
			break;
		case 2:
			robot.mouseMove(width, 0);
			break;
		case 3:
			robot.mouseMove(0, height);
			break;
		case 4:
			robot.mouseMove(width, height);
			break;
		case 5:
			robot.mouseMove(width/2, height/2);
			break;

		default:
			break;
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

	// private String ReceiveMsg(Socket socket) throws IOException {
	// reader = new BufferedReader(new InputStreamReader(
	// socket.getInputStream()));
	// System.out.println("server get input from client socket..");
	// String txt = "Sever send:" + reader.readLine();
	//
	// return txt;
	// }

	public static void main(final String args[]) throws IOException {
		YaoChatServer yaochatserver = new YaoChatServer();
		if (yaochatserver != null) {
			yaochatserver.start();
		}
	}

}