package com.luke.liangzhiying.myapplication.Adapters

import java.util.*

class Node(val name:String,val next:Node?=null)
val node = Node("A",Node("B", Node("C",Node("D",Node("E")))))

fun main(args:Array<String>){
    printNode(node)
    printNode(remove(node,1))
}
fun printNode(node:Node){
    var tmp:Node?=node
    while(tmp!=null){
        print("${tmp.name}->")
        tmp = tmp.next
    }
    println("")
}
fun remove(node:Node,index:Int):Node{
    var result:Node? = node
    var tmp:Node? = node
    var count =0
    var stack = Stack<Node>()
    while(tmp!=null){
        if(count==index)break
        stack.push(tmp)
        tmp = tmp.next
        count++
    }
    if(tmp!=null&&tmp.next!=null){
        result = tmp.next
        while (!stack.empty()) {
            var tmp2 = stack.pop()
            result = buildnode(tmp2, result);
        }
    }
    if(result!=null)return result
    else return node
}
fun buildnode(node:Node,next:Node?):Node{
    return Node(node.name,next)
}