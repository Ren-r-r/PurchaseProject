package com.ryz.entity;

import java.io.Serializable;

public class Type implements Serializable {
    private Long cId;

    private String cNumMaterialType;

    private String cName;

    private String cTypeDec;

    private static final long serialVersionUID = 1L;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcNumMaterialType() {
        return cNumMaterialType;
    }

    public void setcNumMaterialType(String cNumMaterialType) {
        this.cNumMaterialType = cNumMaterialType == null ? null : cNumMaterialType.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcTypeDec() {
        return cTypeDec;
    }

    public void setcTypeDec(String cTypeDec) {
        this.cTypeDec = cTypeDec == null ? null : cTypeDec.trim();
    }
}