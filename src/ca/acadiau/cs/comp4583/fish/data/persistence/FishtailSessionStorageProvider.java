package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class FishtailSessionStorageProvider implements SessionStorageProvider
{
    @Override
    public boolean isProviderAvailable()
    {
        // TODO Auto-generated method stub
        // reach falcon-bool
        try
        {
            if (InetAddress.getByName("falcon.acadiau.ca").isReachable(100))
                return true;
            else
                return false;
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
