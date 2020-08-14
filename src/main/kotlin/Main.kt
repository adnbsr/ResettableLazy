
fun main(){

    val lazyManager = resettableManager()

    val isConnected by resettableLazy(lazyManager) {
        println("isConnected computation")
        false
    }

    println("isConnected: $isConnected")

    lazyManager.reset()
    println("After reset() called: $isConnected")
}