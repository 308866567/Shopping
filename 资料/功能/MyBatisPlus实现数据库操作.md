# springboot使用MybatisPlus

## 层次
- Controller <- Service <- Mapper/DAO <- Model
- Model 层定义了数据库表对应的实体类。
  Mapper/DAO 层包含了数据库操作的接口和 SQL 映射文件。
  Service 层包含业务逻辑，调用 Mapper 层的方法。
  Controller 层处理客户端的 HTTP 请求，调用 Service 层的方法

## 引入依赖

``` xml
<!--mall-api引入-->
<!--引入启动器,防止编译不通过-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.2</version>
    <scope>provided</scope>
</dependency>
<!--xbd-vip-mall引入-->
<!--方便创建javaBean的一些方法-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!--mall-service-dependency 引入-->
<!--MyBatis Plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.2</version>
</dependency>

<!--MySQL-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

## 创建实体类

``` java
//给一个实体类添加以下内容
@Data
@NoArgsConstructor
@AllArgsConstructor
//设置对应的表名
@TableName(value = "brand")
//设置类属性对应的字段
@TableId(type = IdType.AUTO,value="id")
```

## springboot配置
- springboot启动类上配置mapper层地址
```java

@MapperScan(basePackages = {"com.xbd.vip.mall.goods.mapper"})
```
- springboot的yaml文件配置
- 配置数据库连接和端口(略)
- 配置nacos的服务注册(略)
- 配置mybatis-plus
```yaml
mybatis-plus:
  # xml配置文件的路径,写复杂sql
  mapper-locations: mapper/*.xml
  # java的bean类,即实体类
  type-aliases-package: com.xbd.vip.mall.*.model
  configuration:
    #驼峰自动转换
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```
## 增删改
- 实体类,即model
  可以使用注解或者 XML 配置文件来指定实体类与数据库表之间的关系
- Mapper层
  Mapper XML 文件：包含了具体的 SQL 语句（当然，也可以使用注解来替代 XML）
  ``` java
  public interface BrandMapper extends BaseMapper<Brand> {    }
  ```
- service层
  - service接口
    ```java
    public interface BrandService extends IService<Brand> {}
    ```
  - service接口的实现类
    ```java
    public class BrandServiceImpl 
        extends ServiceImpl<BrandMapper,Brand> 
        implements BrandService {
    @Autowired
      private BrandMapper brandMapper;
    }
    ```
- controller(响应http请求)
  ``` java
  @RestController
  @RequestMapping(value = "/brand")
  public class BrandController {
    @Autowired  
    private BrandService brandService;
  }
  ```

## 查询的实现
条件查询需要封装条件信息，MyBatis Plus提供了条件封装对象Wrapper（它的子类QueryWrapper可以直接使用），我们可以用它的子类QueryWrapper实现封装查询条件。
- 接口添加方法声明
- 实现类添加方法
```java
//这是重写了父类的查询,如果要自定义,接口记得添加方法
@Override
public List<Brand> queryList(Brand brand) {
        //条件包装对象
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name",brand.getName());
        //根据initial查询
        queryWrapper.eq("initial",brand.getInitial());
        return brandMapper.selectList(queryWrapper);
    }
```
[Wrapper官网文档](https://baomidou.com/guides/wrapper/)
<details>
<summary>Wrapper常用方法解释</summary>

1. 基础条件方法

   eq：等值查询。用于指定字段等于某个值的条件。
   ne：不等值查询。用于指定字段不等于某个值的条件。
   gt：大于查询。用于指定字段大于某个值的条件。
   ge：大于等于查询。用于指定字段大于等于某个值的条件。
   lt：小于查询。用于指定字段小于某个值的条件。
   le：小于等于查询。用于指定字段小于等于某个值的条件。

2. 范围条件方法

   between：范围查询。用于指定字段在某个范围内的条件。
   notBetween：非范围查询。用于指定字段不在某个范围内的条件。

3. 集合条件方法

   in：集合查询。用于指定字段在某个集合中的条件。
   notIn：非集合查询。用于指定字段不在某个集合中的条件。

4. 模糊查询方法

   like：模糊查询。用于指定字段包含某个值的条件。
   notLike：非模糊查询。用于指定字段不包含某个值的条件。
   likeLeft：左模糊查询。用于指定字段以某个值结尾的条件。
   likeRight：右模糊查询。用于指定字段以某个值开头的条件。

5. 排序方法

   orderByAsc：升序排序。用于指定字段按升序排序。
   orderByDesc：降序排序。用于指定字段按降序排序。

6. 逻辑组合方法

   and：AND 逻辑组合。用于将多个条件组合成 AND 关系。
   or：OR 逻辑组合。用于将多个条件组合成 OR 关系。
   nested：嵌套查询。用于将一组条件嵌套在一起，形成子查询。

7. 空值判断方法

   isNull：空值判断。用于指定字段为空的条件。
   isNotNull：非空值判断。用于指定字段不为空的条件。

8. 字段选择方法

   select：字段选择。用于指定查询时返回的字段。

9. 其他方法

   exists：子查询存在判断。用于指定某个子查询结果存在的条件。
   notExists：子查询不存在判断。用于指定某个子查询结果不存在的条件。
   set：用于更新操作中，指定字段的新值。
   setSql：用于更新操作中，指定复杂的 SQL 语句。
</details>

- 在BrandController中创建条件查询方法：
```java
@PostMapping(value = "/list")
public RespResult<List<Brand>> list(@RequestBody(required = false) Brand brand){
    // 查询
    List<Brand> brands = brandService.queryList(brand);
    return RespResult.ok(brands);
}
```

## 分页查询
- 接口-在BrandService中创建如下方法
```java
Page<Brand> queryPageList(Long currentPage,Long size,Brand brand);
```
- 实现类-在BrandServiceImpl中创建如下方法
使用brandMapper.selectPage方法  
new Page<Brand>(currentPage, size) 是用来创建一个分页对象 Page，这个对象包含了分页查询所需的相关信息：  
currentPage：指定了查询的页码。例如，currentPage=1 表示查询第一页  
size：指定了每页的记录数。例如，size=10 表示每页显示10条记录。
```java
@Override
    public Page<Brand> queryPageList(Brand brand, Long currentPage, Long size) {
        //条件包装对象
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name",brand.getName());
        return brandMapper.selectPage(new Page<Brand>(currentPage,size),queryWrapper);
    }
```
- 控制层-在BrandController中创建  
```java
   @PostMapping(value = "/search/{page}/{size}")
    public RespResult<Page<Brand>> queryPageList(
            @PathVariable(value = "page")Long page,
            @PathVariable(value = "size")Long size,
            @RequestBody Brand brand){
        Page<Brand> pageInfo = brandService.queryPageList(brand,page,size);
        return RespResult.ok(pageInfo);
    }
```