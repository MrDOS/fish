package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    public void submitSession(List<FishingSession> sessions)
    {
        /* First, we must serialize the sessions to JSON. */
        String json = new Gson().toJson(sessions, new TypeToken<List<FishingSession>>() {}.getType());

        System.out.print(json);
    }
}