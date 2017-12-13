# tokenizer

用于分词，`RegexTokenizer`是最常用的分词规则，默认是\\s+，用符号来作为分割;gaps为false，pattern代表的是token；gaps为true，pattern代表的delimiter。

# stopWordsRemover

停顿词可以去除常见的停顿词，由于停顿词在一般情况下都没有什么作用，因此去除可以避免干扰机器学习的效果。也可以通过stopWords自定义一些停顿词。

默认的配置为：`StopWordsRemover.loadDefaultStopWords("english"), caseSensitive -> false`

# n-gram

用于针对一句话生成n个的单词组合对，如果个数小于n就不会输出。

# Binarizer

用于处理一系列的值，通过阈值判断值为0还是1。