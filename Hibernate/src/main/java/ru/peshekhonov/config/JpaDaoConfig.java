package ru.peshekhonov.config;

import jakarta.persistence.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class JpaDaoConfig {

    private static final String DEFAULT_PACKAGE_FOR_ENTITIES_SEARCH = "ru.peshekhonov";

    private static JpaDaoConfig instance;

    private SessionFactory sessionFactory;
    private Session session;

    private String packageName = DEFAULT_PACKAGE_FOR_ENTITIES_SEARCH;

    private JpaDaoConfig() {
    }

    public static JpaDaoConfig getInstance() {
        if (instance == null) {
            instance = new JpaDaoConfig();
        }
        return instance;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    private void createSessionFactory(String packageName) {
        Configuration configuration = new Configuration();

        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotated = reflections.get(SubTypes.of(TypesAnnotated.with(Entity.class)).asClass());
        for (Class<?> aClass : annotated) {
            configuration.addAnnotatedClass(aClass);
        }

        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            createSessionFactory(packageName);
        }
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}
