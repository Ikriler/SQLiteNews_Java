package com.example.news;

public class News {
    private Integer id;
    private String header;
    private String content;
    private String postDate;

    public News(Integer id, String header, String content, String postDate) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.postDate = postDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return this.id;
    }

    public String getHeader() {
        return this.header;
    }

    public String getPostDate() {
        return this.postDate;
    }

    public String getContent() {
        return this.content;
    }
}
