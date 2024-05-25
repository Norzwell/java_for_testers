package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.AddressData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records){
            result.add(convert(record));
        }
        return result;
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

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

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

    // Все для контактов

    static List<AddressData> convertContactList(List<ContactRecord> records) {
        List<AddressData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertContact(record));
        }
        return result;
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
    }
}
