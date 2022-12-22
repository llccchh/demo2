package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

/**
 * @author liuyinghao5
 * @date 2022/11/17 11:12
 */

public  class  EncryptDataVO<T> {


    private String dk;
    private T t;
    private String sId;


    public EncryptDataVO(String dk, T t) {
        this.dk = dk;
        this.t = t;
    }

    public EncryptDataVO(String dk, T t, String sId) {
        this.dk = dk;
        this.t = t;
        this.sId = sId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getDk() {
        return dk;
    }


    public void setDk(String dk) {
        this.dk = dk;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public EncryptDataVO() {
    }

    public EncryptDataVO(String dk, String sId) {
        this.dk = dk;
        this.sId = sId;
    }

    @Override
    public String toString() {
        return "EncryptDataVO{" +
                "dk='" + dk + '\'' +
                ", t=" + t +
                '}';
    }

    public  static <T> EncryptDataVO<T> getData(String dk, T t, String sId){
        return new EncryptDataVO<>(dk, t, sId);

    }
}
