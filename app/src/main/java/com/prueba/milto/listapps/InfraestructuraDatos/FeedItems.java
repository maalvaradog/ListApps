package com.prueba.milto.listapps.InfraestructuraDatos;

/**
 * Created by milto on 10/10/2016.
 */

public class FeedItems {

    private String author; //author
   // private String name; //im:name
    private String image_url; // im:image
    private String summary; //summary
    private String price; // im:price
   // private String im_contentType; //im:contentType
    private String rights; //rights
    private String title; //title
    private String link; //link
   // private String id; // id
   // private String im_artist; // im:artist
    private String category; //category
   // private String releaseDate; // im:releaseDate


    /**public FeedItems(String author, String image_url, String summary, String price, String rights, String title, String link, String category) {
        this.author = author;
        this.image_url = image_url;
        this.summary = summary;
        this.price = price;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.category = category;
    }*/

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
