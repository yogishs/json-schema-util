
package com.awpl.sandbox.domain;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    "addresses",
    "name",
    "industry"
})
public class Company {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("addresses")
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<Address>();
    @JsonProperty("name")
    private String name;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("industry")
    @OneToOne(cascade = CascadeType.ALL)
    private Industry industry;
    protected final static Object NOT_FOUND_VALUE = new Object();

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

    @PrePersist
    public void prePersist() {
        for(Address entry : addresses)
	{
	entry.setCompany(this);
	}
    }

    /**
     * 
     * @return
     *     The addresses
     */
    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * 
     * @param addresses
     *     The addresses
     */
    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The industry
     */
    @JsonProperty("industry")
    public Industry getIndustry() {
        return industry;
    }

    /**
     * 
     * (Required)
     * 
     * @param industry
     *     The industry
     */
    @JsonProperty("industry")
    public void setIndustry(Industry industry) {
        this.industry = industry;
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
            if ("addresses".equals(name)) {
                if (value instanceof List) {
                    setAddresses(((List<Address> ) value));
                } else {
                    throw new IllegalArgumentException(("property \"addresses\" is of type \"java.util.List<com.awpl.sandbox.domain.Address>\", but got "+ value.getClass().toString()));
                }
                return true;
            } else {
                if ("name".equals(name)) {
                    if (value instanceof String) {
                        setName(((String) value));
                    } else {
                        throw new IllegalArgumentException(("property \"name\" is of type \"java.lang.String\", but got "+ value.getClass().toString()));
                    }
                    return true;
                } else {
                    if ("industry".equals(name)) {
                        if (value instanceof Industry) {
                            setIndustry(((Industry) value));
                        } else {
                            throw new IllegalArgumentException(("property \"industry\" is of type \"com.awpl.sandbox.domain.Industry\", but got "+ value.getClass().toString()));
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
        if ("id".equals(name)) {
            return getId();
        } else {
            if ("addresses".equals(name)) {
                return getAddresses();
            } else {
                if ("name".equals(name)) {
                    return getName();
                } else {
                    if ("industry".equals(name)) {
                        return getIndustry();
                    } else {
                        return notFoundValue;
                    }
                }
            }
        }
    }

    @SuppressWarnings({
        "unchecked"
    })
    public<T >T get(String name) {
        Object value = declaredPropertyOrNotFound(name, Company.NOT_FOUND_VALUE);
        if (Company.NOT_FOUND_VALUE!= value) {
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
        return new HashCodeBuilder().append(id).append(addresses).append(name).append(industry).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Company) == false) {
            return false;
        }
        Company rhs = ((Company) other);
        return new EqualsBuilder().append(id, rhs.id).append(addresses, rhs.addresses).append(name, rhs.name).append(industry, rhs.industry).isEquals();
    }

}
