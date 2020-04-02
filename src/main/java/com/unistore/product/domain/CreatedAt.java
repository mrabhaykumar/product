package com.unistore.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CreatedAt {
    private Timestamp $date;


    public Timestamp get$date() {
        return $date;
    }

    public void set$date(Timestamp $date) {
        this.$date = $date;
    }

    public CreatedAt(Timestamp $date) {
        this.$date = $date;
    }
}
