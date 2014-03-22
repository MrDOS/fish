package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class FishtailSessionStorageProvider implements SessionStorageProvider
{
    private static final String TEST_HOSTNAME = "falcon.acadiau.ca";
    private static final int REACHABLE_TIMEOUT = 1000;

    @Override
    public boolean isProviderAvailable()
    {
        try
        {
            return InetAddress
                    .getByName(FishtailSessionStorageProvider.TEST_HOSTNAME)
                    .isReachable(FishtailSessionStorageProvider.REACHABLE_TIMEOUT);
        }
        catch (UnknownHostException e)
        {
        }
        catch (IOException e)
        {
        }
        return false;
    }

    @Override
    public void submitSession(FishingSession session)
    {
        // session to json, then http post it
        Gson gson = new Gson();

        JsonElement jsonElement = gson.toJsonTree(session);
        jsonElement.getAsJsonObject().addProperty("catches", session.getFish().size());
        String s = gson.toJson(jsonElement);

        System.out.print(s);
    }
}
