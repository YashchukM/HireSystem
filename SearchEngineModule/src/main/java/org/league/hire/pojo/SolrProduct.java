package org.league.hire.pojo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Myhailo on 10.07.2015.
 */
public class SolrProduct {
    @Field("id")
    String id;

    @Field("name")
    String name;

    @Field("owner")
    String owner;

    @Field("category")
    String category;

    public SolrProduct() {}

    public SolrProduct(String id, String name, String owner, String category) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
