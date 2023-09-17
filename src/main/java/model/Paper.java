package model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private Date publicationDate;
    private int pageCount;

    @ManyToMany
    @JoinTable(
            name = "paper_references",
            joinColumns = @JoinColumn(name = "paper_id"),
            inverseJoinColumns = @JoinColumn(name = "reference_id")
    )
    @ToString.Exclude
    private List<Paper> references;


    public Paper(String author, String title, Date publicationDate, int pageCount, List<Paper> references) {
        this.author = author;
        this.title = title;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
        this.references = references;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Paper paper = (Paper) o;
        return getId() != null && Objects.equals(getId(), paper.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public int getReferenceCount() {
        return references.size();
    }

    public void addReference(Paper target) {
        references.add(target);
    }
}

