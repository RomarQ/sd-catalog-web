package sd.catalog.model;

import java.util.Objects;

public abstract class BaseEntity<PK> {

    public abstract PK getId();

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
