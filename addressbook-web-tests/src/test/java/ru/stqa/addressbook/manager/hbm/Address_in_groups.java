package ru.stqa.addressbook.manager.hbm;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "address_in_groups")
public class Address_in_groups {

    @Id
    public int group_id;
    @Id
    public int id;

    public Address_in_groups() {

    }

    public Address_in_groups(int id, int group_id) {
        this.id = id;
        this.group_id = group_id;
    }
}
