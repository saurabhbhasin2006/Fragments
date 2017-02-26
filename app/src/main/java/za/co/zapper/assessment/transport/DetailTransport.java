package za.co.zapper.assessment.transport;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import za.co.zapper.assessment.Util.ServerURLs;
import za.co.zapper.assessment.dao.DetailDao;
import za.co.zapper.assessment.dao.ZapperDatabase;
import za.co.zapper.assessment.domain.Detail;
import za.co.zapper.assessment.transport.bean.DetailTransportBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class DetailTransport {

    private String error = null;
    static String serverURL = ServerURLs.MASTER_URL.toString();

    private static DetailTransport detailTransport;
    Context context;


    private DetailTransport(Context context){
        this.context = context;

    }

    public static DetailTransport getInstance(Context context){
        if(detailTransport == null){
            detailTransport = new DetailTransport(context);
        }
        return detailTransport;
    }


    public void setDetailTransportData(Long id) {
        DetailDao detailDao = ZapperDatabase.getInstance(context).getDaoSession().getDetailDao();
        String content;
        String error;
        /************ Make Post Call To Web Server ***********/
        BufferedReader reader = null;
        // Send data
        try {
            String detailURL = serverURL + "/"+ String.valueOf(id);
            // Defined URL  where to send data
            URL url = new URL(detailURL);

            HttpURLConnection conn = (HttpURLConnection )url.openConnection();
            conn.connect();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + " ");
            }
            conn.disconnect();
            // Append Server Response To Content String
            content = sb.toString();
            if (content != null) {
                try {
                    // Getting JSON Array node
                        JSONObject person = new JSONObject(content);
                        if(detailDao.load(person.getLong("id")) == null){
                            Detail detail  = new Detail();
                            detail.setId(person.getLong("id"));
                            detail.setFirstName(person.getString("firstName"));
                            detail.setLastName(person.getString("lastName"));
                            detail.setAge( person.getInt("age"));
                            detail.setFavouriteColour(person.getString("favouriteColour"));
                            detailDao.insertOrReplace(detail);
                        }
                } catch (JSONException ex) {
                    Log.d("DetailFragment","1st ex.toString(): " +ex);
                } finally {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        Log.d("DetailFragment","2nd ex.toString(): " +ex);
                    }
                }

            }
        } catch (Exception e) {
            Log.d("DetailFragment","3rd ex.toString(): " +e);
        }

    }
}
