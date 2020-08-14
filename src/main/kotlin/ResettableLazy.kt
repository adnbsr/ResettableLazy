import kotlin.reflect.KProperty

class ResettableLazy<T>(private val manager: ResettableLazyManager, val init: () -> T): Resettable {

    @Volatile var lazyHolder = makeInitBlock()

    override fun reset() {
        lazyHolder = makeInitBlock()
    }

    private fun makeInitBlock(): Lazy<T> {
        return lazy {
            manager.register(this)
            init()
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return lazyHolder.value
    }
}

fun <T> resettableLazy(manager: ResettableLazyManager, init: () -> T): ResettableLazy<T> {
    return ResettableLazy(manager,init)
}