import java.util.Date
import javax.persistence.*

@Entity
data class Paper(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val author: String,
        val title: String,
        val publicationDate: Date,
        val pageCount: Int,

        @ManyToMany
        @JoinTable(
                name = "paper_references",
                joinColumns = [JoinColumn(name = "paper_id")],
                inverseJoinColumns = [JoinColumn(name = "reference_id")]
        )
        val references: MutableList<Paper> = mutableListOf()
)
