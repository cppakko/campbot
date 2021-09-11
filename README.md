<h3 align="center">基于<a href="https://github.com/Mrs4s/go-cqhttp">go-cqhttp</a>使用正向ws连接的旧世代机器人开发框架 ̑̑</h3>

<h1 align="center">
CAMPBOT ( ·ㅂ·)و

![](https://jitpack.io/v/cppakko/campbot.svg)

</h1>

---

## 导入:

添加jitpack仓库到pom.xml中

``` xml
<!-- 如果使用maven作为构建系统的话 -->
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

``` gradle
//如果使用gradle作为构建系统的话
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

然后添加这个项目

``` xml
<!-- 如果使用maven作为构建系统的话 -->
<dependency>
    <groupId>com.github.cppakko</groupId>
    <artifactId>campbot</artifactId>
    <version>0.5.0-beta</version>
</dependency>
```

``` gradle
//如果使用gradle作为构建系统的话
dependencies {
	        implementation 'com.github.cppakko:campbot:0.5.0-beta'
	}
```

---

## 快速开始

在初始化go-cqhttp设置是务必选择**2: 正向 Websocket 通信**

或者在config.yml配置文件中 将**servers**下的-ws:部分替换为

```yml
  - ws:
      # 正向WS服务器监听地址
      host: 127.0.0.1
      # 正向WS服务器监听端口
      port: 6700
      middlewares:
```

首先构建一个bot对象

``` kotlin
    //WS服务器地址 端口 以及可选的加密口令
    val bot = Bot("127.0.0.1",6700,"accessToken")
```

然后通过bot的监听管理器设置指令前缀以及注册您自定义的事件监听或者指令监听类

```kotlin
    bot.eventManager
    .setCommandPrefix('!', '#')         //设置指令前缀
    .register(AdminSetListener())       //事件监听注册
    .commandRegister(ShowVersion())     //指令注册
    .commandRegister(ShowHelp())
```

事件监听类示例

MessageBuilder封装了go-cqhttp所支持的CQ码 可以直接看他的[doc](https://docs.go-cqhttp.org/cqcode/)

命名规则就是Add + Type 如text类型就是AddText

```kotlin
class AdminSetListener : GroupEventListener<GroupRecall> {
    override suspend fun handle(bot: Bot, info: GroupRecall, group: Group) {
        group.sendGroupMsg(
            MessageBuilder()
                .addText("恭喜 xxx 成为了新的管理")
                .build()
        )
        //直接对触发该事件的Group进行操作
    }
}
```

指令监听示例

```kotlin
class ShowVersion : PrivateCommandListener<FriendPrivateMsg> {
    override suspend fun handle(bot: Bot, info: FriendPrivateMsg, user: User) {
        info.data.reply(
            MessageBuilder()
                .addText("version: 0.5.0-beta")
                .build()
        )
        //这里执行的操作是对该事件的快速操作
        //部分事件为了区分被我包了一层
        //所以这里要info.data才能真的拿到该事件的数据

        //也可以选择用user对象对其发送私聊消息
        user.sendPrivateMsg(MessageBuilder().addText("version: 0.5.0-beta").build())
    }

    override fun commandPrefix(): List<String> {
        return arrayListOf("v", "version") //设定触发指令的关键词
        //结合之前的前缀设置 触发该指令的 可以是 !v !version 或者 #v #version
    }
}
```

最后调用

```kotlin
    bot.open() //开启bot
```

<details>

<summary>完整例子</summary>

```kotlin
fun main(): Unit = runBlocking {
    val bot = Bot("127.0.0.1", 6700, "hhh1234567890")
    bot.eventManager
        .setCommandPrefix('!', '#')
        .register(AdminSetListener())
        .commandRegister(ShowVersion())
    launch {
        bot.open()
    }
}

class AdminSetListener : GroupEventListener<GroupRecall> {
    override suspend fun handle(bot: Bot, info: GroupRecall, group: Group) {
        group.sendGroupMsg(
            MessageBuilder()
                .addText("恭喜 xxx 成为了新的管理")
                .build()
        )
        //直接对触发该事件的Group进行操作
    }
}

class ShowVersion : PrivateCommandListener<FriendPrivateMsg> {
    override suspend fun handle(bot: Bot, info: FriendPrivateMsg, user: User) {
        info.data.reply(
            MessageBuilder()
                .addText("version: 0.5.0-beta")
                .build()
        )
        //这里执行的操作是对该事件的快速操作
        //部分事件为了区分被我包了一层
        //所以这里要info.data才能真的拿到该事件的数据
        //也可以选择用user对象对其发送私聊消息
    }

    override fun commandPrefix(): List<String> {
        return arrayListOf("v", "version") //设定触发指令的关键词
        //结合之前的前缀设置 触发该指令的 可以是 !v !version 或者 #v #version
    }
}
```

</details>

---

~~ps 文档尚未完善 事件列表可以先去看~~
[go-cqhttp](https://docs.go-cqhttp.org/event/)
~~的文档 名字基本上都一一对应了(确信 过段时间再在这里补上(确信~~

~~Api部分 针对Group的Api都在Group对象里 User也是一样~~

~~主动查询的Api在Bot.utils下~~


