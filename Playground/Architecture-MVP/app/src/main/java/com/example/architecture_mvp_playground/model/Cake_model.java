package com.firstcode.cakedemo.model;

import com.google.gson.annotations.Expose;


public class Cake_model {

    //TODO 6. @Expose makes it recognizable by the Retrofit
    @Expose
    private String title;
    @Expose
    private String desc;
    @Expose
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //    @Expose
//    private String title;
//    @Expose
//    private String desc;
//    @Expose
//    private String image;
//
//    /**
//     *
//     * @return
//     * The title
//     */
//    public String getTitle() {
//        return title;
//    }
//
//    /**
//     *
//     * @param title
//     * The title
//     */
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    /**
//     *
//     * @return
//     * The desc
//     */
//    public String getDesc() {
//        return desc;
//    }
//
//    /**
//     *
//     * @param desc
//     * The desc
//     */
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    /**
//     *
//     * @return
//     * The image
//     */
//    public String getImage() {
//        return image;
//    }
//
//    /**
//     *
//     * @param image
//     * The image
//     */
//    public void setImage(String image) {
//        this.image = image;
//    }

}
