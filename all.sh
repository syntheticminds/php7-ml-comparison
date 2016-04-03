#!/bin/bash

echo "*** Running all benchmarks ***"
php5.6 php/iris.php
php php/iris.php
python python/iris.py
python3 python/iris.py
hhvm php/iris.php
pypy python/iris.py
nodejs javascript/iris.js
cd java
java Iris