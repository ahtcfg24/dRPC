
# dRPC-Server

## 配置私钥

## 安装git

yum install git

## 安装wget 

yum install wget

## 安装maven 

wget http://www-us.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz

tar zxf apache-maven-3.5.3-bin.tar.gz

mv apache-maven-3.5.3/ /usr/local/apache-maven/

## 安装java并配置环境变量

wget http://download.oracle.com/otn-pub/java/jdk/8u172-b11/a58eab1ec242421181065cdc37240b08/jdk-8u172-linux-x64.tar.gz?AuthParam=1526040695_e18f35f0e5b1a66fdc89e9b5535b82f4 -O jdk-8u172-linux-x64.tar.gz

tar zxf jdk-8u172-linux-x64.tar.gz

mv jdk1.8.0_172/ /usr/local/java/

echo -e "\nexport JAVA_HOME=/usr/local/java\nexport CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar\nexport PATH=\$JAVA_HOME/bin:\$PATH\n" >> /etc/profile

source /etc/profile

## 创建脚本

## 执行脚本
