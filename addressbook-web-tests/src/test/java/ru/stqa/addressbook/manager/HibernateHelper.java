package ru.stqa.addressbook.manager;

import jakarta.persistence.Table;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.manager.hbm.Address_in_groups;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.AddressData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HalperBase{

    private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
            .addAnnotatedClass(ContactRecord.class)
            .addAnnotatedClass(GroupRecord.class)
            .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
            .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
            .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
            .buildSessionFactory();
    }

    //Все для группы

    static List<GroupData> convertList(List<GroupRecord> records) {
        return  records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }



    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    // Получение количества групп из таблицы
    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }


    // Создание группы в таблице
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
                });
    }

    public List<AddressData> getContactsInGroup(GroupData group) {
       return sessionFactory.fromSession(session -> {
           return convertContactList(session.get(GroupRecord.class, group.id()).contacts);

       });
    }

    static List<AddressData> convertContactList(List<ContactRecord> records) {
        return  records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
    }


    // Все для контактов

    private static AddressData convertContact (ContactRecord record) {
        return new AddressData().withId("" + record.id)
                .withFirsName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withEmail(record.email)
                .withMobile(record.mobile);
    }

    private static ContactRecord convert(AddressData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address(), data.mobile(), data.email());
    }

    public List<AddressData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContatCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void creatingAddress(AddressData addressData) {
            sessionFactory.inSession(session -> {
                session.getTransaction().begin();
                session.persist(convert(addressData));
                session.getTransaction().commit();
            });
    }

    // для таблицы address_in_group

/*    static List<AddressData> convertAddressInGroupList(List<ContactRecord> records) {
        return  records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
    }

    private static AddressData convertContact (ContactRecord record) {
        return new AddressData().withId("" + record.id)
                .withFirsName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withEmail(record.email)
                .withMobile(record.mobile);
    }

    private static ContactRecord convert(AddressData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address(), data.mobile(), data.email());
    }

    public List<AddressData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContatCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void creatingAddress(AddressData addressData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(addressData));
            session.getTransaction().commit();
        });
    }*/



    // Все для удлаения таблицы контакта связанного с группой

   /* static List<AddressData> convertAddress_in_groupsList(List<Address_in_groups> records) {
        List<AddressData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertContact(record));
        }
        return result;
    }

    private static Address_in_groups convertAddress_in_groups (Address_in_groups record) {
        return new Address_in_groups(record.id, record.group_id, record.group_id);
    }

    private static Address_in_groups convert(Address_in_groups data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new Address_in_groups(Integer.parseInt(id), data.domain_id(), data.group_id());
    }


    public long getContatInGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from address_in_groups", Long.class).getSingleResult();
        });
    }*/

}
