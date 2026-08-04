package com.vmp.entity.vo;

public class ProductImageVO {

    /**
     * 需要输出的本地图片绝对路径。
     */
    private String imagePath;

    /**
     * 当前返回的是否为默认图片。
     */
    private Boolean defaultImage;

    /**
     * 返回图片是否存在。
     */
    private Boolean available;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}