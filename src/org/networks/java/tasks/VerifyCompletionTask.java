package org.networks.java.tasks;

import org.networks.java.service.Peer;

import java.io.IOException;
import java.util.TimerTask;

public class VerifyCompletionTask extends TimerTask {

    private final Peer peer;

    public VerifyCompletionTask(Peer peer) {
        this.peer = peer;
    }

    @Override
    public void run() {
        if (true
                && peer.peerInfo.isFilePresent()) {
            try {
                peer.getTaskTimer().cancel();
                peer.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
