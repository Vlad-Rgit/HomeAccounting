package cf.feuerkrieg.homeaccounting.adapters

interface ReplacableAdapter<T> {
    fun replaceAll(items: Collection<T>)
}