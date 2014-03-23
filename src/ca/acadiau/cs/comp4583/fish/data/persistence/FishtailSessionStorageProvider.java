package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import ca.acadiau.cs.comp4583.fish.data.FishingSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * A connection to <a href="https://github.com/MrDOS/fishtail">Fishtail</a>.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 * @author Jeremy Wheaton <105823w@acadiau.ca>
 */
public class FishtailSessionStorageProvider implements SessionStorageProvider
{
    private static final String TEST_HOSTNAME = "falcon.acadiau.ca";
    private static final int REACHABLE_TIMEOUT = 1000;

    private final String endpoint;
    private final String secret;

    public FishtailSessionStorageProvider(String endpoint, String secret)
    {
        this.endpoint = endpoint;
        this.secret = secret;
    }

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
    public void submitSession(List<FishingSession> sessions) throws IOException
    {
        /* First, we must serialize the sessions to JSON. */
        String json = new Gson().toJson(sessions, new TypeToken<List<FishingSession>>() {}.getType());

        /* Now we POST it off. */
        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(this.endpoint);
        LinkedList<NameValuePair> parameters = new LinkedList<NameValuePair>();
        parameters.add(new BasicNameValuePair("secret", this.secret));
        parameters.add(new BasicNameValuePair("session", json));

        try
        {
            request.setEntity(new UrlEncodedFormEntity(parameters));
        }
        catch (UnsupportedEncodingException e)
        {
            /* URL encoding should always be supported. */
        }

        client.execute(request);
    }
}