package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import ca.acadiau.cs.comp4583.fish.data.FishingSession;

import com.google.gson.GsonBuilder;
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
    private static final int TEST_PORT = 80; /* HTTP */
    private static final int REACHABLE_TIMEOUT = 1000;

    private static final int SECRET_LENGTH = 40;

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
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(
                    FishtailSessionStorageProvider.TEST_HOSTNAME,
                    FishtailSessionStorageProvider.TEST_PORT),
                    FishtailSessionStorageProvider.REACHABLE_TIMEOUT);
            socket.close();
            return true;
        }
        catch (IOException e)
        {
            /* Any exceptions can be considered a test failure. */
            return false;
        }
    }

    @Override
    public void submitSessions(List<FishingSession> sessions) throws IOException
    {
        /* First, we must serialize the sessions to JSON. */
        String json = new GsonBuilder()
                .serializeNulls()
                .create()
                .toJson(sessions, new TypeToken<List<FishingSession>>() {}.getType());

        /* Now we POST it off. */
        HttpClient client = new DefaultHttpClient();

        HttpPost request = new HttpPost(this.endpoint);
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("secret", this.secret));
        parameters.add(new BasicNameValuePair("sessions", json));

        try
        {
            request.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            /* URL encoding should always be supported. */
        }

        String response = EntityUtils.toString(client.execute(request).getEntity());
        Log.d(FishtailSessionStorageProvider.class.getSimpleName(), response);
        /* Error checking? Who cares! If the data was rejected, chances are
         * incredibly good it's not something that resubmission will fix. */
    }

    /**
     * Load and decode the Fishtail secret key from an input stream.
     * 
     * @param stream the stream containing the encoded secret key
     * @return the Fishtail secret key
     */
    public static String loadSecret(InputStream stream) throws IOException
    {
        StringBuilder secret = new StringBuilder(FishtailSessionStorageProvider.SECRET_LENGTH);
        for (int i = 0; i < FishtailSessionStorageProvider.SECRET_LENGTH; i++)
            secret.append((char) stream.read());

        return secret.toString();
    }
}