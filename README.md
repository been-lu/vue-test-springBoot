# vue-test-springBoot  
前后端分离的后端，springBoot实现
***
###2022/2/13
总结一下今天及以前的问题

数据库版本5.1.55导致springboot默认的配置失败，因为默认的支持最低5.6，要改成
            
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
     </dependency>   
lombok的data注解无效，idea缺少lombok插件，要命的是连不上服务器，下载不了    
还有mybatisx的插件也是，都手机下载的

mybatisx的xml莫名爆红  
找到了，头文件引用的老版本，错误了

然后大概说一下今天的进度  
动态编译mapper文件路径要改  
测试了一些以前还会现在已经忘了的东西

###2022/2/14
改用了mybatis-plus，好用很多，至少不需要动态编译了，这两天就应该可以把后端写完，之后添加一些高性能，或者安全配置  
今天kotlin一直报错，把插件禁用就好了，应该还是idea的问题，插件市场也连接不上