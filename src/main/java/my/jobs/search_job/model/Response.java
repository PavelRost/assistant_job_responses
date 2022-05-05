package my.jobs.search_job.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "company")
public class Response implements Comparable<Response>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date created = new Date(System.currentTimeMillis());;

    private Boolean done = false;

    public Response() {
    }

    public Response(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    public Boolean getDone() {
        return done;
    }

    @Override
    public int compareTo(Response o) {
        return this.created.compareTo(o.created);
    }
}
