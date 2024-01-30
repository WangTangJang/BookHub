# 日志

果然，只是自己摸索的话，容易失去方向。

不如暂且只是三件套先用着，等到他日熟悉时可再更换。

开发挺有趣的，哈哈。

跟着chatGPT做开发有一种在原地打转的感觉。

Template好复杂

还是找一本教程看看吧。

git push 到远程仓库时，原来不是原封不动的传上去的。

搞了半天,spring6必须要tomcat的版本高于10才行.我还以为我哪写错了...

远程与本地仓库不同步，用fetch似乎还要合并，直接用pull更方便.

`` git branch ‘查看本地分支’ ``

``git pull 远程分支 本地分支``

```xml
    <!--配置通用DAO-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.wang.mapper"/>
    </bean>
```

在spring配置文件中缺少这一段时，会报如下错误，找了许久。但是我似乎添加过，报错又不报错。

```text
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'bookInfoServiceImpl': Unsatisfied dependency expressed through field 'mapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.wang.mapper.BookInfoMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
..........
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.wang.mapper.BookInfoMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
```

每次依赖添加完之后，用idea更新一下，设置里就会变成java5

Xml文件的位置又出错了，大概是我一开始打错字符，而后又修改，但是并没有被识别为文件夹层次的原因吧。

未曾设置过别名的情况下，xml里面应该输入实体类的路径

```xml
    <select id="selectAll" resultType="com.wang.model.BookInfo">
        SELECT * FROM bookInfo;
    </select>
```

为什么每次在pom中更改依赖的时候都要在项目结构里put一下？？

本打算将几个页面整合到一个main中，但是在控制器中返回时如过不指定某个文件，
那么那个文件中的th语法就无法识别Attribute。作罢。

idea中输入字符变宽了　使用ｓｈｉｆｔ＋空格＋点（半角全角切换似乎也可以不要)

但是,导航栏也和其他页面不在一个文件,控制器的model的Attribute也无法被识别到.虽然能用..

静态资源无法访问。

当我把static文件夹放到webapp目录下的时候，去编译后的target里面看时，
static会直接在根目录下生成，
此时，在配置中写上``<mvc:resources location="/static/" mapping="/static/**" />``
则可以用``http://localhost:8080/BookHub/static/css/bootstrap_3.3.7.css`` 访问到。
如果我把static放到src里的resources文件夹下，它会在**/根目录/WEB-INF/classes**目录下生成。
那么我需要在配置中写上 ``<mvc:resources location="/WEB-INF/classes/static/" mapping="/static/**" />``
_(测试成功，油管的印度老哥名不虚传，美中不足的是idea智能识别不了修改后的location，会报红。或许有更简便的方法，但至少现在能运行。)_

在前端页面中用thymeleaf时，使用``th:href="@{}"``会自动加上根目录。_chatGPT误我_

idea每次更新maven后设置里的Java Compiler中target bytecode version都自动变成1.5。
需要在pom指定编译版本。能用，但是版本报错。。

终究不过拾人牙慧。

先来写后端的功能实现吧,

可以验证码接收等待期间生成一下文段消解时间

需不需要将(书籍-提交者-审核状态)单独做一张表

在MyBatis中，如果查询结果为空（即没有匹配的记录），那么select语句返回的结果将是null，而不是整数。

准备前端，但是我认为先是写js吧，暂时没必要碰那些框架吧，只是用bootstrap布个局。

单页应用，不用刷新页面，所有内容一次性全部加载下来。

mybatis的useGeneratedKeys真是神来之笔。
