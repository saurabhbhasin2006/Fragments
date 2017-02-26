package za.co.zapper.assessment.transport;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import za.co.zapper.assessment.Util.ServerURLs;
import za.co.zapper.assessment.dao.MasterDao;
import za.co.zapper.assessment.dao.ZapperDatabase;
import za.co.zapper.assessment.domain.Master;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SaurabhB on 2017/02/23.
 */
public class MasterTransport {


    private String error = null;
    static String serverURL = ServerURLs.MASTER_URL.toString();
    private static MasterTransport masterTransport;
    Context context;

     private MasterTransport(Context context){
         this.context = context;
    }

    public static MasterTransport getInstance(Context context){
        if(masterTransport == null){
            masterTransport = new MasterTransport(context);
        }
        return masterTransport;
    }

    public Boolean getMasterTransportData() {
        boolean dataDownloaded = false ;
        MasterDao masterDao = ZapperDatabase.getInstance(context).getDaoSession().getMasterDao();
        String content;
        String error;
        BufferedReader reader = null;

        try {
            URL url = new URL(serverURL) ;
            HttpURLConnection conn = (HttpURLConnection )url.openConnection();
            conn.connect();
            // Get the server response
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
                    JSONArray contacts = new JSONArray(content);
                    System.out.println("contacts : " + contacts.toString());

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        long id = c.getLong("id");
                        String firstName = c.getString("firstName");
                        String lastName = c.getString("lastName");

                        if(masterDao.load(id) == null){
                            Master master = new Master();
                            master.setId(id);
                            master.setFirstName(firstName);
                            master.setLastName(lastName);
                            masterDao.insertOrReplace(master);
                            DetailTransport.getInstance(context).setDetailTransportData(id);
                            dataDownloaded = true;
                        }
                    }
                } catch (JSONException ex) {
                    Log.d("MasterTransport","Exception : " + ex);
                } finally {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        Log.d("MasterTransport","Exception : " + ex);
                    }
                }
            }
        } catch (Exception e) {
               Log.d("MasterTransport","Exception : " + e);
        }
        finally {

        }
       return dataDownloaded;
    }
}