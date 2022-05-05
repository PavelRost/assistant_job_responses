package my.jobs.search_job.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note implements Comparable<Note>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date created = new Date(System.currentTimeMillis());;

    public Note() {
    }

    public Note(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int compareTo(Note o) {
        return this.description.compareTo(o.description);
    }
}
