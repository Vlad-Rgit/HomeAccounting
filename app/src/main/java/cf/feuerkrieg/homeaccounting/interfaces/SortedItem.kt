package cf.feuerkrieg.homeaccounting.interfaces

interface SortedItem: Comparable<SortedItem> {
    fun areItemsTheSame(item: SortedItem): Boolean
    fun areContentsTheSame(item: SortedItem): Boolean
}