import java.util.*

class ResettableLazyManager {

    private val managedDelegates = LinkedList<Resettable>()

    fun register(managed: Resettable){
        synchronized(managedDelegates) {
            managedDelegates.add(managed)
        }
    }

    fun reset(){
        synchronized(managedDelegates){
            managedDelegates.forEach { it.reset() }
            managedDelegates.clear()
        }
    }
}

fun resettableManager(): ResettableLazyManager = ResettableLazyManager()