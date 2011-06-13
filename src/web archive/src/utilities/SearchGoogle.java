package utilities;

import beans.SearchResultBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchGoogle {

    ArrayList<SearchResultBean> resultBeanArray;

    public SearchGoogle() {
        resultBeanArray = new ArrayList();
    }

    public ArrayList getArraylist(String searchstring, int pagenumber, StringBuffer referrer) throws IOException, JSONException {

        /*initialize start*/
        //int pagenumber = Integer.parseInt(page);
        int start = (pagenumber - 1) * 8;

        /* Loop twice to get 2 JSON Objects
         * each JSON Object contains 4 search results
         * each page would return 8 search results
         */
        for (int i = 1; i <= 2; i++, start += 4) {

            /*initialize search url*/
            String searchurl = getSearchUrl(start, searchstring);

            /* create a socket */
            URLConnection connection = createConnection(searchurl, referrer);

            /* get the response */
            InputStream stream = connection.getInputStream();
            StringBuilder builder = streamToBuilder(stream);

            /* Wrap the response in JSONObject */
            JSONObject json = new JSONObject(builder.toString());

            /* Get StatusCode sent by Google */
            int statusCode = json.getInt("responseStatus");

            if (statusCode == 200) {   // status(200) is OK

                /* Fetch responsedata */
                JSONObject responseData = json.getJSONObject("responseData");

                /* Ferch results */
                JSONArray results = responseData.getJSONArray("results");

                /* Iterate the results JSONArray */
                for (int j = 0; j < results.length(); j++) {

                    /* Fetch a search result */
                    JSONObject result = results.getJSONObject(j);

                    /* Push search result details to searchresultbean */
                    SearchResultBean resultbean = jsontoBean(result);                    

                    /* Add the bean to ArrayList */
                    resultBeanArray.add(resultbean);
                }
            }
        }
        return (resultBeanArray);
    }

    public ArrayList<SearchResultBean> getArraylist(String searchstring, int pagenumber, String visibleURL, StringBuffer referrer) throws IOException, JSONException {

        do {

            /*initialize start*/
            //int pagenumber = Integer.parseInt(page);
            int start = (pagenumber - 1) * 8;

            /* Loop twice to get 2 JSON Objects
             * each JSON Object contains 4 search results
             * each page would return 8 search results
             */
            for (int i = 1; i <= 2; i++, start += 4) {

                /*initialize search url*/
                String searchurl = getSearchUrl(start, searchstring);

                /* create a socket */
                URLConnection connection = createConnection(searchurl, referrer);

                /* get the response */
                InputStream stream = connection.getInputStream();
                StringBuilder builder = streamToBuilder(stream);

                /* Wrap the response in JSONObject */
                JSONObject json = new JSONObject(builder.toString());

                /* Get StatusCode sent by Google */
                int statusCode = json.getInt("responseStatus");

                if (statusCode == 200) {   // status(200) is OK

                    /* Fetch responsedata */
                    JSONObject responseData = json.getJSONObject("responseData");

                    /* Ferch results */
                    JSONArray results = responseData.getJSONArray("results");

                    /* Iterate the results JSONArray */
                    for (int j = 0; j < results.length(); j++) {

                        /* Fetch a search result */
                        JSONObject result = results.getJSONObject(j);

                        /* Push search result details to searchresultbean */
                        SearchResultBean resultbean = jsontoBean(result);

                        /* Check if Channel Matches */
                        if (resultbean.getVisibleUrl().equalsIgnoreCase(visibleURL)) {

                            /* Add the bean to ArrayList */
                            resultBeanArray.add(resultbean);
                        }
                    }
                }
            }
            pagenumber += 1;
        } while (pagenumber < 8 && resultBeanArray.size() < 8);

        return (resultBeanArray);
    }

    /* create a socket */
    private URLConnection createConnection(String searchurl, StringBuffer referrer) throws IOException, MalformedURLException {

        URL sendurl = new URL(searchurl);
        URLConnection connection = sendurl.openConnection();
        connection.addRequestProperty("Referer", referrer.toString());
        return connection;
    }

    /*initialize search url*/
    private String getSearchUrl(int start, String searchstring) {

        String startparam = "&start=" + start;
        String searchparam = "&q=" + searchstring;
        String googleapipath = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0";

        String url = googleapipath + startparam + searchparam;
        return (url);
    }

    /* wrap stream to stringbuilder */
    private StringBuilder streamToBuilder(InputStream stream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = null;

        do {    /* iterate until EOF */

            line = reader.readLine();
            if (line != null) {
                builder.append(line);
            }
        } while (line != null);

        return (builder);
    }

    /* wrap jsonobject result to searchresult bean */
    private SearchResultBean jsontoBean(JSONObject result) throws JSONException {

        /* Fetch details of search result */
        String cacheUrl = result.getString("cacheUrl");
        String content = result.getString("content");
        String title = result.getString("title");
        String titleNoFormatting = result.getString("titleNoFormatting");
        String unescapedUrl = result.getString("url");
        String url = result.getString("url");
        String visibleUrl = result.getString("visibleUrl");

        /* Generate random resultID */
        UUID randomUUID = UUID.randomUUID();
        String resultID = randomUUID.toString();

        /* Initialize search result bean */
        SearchResultBean resultbean = new SearchResultBean();

        /* Populate the bean */
        resultbean.setCacheUrl(cacheUrl);
        resultbean.setContent(content);
        resultbean.setTitle(title);
        resultbean.setTitleNoFormatting(titleNoFormatting);
        resultbean.setUnescapedUrl(unescapedUrl);
        resultbean.setUrl(url);
        resultbean.setVisibleUrl(visibleUrl);
        resultbean.setID(resultID);

        return (resultbean);
    }
}
