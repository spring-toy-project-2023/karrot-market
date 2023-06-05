package com.karrot.domain.demo_postgis_docker;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class ActivityAreasPK implements Serializable {
    private Long user_id;
    private Long reference_area_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityAreasPK)) return false;
        ActivityAreasPK that = (ActivityAreasPK) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(reference_area_id, that.reference_area_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, reference_area_id);
    }
}
