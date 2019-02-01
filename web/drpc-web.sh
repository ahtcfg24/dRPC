#!/bin/bash

name=dRPC-Web
repo=git@github.com:ahtcfg24/dRPC-Web.git

repo_parent=/data/repo
code_home=${repo_parent}/${name}


html_home=/usr/share/nginx/html

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
echo "--- npm install"
npm install
echo "--- npm run build"
npm run build
echo "--- move to ${html_home}/dist/"
rm -rf ${html_home}/dist/
mv ./dist/ ${html_home}/dist/
echo "--- copy to ${html_home}/index.html"
rm -rf ${html_home}/index.html
cp ./index.html ${html_home}/index.html
echo "finished"

