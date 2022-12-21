package at.java.ex08.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.MERGE)
    private SalesRep salesRep;

    public Customer(Long id, String name, List<Address> addresses, SalesRep salesRep) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
        this.salesRep = salesRep;
    }

    public Customer() {
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public SalesRep getSalesRep() {
        return this.salesRep;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Customer)) return false;
        final Customer other = (Customer) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$addresses = this.getAddresses();
        final Object other$addresses = other.getAddresses();
        if (this$addresses == null ? other$addresses != null : !this$addresses.equals(other$addresses)) return false;
        final Object this$salesRep = this.getSalesRep();
        final Object other$salesRep = other.getSalesRep();
        if (this$salesRep == null ? other$salesRep != null : !this$salesRep.equals(other$salesRep)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Customer;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $addresses = this.getAddresses();
        result = result * PRIME + ($addresses == null ? 43 : $addresses.hashCode());
        final Object $salesRep = this.getSalesRep();
        result = result * PRIME + ($salesRep == null ? 43 : $salesRep.hashCode());
        return result;
    }

    public String toString() {
        return "Customer(id=" + this.getId() + ", name=" + this.getName() + ", addresses=" + this.getAddresses() + ", salesRep=" + this.getSalesRep() + ")";
    }

    public static class CustomerBuilder {
        private Long id;
        private String name;
        private List<Address> addresses;
        private SalesRep salesRep;

        CustomerBuilder() {
        }

        public CustomerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder addresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }

        public CustomerBuilder salesRep(SalesRep salesRep) {
            this.salesRep = salesRep;
            return this;
        }

        public Customer build() {
            return new Customer(id, name, addresses, salesRep);
        }

        public String toString() {
            return "Customer.CustomerBuilder(id=" + this.id + ", name=" + this.name + ", addresses=" + this.addresses + ", salesRep=" + this.salesRep + ")";
        }
    }
}
