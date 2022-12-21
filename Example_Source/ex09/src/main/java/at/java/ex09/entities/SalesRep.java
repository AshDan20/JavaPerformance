package at.java.ex09.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public SalesRep(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SalesRep() {
    }

    public static SalesRepBuilder builder() {
        return new SalesRepBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SalesRep)) return false;
        final SalesRep other = (SalesRep) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SalesRep;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "SalesRep(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    public static class SalesRepBuilder {
        private Long id;
        private String name;

        SalesRepBuilder() {
        }

        public SalesRepBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SalesRepBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SalesRep build() {
            return new SalesRep(id, name);
        }

        public String toString() {
            return "SalesRep.SalesRepBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
