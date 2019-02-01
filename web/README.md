# dRPC-Web

## 安装nginx(默认配置路径/etc/nginx 资源路径 /usr/share/nginx/html)

yum install epel-release

yum install nginx

systemctl start nginx

http://blog.51cto.com/wangpengtai/1913469 国外的机器如果使用selinux则需要关闭

## 安装node.js

wget https://nodejs.org/dist/v8.11.1/node-v8.11.1-linux-x64.tar.xz

tar xvJf node-v8.11.1-linux-x64.tar.xz

mv node-v8.11.1-linux-x64 /usr/local/nodejs

echo -e "\nexport NODE_HOME=/usr/local/nodejs \nexport PATH=\$NODE_HOME/bin:\$PATH" >> /etc/profile

source /etc/profile

## 安装webpack和vue脚手架

npm install webpack -g

npm install vue-cli -g

## 创建脚本

## 执行脚本
