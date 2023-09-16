import java.util.Date
import java.util.ArrayList

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Paper {
    private var author: String? = null
    private var title: String? = null
    private var publicationDate: Date? = null
    private var pageCount = 0
    private var references: MutableList<Paper?>? = ArrayList()

    constructor(
            author: String?,
            title: String?,
            publicationDate: Date?,
            pageCount: Int
    ) : this() {
        this.author = author
        this.title = title
        this.publicationDate = publicationDate
        this.pageCount = pageCount
    }

    fun addReference(paper: Paper?) {
        references?.add(paper)
    }

    val references: List<Paper?>
        get() = references ?: ArrayList()

    val referenceCount: Int
        get() = references?.size ?: 0

    fun containsFilterString(filterString: String?): Boolean {
        return (author != null && author!!.contains(filterString!!)) || (title != null && title!!.contains(filterString))
    }
}
