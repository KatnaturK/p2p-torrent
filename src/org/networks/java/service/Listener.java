package org.networks.java.service;

import org.networks.java.helper.Const;
import org.networks.java.model.PeerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

public class Listener implements Runnable {

	private String logMsg;
	private PeerInfo peerInfo;

	public Listener() {
		this(null);
	}

	public Listener(PeerInfo peerInfo) {
		logMsg = "";
		this.peerInfo = peerInfo;
	}

	@Override
	public void run() {
		logMsg = "Peer " + peerInfo.getPeerId() + " listening on " + peerInfo.getPortNum() + ".";
		P2PLogger.getLogger().log(Level.INFO, logMsg);

		try {
			ServerSocket listener = new ServerSocket(peerInfo.getPortNum());

			int clientNum = 1;
			while(true) {

				Server server = new Server(peerInfo, listener.accept());
				new Thread(server).start();
				logMsg = "Peer " + peerInfo.getPeerId() + " connecting to client " + clientNum + ".";
				P2PLogger.getLogger().log(Level.INFO, logMsg);
				clientNum++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
