# tokenizer

用于分词，`RegexTokenizer`是最常用的分词规则，默认是\\s+，用符号来作为分割;gaps为false，pattern代表的是token；gaps为true，pattern代表的delimiter。

# stopWordsRemover

停顿词可以去除常见的停顿词，由于停顿词在一般情况下都没有什么作用，因此去除可以避免干扰机器学习的效果。也可以通过stopWords自定义一些停顿词。

默认的配置为：`StopWordsRemover.loadDefaultStopWords("english"), caseSensitive -> false`

# n-gram

用于针对一句话生成n个的单词组合对，如果个数小于n就不会输出。

# Binarizer

用于处理一系列的值，通过阈值判断值为0还是1。

# pca 

主成分分析，这个内容很多，单独去研究吧。

http://www.jianshu.com/p/85df3a5e2eb4

# ploynomial expansion....没理解

多项式展开、特征扩展、多项式回归
http://blog.csdn.net/kwame211/article/details/78109254

# dct, discrete cosine transform ....没理解

离散余弦变换 与 傅立叶 有关系，属于图像领域的应用

# stringindexer

stringindexer用于给一列的字符串，转换成index标示。索引是从[0,num)，并且按照频率从排序，频率最高的为0。
没有出现过的label都会放入num位置的索引中。如果输入是数字，那么会先转换成字符串，再进行索引。

对于未出现过的label，有三种处理方式：

- 抛出异常
- 跳过忽略
- 把它放入num所在的索引位置

# indexTostring

stringindexer会把string和index的关系存储到Model里面，indextostring就是根据这个labels关联回来原来的string

# onehot encoder

onehot可以生成哑变量，即每个独立不重复的n个数，可以用n-1维的向量来表示，每一位为1代表一个数，全为0也是一个数，

# vectorindexer

这个可以自动识别那些是分类特征，即不同的值比较少的。

用户可以通过maxcategories设置目录特征不同的值的阈值，小于这个值的特征都会被当作是目录特征，然后转变成0开始的索引。

# interaction

向量乘法，类似于r-formular里面的 a:b

# normalizer

标准化,范数，需要设置参数p, p-norm,默认是2。也叫做正则化

http://blog.csdn.net/pipisorry/article/details/52247379

向量的范数可以参考：

http://blog.csdn.net/wangpei1949/article/details/53150089

1，2，3
6
1／6，1／3，1／2

# StandardScaler

标准化，需要两个参数, 平均差 或者 标准差

- withStd，默认是为true。
- withMean，默认是false。

对一列的数值去做相应的标准化，比如如果只是做平均值的标准化，会挨个元素遍历，元素值-这一列对应的平均值，即

[
1,
2,
3
]
平均值为2，计算后为
-1,
0,
1


+-----+-------------------------+----------------------+
|label|features                 |scaledFeatures        |
+-----+-------------------------+----------------------+
|0.0  |(4,[0,3],[1.0,4.0])      |[0.25,-1.25,-0.5,1.0] |
|1.0  |(4,[0,2,3],[1.0,2.0,3.0])|[0.25,-1.25,1.5,0.0]  |
|1.0  |(4,[1],[5.0])            |[-0.75,3.75,-0.5,-3.0]|
|0.0  |(4,[0,3],[1.0,5.0])      |[0.25,-1.25,-0.5,2.0] |
+-----+-------------------------+----------------------+

1 0 0 4
1 0 2 3 
0 5 0 0 
1 0 0 5

0.75 1.25 0.5 3

0.25, -1.25, -0.5, 1 


100 1000 10000




# MinMaxScaler 最大最小值标准化

最大最小值的归一化，一般范围都是[0,1].需要两个参数：
- min，默认是0。
- max，默认是1

# maxabsscaler 绝对值最大标准化

每一个特征，除以最大的绝对值，获得[-1,1]之间的数据。
注意是vector的每一列，分别计算。

# Bucketzier 

bucketizer，根据用户指定的范围区间分桶。需要指定一个参数，splits:

splits是一端连续的数值数组，通过指定n+1个数，可以把数据分成n个桶。每个桶的范围都是[x,y)。最小值和最大值必须指定，如果不确定，可以是
Double.NegativeInfinity，Double.PositiveInfinity，如果不指定会抛出异常。

指定的数值，必须是严格按照 s0<s1<s2<...<sn

# ElementwiseProduct

对每个元素乘以一个权重的值，对向量逐个进行缩放。感觉就是个点积啊，不知道跟阿达玛积有什么关系。

hadamard product 阿达玛积

# SQLTransformer

支持基于sql进行数据转换, 支持类似下面的预发：

- SELECT a, a + b AS a_b FROM __THIS__
- SELECT a, SQRT(b) AS b_sqrt FROM __THIS__ where a > 5
- SELECT a, b, SUM(c) AS c_sum FROM __THIS__ GROUP BY a, b

可以支持与其他的表关联！给力哇！

# VectorAssembler 

vector组合，在逻辑回归或者决策树中，适合把旧的特征与新生成的特征相组合。
输入的列支持数值型、布尔型、向量型，truefalse会被转换成1和0。

# quantileDiscretizer 分位数分桶

作用跟bucketizer差不多，也是分桶，只不过不需要手动指定每个区间／

原理是，首先获得所有的数据数组：
[0,1,2,3,4]
然后计算分位数：
[0,2,4]
然后最小值和最大值替换:
[-inf,2,inf]
然后按照bucketizer的思路，转换值


http://blog.csdn.net/nchu2020/article/details/53192686

分位数：
中位数：
四分位数

# imputer 纠错器

针对那些为空的值，可以给设置一些默认的值.

- missingValue可以设置针对哪个值进行纠错
- setstrategy mean代表平均值，median代表中位数

