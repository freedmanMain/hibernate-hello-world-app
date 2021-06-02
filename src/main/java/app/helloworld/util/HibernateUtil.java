package app.helloworld.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        return getMetaDataSources().buildMetadata().buildSessionFactory();
    }

    private static StandardServiceRegistryBuilder getStandardServiceRegisterBuilder() {
        StandardServiceRegistryBuilder serviceRegistryBuilder =
                new StandardServiceRegistryBuilder();
        serviceRegistryBuilder
                .applySetting("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .applySetting("hibernate.connection.url", "jdbc:mysql://localhost:3306/hello_world?serverTimezone=UTC")
                .applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .applySetting("hibernate.connection.username", "root")
                .applySetting("hibernate.connection.password", "1234")
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.hbm2ddl.auto", "create-drop");
        return serviceRegistryBuilder;
    }

    private static MetadataSources getMetaDataSources() {
        MetadataSources metadataSources = new MetadataSources(getStandardServiceRegisterBuilder().build());
        metadataSources.addAnnotatedClass(app.helloworld.model.Message.class);
        return metadataSources;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
