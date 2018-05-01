#!/bin/bash

# replace your path and name
name=dRPC-Server-1.0.0.jar
log_name=/opt/logs/dRPC-Server/dRPC-Server.log
repo_home=/data/repo/dRPC-Server
application_home=/data/application/dRPC-Server
maven_home=/usr/maven/apache-maven/bin
code_profile=prod

deploy(){
    cd ${repo_home}
    echo "--- pull code to ${repo_home}"
    git pull
    echo "--- mvn clean"
    ${maven_home}/mvn clean
    echo "--- mvn package"
    ${maven_home}/mvn package -P${code_profile}
    stop
    echo "--- backup"
    cp ${application_home}/${name} ${application_home}/${name}.bak
    echo "--- replace"
    cp ${repo_home}/target/${name} ${application_home}/${name}
    echo "--- start"
    start
}

restart(){
	stop
	start
}

start(){
    nohup java -jar ${application_home}/${name} </dev/null > /dev/null 2>&1 &
    log
}

stop(){
	echo `ps -ef |grep "${name}" | grep -v "grep"`
    ID=`ps -ef | grep "${name}" | grep -v "grep" | awk '{print $2}'`
    echo "------find---$ID-----"
    for id in $ID
    do
        kill -9 $id
        echo "------killed $id-----"
    done
    echo "------stoped---------"
}

log(){
    tail -f ${log_name}
}

case $1 in
    start)  start
    ;;
    stop)  stop
    ;;
    restart)  restart
    ;;
    deploy)  deploy
    ;;
    log) log
    ;;
    *)  echo 'params must be one of [start,stop,restart,deploy,log]'
    ;;
esac