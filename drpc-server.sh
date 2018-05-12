#!/bin/bash

# replace your path and name
name=dRPC-Server
repo=git@github.com:ahtcfg24/dRPC-Server.git

application_name=${name}-1.0.0.jar
log_name=${name}.log

log_home=/data/logs/${name}
application_home=/data/application/${name}
repo_parent=/data/repo
code_home=${repo_parent}/${name}


maven_home=/usr/local/apache-maven/bin
code_profile=prod

deploy(){
    echo "--- deploy"
    if [ ! -d ${repo_parent} ]; then
        mkdir -p ${repo_parent}
        cd ${repo_parent}
        echo "--- clone repo to ${repo_parent}"
        git clone ${repo}
    fi
    if [ ! -d ${code_home} ]; then
        cd ${repo_parent}
        echo "--- clone repo to ${repo_parent}"
        git clone ${repo}
    fi
    echo "--- pull code to ${code_home}"
    cd ${code_home}
    git pull
    echo "--- mvn clean"
    ${maven_home}/mvn clean
    echo "--- mvn package"
    ${maven_home}/mvn package -P${code_profile}
    stop
    echo "--- backup"
    if [ ! -d ${application_home} ]; then
        mkdir -p ${application_home}
    fi
    if [ ! -f ${application_home}/${application_name} ]; then
        echo "--- don't need backup"
    else
        mv ${application_home}/${application_name} ${application_home}/${application_name}.bak
    fi
    echo "--- replace"
    cp ${code_home}/target/${application_name} ${application_home}/${application_name}
    start
}

restart(){
    echo "--- restart"
	stop
	start
}

start(){
    echo "--- start"
    nohup java -jar ${application_home}/${application_name} </dev/null > /dev/null 2>&1 &
    log
}

stop(){
    echo "--- stop"
	echo `ps -ef |grep "${application_name}" | grep -v "grep"`
    ID=`ps -ef | grep "${application_name}" | grep -v "grep" | awk '{print $2}'`
    echo "--- find---$ID-----"
    for id in $ID
    do
        kill -9 $id
        echo "--- killed $id-----"
    done
    echo "--- stoped---------"
}

log(){
    echo "--- log"

    if [ ! -d ${log_home} ]; then
        mkdir -p ${log_home}
    fi

    if [ ! -f ${log_home}/${log_name} ]; then
        touch ${log_home}/${log_name}
    fi
    tail -f ${log_home}/${log_name}
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