package com.message.entities;

import java.io.Serializable;

/**
 * Created by hpj on 2015-11-27.
 */
public class Roles implements Serializable {

    private Long id;

    private String name;

    private String description;

    private boolean hasRoles;

    private boolean selected; //是否被选中（前端使用）


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasRoles() {
        return hasRoles;
    }

    public void setHasRoles(boolean hasRoles) {
        this.hasRoles = hasRoles;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
