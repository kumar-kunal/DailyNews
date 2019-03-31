package com.example.kunal.newsapp.Model;

public class News {
    Source source;
    String author,title,description,url,imageUrl,publishedAt;

    public News(String author, String title, String description, String url, String imageUrl, String publishedAt,Source source) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedAt() {

        return publishedAt;
    }
}
