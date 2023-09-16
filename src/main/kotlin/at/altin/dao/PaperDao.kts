import org.springframework.data.jpa.repository.JpaRepository

interface PaperDao : JpaRepository<Paper, Long> {
    //empty
}
