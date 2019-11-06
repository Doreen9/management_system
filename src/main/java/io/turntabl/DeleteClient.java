package io.turntabl;

import java.util.prefs.Preferences;

public class DeleteClient extends ClientManager {
    private Client listOfClient;

    public boolean removeClient(int id) {
        int index = -1;
        for (int i = 0, n = count(); i < n; i++) {
            if (this.listOfClient.getAddress(i).gedId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            this.listOfClient.delete(index);
            return true;
        }
        return false;

    }

}