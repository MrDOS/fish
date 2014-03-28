package ca.acadiau.cs.comp4583.fish.data.persistence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Looper;
import ca.acadiau.cs.comp4583.fish.R;
import ca.acadiau.cs.comp4583.fish.data.User;

/**
 * A login provider which authenticates credentials against Track My Fish.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class TrackMyFishLoginProvider extends AbstractLoginProvider
{
    final protected static char[] hexArray = "0123456789abcdef".toCharArray();

    private final String endpoint;
    private final Looper callbackLooper;

    /**
     * Construct the login provider.
     * 
     * @param endpoint the Track My Fish authentication endpoint
     */
    public TrackMyFishLoginProvider(String endpoint)
    {
        this.endpoint = endpoint;
        this.callbackLooper = Looper.myLooper();
    }

    @Override
    public void validate(final String username, final String password)
    {
        (new Thread() {
            @Override
            public void run()
            {
                HttpClient client = new DefaultHttpClient();
                HttpPost request = TrackMyFishLoginProvider.buildRequest(
                        TrackMyFishLoginProvider.this.endpoint,
                        username, password);

                try
                {
                    String response = EntityUtils.toString(client.execute(request).getEntity());
                    if (response.equals("true"))
                        new Handler(TrackMyFishLoginProvider.this.callbackLooper).post(new Runnable() {
                            @Override
                            public void run()
                            {
                                TrackMyFishLoginProvider.this.notifyLoginSuccess(new User(username));
                            }
                        });
                    else
                        new Handler(TrackMyFishLoginProvider.this.callbackLooper).post(new Runnable() {
                            @Override
                            public void run()
                            {
                                TrackMyFishLoginProvider.this.notifyLoginFailure(R.string.trackmyfish_login_rejection);
                            }
                        });
                }
                catch (IOException e)
                {
                    TrackMyFishLoginProvider.this.notifyLoginFailure(R.string.trackmyfish_login_failure);
                }
            }
        }).start();
    }

    /**
     * Build the HTTP request for authentication.
     * 
     * @param endpoint the endpoint URL
     * @param username the username
     * @param password the password
     * @return the {@link HttpPost} request
     */
    private static HttpPost buildRequest(String endpoint, String username, String password)
    {
        String passwordHash = null;
        try
        {
            passwordHash = TrackMyFishLoginProvider.bytesToHex(
                    MessageDigest
                            .getInstance("MD5")
                            .digest(password.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e)
        {
            /* MD5 should always be available. */
        }
        catch (UnsupportedEncodingException e)
        {
            /* UTF-8, being the platform default, will always be available. */
        }

        HttpPost request = new HttpPost(endpoint);
        LinkedList<NameValuePair> parameters = new LinkedList<NameValuePair>();
        parameters.add(new BasicNameValuePair("u", username));
        parameters.add(new BasicNameValuePair("p", passwordHash));

        try
        {
            request.setEntity(new UrlEncodedFormEntity(parameters));
        }
        catch (UnsupportedEncodingException e)
        {
            /* URL encoding should always be supported. */
        }

        return request;
    }

    /**
     * Convert an array of bytes to a hex string. Blatantly stolen from
     * http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex
     * -string-in-java
     * 
     * @param bytes the byte array
     * @return the byte array as a hexadecimal string
     */
    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}