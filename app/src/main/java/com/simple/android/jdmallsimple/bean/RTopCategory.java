package com.simple.android.jdmallsimple.bean;

public class RTopCategory {

    private long id;//分类id
    private String bannerUrl;//分类图片路径
    private String name;//分类名称

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
