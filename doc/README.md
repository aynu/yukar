# DBサーバ

## ユーザ

### 管理ユーザへの切替
`> set PGUSER=postgres`

### ユーザの作成
`> createuser --createdb --encrypted --inherit --login --pwprompt --createrole --superuser --replication yukar`

### ユーザの削除
`> dropuser yukar`

## DB

### DBの作成
`> createdb --encoding=UTF8 --owner=yukar --template=template0 yukar "Aynu Yukar Database"`

### DBの削除
`> dropdb yukar`

# JAAS

## 認証テーブルの作成
`yukar=# create table credential (username varchar(128), password varchar(128), userrole varchar(128));`

## 認証データの作成
`yukar=# insert into credential (username, password, userrole) values ('', '', '');`
