package com.prueba.milto.listapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.prueba.milto.listapps.InfraestructuraDatos.FeedItems;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by milto on 12/10/2016.
 */

public class LeerRss extends AsyncTask<Object, Object, Document> {

    Context context;
    String address = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";
    ProgressDialog progressDialog;
    URL url;
    ArrayList <FeedItems>feedItemses;
    RecyclerView recyclerView;


    public LeerRss(Context context, RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Cargando....");
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param aVoid The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Document aVoid) {
        progressDialog.dismiss();
        super.onPostExecute(aVoid);


    }

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     */
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Document doInBackground(Object... params) {
        ProcessXml(getData());
        return null;
    }

    private void ProcessXml(Document data) {
        if (data!=null){
            Log.d("Root",data.getDocumentElement().getNodeName());
        }


       if (data!=null){
            feedItemses = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel= (Node) (Node) root.getChildNodes();
            NodeList items=channel.getChildNodes();
            for (int i=0;i<items.getLength();i++){
                Node cureentchild=items.item(i);
                    if (cureentchild.getNodeName().equalsIgnoreCase("feed")){
                    FeedItems item = new FeedItems();
                    NodeList itemchilds=cureentchild.getChildNodes();
                    for (int j=0;j<itemchilds.getLength();j++){
                        Node cureent=itemchilds.item(j);
                        if(cureent.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(cureent.getTextContent());
                        }else if (cureentchild.getNodeName().equalsIgnoreCase("author")){
                            item.setAuthor(cureent.getTextContent());
                        }else if (cureentchild.getNodeName().equalsIgnoreCase("price")){
                            item.setPrice(cureent.getTextContent());
                        }else if (cureentchild.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(cureent.getTextContent());
                        }else if (cureentchild.getNodeName().equalsIgnoreCase("description")){
                            item.setSummary(cureent.getTextContent());
                        }else if (cureent.getNodeName().equalsIgnoreCase("label")){
                            String url = cureent.getAttributes().item(0).getTextContent();
                            item.setImage_url(url);
                        }
                    }
                    feedItemses.add(item);
                    /*Log.d("itemTitle",item.getTitle());
                    Log.d("itemPrice",item.getPrice());
                    Log.d("link",item.getLink());
                    Log.d("itemDescription",item.getSummary());*/
                    Log.d("itemImage_Url",item.getImage_url());
                }
            }
        }

    }

    public Document getData(){
        try {
            url = new URL(address);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory BuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=BuilderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
