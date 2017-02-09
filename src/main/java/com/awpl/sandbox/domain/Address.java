
package com.awpl.sandbox.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "streetAddress",
    "city",
    "state",
    "categoryMappings"
})
public class Address {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("streetAddress")
    private String streetAddress;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("city")
    private String city;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("state")
    private String state;
    @JsonProperty("categoryMappings")
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<AddressCategoryMapping> categoryMappings = new ArrayList<AddressCategoryMapping>();
    protected final static Object NOT_FOUND_VALUE = new Object();
    @ManyToOne
    private Company company;

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
     *     The streetAddress
     */
    @JsonProperty("streetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * 
     * (Required)
     * 
     * @param streetAddress
     *     The streetAddress
     */
    @JsonProperty("streetAddress")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * 
     * (Required)
     * 
     * @param city
     *     The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * (Required)
     * 
     * @param state
     *     The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @PrePersist
    public void prePersist() {
        for(AddressCategoryMapping entry : categoryMappings)
	{
	entry.setAddress(this);
	}
    }

    /**
     * 
     * @return
     *     The categoryMappings
     */
    @JsonProperty("categoryMappings")
    public List<AddressCategoryMapping> getCategoryMappings() {
        return categoryMappings;
    }

    /**
     * 
     * @param categoryMappings
     *     The categoryMappings
     */
    @JsonProperty("categoryMappings")
    public void setCategoryMappings(List<AddressCategoryMapping> categoryMappings) {
        this.categoryMappings = categoryMappings;
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
            if ("streetAddress".equals(name)) {
                if (value instanceof String) {
                    setStreetAddress(((String) value));
                } else {
                    throw new IllegalArgumentException(("property \"streetAddress\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                }
                return true;
            } else {
                if ("city".equals(name)) {
                    if (value instanceof String) {
                        setCity(((String) value));
                    } else {
                        throw new IllegalArgumentException(("property \"city\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                    }
                    return true;
                } else {
                    if ("state".equals(name)) {
                        if (value instanceof String) {
                            setState(((String) value));
                        } else {
                            throw new IllegalArgumentException(("property \"state\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                        }
                        return true;
                    } else {
                        if ("categoryMappings".equals(name)) {
                            if (value instanceof List) {
                                setCategoryMappings(((List<AddressCategoryMapping> ) value));
                            } else {
                                throw new IllegalArgumentException(("property \"categoryMappings\" is of type \"java.util.List<com.awpl.sandbox.domain.AddressCategoryMapping>\", but got "+ value.getClass().toString()));
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }

    protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
        if ("id".equals(name)) {
            return getId();
        } else {
            if ("streetAddress".equals(name)) {
                return getStreetAddress();
            } else {
                if ("city".equals(name)) {
                    return getCity();
                } else {
                    if ("state".equals(name)) {
                        return getState();
                    } else {
                        if ("categoryMappings".equals(name)) {
                            return getCategoryMappings();
                        } else {
                            return notFoundValue;
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public<T >T get(String name) {
        Object value = declaredPropertyOrNotFound(name, Address.NOT_FOUND_VALUE);
        if (Address.NOT_FOUND_VALUE!= value) {
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
        return new HashCodeBuilder().append(id).append(streetAddress).append(city).append(state).append(categoryMappings).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(id, rhs.id).append(streetAddress, rhs.streetAddress).append(city, rhs.city).append(state, rhs.state).append(categoryMappings, rhs.categoryMappings).isEquals();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }

}
