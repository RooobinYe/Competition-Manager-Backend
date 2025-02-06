## Design Draft

### DTO

dto 的结构如下：

```
─── dto
    ├── AcademyDTO.java
    ├── MemberResponseDTO.java
    ├── TeamDTO.java // 这是对 model 的封装
    └── captain // 文件夹是接口名字 针对有需要的不同接口设计的不同的 DTO 使用时需要转换
        └── TeamDTO.java
        └── ...
```

### 使用流程

1. 超级管理员创建比赛 并且配置比赛的信息
2. 二级管理员（老师）操作 User 表 分配 role->0(Captain)
3. Captain 登录系统 并且创建队伍 此时操作 Member 表

### JWT

// TODO
jwt 密钥的处理 非明文的形式存储

### DTO

// TODO
使用 V2 的接口，使用了 Java 14+ 引入的 Record 特性，这使得它们都是不可变的数据传输对象，自动生成了构造器、getter、equals、hashCode 和 toString 方法。
src/main/java/fun/sast/evento/lark/api/value/V2.java