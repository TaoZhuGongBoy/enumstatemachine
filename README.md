## enumstatemachine架构说明

目前开源的状态机方案有spring-statemachine、squirrel-foundation、sateless4j等。其中spring-statemachine、squirrel-foundation在github上star和fock数排前二。这些开源的状态机普通存在两个问题：

问题一:太复杂

这些开源状态机普遍包含了UML State Machine上列举的功能点，功能是强大了，但也搞得体积过于庞大，功能太过复杂，很多功能实际生产场景中根本用不到。

高级玩法有：状态的嵌套（substate），状态的并行（parallel，fork，join）、子状态机等等。大家可以对照一下这些功能你是否用的到。


问题二:性能差

这些状态机都是有状态的（Stateful）的，有状态意味着多线程并发情况下它的实例容易出现线程安全问题。在如今的普遍分布式多线程环境中，为了保证线程安全性，所以就不能不为每次的请求创建一个状态机实例，一旦碰到某些构建状态机的过程很复杂，当下应用QPS又很高话，这往往会造成系统的性能瓶颈。

在这里我给大家推荐一款阿里开源的状态机：cola-statemachine。

github地址:https://github.com/alibaba/COLA/tree/master/cola-components/cola-component-statemachine

作者就是基于上述两个问题，设计了此款状态机，没有太过复杂的功能，聚焦在状态流转​。
​示例代码：
<code>
//构建一个状态机(实际生产场景下，可以维护一个Bean)
StateMachineBuilder<StateMachineTest.ApplyStates, StateMachineTest.ApplyEvents, Context> builder = StateMachineBuilderFactory.create();
//外部流转(两个不同状态的流转)
builder.externalTransition()
.from(StateMachineTest.ApplyStates.APPLY_SUB)//原来状态
.to(StateMachineTest.ApplyStates.AUDIT_ING)//目标状态
.on(StateMachineTest.ApplyEvents.SUBMITING)//基于此事件触发
.when(checkCondition1())//前置过滤条件
.perform(doAction());//满足条件，最终触发的动作
</code>

