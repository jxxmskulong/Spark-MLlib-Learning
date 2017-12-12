
# TF-IDF

用于分析词在文档中的重要性，t代表词，d代表文档，文档集合为D。

词频就是词t出现在文档d中的次数，文档频率为包含词t的文档总数。'

IDF(t,D)=log((|D|+1)/(DF(t,D)+1))
TFIDF(t,d,D)=TF(t,d)⋅IDF(t,D)

在mllib连它分为两个步骤：

- HashingTF用于计算词频
- CountVectorizer用于统计个数

hashingTF是一个transformer，把词的集合变成相同长度的向量。
原始的每个词都会使用murmurhash3生成hash映射到固定的空间上。
不过它可能会造成hash冲突，也就是说不同的词会映射到同一个index上。

为了避免哈希冲突，可以设置一个很大的桶的size，默认值是2^18=262144。
有一个参数可以控制词频的个数，如果设置为true，那么所有的位置的词频都是1，
结果就有点类似01的二进制。

IDF是一个estimator，生成IDFModel。这个模型接受vector(从hashingTF或者countVector获得)

# word2vec

word2vec也是一个estimator，它接收一堆文档，生成一个model。这个model把所有的词都映射成一个向量。
这个vector可以用来做预测、文档相似度计算等等。

http://spark.apache.org/docs/latest/mllib-feature-extraction.html#word2vec

# countvectorizer

countVectorizer和CountVectorizerModel用来把文档转变成词的索引，
按照词出现的个数进行排序统计。