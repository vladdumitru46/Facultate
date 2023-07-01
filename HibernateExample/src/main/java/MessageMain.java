import hello.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MessageMain {
    //INSERT
    void addMessage(){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Message message = new Message("New Hello World " + (new Date()));
                session.save(message);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    void updateMessage(){
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Message message = session.load( Message.class, 69L );

               // Message messageNou = session.load( Message.class, new Long(45) );

              //  message.setText("New Text 19 aprilie");
                Message nextMessage = new Message("Next message of 69 "+(new Date()));
            //    message.setNextMessage( messageNou );
                message.setNextMessage( nextMessage );

                //session.update(message);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    //DELETE
    void deleteMessage(){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Message crit = session.createQuery("from Message where text like 'New Hello%'", Message.class)
                        .setMaxResults(1)
                        .uniqueResult();
                System.err.println("Stergem mesajul " + crit.getId());
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    //SELECT
    void getMessages(){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Message> messages =
                        session.createQuery("from Message as m  order by m.text asc", Message.class).
              //        session.createQuery("select m from Message as m join fetch m.nextMessage order by m.text asc", Message.class). //initializarea obiectelor asociate

                                 setFirstResult(10).setMaxResults(5).
                                        list();
                System.out.println(messages.size() + " message(s) found:");
                for (Message m : messages) {
                    System.out.println(m.getText() + ' ' + m.getId());
                  //  System.out.println(m.getText() + ' ' + m.getId()+" textul urmatorului mesaj ["+m.getNextMessage().getText()+"]");
                }
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }

    }

    public static void main(String[] args) {
        try {
            initialize();

            MessageMain test = new MessageMain();
            test.addMessage();
          //  test.getMessages();
            //test.updateMessage();
            test.deleteMessage();
            test.getMessages();
        }catch (Exception e){
            System.err.println("Exception "+e);
            e.printStackTrace();
        }finally {
            close();
        }
    }


    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

}
