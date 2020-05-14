### 用户登录操作
- git config -- user.name "用户名"
- git config -- user.email "邮箱"
### 创建库，和库的初始化
- mkdir
- git init
#### git常用基础指令
- git status //查看当前状态
- git add 文件名 //将文件添加到缓存区
- git commit -m "注释内容" //提交至版本库

#### 版本回退操作命令
1. 查看版本，确定需要回到的时刻点
    1. 指令 获取日志
        1. git log
        2. ==git log --pretty=oneline==
        3. ++git reflog++ //查询历史commit id
2. 回退操作
    1. 指令 
        1. git reset -hard 版本号

###### 小结
- 回退: 需要先得到commit id，然后通过++git reset --hard 版本号++ 回退
- 回未来： 需要通过git reflog进行历史操作查看；获取最新的commit id;
