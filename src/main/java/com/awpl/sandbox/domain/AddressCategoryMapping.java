
package com.awpl.sandbox.domain;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "category"
})
public class AddressCategoryMapping {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("category")
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
    protected final static Object NOT_FOUND_VALUE = new Object();
    @ManyToOne
    private Address address;

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The category
     */
    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * (Required)
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    protected boolean declaredProperty(String name, Object value) {
        if ("id".equals(name)) {
            if (value instanceof Long) {
                setId(((Long) value));
            } else {
                throw new IllegalArgumentException(("property \"id\" is of type \"java.lang.Long\", but got "+ value.getClass().toString()));
            }
            return true;
        } else {
            if ("category".equals(name)) {
                if (value instanceof Category) {
                    setCategory(((Category) value));
                } else {
                    throw new IllegalArgumentException(("property \"category\" is of type \"com.awpl.sandbox.domain.Category\", but got "+ value.getClass().toString()));
                }
                return true;
            } else {
                return false;
            }
        }
    }

    protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
        if ("id".equals(name)) {
            return getId();
        } else {
            if ("category".equals(name)) {
                return getCategory();
            } else {
                return notFoundValue;
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public<T >T get(String name) {
        Object value = declaredPropertyOrNotFound(name, AddressCategoryMapping.NOT_FOUND_VALUE);
        if (AddressCategoryMapping.NOT_FOUND_VALUE!= value) {
            return ((T) value);
        } else {
            throw new IllegalArgumentException((("property \""+ name)+"\" is not defined"));
        }
    }

    public void set(String name, Object value) {
        if (!declaredProperty(name, value)) {
            throw new IllegalArgumentException((("property \""+ name)+"\" is not defined"));
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(category).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddressCategoryMapping) == false) {
            return false;
        }
        AddressCategoryMapping rhs = ((AddressCategoryMapping) other);
        return new EqualsBuilder().append(id, rhs.id).append(category, rhs.category).isEquals();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }

}
