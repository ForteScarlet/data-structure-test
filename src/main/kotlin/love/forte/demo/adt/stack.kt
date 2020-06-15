package love.forte.demo.adt

import java.util.*

/*
    栈
 */

fun main() {
    Postfix.test1()
}

/**
 * 后缀
 */
object Postfix{
    /**
     * 中缀转后缀
     * 配合栈使用 [Stack1]
     * a+b+c+(d*e*f)*g -> abc*+de*f+g*+
     */
    fun test1(){
        val infixStr = "a+b+c+(d*e*f)*g"

        val st = LIFOStack<Int>()

        for(i in 1..5) st.push(i)

        println(st)

        println(st.pop())

        println(st)
    }
}

/**
 * 一个栈，只可见最后一个元素，即top中的元素。
 * \ c \ <--- top
 * \ b \
 * \ a \
 * -----
 * 定义两个栈的核心方法：弹出[pop]与推入[push]
 * 和一个获取元素大小的方法
 */
interface Stack<T> {
    fun push(element: T)
    fun pop(): T?
    fun size(): Int
}

/**
 * 定义元素
 */
interface Element<T> {
    val element: T
}


/**
 * 后入先出栈。
 * 使用最基础的数据保存栈元素，不进行预扩容。
 */
class LIFOStack<T>: Stack<T> {

    private var st: Array<Element<T>> = emptyArray()

    /**
     * 推元素入栈
     */
    override fun push(element: T) {
        st = st.plus(element.toElement())
    }

    /**
     * 弹元素出栈
     */
    override fun pop(): T? {
        return if(st.isEmpty()) null else {
            val last = st.last().element
            st = st.copyOf(st.size - 1) as Array<Element<T>>
            last
        }
    }


    override fun size(): Int = st.size
    override fun toString(): String = st.joinToString(" - ", "[", "]") { it.element.toString() }
    /**
     * LIFO 栈中使用的元素类
      */
    class LIFOElement<T>(override val element: T): Element<T>
    private fun T.toElement() = LIFOElement(this)
}
