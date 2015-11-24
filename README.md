# PHP 7 Machine Learning Comparison

This repository holds the source code for the PHP, Python and Java benchmarks used in the blog post, [Is PHP now suitable for machine learning?](http://www.syntheticminds.co.uk/blog/is-php-now-suitable-for-machine-learning.html)

# Instructions

In the blog post, the benchmarks were run on a single core [php7dev Vagrant box](https://github.com/rlerdorf/php7dev). If you'd like to do the same, you'll need to install [HHVM](https://github.com/facebook/hhvm/wiki/Prebuilt-Packages-on-Debian-8), [PyPy](https://packages.debian.org/sid/pypy) and the [default JDK](https://wiki.debian.org/Java#JDK).

From the location of this readme file, the following commands will run the benchmarks.

```
# PHP 5.6
newphp 56
php php/iris.php

# PHP 7
newphp 70
php php/iris.php

# Python 2
python python/iris.py

# Python 3
python 3 python/iris.py

# HHVM
hhvm php/iris.php

# PyPy
pypy python/iris.py

# Java
cd java
javac Iris.java
java Iris
```