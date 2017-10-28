# WarWolf

- 2017年10月28日  去除掉layout文件夹下所有XML文件中的中文。
 
> Mac与Windows默认编码格式不统一，会导致在Windows上面报错。只要将layout下面所有的XML中的中文（包括标点符号）去掉就可以了。  

简书地址：[Android独立开发从入门到放弃](http://www.jianshu.com/p/8f0df064e442)

* 框架选型：MVVM+DataBinding
* 网络请求：RxJava+Retrofit+OkHttp
* 图片加载：Glide
* 主UI框架：BottomNavigation + ViewPager
* 实现了首页列表展示历史上的今天，数据来源聚合API。
* 模拟实现了登录功能，由于没有接口，会登录失败。
* 列出了一些DataBinding的基础使用方法。