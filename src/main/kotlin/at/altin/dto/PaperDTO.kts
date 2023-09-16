import java.util.Date

data class PaperDTO(
        val author: String,
        val title: String,
        val publicationDate: Date,
        val pageCount: Int,
        val references: List<PaperDTO> = emptyList()
)
