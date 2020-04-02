package com.unistore.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class PublishedAt {
    public PublishedAt(Timestamp $date) {
        this.$date = $date;
    }

    public Timestamp get$date() {
        return $date;
    }

    public void set$date(Timestamp $date) {
        this.$date = $date;
    }

    private Timestamp $date;
}
